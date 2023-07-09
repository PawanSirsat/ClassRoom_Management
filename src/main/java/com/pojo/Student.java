package com.pojo;

import java.io.Serializable;

public class Student implements Serializable
{
	private int id;
	private String idString;
	private String username;
	private String email;
	private String city ;
	private String phone ;
	private String age ;
	private String course ;
	
	private int intage;
	private int intphone;
	

	private byte[] imageBytes ;
	private String imagePath;
	
	
	private int batchId;
	private int facultyId;
	private String batchName;
	private String facultyName;
	private String fullName;
	private String batchcourse;
	private int batchyear ;
	private String Password;

	
	public Student(String username, String email, String city, int mobile, int age2,String course, byte[] imageBytes,
			String fullName, String password)
	{
		super();
		this.username = username;
		this.email = email;
		this.city = city;
		this.intphone = mobile;
		this.intage = age2;
		this.course = course;
		this.imageBytes = imageBytes;
		this.fullName = fullName;
		Password = password;
	}

	private double coursefees;
	private double paidfees;
	private double unpaidfees;

	public Student(int id, String username,String fullname, String email, String city, String phone, String age, String course,
			String imagePath, String batchName, String facultyName, String password,double coursefees,double paidfees,double unpaidfees)
	{
		super();
		this.id = id;
		this.username = username;
		this.fullName = fullname;
		this.email = email;
		this.city = city;
		this.phone = phone;
		this.age = age;
		this.course = course;
		this.imagePath = imagePath;
		this.batchName = batchName;
		this.facultyName = facultyName;
		Password = password;
		this.coursefees = coursefees;
		this.paidfees = paidfees;
		this.unpaidfees = unpaidfees;
	}
	
	public Student(int id, String username,String fullname, String email, String city, String phone, String age, String course,
			String imagePath, String batchName, String facultyName, String password)
	{
		super();
		this.id = id;
		this.username = username;
		this.fullName = fullname;
		this.email = email;
		this.city = city;
		this.phone = phone;
		this.age = age;
		this.course = course;
		this.imagePath = imagePath;
		this.batchName = batchName;
		this.facultyName = facultyName;
		Password = password;
		
	}


	public double getCoursefees()
	{
		return coursefees;
	}


	public void setCoursefees(double coursefees)
	{
		this.coursefees = coursefees;
	}


	public double getPaidfees()
	{
		return paidfees;
	}


	public void setPaidfees(double paidfees)
	{
		this.paidfees = paidfees;
	}


	public double getUnpaidfees()
	{
		return unpaidfees;
	}


	public void setUnpaidfees(double unpaidfees)
	{
		this.unpaidfees = unpaidfees;
	}


	public Student(String batchName,int batchId, int facultyId)
	{
		super();
		this.batchName = batchName;
		this.batchId = batchId;
		this.facultyId = facultyId;
	}

	public Student(String batchName, int batchId)
	{
		super();
		this.batchName = batchName;
		this.batchId = batchId;
	}
	
	public Student(int id, String username, String email, String batchName, int batchId)
	{
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.batchName = batchName;
		this.batchId = batchId;
	}

	public Student(int id,String username, String email, String city, int phone, int age, String course,
			String imagePath, String fullName, String password)
	{
		super();
		this.username = username;
		this.email = email;
		this.city = city;
		this.intphone = phone;
		this.intage = age;
		this.course = course;
		this.imagePath = imagePath;
		this.fullName = fullName;
		Password = password;
		this.id=id;
	}
	
	public Student(int id, String username, String email, String city, String phone, String age, String course,
			byte[] imageBytes, int batchId, int facultyId, String batchName, String facultyName, String fullName,
			String batchcourse, int batchyear)
	{
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.city = city;
		this.phone = phone;
		this.age = age;
		this.course = course;
		this.imageBytes = imageBytes;
		this.batchId = batchId;
		this.facultyId = facultyId;
		this.batchName = batchName;
		this.facultyName = facultyName;
		this.fullName = fullName;
		this.batchcourse = batchcourse;
		this.batchyear = batchyear;
	}


	public Student()
	{
		// TODO Auto-generated constructor stub
	}

	public Student(int id, String username, String email,String password,  int phone,String city,int age, String course, String imagePath)
	{
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.city = city;
		this.intphone = phone;
		this.course = course;
		this.imagePath = imagePath;
		this.intage = age;
		Password = password;
	}


	public String getPassword()
	{
		return Password;
	}

	public void setPassword(String password)
	{
		Password = password;
	}
	
	public String getIdString()
	{
		return idString;
	}

	public void setIdString(String idString)
	{
		this.idString = idString;
	}

	public String getImagePath()
	{
		return imagePath;
	}

	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}
	
	public String getFacultyName()
	{
		return facultyName;
	}

	public void setFacultyName(String facultyName)
	{
		this.facultyName = facultyName;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getAge()
	{
		return age;
	}

	public void setAge(String age)
	{
		this.age = age;
	}

	public String getCourse()
	{
		return course;
	}

	public void setCourse(String course)
	{
		this.course = course;
	}

	public byte[] getImageBytes()
	{
		return imageBytes;
	}

	public void setImageBytes(byte[] imageBytes)
	{
		this.imageBytes = imageBytes;
	}

	public String getFullName()
	{
		return fullName;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	public String getBatchcourse()
	{
		return batchcourse;
	}

	public void setBatchcourse(String batchcourse)
	{
		this.batchcourse = batchcourse;
	}

	public int getBatchyear()
	{
		return batchyear;
	}

	public void setBatchyear(int batchyear)
	{
		this.batchyear = batchyear;
	}

	
	public int getFacultyId()
	{
		return facultyId;
	}

	public void setFacultyId(int facultyId)
	{
		this.facultyId = facultyId;
	}


	public int getBatchId()
	{
		return batchId;
	}
	public void setBatchId(int batchId)
	{
		this.batchId = batchId;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getBatchName()
	{
		return batchName;
	}
	public void setBatchName(String batchName)
	{
		this.batchName = batchName;
	}
	
	public int getIntage()
	{
		return intage;
	}

	public void setIntage(int intage)
	{
		this.intage = intage;
	}

	public int getIntphone()
	{
		return intphone;
	}

	public void setIntphone(int intphone)
	{
		this.intphone = intphone;
	}
}

