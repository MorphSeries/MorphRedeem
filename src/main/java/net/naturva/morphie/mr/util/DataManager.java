package net.naturva.morphie.mr.util;

import java.util.UUID;

import net.naturva.morphie.mr.MorphRedeem;
import net.naturva.morphie.mr.files.PlayerFileMethods;
import net.naturva.morphie.mr.util.Database.MySQLConnection;
import net.naturva.morphie.mr.util.Database.RedisConnection;
import net.naturva.morphie.mr.util.Database.SQLiteConnection;

public class DataManager {
	
	private MorphRedeem plugin;
	
	public DataManager(MorphRedeem plugin) {
		this.plugin = plugin;
	}

	public String getData(UUID uuid, String name) {
		if (this.plugin.getConfig().getString("StorageMethod").equalsIgnoreCase("MySQL")) {
			return new MySQLConnection(this.plugin).getData(uuid, name);
		} else if(this.plugin.getConfig().getString("StorageMethod").equalsIgnoreCase("Redis")){
			return new RedisConnection(this.plugin).getData(uuid, name);
		} else if(this.plugin.getConfig().getString("StorageMethod").equalsIgnoreCase("SQLite")){
			return new SQLiteConnection(this.plugin).getData(uuid, name);
		}
		else {
			return new PlayerFileMethods(plugin).getStat(uuid, name);
		}
	}
	
	public void updateData(UUID uuid, int data, String name, String type) {
		if (this.plugin.getConfig().getString("StorageMethod").equalsIgnoreCase("MySQL")) {
			new MySQLConnection(this.plugin).updateData(uuid, data, name, type);
		} else if(this.plugin.getConfig().getString("StorageMethod").equalsIgnoreCase("Redis")){
			new RedisConnection(this.plugin).updateData(uuid, data, name, type);
		} else if (this.plugin.getConfig().getString("StorageMethod").equalsIgnoreCase("SQLite")) {
			new SQLiteConnection(this.plugin).updateData(uuid, data, name, type);
		} else if (type == "add" || type == "remove"){
			new PlayerFileMethods(plugin).updateCredits(uuid, name, data);
		} else if (type == "set") {
			new PlayerFileMethods(plugin).setData(uuid, name, data);
		}
	}
}
