package com.pacificgeotech.epayTestFramework.dbConnection;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

	
	private static final Logger logger = Logger.getLogger("DbConnection");
	private static File fileUrl = new File(
			DbConnection.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile();

	public static Connection getConnection() {
		String[] jdbc = null;

		try {
			jdbc = getJdbcInfo();
			Class.forName(jdbc[0]);
		} catch (ClassNotFoundException | IOException e) {
			logger.info("NO Oracle JDBC Driver found!");
			e.printStackTrace();
		}

		Connection connection = null;

		try {
			connection = DriverManager.getConnection(jdbc[1], jdbc[2], jdbc[3]);
		} catch (SQLException e) {
			logger.info("Connection Failed! Check output console");
			e.printStackTrace();
		}
		return connection;
	}

	public static String[] getJdbcInfo() throws IOException {
		String jdbcDriver, jdbcUrl, username, password = null;
		Properties jdbcProperties = new Properties();
		FileInputStream file;

		file = new FileInputStream(fileUrl.getAbsolutePath() + "\\Config\\jdbc.properties");
		jdbcProperties.load(file);
		file.close();

		jdbcDriver = jdbcProperties.getProperty("jdbc.driver");
		jdbcUrl = jdbcProperties.getProperty("jdbc.url");
		username = jdbcProperties.getProperty("jdbc.username");
		password = jdbcProperties.getProperty("jdbc.password");
		String[] jdbc = { jdbcDriver, jdbcUrl, username, password };
		return jdbc;
	}
}
