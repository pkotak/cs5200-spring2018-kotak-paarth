package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Developer;

public class DeveloperDao {
	public static DeveloperDao instance = null;

	public static DeveloperDao getInstance() {
		if (instance == null)
			return new DeveloperDao();
		else
			return instance;
	}

	private DeveloperDao() {
	}

	public int createDeveloper(Developer developer) {

		int rowsAffected = 0;
		Connection conn = null;
		PreparedStatement personStatement = null;
		PreparedStatement developerStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://cs5200-spring2018-kotak1.cy3rxgyvpjez.us-east-2.rds.amazonaws.com/hw2_kotak_paarth_spring_2018",
					"pkotak", "psp3004.com");

			String personInsert = "insert INTO person (id, first_name, last_name, username, password, email, dob) "
					+ "VALUES (?,?,?,?,?,?,?)";
			String developerInsert = "insert INTO developer (id, developer_key) " + "VALUES (?,?)";
			personStatement = conn.prepareStatement(personInsert);
			personStatement.setInt(1, developer.getId());
			personStatement.setString(2, developer.getFirstName());
			personStatement.setString(3, developer.getLastName());
			personStatement.setString(4, developer.getUsername());
			personStatement.setString(5, developer.getPassword());
			personStatement.setString(6, developer.getEmail());
			personStatement.setString(7, developer.getDob());
			rowsAffected += personStatement.executeUpdate();

			developerStatement = conn.prepareStatement(developerInsert);
			developerStatement.setInt(1, developer.getId());
			developerStatement.setString(2, developer.getDeveloperKey());
			rowsAffected += developerStatement.executeUpdate();

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

	public int updateDeveloper(int developerId, Developer developer) {

		int rowsAffected = 0;
		Connection conn = null;
		PreparedStatement personStatement = null;
		PreparedStatement developerStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://cs5200-spring2018-kotak1.cy3rxgyvpjez.us-east-2.rds.amazonaws.com/hw2_kotak_paarth_spring_2018",
					"pkotak", "psp3004.com");

			String developerUpdate = "UPDATE person p INNER JOIN developer d ON (p.id = d.id) "
					+ "SET p.id = ?, p.first_name = ?, p.last_name = ?, p.username = ?, p.password = ?, p.email = ?, p.dob = ?,"
					+ "d.id = ?, d.developer_key = ? " + "WHERE p.id = ? AND d.id = ?";
			personStatement = conn.prepareStatement(developerUpdate);
			personStatement.setInt(1, developerId);
			personStatement.setString(2, developer.getFirstName());
			personStatement.setString(3, developer.getLastName());
			personStatement.setString(4, developer.getUsername());
			personStatement.setString(5, developer.getPassword());
			personStatement.setString(6, developer.getEmail());
			personStatement.setString(7, developer.getDob());

			personStatement.setInt(8, developerId);
			personStatement.setString(9, developer.getDeveloperKey());
			personStatement.setInt(10, developerId);
			personStatement.setInt(11, developerId);

			rowsAffected += personStatement.executeUpdate();

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

	public int deleteDeveloper(int developerId) {
		int rowsAffected = 0;
		Connection conn = null;
		PreparedStatement developerStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://cs5200-spring2018-kotak1.cy3rxgyvpjez.us-east-2.rds.amazonaws.com/hw2_kotak_paarth_spring_2018",
					"pkotak", "psp3004.com");

			String developerDelete = "DELETE FROM person where id = ?";
			developerStatement = conn.prepareStatement(developerDelete);
			developerStatement.setInt(1, developerId);
			rowsAffected += developerStatement.executeUpdate();

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

	public List<Developer> findAllDevelopers() {
		List<Developer> developerList = new ArrayList<Developer>();
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://cs5200-spring2018-kotak1.cy3rxgyvpjez.us-east-2.rds.amazonaws.com/hw2_kotak_paarth_spring_2018",
					"pkotak", "psp3004.com");
			String sql = "select * from person p, developer d" + " where p.id = d.id";
			statement = conn.prepareStatement(sql);
			results = statement.executeQuery();

