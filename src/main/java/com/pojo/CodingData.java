package com.pojo;

public class CodingData
{
	private String challengeName;
	private String description;
	private String difficultyLevel;
	private String challengeType;
	private String createdDate;
	private String studentName;
	private String batchName;
	private String imagePath;
	
	private int facultyid;
	private int studentid;
	private int batchid;
	private int stdid;
	private int challengeID;
		
	public int getChallengeID()
	{
		return challengeID;
	}

	public void setChallengeID(int challengeID)
	{
		this.challengeID = challengeID;
	}

	public int getStdid()
	{
		return stdid;
	}

	public void setStdid(int stdid)
	{
		this.stdid = stdid;
	}

	public CodingData( String challengeName, String description, String difficultyLevel, String challengeType,
			int facultyid, int studentid, int batchid)
	{
		super();
		this.challengeName = challengeName;
		this.description = description;
		this.difficultyLevel = difficultyLevel;
		this.challengeType = challengeType;
		this.facultyid = facultyid;
		this.studentid = studentid;
		this.batchid = batchid;
	}

	public CodingData(int challengeID,int stdid,String challengeName, String description, String difficultyLevel, String challengeType,
			String createdDate, String studentName, String batchName, String imagePath)
	{
		super();
		this.challengeID = challengeID;
		this.stdid = stdid;
		this.challengeName = challengeName;
		this.description = description;
		this.difficultyLevel = difficultyLevel;
		this.challengeType = challengeType;
		this.createdDate = createdDate;
		this.studentName = studentName;
		this.batchName = batchName;
		this.imagePath = imagePath;
	}
	
	
	public int getFacultyid()
	{
		return facultyid;
	}

	public void setFacultyid(int facultyid)
	{
		this.facultyid = facultyid;
	}

	public int getStudentid()
	{
		return studentid;
	}

	public void setStudentid(int studentid)
	{
		this.studentid = studentid;
	}

	public int getBatchid()
	{
		return batchid;
	}

	public void setBatchid(int batchid)
	{
		this.batchid = batchid;
	}

	public CodingData()
	{
		// TODO Auto-generated constructor stub
	}
	public String getChallengeName()
	{
		return challengeName;
	}
	public void setChallengeName(String challengeName)
	{
		this.challengeName = challengeName;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getDifficultyLevel()
	{
		return difficultyLevel;
	}
	public void setDifficultyLevel(String difficultyLevel)
	{
		this.difficultyLevel = difficultyLevel;
	}
	public String getChallengeType()
	{
		return challengeType;
	}
	public void setChallengeType(String challengeType)
	{
		this.challengeType = challengeType;
	}
	public String getCreatedDate()
	{
		return createdDate;
	}
	public void setCreatedDate(String createdDate)
	{
		this.createdDate = createdDate;
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
	public String getImagePath()
	{
		return imagePath;
	}
	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}

	
}

