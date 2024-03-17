package net.naturva.morphie.mr.util;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UpdateChecker {
    private URL checkURL;
    private String newVersion = "";
    private JavaPlugin plugin;
    private int project = 67435;


    // https://api.spigotmc.org/legacy/update.php?resource=67435
    public UpdateChecker(JavaPlugin plugin, int projectID) {
        this.plugin = plugin;
        this.newVersion = plugin.getDescription().getVersion();
        this.project = projectID;
        try {
            this.checkURL = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + projectID);
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
        return "https://www.spigotmc.org/resources/" + this.project;
    }

    public boolean checkForUpdates() throws Exception {
        URLConnection con = this.checkURL.openConnection();
        this.newVersion = (new BufferedReader(new InputStreamReader(con.getInputStream()))).readLine();
        return !this.plugin.getDescription().getVersion().equals(this.newVersion);
    }
}