			while (results.next()) {
				int id = Integer.parseInt(results.getString("id"));
				String firstName = results.getString("first_name");
				String lastName = results.getString("last_name");
				String username = results.getString("username");
				String password = results.getString("password");
				String email = results.getString("email");
				String dob = results.getString("dob");
				String developerKey = results.getString("developer_key");

				Developer dev = new Developer();
				dev.setId(id);
				dev.setPersonId(id);
				dev.setFirstName(firstName);
				dev.setLastName(lastName);
				dev.setUsername(username);
				dev.setPassword(password);
				dev.setEmail(email);
				dev.setDob(dob);
				dev.setDeveloperKey(developerKey);

				developerList.add(dev);

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
		return developerList;
	}

	public Developer findDeveloperById(int developerId) {

		Developer dev = null;
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://cs5200-spring2018-kotak1.cy3rxgyvpjez.us-east-2.rds.amazonaws.com/hw2_kotak_paarth_spring_2018",
					"pkotak", "psp3004.com");

			String sql = "select * from person p, developer d" + " where p.id = ?";
			statement = conn.prepareStatement(sql);
			statement.setInt(1, developerId);
			results = statement.executeQuery();

			if (results.next()) {
				int id = Integer.parseInt(results.getString("id"));
				String firstName = results.getString("first_name");
				String lastName = results.getString("last_name");
				String username = results.getString("username");
				String password = results.getString("password");
				String email = results.getString("email");
				String dob = results.getString("dob");
				String developerKey = results.getString("developer_key");

				dev = new Developer();
				dev.setId(id);
				dev.setPersonId(id);
				dev.setFirstName(firstName);
				dev.setLastName(lastName);
				dev.setUsername(username);
				dev.setPassword(password);
				dev.setEmail(email);
				dev.setDob(dob);
				dev.setDeveloperKey(developerKey);

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
		return dev;

	}

	public Developer findDeveloperByUsername(String username) {

		Developer dev = null;
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://cs5200-spring2018-kotak1.cy3rxgyvpjez.us-east-2.rds.amazonaws.com/hw2_kotak_paarth_spring_2018",
					"pkotak", "psp3004.com");

			String sql = "select * from person p, developer d" + " where p.username = ?";
			statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			results = statement.executeQuery();

			if (results.next()) {
				int id = Integer.parseInt(results.getString("id"));
				String firstName = results.getString("first_name");
				String lastName = results.getString("last_name");
				String password = results.getString("password");
				String email = results.getString("email");
				String dob = results.getString("dob");
				String developerKey = results.getString("developer_key");

				dev = new Developer();
				dev.setId(id);
				dev.setPersonId(id);
				dev.setFirstName(firstName);
				dev.setLastName(lastName);
				dev.setUsername(username);
				dev.setPassword(password);
				dev.setEmail(email);
				dev.setDob(dob);
				dev.setDeveloperKey(developerKey);

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
		return dev;

	}

	public Developer findDeveloperByCredentials(String username, String password) {

		Developer dev = null;
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://cs5200-spring2018-kotak1.cy3rxgyvpjez.us-east-2.rds.amazonaws.com/hw2_kotak_paarth_spring_2018",
					"pkotak", "psp3004.com");

			String sql = "select * from person p, developer d" + " where p.username = ?" + " and p.password = ?";
			statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			results = statement.executeQuery();

			if (results.next()) {
				int id = Integer.parseInt(results.getString("id"));
				String firstName = results.getString("first_name");
				String lastName = results.getString("last_name");
				String email = results.getString("email");
				String dob = results.getString("dob");
				String developerKey = results.getString("developer_key");

				dev = new Developer();
				dev.setId(id);
				dev.setPersonId(id);
				dev.setFirstName(firstName);
				dev.setLastName(lastName);
				dev.setUsername(username);
				dev.setPassword(password);
				dev.setEmail(email);
				dev.setDob(dob);
				dev.setDeveloperKey(developerKey);

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
		return dev;

	}
}
