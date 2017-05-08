package com.EMD.LSDB.vo.common;

/*******************************************************************************************
 * @author  Satyam Computer Services Ltd  
 * @version 1.0  
 * This class has the setter and getter methods for the Distributor 
 ******************************************************************************************/

public class DistributorVO extends EMDVO {
	
	private int distributorSeqNo;
	
	private String distributorName;
	
	private String distributorDesc;
	private String firstName=null;
	private String lastName=null;
	private String logoUpdatedDate=null;
	
	private int imageSeqNo=0;
	//Added for CR_106 Distributor Logo
 private FileVO objFileVO = new FileVO();
	
	public FileVO getFileVO() {
		
		return this.objFileVO;
	}
	
	public void setFileVO(FileVO fileVO) {
		
		this.objFileVO = fileVO;
	}
	
	public String getDistributorDesc() {
		return distributorDesc;
	}
	
	public void setDistributorDesc(String distributorDesc) {
		this.distributorDesc = distributorDesc;
	}
	
	public String getDistributorName() {
		return distributorName;
	}
	
	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}
	
	public int getDistributorSeqNo() {
		return distributorSeqNo;
	}
	
	public void setDistributorSeqNo(int distributorSeqNo) {
		this.distributorSeqNo = distributorSeqNo;
	}
	public int getImageSeqNo() {
		return imageSeqNo;
	}
	
	
	/**
	 * @param ImageSeqNo
	 * The Image Sequence No to Set
	 */
	public void setImageSeqNo(int imageSeqNo) {
		this.imageSeqNo = imageSeqNo;
	}
//	Added for CR 106 Distributor Logo Updated Date JG101007
	public String getLogoUpdatedDate() {
		return logoUpdatedDate;
	}
	public void setLogoUpdatedDate(String logoUpdatedDate){
		this.logoUpdatedDate= logoUpdatedDate;
		
	}
//	Added for CR 106 Distributor Logo Updated By User Name JG101007
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName){
		this.firstName= firstName;
		
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastname(String lastName){
		this.lastName= lastName;
		
	}

	

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
