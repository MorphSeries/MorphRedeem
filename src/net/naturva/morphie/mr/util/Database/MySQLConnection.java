package net.naturva.morphie.mr.util.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.entity.Player;

import net.naturva.morphie.mr.MorphRedeem;

public class MySQLConnection {
	
	private MorphRedeem plugin;
	
	public MySQLConnection(MorphRedeem plugin) {
		this.plugin = plugin;
	}
	
	public Connection connection;
	public String host, database, tablePrefix, username, password, ssl;
	public int port;
	
	public void mysqlSetup() {
		host = this.plugin.getConfig().getString("MySQL.Host");
		port = this.plugin.getConfig().getInt("MySQL.Port");
		database = this.plugin.getConfig().getString("MySQL.Database");
		username = this.plugin.getConfig().getString("MySQL.Username");
		password = this.plugin.getConfig().getString("MySQL.Password");
		tablePrefix = this.plugin.getConfig().getString("MySQL.TablePrefix");
		if (this.plugin.getConfig().getBoolean("MySQL.SSL") == true) {
			ssl = "?verifyServerCertificate=false"+"&useSSL=true"+"&requireSSL=true";
		} else {
			ssl = "?useSSL=false";
		}
		
		try {
			synchronized (this) {
				if (getConnection() != null && getConnection().isClosed()) {
					return;
				}
				
				Class.forName("com.mysql.jdbc.Driver");
				setConnection((Connection) DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + this.ssl, this.username, this.password));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void checkStructure() {
		if (getConnection() == null) {
			this.mysqlSetup();
		}
		try {
			PreparedStatement statement = this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS `" + this.tablePrefix + "creditdata` (" 
					+ "`uuid` varchar(36) NULL DEFAULT NULL," 
					+ "`credits` int(32) unsigned NOT NULL DEFAULT '0',"
					+ "`credits_spent` int(32) unsigned NOT NULL DEFAULT '0',"
					+ "UNIQUE KEY `uuid` (`uuid`)) "
                    + "DEFAULT CHARSET=latin1;");
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean playerExists(UUID uuid) {
		if (getConnection() == null) {
			this.mysqlSetup();
		}
		try {
			PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM `" + this.tablePrefix + "creditdata` WHERE uuid=?");
			statement.setString(1, uuid.toString());
			
			ResultSet results = statement.executeQuery();
			if (results.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void createPlayer(final UUID uuid, Player player) {
		if (getConnection() == null) {
			this.mysqlSetup();
		}
		try {
			PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM `" + this.tablePrefix + "creditdata` WHERE uuid=?");
			statement.setString(1, uuid.toString());
			
			ResultSet results = statement.executeQuery();
			results.next();
			if (playerExists(uuid) != true) {
				PreparedStatement insert = this.connection.prepareStatement("INSERT INTO `" + this.tablePrefix + "creditdata` (uuid,credits,credits_spent) VALUE (?,?,?)");
				insert.setString(1, uuid.toString());
				insert.setInt(2, 0);
				insert.setInt(3, 0);
				insert.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateData(UUID uuid, int num, String column) {
		if (getConnection() == null) {
			this.mysqlSetup();
		}
		try {
			int data = Integer.parseInt(this.getData(uuid, column));
			PreparedStatement statement = this.connection.prepareStatement("UPDATE `" + this.tablePrefix + "creditdata` SET " + column.toLowerCase() + "=? WHERE uuid=?");
			statement.setInt(1, data + num);
			statement.setString(2, uuid.toString());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getData(UUID uuid, String data) {
		if (getConnection() == null) {
			this.mysqlSetup();
		}
		try {
			PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM `" + this.tablePrefix + "creditdata` WHERE uuid=?");
			statement.setString(1, uuid.toString());
			
			ResultSet results = statement.executeQuery();
			results.next();
			
			if (data == "Credits") {
				return results.getString(2).toString();	
			} else if (data == "Credits_Spent") {
				return results.getString(3).toString();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
