package com.pojo;

public class Message
{
	private String messageText;
	private String createDate;
	private String senderName;
	private String batchName;
	private String senderImg;
	private int batch_id;
	
	public Message(String messageText, String createDate, String senderName, String batchName, String senderImg, int batch_id)
	{
		super();
		this.messageText = messageText;
		this.createDate = createDate;
		this.senderName = senderName;
		this.batchName = batchName;
		this.senderImg = senderImg;
		this.batch_id = batch_id;
	}
	public int getBatch_id()
	{
		return batch_id;
	}
	public void setBatch_id(int batch_id)
	{
		this.batch_id = batch_id;
	}
	public String getMessageText()
	{
		return messageText;
	}
	public void setMessageText(String messageText)
	{
		this.messageText = messageText;
	}
	public String getCreateDate()
	{
		return createDate;
	}
	public void setCreateDate(String createDate)
	{
		this.createDate = createDate;
	}
	public String getSenderName()
	{
		return senderName;
	}
	public void setSenderName(String senderName)
	{
		this.senderName = senderName;
	}
	public String getBatchName()
	{
		return batchName;
	}
	public void setBatchName(String batchName)
	{
		this.batchName = batchName;
	}
	public String getSenderImg()
	{
		return senderImg;
	}
	public void setSenderImg(String senderImg)
	{
		this.senderImg = senderImg;
	}
	
	
}
