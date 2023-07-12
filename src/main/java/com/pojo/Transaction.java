package com.pojo;

public class Transaction {
    private String transactionDate;
    private int paymentAmount;
    private int studentId;
    private String studentName;
    private String batchName;

    public Transaction(int studentId, String studentName, String batchName, String transactionDate, int paymentAmount) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.batchName = batchName;
        this.transactionDate = transactionDate;
        this.paymentAmount = paymentAmount;
    }
    public Transaction(String transactionDate, int paymentAmount)
    {
        this.transactionDate = transactionDate;
        this.paymentAmount = paymentAmount;
    }

    public int getStudentId()
	{
		return studentId;
	}
	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}
	public String getStudentName()
	{
		return studentName;
	}
	public void setStudentName(String studentName)
	{
		this.studentName = studentName;
	}
	public String getBatchName()
	{
		return batchName;
	}
	public void setBatchName(String batchName)
	{
		this.batchName = batchName;
	}
	public void setTransactionDate(String transactionDate)
	{
		this.transactionDate = transactionDate;
	}
	public void setPaymentAmount(int paymentAmount)
	{
		this.paymentAmount = paymentAmount;
	}
	public String getTransactionDate() {
        return transactionDate;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }
}

