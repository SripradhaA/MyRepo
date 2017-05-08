/* AK49339
 * Created on Aug 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.EMD.LSDB.vo.common;



/*******************************************************************************
 * @author Satyam Computer Services Ltd
 * @version 1.0 This class has the setter and getter methods for the Customer
 ******************************************************************************/

public class CustomerVO extends EMDVO {
	
	public CustomerVO() {
		
	}
	
	private int customerSeqNo = 0;
	
	private String customerName = null;
	
	private String customerDesc = null;
	
	private int custTypeSeqNo = 0;
	
	private String customerType = null;
	
	private String firstName=null;
	private String lastName=null;
	private String logoUpdatedDate=null;
	
	private int imageSeqNo=0;
	
	/**
	 * Added specTypeSeqNo for LSDB_CR-46 Added on 27-Aug-08 by ps57222
	 */
	private int specTypeSeqNo;
	
	//Added for CR_106 Customer Logo Image Sequence No
	private FileVO objFileVO = new FileVO();
	
	public FileVO getFileVO() {
		
		return this.objFileVO;
	}
	
	public void setFileVO(FileVO fileVO) {
		
		this.objFileVO = fileVO;
	}
	/**
	 * @return
	 */
	public String getCustomerDesc() {
		return customerDesc;
	}
	
	/**
	 * @param customerDesc
	 */
	public void setCustomerDesc(String customerDesc) {
		this.customerDesc = customerDesc;
	}
	
	/**
	 * @return
	 */
	public String getCustomerName() {
		return customerName;
	}
	
	/**
	 * @param customerName
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	/**
	 * @return
	 */
	public int getCustomerSeqNo() {
		return customerSeqNo;
	}
	
	/**
	 * @param customerSeqNo
	 */
	/**
	 * @param customerSeqNo
	 */
	public void setCustomerSeqNo(int customerSeqNo) {
		this.customerSeqNo = customerSeqNo;
	}
	
	/**
	 * @return
	 */
	public int getCustTypeSeqNo() {
		return custTypeSeqNo;
	}
	
	/**
	 * @param custTypeSeqNo
	 */
	public void setCustTypeSeqNo(int custTypeSeqNo) {
		this.custTypeSeqNo = custTypeSeqNo;
	}
	
	public String getCustomerType() {
		return customerType;
	}
	
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	
	/**
	 * @return Returns the specTypeSeqNo.
	 */
	public int getSpecTypeSeqNo() {
		return specTypeSeqNo;
	}
	
	/**
	 * @param specTypeSeqNo
	 *            The specTypeSeqNo to set.
	 */
	public void setSpecTypeSeqNo(int specTypeSeqNo) {
		this.specTypeSeqNo = specTypeSeqNo;
	}
	/**
	 * @return ImageSeqNo
	 */
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
	//Added for CR 106 Customer Logo Updated Date JG101007
	public String getLogoUpdatedDate() {
		return logoUpdatedDate;
	}
	public void setLogoUpdatedDate(String logoUpdatedDate){
		this.logoUpdatedDate= logoUpdatedDate;
		
	}
//	Added for CR 106 Customer Logo Updated By User Name JG101007
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName){
		this.firstName= firstName;
		
	}
	public String getLastName() {
		return lastName;
	}
	
	

	public FileVO getObjFileVO() {
		return objFileVO;
	}

	public void setObjFileVO(FileVO objFileVO) {
		this.objFileVO = objFileVO;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
