package com.EMD.LSDB.action.common;

import java.awt.Color;
import java.net.URL;
import java.util.Calendar;
import com.EMD.LSDB.common.constant.ApplicationConstants;
import com.EMD.LSDB.common.errorhandler.ErrorInfo;
import com.EMD.LSDB.common.logger.LogUtil;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEvent;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

/*******************************************************************************************
 * @author  Satyam Computer Services Ltd  
 * @version 1.0  
 * This class has the page event methods for Footer Alignment Added by VV49326
 ******************************************************************************************/
public class PDFView implements PdfPageEvent {
	
	
	//Added after moving PDFView to common
	private String strWaterMarkFlag; 	
	//Added For CR.No.79 To add EMD Header Image to PDF on 02-Nov-2009 by RR68151
	private String strPdfHeaderFlag;
	//Added For CR.No.100 To add Order Number to PDF Footer
	private String strOrderNumber = "";
	//Added For CR.No.101 To add Spec Status to PDF Footer
	private String strSpecType = "";
	
	public PDFView() {
		
	}
	//Modified for CR_100 To add Order Number to PDF Footer
	//Modified For CR_101 To add Spec Status to PDF Footer
	public PDFView(String flag,String headerflag,String orderNo,String specType){
		this.strWaterMarkFlag=flag;
		this.strPdfHeaderFlag=headerflag;
		this.strOrderNumber=orderNo;
		this.strSpecType=specType;
	}	
	
	public void onEndPage(PdfWriter writer, Document document) {
		
		FontFactory.registerDirectories();
		BaseFont bf = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8)
		.getCalculatedBaseFont(false);
						
		Calendar c = Calendar.getInstance();
		
		Font strFointSizeEight = new Font(Font.TIMES_ROMAN, 8, 0, Color.BLACK);
		Rectangle page = document.getPageSize();
		//Modified below code for CR_101 to accomodate changes for Order No and Spec Status on footer
		PdfPTable foot = new PdfPTable(2);
		foot.setSpacingAfter(2);
		PdfPCell phraseAfter = new PdfPCell(new Phrase(Chunk.NEWLINE));
		phraseAfter.setBorderColor(new Color(255, 255, 255));
		phraseAfter.setColspan(2);
		foot.addCell(phraseAfter);
		
		phraseAfter = new PdfPCell(new Phrase(Chunk.NEWLINE));
		phraseAfter.setBorderColor(new Color(255, 255, 255));
		phraseAfter.setColspan(2);
		foot.addCell(phraseAfter);
				
		phraseAfter = new PdfPCell(new Phrase(Chunk.NEWLINE));
		phraseAfter.setBorderColor(new Color(255, 255, 255));
		phraseAfter.setColspan(2);
		foot.addCell(phraseAfter);
		
		phraseAfter = new PdfPCell(new Phrase(11f, String.valueOf(document
				.getPageNumber()),
				new Font(Font.TIMES_ROMAN, 8, 0, Color.BLACK)));
		phraseAfter.setBorderColor(new Color(255, 255, 255));
		phraseAfter.setHorizontalAlignment(Element.ALIGN_RIGHT);
		foot.addCell(phraseAfter);		
		
		//Modified for CR_100 and then for CR_101 To add Order Number/Spec Status to PDF Footer		
		if (strOrderNumber!=""){
			phraseAfter = new PdfPCell(new Phrase(11f, "#" + strOrderNumber + " " + strSpecType,
					new Font(Font.TIMES_ROMAN, 8, 0, Color.BLACK)));
			phraseAfter.setBorderColor(new Color(255, 255, 255));
			phraseAfter.setHorizontalAlignment(Element.ALIGN_RIGHT);
			foot.addCell(phraseAfter);
		}
		else{
			phraseAfter = new PdfPCell(new Phrase(Chunk.NEWLINE));
			phraseAfter.setBorderColor(new Color(255, 255, 255));
			foot.addCell(phraseAfter);
		}
		
		phraseAfter = new PdfPCell(new Phrase(11f,
				"Proprietary Notice: © Electro Motive Diesel, Inc "
				+ c.get(Calendar.YEAR) + ".", new Font(
						Font.TIMES_ROMAN, 8, Font.BOLD + Font.UNDERLINE,
						Color.BLACK)));
		phraseAfter.setBorderColor(new Color(255, 255, 255));
		//phraseAfter.setLeading(0f,1.5f);
		phraseAfter.setColspan(2);
		foot.addCell(phraseAfter);
		
		phraseAfter = new PdfPCell(
				new Phrase(
						11f,
						"Information contained in this document is proprietary to Electro-Motive Diesel, Inc. No part or whole of this document may be disclosed to third parties, copied or reproduced in any",
						strFointSizeEight));
		phraseAfter.setBorderColor(new Color(255, 255, 255));
		//phraseAfter.setLeading(0f,1.5f);
		phraseAfter.setColspan(2);
		foot.addCell(phraseAfter);
		
