package com.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC
{
	final private String url = "jdbc:mysql://localhost:3306/codenera";
	final private String dbUsername = "root";
	final private String dbPassword = "ieytsak7eq@123";

	public String getUrl()
	{
		return url;
	}

	public String getDbUsername()
	{
		return dbUsername;
	}

	public String getDbPassword()
	{
		return dbPassword;
	}
	
	private static Connection connection = null;
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
        if(connection == null){
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/codenera","root","ieytsak7eq@123");
            System.out.print("connected");
        }
        return connection;
	}
}
