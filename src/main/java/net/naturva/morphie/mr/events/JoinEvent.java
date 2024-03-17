package net.naturva.morphie.mr.events;

import net.naturva.morphie.mr.MorphRedeem;
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
        if (this.plugin.getConfig().getBoolean("Settings.UpdateChecker")) {
            if (player.hasPermission("morphredeem.admin") || player.hasPermission("morphredeem.updateChecker")) {

            }
        }
    }
}