		phraseAfter = new PdfPCell(
				new Phrase(
						11f,
						"manner without prior written permission of Electro-Motive Diesel, Inc.",
						strFointSizeEight));
		phraseAfter.setBorderColor(new Color(255, 255, 255));
		phraseAfter.setColspan(2);
		foot.addCell(phraseAfter);
		
		foot.setTotalWidth(page.getWidth() - document.leftMargin()
				- document.rightMargin());
		foot.writeSelectedRows(0, -1, document.leftMargin(), document
				.bottomMargin(), writer.getDirectContent());
		
		if(strWaterMarkFlag.equalsIgnoreCase("Y")){
		PdfContentByte cb = writer.getDirectContentUnder(); 
        cb.saveState(); 
        cb.beginText();
        BaseFont bf1 =
      	  FontFactory.getFont(FontFactory.TIMES_ROMAN, 12).getCalculatedBaseFont(true);
        cb.setColorFill(new Color(232,232,232));
        cb.setFontAndSize( bf1, 40 );
        
        cb.setTextMatrix(1, 1, -1, 1, 170, 300 );
        cb.showText( "Proofreading Draft" );
        cb.endText();
		}

		//Added For CR.No.79 To add EMD Header Image to PDF on 02-Nov-2009 by RR68151
		if ((ApplicationConstants.YES).equals(strPdfHeaderFlag))	{
			
			Image image = null;

			try	{
				//Image modified to preserve scaling on 18-Nov-09 by RR68151
				//Image type-Scaling changed to png-62 as per New Header Image on 13-Nov-09 by RR68151
				//URL headerurl = PDFView.class.getClassLoader().getResource("images/Header.png");
				PdfImportedPage headerurl = writer.getImportedPage(new 
						PdfReader(PDFView.class.getClassLoader().getResource("images/Header.pdf")), 1);
				image = Image.getInstance(headerurl);
				//image.scalePercent(62);
				
				PdfPTable head = new PdfPTable(1);
				PdfPCell cel = new PdfPCell(image);//true denotes fit to cell
				cel.setBorderColor(new Color(255, 255, 255));
				cel.setHorizontalAlignment(Element.ALIGN_CENTER);
				cel.setFixedHeight(30f);
				head.addCell(cel);
		
				head.setTotalWidth(page.getWidth() - document.leftMargin()
						- document.rightMargin());
				
				LogUtil.logMessage("xpos "+document.leftMargin());
				LogUtil.logMessage("ypos "+(page.getHeight() - document.topMargin() + head.getTotalHeight()-15));
				
				head.writeSelectedRows(0, -1, 7f,
						830f, writer.getDirectContent());//Edited for PDF logo alignment issue
				
			}
			catch (Exception objExp) {
				ErrorInfo objErrorInfo = new ErrorInfo();
				objErrorInfo.setMessage(objExp.getMessage() + "");
				LogUtil.logError(objErrorInfo);
			}
		}
		
		//Added For CR.No.79 To add EMD Header Image to PDF on 02-Nov-2009 by RR68151 - Ends here
	}
	
	public void onCloseDocument(PdfWriter arg0, Document arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void onParagraph(PdfWriter arg0, Document arg1, float arg2) {
		// TODO Auto-generated method stub
		
	}
	
	public void onParagraphEnd(PdfWriter arg0, Document arg1, float arg2) {
		// TODO Auto-generated method stub
		
	}
	
	public void onChapter(PdfWriter arg0, Document arg1, float arg2,
			Paragraph arg3) {
		// TODO Auto-generated method stub
		
	}
	
	public void onChapterEnd(PdfWriter arg0, Document arg1, float arg2) {
		// TODO Auto-generated method stub
		
	}
	
	public void onSection(PdfWriter arg0, Document arg1, float arg2, int arg3,
			Paragraph arg4) {
		// TODO Auto-generated method stub
		
	}
	
	public void onSectionEnd(PdfWriter arg0, Document arg1, float arg2) {
		// TODO Auto-generated method stub
		
	}
	
	public void onGenericTag(PdfWriter arg0, Document arg1, Rectangle arg2,
			String arg3) {
		// TODO Auto-generated method stub
		
	}
	
	public void onOpenDocument(PdfWriter arg0, Document arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void onStartPage(PdfWriter writer, Document arg1) {
		
		/*FontFactory.registerDirectories();
		
		BaseFont bf = FontFactory.getFont("arial black", BaseFont.WINANSI,
				BaseFont.EMBEDDED).getCalculatedBaseFont(true);
		PdfContentByte cb = writer.getDirectContentUnder();
		cb.saveState();
		
		cb.setGrayFill(0.9f);
		cb.beginText();
		cb.setFontAndSize(bf, 48);
		cb.showTextAligned(Element.ALIGN_CENTER, "Proofreading Draft", 305,
				402, 45);
		cb.endText();
		cb.restoreState();*/
		
	}
	
}