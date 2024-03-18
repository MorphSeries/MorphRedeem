package net.naturva.morphie.mr.events.chat;

import java.util.UUID;

import net.naturva.morphie.mr.util.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.gmail.nossr50.api.ExperienceAPI;

import net.md_5.bungee.api.ChatColor;
import net.naturva.morphie.mr.MorphRedeem;
import net.naturva.morphie.mr.util.DataManager;

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
	        	
	        	ExperienceAPI.addLevel(player, skill, amountToAdd);
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
