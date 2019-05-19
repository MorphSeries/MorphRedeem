package net.naturva.morphie.events.chat;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.gmail.nossr50.api.ExperienceAPI;

import net.md_5.bungee.api.ChatColor;
import net.naturva.morphie.MorphRedeem;
import net.naturva.morphie.files.PlayerFileMethods;

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
	        chatMessage = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', chatMessage));
	        this.plugin.addCredits.remove(player);
	        try {
	        	Integer.parseInt(chatMessage);
	        }
	        catch (NumberFormatException e1) {
	        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidNumber")));
	        	return;
	        }
	        int amountToAdd = Integer.parseInt(chatMessage);
	        int credits = Integer.parseInt(new PlayerFileMethods(this.plugin).getStat(uuid, "Credits"));
	        if (credits < amountToAdd) {
	        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidCredits")));
	        	return;
	        }
	        if (amountToAdd < 0) {
	        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidNumberNegative")));
	        	return;
	        }
	        if (amountToAdd == 0) {
	        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAssignmentCanceled")));
	        	return;
	        }
	        int cap = ExperienceAPI.getLevelCap(skill);
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
	          player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + message));
	        } else {
	          new PlayerFileMethods(this.plugin).addCredits(player, uuid, "CreditsSpent", amountToAdd);
	          new PlayerFileMethods(this.plugin).removeCredits(player, uuid, "Credits", amountToAdd);
	          
	          ExperienceAPI.addLevel(player, skill, amountToAdd);
	          String message = this.plugin.getMessage("CreditAssignmentSuccess");
	          if (message.contains("%SKILL%")) {
	            message = message.replaceAll("%SKILL%", skill);
	          }
	          if (message.contains("%CREDITS%")) {
	            message = message.replaceAll("%CREDITS%", "" + amountToAdd);
	          }
	          player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + message));
	        }
		}
	}
}
