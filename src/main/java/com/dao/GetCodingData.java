package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.pojo.CodingData;

public class GetCodingData
{	
	private Connection con;
	private static final String DELETE_USERS_SQL = "delete from coding_challenge where challenge_id = ?;";

	public GetCodingData(Connection con) 
	{
		this.con = con;
	}
	
	public boolean deleteChallenge(int id) throws SQLException {
		boolean rowDeleted = false;
		try
		{
			PreparedStatement statement = con.prepareStatement(DELETE_USERS_SQL);
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		catch (Exception e) 
		{
		}
		return rowDeleted;
	}
	
	public boolean addChallenge(CodingData data) throws SQLException
	{

		boolean check = false;
		String insertData = "INSERT INTO coding_challenge (challenge_name, description, difficulty_level, tags, faculty_id, user_id, batch_id ) VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = con.prepareStatement(insertData);

        statement.setString(1, data.getChallengeName());
        statement.setString(2, data.getDescription());
        statement.setString(3, data.getDifficultyLevel());
        statement.setString(4, data.getChallengeType());
        statement.setInt(5, data.getFacultyid());
        statement.setInt(6, data.getStudentid());
        statement.setInt(7, data.getBatchid());

        check = statement.executeUpdate() > 0;
        
		return check;	
	}
	
	public List<CodingData> getCodingChallenges() throws SQLException
	{	
		CodingData challenge = new CodingData();
		
		List<CodingData> challenges = new ArrayList<>();	
		String sql = "SELECT cc.challenge_id, cc.challenge_id, cc.challenge_name, cc.description, cc.difficulty_level, cc.tags, cc.date_created, "
				+ "b.batch_name, s.fullname, s.img , s.id " + "FROM coding_challenge cc "
				+ "JOIN batch b ON cc.batch_id = b.batch_id " + "JOIN user s ON cc.user_id = s.id "
				+ "ORDER BY cc.date_created DESC";

		PreparedStatement statement = this.con.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		try
		{
		
			while (resultSet.next())
			{
				int challengeID = resultSet.getInt("challenge_id");
				int stdID = resultSet.getInt("id");
				String challengeName = resultSet.getString("challenge_name");
				String description = resultSet.getString("description");
				String difficultyLevel = resultSet.getString("difficulty_level");
				String tags = resultSet.getString("tags");
				String createDate = resultSet.getString("date_created");
				String studentName = resultSet.getString("fullname");
				String batchName = resultSet.getString("batch_name");
				byte[] imageBytes = resultSet.getBytes("img");

				String imagePath = null;
				if (imageBytes != null && imageBytes.length > 0)
				{
					// Convert imageBytes to Base64-encoded string
					String base64Image = Base64.getEncoder().encodeToString(imageBytes);

					// Construct the image URL
					imagePath = "data:image/jpeg;base64," + base64Image;
				}

				String timeAgo = "";
				if (createDate != null)
				{
					LocalDateTime currentTime = LocalDateTime.now();
					LocalDateTime challengeTime = LocalDateTime.parse(createDate,
							DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
					Duration duration = Duration.between(challengeTime, currentTime);

					long days = duration.toDays();
					long hours = duration.toHoursPart();
					long minutes = duration.toMinutesPart();
					long seconds = duration.toSecondsPart();

					if (days > 0)
					{
						timeAgo += days + (days == 1 ? " day " : " days ");
					}
					if (hours > 0)
					{
						timeAgo += hours + (hours == 1 ? " hour " : " hours ");
					}
					if (minutes > 0)
					{
						timeAgo += minutes + (minutes == 1 ? " minute " : " minutes ");
					}
					timeAgo += "ago";
				}
				 challenge = new CodingData(challengeID,stdID,challengeName, description, difficultyLevel, tags, timeAgo,
				studentName, batchName, imagePath);
				challenges.add(challenge);
			}

			return challenges;

		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return challenges;
	}
}
