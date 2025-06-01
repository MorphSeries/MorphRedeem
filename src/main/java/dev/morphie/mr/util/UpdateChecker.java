package dev.morphie.mr.util;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;

public class UpdateChecker {
    private URL checkURL;
    private String newVersion = "";
    private JavaPlugin plugin;
    private int project = 67435;


    // https://api.spigotmc.org/legacy/update.php?resource=67435
    public UpdateChecker(JavaPlugin plugin) {
        this.plugin = plugin;
        this.newVersion = plugin.getDescription().getVersion();
        try {
            this.checkURL = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + project);
        } catch (MalformedURLException exception) {
            plugin.getLogger().info("Unable to check for updates: " + exception.getMessage());
        }
    }
    public int getProjectID() {
        return this.project;
    }

    public JavaPlugin getPlugin() {
        return this.plugin;
    }

    public String getLatestVersion() {
        return this.newVersion;
    }

    public String getResourceURL() {
        return "https://www.spigotmc.org/resources/" + this.getProjectID();
    }

    public boolean checkForUpdates() {
        try {
            if (this.checkURL == null) {
                plugin.getLogger().warning("Update check URL is not configured.");
                return false;
            }
            URLConnection con = this.checkURL.openConnection();
            // Set timeouts to prevent hangs
            con.setConnectTimeout(5000); // 5 seconds connection timeout
            con.setReadTimeout(5000);    // 5 seconds read timeout

            this.newVersion = (new BufferedReader(new InputStreamReader(con.getInputStream()))).readLine();
            return !this.plugin.getDescription().getVersion().equals(this.newVersion);
        } catch (Exception e) {
            plugin.getLogger().warning("Could not check for updates: " + e.getMessage());
            return false;
        }
    }
}
