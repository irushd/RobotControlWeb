package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccessDb {

	private static String createDb = "CREATE DATABASE IF NOT EXISTS `Robot`;";
	private static String createSchema = "CREATE SCHEMA IF NOT EXISTS `Robot`;";
	private static String useDb = "Use Robot";
	private static String createUserTable = "CREATE TABLE IF NOT EXISTS `Users`("
			+ "`id` INT NOT NULL AUTO_INCREMENT ,"
			+ "`user` VARCHAR(45) NULL ,"
			+ "`pass` VARCHAR(45) NULL ,"
			+ "`email` VARCHAR(45) NULL ," + "PRIMARY KEY (`id`) );;";
	private static String createUserSettingsTable = "CREATE TABLE IF NOT EXISTS `Robot`.`Usersettings` ("
			+ "`id` INT NOT NULL AUTO_INCREMENT ,"
			+ "`user` VARCHAR(45) NULL ,"
			+ "`left` VARCHAR(45) NULL DEFAULT 'l' ,"
			+ "`right` VARCHAR(45) NULL DEFAULT 'r' ,"
			+ "`forward` VARCHAR(45) NULL DEFAULT 'f' ,"
			+ "`back` VARCHAR(45) NULL DEFAULT 'b' , PRIMARY KEY (`id`) );";

	private static String createUser = "INSERT INTO `robot`.`users` (`user`, `pass`, `email`) VALUES (?, ?, ?);";
	private static String getUser = "SELECT * FROM `robot`.`users` where user = ? and pass = ? ;";
	private static String getUserSettings = "SELECT * FROM robot.usersettings where user = ?;";
	private static String saveUserSettings = "INSERT INTO" +
			" `robot`.`usersettings` (`user`, `left`, `right`," +
			" `forward`, `back`) VALUES (?, ?, ?, ?, ?);";
	private static String dbConnString = "jdbc:mysql://mysql-stryker.jelastic.servint.net";
//	private static String dbConnString = "jdbc:mysql://localhost";
	private static Connection dbConn;
	//private static String dbUser = "root";
	private static String dbUser = "Stryker";
	//private static String dbPass = "5615676";
	private static String dbPass = "W7spuu2N54q6Zfeq";

	public static synchronized boolean InitializeDatabase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			dbConn = DriverManager.getConnection(dbConnString, dbUser, dbPass);
			boolean result;
			Statement createDbSchema = dbConn.createStatement();
			result = createDbSchema.execute(createDb);
		//	System.out.println(result);
			result = createDbSchema.execute(useDb);
		//	System.out.println(result);
			result = createDbSchema.execute(createSchema);
		//	System.out.println(result);
			result = createDbSchema.execute(createUserTable);
		//	System.out.println(result);
			result = createDbSchema.execute(createUserSettingsTable);
		//	System.out.println(result);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static synchronized boolean loginCheck(String user, String pass) {
		try {
			PreparedStatement getUserSt = dbConn.prepareStatement(getUser);
			getUserSt.setString(1, user);
			getUserSt.setString(2, pass);
			getUserSt.execute();
			ResultSet results = getUserSt.getResultSet();
			if (!results.next()) {
				return false;
			} else {
				results.last();
				int rowCount = results.getRow();
				if (rowCount > 0)
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static synchronized boolean createUser(String user, String pass, String email) {
		try {
			PreparedStatement createUserSt = dbConn
					.prepareStatement(createUser);
			createUserSt.setString(1, user);
			createUserSt.setString(2, pass);
			createUserSt.setString(3, email);
			createUserSt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static synchronized User getUser(String user, String pass) {
		User userObj = null;
		try {
			PreparedStatement getUserSt = dbConn.prepareStatement(getUser);
			getUserSt.setString(1, user);
			getUserSt.setString(2, pass);
			getUserSt.execute();
			ResultSet results = getUserSt.getResultSet();
			if (!results.next()) {
				return null;
			} else {
				userObj = new User(results.getString("user"),
						results.getString("pass"));
				String[] settings = getUserSettings(user, pass);
				userObj.setLeft(settings[0]);
				userObj.setRight(settings[1]);
				userObj.setForward(settings[2]);
				userObj.setBack(settings[3]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return userObj;
	}

	public static synchronized String[] getUserSettings(String user, String pass) {
		String[] userSettings = null;
		try {
			PreparedStatement getSettingsSt = dbConn
					.prepareStatement(getUserSettings);
			getSettingsSt.setString(1, user);
			getSettingsSt.execute();
			ResultSet results = getSettingsSt.getResultSet();
			if (!results.next()) {
				return null;
			} else {
				userSettings = new String[4];
				userSettings[0] = results.getString("left");
				userSettings[1] = results.getString("right");
				userSettings[2] = results.getString("forward");
				userSettings[3] = results.getString("back");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return userSettings;

	}

	public static synchronized boolean saveUserSettings(String left, String right,
			String forward, String back, int duration) {
		
		return false;
	}

	public static void main(String[] args) {
		System.out.println(AccessDb.InitializeDatabase());
	System.out.println(AccessDb.loginCheck("rushd", "1234"));
//			System.out.println(AccessDb.createUser("Rushd", "1234", "ibn@i.com"));
//		System.out.println((AccessDb.loginCheck("rushd", "1234")));
//		User us = AccessDb.getUser("Rushd", "1234");
//		System.out.println(us);
	}
}