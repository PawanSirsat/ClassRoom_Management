package com.pojo;

public class Batch
{
	private String batchName;
	private int batchId;
	public Batch(String batchName, int batchId)
	{
		super();
		this.batchName = batchName;
		this.batchId = batchId;
	}
	public Batch()
	{
		// TODO Auto-generated constructor stub
	}
	public String getBatchName()
	{
		return batchName;
	}
	public void setBatchName(String batchName)
	{
		this.batchName = batchName;
	}
	public int getBatchId()
	{
		return batchId;
	}
	public void setBatchId(int batchId)
	{
		this.batchId = batchId;
	}

}
