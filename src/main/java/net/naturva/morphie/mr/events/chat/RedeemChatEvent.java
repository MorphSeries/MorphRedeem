package net.naturva.morphie.mr.events.chat;

import java.util.UUID;

import net.naturva.morphie.mr.util.McMMOMethods;
import net.naturva.morphie.mr.util.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.gmail.nossr50.api.ExperienceAPI;

import net.md_5.bungee.api.ChatColor;
import net.naturva.morphie.mr.MorphRedeem;
import net.naturva.morphie.mr.util.DataManager;
import org.bukkit.scheduler.BukkitTask;

public class RedeemChatEvent implements Listener {

	private MorphRedeem plugin;
	
	public RedeemChatEvent(MorphRedeem plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		UUID uuid = player.getUniqueId();
		if (plugin.addCredits.containsKey(player)) {
			e.setCancelled(true);
	        String skill = (String) this.plugin.addCredits.get(player);
	        String chatMessage = e.getMessage();
	        String ignoreFromChat = this.plugin.getMessage("IgnoreFormat");
	        if (chatMessage.contains(ignoreFromChat)) {
	        	chatMessage = chatMessage.replaceAll(ignoreFromChat, "");
	        }
	        if (chatMessage.contains(" ")) {
	        	chatMessage = chatMessage.replaceAll(" ", "");
	        }
	        chatMessage = ChatColor.stripColor(new StringUtils().addColor(chatMessage));
	        this.plugin.addCredits.remove(player);
	        try {
	        	Integer.parseInt(chatMessage);
	        }
	        catch (NumberFormatException e1) {
	        	player.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidNumber")));
	        	return;
	        }
	        int amountToAdd = Integer.parseInt(chatMessage);
	        int credits = Integer.parseInt(new DataManager(plugin).getData(uuid, "Credits"));
	        if (credits < amountToAdd) {
	        	player.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidCredits")));
	        	return;
	        }
	        if (amountToAdd < 0) {
	        	player.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidNumberNegative")));
	        	return;
	        }
	        if (amountToAdd == 0) {
	        	player.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAssignmentCanceled")));
	        	return;
	        }
	        int cap = ExperienceAPI.getLevelCap(skill);
			if (cap > 0) {
				if (plugin.getConfig().getBoolean("Settings.mcMMOSkillXP.Enabled")) {
					int xp = plugin.getConfig().getInt("Settings.mcMMOSkillXP.XPpercredit");
					if (new McMMOMethods().getSkillXP(player, skill) + amountToAdd*xp >  ExperienceAPI.getXPToNextLevel(player, skill)) {
						String message = this.plugin.getMessage("SkillXPCapReached");
						if (message.contains("%SKILL%")) {
							message = message.replaceAll("%SKILL%", skill);
						}
						if (message.contains("%SKILLXP_NEEDED%")) {
							message = message.replaceAll("%SKILLXP_NEEDED%", "" + (ExperienceAPI.getXPToNextLevel(player, skill)-new McMMOMethods().getSkillXP(player, skill)));
						}
						if (message.contains("%CREDITXP%")) {
							message = message.replaceAll("%CREDITXP%", "" + amountToAdd*xp);
						}
						player.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + message));
					} else {
						new DataManager(plugin).updateData(uuid, +amountToAdd, "Credits_Spent", "add");
						new DataManager(plugin).updateData(uuid, -amountToAdd, "Credits", "remove");
						if (plugin.getConfig().getBoolean("Settings.mcMMOSkillXP.Enabled")) {
							Bukkit.getScheduler().runTask(plugin, () -> {
								new McMMOMethods().applyXP(player, skill, amountToAdd*xp);
							});
						} else {
							ExperienceAPI.addLevel(player, skill, amountToAdd);
						}
						String message = this.plugin.getMessage("CreditAssignmentSuccess");
						if (message.contains("%SKILL%")) {
							message = message.replaceAll("%SKILL%", skill);
						}
						if (message.contains("%CREDITS%")) {
							message = message.replaceAll("%CREDITS%", "" + amountToAdd);
						}
						player.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + message));
					}
				} else {
					if (ExperienceAPI.getLevel(player, skill) + amountToAdd > cap) {
						String message = this.plugin.getMessage("SkillCapReached");
						if (message.contains("%SKILL%")) {
							message = message.replaceAll("%SKILL%", skill);
						}
						if (message.contains("%CAP%")) {
							message = message.replaceAll("%CAP%", "" + cap);
						}
						if (message.contains("%LEVEL%")) {
							message = message.replaceAll("%LEVEL%", "" + (ExperienceAPI.getLevel(player, skill) + amountToAdd));
						}
						player.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + message));
					} else {
						new DataManager(plugin).updateData(uuid, +amountToAdd, "Credits_Spent", "add");
						new DataManager(plugin).updateData(uuid, -amountToAdd, "Credits", "remove");
						if (plugin.getConfig().getBoolean("Settings.mcMMOSkillXP.Enabled")) {
							int xp = plugin.getConfig().getInt("Settings.mcMMOSkillXP.XPpercredit");
							Bukkit.getScheduler().runTask(plugin, () -> {
								new McMMOMethods().applyXP(player, skill, amountToAdd*xp);
							});
						} else {
							ExperienceAPI.addLevel(player, skill, amountToAdd);
						}
						String message = this.plugin.getMessage("CreditAssignmentSuccess");
						if (message.contains("%SKILL%")) {
							message = message.replaceAll("%SKILL%", skill);
						}
						if (message.contains("%CREDITS%")) {
							message = message.replaceAll("%CREDITS%", "" + amountToAdd);
						}
						player.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + message));
					}
				}
			} else {
	        	new DataManager(plugin).updateData(uuid, +amountToAdd, "Credits_Spent", "add");
	        	new DataManager(plugin).updateData(uuid, -amountToAdd, "Credits", "remove");
	        	if (plugin.getConfig().getBoolean("Settings.mcMMOSkillXP.Enabled")) {
					int xp = plugin.getConfig().getInt("Settings.mcMMOSkillXP.XPpercredit");
					Bukkit.getScheduler().runTask(plugin, () -> {
						new McMMOMethods().applyXP(player, skill, amountToAdd*xp);
					});
				} else {
					ExperienceAPI.addLevel(player, skill, amountToAdd);
				}
	        	String message = this.plugin.getMessage("CreditAssignmentSuccess");
	        	if (message.contains("%SKILL%")) {
	        		message = message.replaceAll("%SKILL%", skill);
	        	}
	        	if (message.contains("%CREDITS%")) {
	        		message = message.replaceAll("%CREDITS%", "" + amountToAdd);
	        	}
	        	player.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + message));
	        }
		}
	}
}
