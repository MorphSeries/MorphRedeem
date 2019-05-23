package net.naturva.morphie.util;

import com.mysql.jdbc.Connection;

public class MySQLDatabase {
	private Connection connection;
	public String host, database, username, password;
}

//# Storage method, can be MySQL or YML
//StorageMethod: YML
//
//# Requires 'StorageMethod' to be MySQL
//MySQL:
//  Username: "root"
//  Password: "password"
//  Host: "localhost"
//  Port: 3306
//  Database: "minecraft"
//  TablePrefix: "MR_"
