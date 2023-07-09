package com.pojo;

public class Payment
{

	private String course_name;
	private double course_fees;
	private double paid_fees;
	private double unpaid_fees;

	private double total_fees;
	private double total_paidfees;
	private double total_unpaidfees;
	
	public double getTotal_fees()
	{
		return total_fees;
	}

	public void setTotal_fees(double total_fees)
	{
		this.total_fees = total_fees;
	}

	public Payment(double course_fees, double paid_fees, double unpaid_fees)
	{
		super();
		this.course_fees = course_fees;
		this.paid_fees = paid_fees;
		this.unpaid_fees = unpaid_fees;
	}
	
	public Payment(String course_name,double total_fees, double total_paidfees, double total_unpaidfees)
	{
		super();
		
		this.course_name = course_name;
		this.total_fees = total_fees;
		this.total_paidfees = total_paidfees;
		this.total_unpaidfees = total_unpaidfees;
	}

	public Payment()
	{
		// TODO Auto-generated constructor stub
	}
	
	public String getCourse_name()
	{
		return course_name;
	}
	public void setCourse_name(String course_name)
	{
		this.course_name = course_name;
	}
	public double getCourse_fees()
	{
		return course_fees;
	}
	public void setCourse_fees(double course_fees)
	{
		this.course_fees = course_fees;
	}
	public double getPaid_fees()
	{
		return paid_fees;
	}
	public void setPaid_fees(double paid_fees)
	{
		this.paid_fees = paid_fees;
	}
	public double getUnpaid_fees()
	{
		return unpaid_fees;
	}
	public void setUnpaid_fees(double unpaid_fees)
	{
		this.unpaid_fees = unpaid_fees;
	}
	public double getTotal_paidfees()
	{
		return total_paidfees;
	}
	public void setTotal_paidfees(double total_paidfees)
	{
		this.total_paidfees = total_paidfees;
	}
	public double getTotal_unpaidfees()
	{
		return total_unpaidfees;
	}
	public void setTotal_unpaidfees(double total_unpaidfees)
	{
		this.total_unpaidfees = total_unpaidfees;
	}


}
