package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WebsiteDao {
	public static WebsiteDao instance = null;

	public static WebsiteDao getInstance() {
		if (instance == null)
			return new WebsiteDao();
		else
			return instance;
	}

	private WebsiteDao() {
	}

	public List<Website> findAllWebsites() {

		List<Website> websiteList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://cs5200-spring2018-kotak1.cy3rxgyvpjez.us-east-2.rds.amazonaws.com/hw2_kotak_paarth_spring_2018",
					"pkotak", "psp3004.com");
			String sql = "select * FROM website";
			statement = conn.prepareStatement(sql);
			results = statement.executeQuery();

			while (results.next()) {
				int id = Integer.parseInt(results.getString("id"));
				String websiteName = results.getString("name");
				String description = results.getString("description");
				String created = results.getString("created");
				String updated = results.getString("updated");
				int visits = Integer.parseInt(results.getString("visits"));

				Website website = new Website();
				website.setId(id);
				website.setName(websiteName);
				website.setDescription(description);
				website.setCreated(created);
				website.setUpdated(updated);
				website.setVisits(visits);

				websiteList.add(website);

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return websiteList;

	}

	public List<Website> findWebsitesForDeveloper(int developerId) {
		List<Website> websiteList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://cs5200-spring2018-kotak1.cy3rxgyvpjez.us-east-2.rds.amazonaws.com/hw2_kotak_paarth_spring_2018",
					"pkotak", "psp3004.com");
			String sql = "select * FROM website WHERE developerId = ?";
			statement = conn.prepareStatement(sql);
			statement.setInt(1, developerId);
			results = statement.executeQuery();

			while (results.next()) {
				int id = Integer.parseInt(results.getString("id"));
				String websiteName = results.getString("name");
				String description = results.getString("description");
				String created = results.getString("created");
				String updated = results.getString("updated");
				int visits = Integer.parseInt(results.getString("visits"));

				Website website = new Website();
				website.setId(id);
				website.setDeveloperId(developerId);
				website.setName(websiteName);
				website.setDescription(description);
				website.setCreated(created);
				website.setUpdated(updated);
				website.setVisits(visits);

				websiteList.add(website);

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return websiteList;
	}
	
	public Website findWebsiteById(int websiteId) {

		Website website = null;
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://cs5200-spring2018-kotak1.cy3rxgyvpjez.us-east-2.rds.amazonaws.com/hw2_kotak_paarth_spring_2018",
					"pkotak", "psp3004.com");
			String sql = "select * FROM website WHERE id = ?";
			statement = conn.prepareStatement(sql);
			statement.setInt(1, websiteId);
			results = statement.executeQuery();

			if (results.next()) {
				int developerId = Integer.parseInt(results.getString("developerId"));
				String websiteName = results.getString("name");
				String description = results.getString("description");
				String created = results.getString("created");
				String updated = results.getString("updated");
				int visits = Integer.parseInt(results.getString("visits"));

				website = new Website();
				website.setId(websiteId);
				website.setDeveloperId(developerId);
				website.setName(websiteName);
				website.setDescription(description);
				website.setCreated(created);
				website.setUpdated(updated);
				website.setVisits(visits);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return website;
	
	}
	
	public int createWebsite(int developerId, Website website) {
		int rowsAffected = 0;
		Connection conn = null;
		PreparedStatement websiteStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://cs5200-spring2018-kotak1.cy3rxgyvpjez.us-east-2.rds.amazonaws.com/hw2_kotak_paarth_spring_2018",
					"pkotak", "psp3004.com");

			String websiteInsert = "insert INTO website (id, developerId, name, description, created, updated, visits) "
					+ "VALUES (?,?,?,?,?,?,?)";
			
			websiteStatement = conn.prepareStatement(websiteInsert);
			websiteStatement.setInt(1, website.getId());
			websiteStatement.setInt(2, developerId);
			websiteStatement.setString(3, website.getName());
			websiteStatement.setString(4, website.getDescription());
			websiteStatement.setString(5, website.getCreated());
			websiteStatement.setString(6, website.getUpdated());
			websiteStatement.setInt(7, website.getVisits());
			rowsAffected += websiteStatement.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsAffected;
	}

	public int updateWebsite(int websiteId, Website website) {
		int rowsAffected = 0;
		Connection conn = null;
		PreparedStatement websiteStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://cs5200-spring2018-kotak1.cy3rxgyvpjez.us-east-2.rds.amazonaws.com/hw2_kotak_paarth_spring_2018",
					"pkotak", "psp3004.com");

			String websiteUpdate = "UPDATE website "
					+ "SET id = ?, developerId = ?, name = ?, description = ?, created = ?, updated = ?, visits = ? "
					+ "WHERE id = ?";
			websiteStatement = conn.prepareStatement(websiteUpdate);
			websiteStatement.setInt(1, websiteId);
			websiteStatement.setInt(2, website.getDeveloperId());
			websiteStatement.setString(3, website.getName());
			websiteStatement.setString(4, website.getDescription());
			websiteStatement.setString(5, website.getCreated());
			websiteStatement.setString(6, website.getUpdated());
			websiteStatement.setInt(7, website.getVisits());
			websiteStatement.setInt(8, websiteId);

			rowsAffected += websiteStatement.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsAffected;
	}

	public int deleteWebsite(int websiteId) {

		int rowsAffected = 0;
		Connection conn = null;
		PreparedStatement websiteStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://cs5200-spring2018-kotak1.cy3rxgyvpjez.us-east-2.rds.amazonaws.com/hw2_kotak_paarth_spring_2018",
					"pkotak", "psp3004.com");

			String websiteDelete = "DELETE FROM website where id = ?";
			websiteStatement = conn.prepareStatement(websiteDelete);
			websiteStatement.setInt(1, websiteId);
			rowsAffected += websiteStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsAffected;
	}
}

