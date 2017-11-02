package com.db;

import java.sql.Connection;
import java.sql.DriverManager;


public class Db {
	public static String dbDriver = null;
	public static String dbURL = null;
	public static String dbUserName = null;
	public static String dbPassWord = null;

	public Db() {

	}

	public static void destroyDataSource() {
	}

	public static Connection getCn() {
		try {
			Class.forName(Db.dbDriver).newInstance();
			return DriverManager.getConnection(Db.dbURL, Db.dbUserName,
					Db.dbPassWord);

		} catch (Exception e) {
			System.out.println("得到连接失败,cause:" + e.getMessage());
			return null;
		}

	}
}
