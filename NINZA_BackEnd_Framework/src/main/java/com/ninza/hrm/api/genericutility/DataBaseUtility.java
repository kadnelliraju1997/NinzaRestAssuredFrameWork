package com.ninza.hrm.api.genericutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBaseUtility implements IPathConstants {
	Connection con;

	public void getDbConnection(String url, String username, String password) {
		try {
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			con = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
		}
	}

	public void getDbConnection() {
		try {
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			con = DriverManager.getConnection(DbUrl, DbUsername, DbPassword);

		} catch (Exception e) {
		}
	}

	public boolean executeSelectQuaryVerifyAndGetData(String query, int columnIndex, String expectedData) {
		ResultSet resultSet = null;
		boolean flag = false;
		try {
			Statement stat = con.createStatement();
			resultSet = stat.executeQuery(query);
			while (resultSet.next()) {
				if (resultSet.getString(columnIndex).equals(expectedData)) {
					flag = true;
					break;
				}
			}
			if (flag) {
				System.out.println(expectedData + "===>data verified in data base table");
				return true;
			} else {
				{
					System.out.println(expectedData + "===>data is not verified in data base table");
					return false;
				}
			}
		} catch (Exception e) {
		}
		return flag;

	}

	public int executeNonSelectQuary(String query) {
		int resultSet = 0;
		try {
			Statement stat = con.createStatement();
			resultSet = stat.executeUpdate(query);
		} catch (Exception e) {
		}
		return resultSet;
	}

	public void closeDbConnection() {
		try {
			con.close();
		} catch (Exception e) {
		}
	}
}
