package net.naturva.morphie.mr.util.Database;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.sql.*;
import java.util.UUID;

import org.bukkit.entity.Player;

import net.naturva.morphie.mr.MorphRedeem;

public class SQLiteConnection {
    private static MorphRedeem plugin;
    private static String tablePrefix;
    private static String databasePath;

    public SQLiteConnection(MorphRedeem plugin) {
        SQLiteConnection.plugin = plugin;
        tablePrefix = SQLiteConnection.plugin.getConfig().getString("SQLite.TablePrefix", "mr_");
    }

    public void sqliteSetup() {
        try {
            synchronized (this) {
                File dbFile = new File(plugin.getDataFolder(), "database.db");

                // Create parent directories if they don't exist
                if (!dbFile.getParentFile().exists()) {
                    dbFile.getParentFile().mkdirs();
                }

                // Create the database file if it doesn't exist
                if (!dbFile.exists()) {
                    try {
                        dbFile.createNewFile();
                    } catch (IOException e) {
                        throw new SQLException("Could not create database file", e);
                    }
                }

                databasePath = dbFile.getPath();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkStructure() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath)) {
            if (connection == null) {
                return;
            }

            String sql = "CREATE TABLE IF NOT EXISTS " + this.tablePrefix + "creditdata ("
                    + "uuid TEXT NULL DEFAULT NULL, "
                    + "credits INTEGER NOT NULL DEFAULT 0, "
                    + "credits_spent INTEGER NOT NULL DEFAULT 0, "
                    + "UNIQUE(uuid)"
                    + ");";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean playerExists(UUID uuid) {
        String query = "SELECT 1 FROM `" + tablePrefix + "creditdata` WHERE uuid=?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, uuid.toString());

            try (ResultSet results = statement.executeQuery()) {
                return results.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void createPlayer(final UUID uuid, Player player) {
        String insertQuery = "INSERT INTO `" + tablePrefix + "creditdata` (uuid, credits, credits_spent) VALUES (?, ?, ?)";

        // Use a try-with-resources block to manage connection and statement
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
             PreparedStatement insert = connection.prepareStatement(insertQuery)) {

            if (!playerExists(uuid)) {
                insert.setString(1, uuid.toString());
                insert.setInt(2, 0); // Starting credits
                insert.setInt(3, 0); // Starting credits spent
                insert.executeUpdate();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData(UUID uuid, int num, String column, String type) {
        String sql = "UPDATE `" + tablePrefix + "creditdata` SET " + column.toLowerCase() + "=? WHERE uuid=?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            int data = Integer.parseInt(getData(uuid, column));

            if (type.equalsIgnoreCase("set")) {
                statement.setInt(1, num);
            } else if (type.equalsIgnoreCase("add") || type.equalsIgnoreCase("remove")) {
                statement.setInt(1, data + num);
            }
            statement.setString(2, uuid.toString());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getData(UUID uuid, String data) {
        String sql = "SELECT credits, credits_spent FROM `" + tablePrefix + "creditdata` WHERE uuid=?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, uuid.toString());

            try (ResultSet results = statement.executeQuery()){
                if (results.next()) {
                    if (data.equals("Credits")) {
                        return String.valueOf(results.getInt("credits"));
                    } else if (data.equals("Credits_Spent")) {
                        return String.valueOf(results.getInt("credits_spent"));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "0";
    }
}