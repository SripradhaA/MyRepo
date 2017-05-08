package com.EMD.LSDB.vo.common;

import java.sql.Blob;

/**
 * @author rr108354
 * 
 */

public class ChangeRequest1058AttachmentsVO extends EMDVO{
private int imgSeqNo;
private String imgName =null;
private String message =null;
//Added for CR-135 -- To fetch PDF Attachments
public Blob imageBlob;
private String image=null;

public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getImgName() {
	return imgName;
}
public void setImgName(String imgName) {
	this.imgName = imgName;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public Blob getImageBlob() {
	return imageBlob;
}
public void setImageBlob(Blob imageBlob) {
	this.imageBlob = imageBlob;
}
public int getImgSeqNo() {
	return imgSeqNo;
}
public void setImgSeqNo(int imgSeqNo) {
	this.imgSeqNo = imgSeqNo;
}
	
}
