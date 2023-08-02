package com.pojo;

public class StudentSubmission
{

	private String studentName;
	private String status;
	private String submissionDate;
	private String due_date;
	private String done;
	private int marks;
	private int sub_id;
	
	
	public StudentSubmission(String studentName, String status, String submissionDate, String done, int marks,int sub_id,String due_date)
	{
		super();
		this.studentName = studentName;
		this.status = status;
		this.submissionDate = submissionDate;
		this.done = done;
		this.marks = marks;
		this.sub_id = sub_id;
		this.due_date = due_date;
	}
	
	
	public String getDue_date()
	{
		return due_date;
	}


	public void setDue_date(String due_date)
	{
		this.due_date = due_date;
	}


	public int getSub_id()
	{
		return sub_id;
	}


	public void setSub_id(int sub_id)
	{
		this.sub_id = sub_id;
	}


	public String getStudentName()
	{
		return studentName;
	}
	public void setStudentName(String studentName)
	{
		this.studentName = studentName;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public String getSubmissionDate()
	{
		return submissionDate;
	}
	public void setSubmissionDate(String submissionDate)
	{
		this.submissionDate = submissionDate;
	}
	public String getDone()
	{
		return done;
	}
	public void setDone(String done)
	{
		this.done = done;
	}
	public int getMarks()
	{
		return marks;
	}
	public void setMarks(int marks)
	{
		this.marks = marks;
	}
	
}
