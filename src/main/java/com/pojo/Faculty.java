package com.pojo;

public class Faculty
{
	private int facultyID;
	private String facultyName;
	private String facultyUserName;
	private String facultyPassword;

	public Faculty(int facultyID, String facultyName, String facultyUserName)
	{
		super();
		this.facultyID = facultyID;
		this.facultyName = facultyName;
		this.facultyUserName = facultyUserName;
	}
	
	@Override
	public String toString()
	{
		return "Faculty [facultyID=" + facultyID + ", facultyName=" + facultyName + ", facultyUserName="
				+ facultyUserName + ", facultyPassword=" + facultyPassword + "]";
	}

	public String getFacultyPassword()
	{
		return facultyPassword;
	}

	public void setFacultyPassword(String facultyPassword)
	{
		this.facultyPassword = facultyPassword;
	}

	public Faculty(int facultyID, String facultyName, String facultyUserName, String facultyPassword)
	{
		super();
		this.facultyID = facultyID;
		this.facultyName = facultyName;
		this.facultyUserName = facultyUserName;
		this.facultyPassword = facultyPassword;
	}

	public int getFacultyID()
	{
		return facultyID;
	}
	public void setFacultyID(int facultyID)
	{
		this.facultyID = facultyID;
	}
	public String getFacultyName()
	{
		return facultyName;
	}
	public void setFacultyName(String facultyName)
	{
		this.facultyName = facultyName;
	}
	public String getFacultyUserName()
	{
		return facultyUserName;
	}
	public void setFacultyUserName(String facultyUserName)
	{
		this.facultyUserName = facultyUserName;
	}

}
