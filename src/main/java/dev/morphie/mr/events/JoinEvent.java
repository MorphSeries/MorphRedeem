package dev.morphie.mr.events;

import dev.morphie.mr.MorphRedeem;
import dev.morphie.mr.util.StringUtils;
import dev.morphie.mr.util.UpdateChecker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    private MorphRedeem plugin;

    public JoinEvent(MorphRedeem plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        UpdateChecker updater = new UpdateChecker(plugin);

        if (updater.checkForUpdates()) {
            if (this.plugin.getConfig().getBoolean("Settings.UpdateChecker")) {
                if (player.hasPermission("morphredeem.admin") || player.hasPermission("morphredeem.updateChecker")) {
                    // Use the already fetched latest version from the updater instance
                    player.sendMessage(new StringUtils().addColor(plugin.getMessage("Prefix") + plugin.getMessage("UpdateMessage").replace("%VERSION%", updater.getLatestVersion()).replace("%LINK%", updater.getResourceURL())));
                }
            }
        }
    }
}
