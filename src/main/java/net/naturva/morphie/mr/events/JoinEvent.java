package net.naturva.morphie.mr.events;

import net.naturva.morphie.mr.MorphRedeem;
import net.naturva.morphie.mr.util.StringUtils;
import net.naturva.morphie.mr.util.UpdateChecker;
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
        try {
            if (updater.checkForUpdates()) {
                if (this.plugin.getConfig().getBoolean("Settings.UpdateChecker")) {
                    if (player.hasPermission("morphredeem.admin") || player.hasPermission("morphredeem.updateChecker")) {
                        player.sendMessage(new StringUtils().addColor(plugin.getMessage("Prefix") + plugin.getMessage("UpdateMessage").replace("%VERSION%", new UpdateChecker(plugin).getLatestVersion()).replace("%LINK%", new UpdateChecker(plugin).getResourceURL())));
                    }
                }
            }
        } catch (Exception e1) {
        e1.printStackTrace();
        }
    }
}
