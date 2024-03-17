package net.naturva.morphie.mr.events.menus;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.naturva.morphie.mr.MorphRedeem;
import net.naturva.morphie.mr.util.DataManager;

public class RedeemMenuEvent implements Listener {

	private MorphRedeem plugin;
	
	public RedeemMenuEvent(MorphRedeem plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (event.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Menu.Title")))) {
			Player player = (Player)event.getWhoClicked();
			UUID uuid = player.getUniqueId(); 
			if ((event.getCurrentItem() == null) || (!event.getCurrentItem().hasItemMeta())) {
				return;
			}
			
			String credits = new DataManager(plugin).getData(uuid, "Credits");
			ItemStack item = event.getCurrentItem();
		    ItemMeta itemmeta = item.getItemMeta();
		    ArrayList<String> itemlore = (ArrayList<String>) itemmeta.getLore();
		    String DisplayName = itemmeta.getDisplayName();
		    Boolean skillDisable = this.plugin.getConfig().getBoolean("Settings.DisabledSkills.Enabled");
		      
		    boolean normalItem = false;
		    if (DisplayName.equals(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Menu.mcMMOCredits.Name")))) {
		    	event.setCancelled(true);
				normalItem = true;
		    } else if (DisplayName.equalsIgnoreCase(" ")) {
				event.setCancelled(true);	
				normalItem = true;
		    } else if (DisplayName.equals(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Menu.CreditsSpent.Name")))) {
		        event.setCancelled(true);
		        normalItem = true;
		    } else if (DisplayName.equals(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Menu.PluginCredits.Name")))) {
		        event.setCancelled(true);
		        player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("SpigotLink")));
		        normalItem = true;
		    } if (!normalItem) {
		    	if (DisplayName.equals(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Menu.Acrobatics.Name")))) {
		    		event.setCancelled(true);
		    		if (skillDisable == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Acrobatics")) {
		    			event.setCancelled(true);
		    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("SkillDisabledMessage")));
		    		} else {
			    		if (!(plugin.addCredits.containsKey(player))) {
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAssignmentMessage").replace("%CREDITS%", credits)));
			    			plugin.addCredits.put(player, "Acrobatics");
			    			player.closeInventory();
			    		} else {
			    			String skill = plugin.addCredits.get(player);
			    			player.closeInventory();
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CreditInProgressMessage").replace("%SKILL%", skill).replace("%CREDITS%", credits)));
			    		}
		    		}
		    	} else if (DisplayName.equals(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Menu.Alchemy.Name")))) {
		    		event.setCancelled(true);
		    		if (skillDisable == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Alchemy")) {
		    			event.setCancelled(true);
		    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("SkillDisabledMessage")));
		    		} else {
			    		if (!(plugin.addCredits.containsKey(player))) {
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAssignmentMessage").replace("%CREDITS%", credits)));
			    			plugin.addCredits.put(player, "Alchemy");
			    			player.closeInventory();
			    		} else {
			    			String skill = plugin.addCredits.get(player);
			    			player.closeInventory();
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CreditInProgressMessage").replace("%SKILL%", skill).replace("%CREDITS%", credits)));
			    		}
		    		}
		    	} else if (DisplayName.equals(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Menu.Archery.Name")))) {
		    		event.setCancelled(true);
		    		if (skillDisable == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Archery")) {
		    			event.setCancelled(true);
		    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("SkillDisabledMessage")));
		    		} else {
			    		if (!(plugin.addCredits.containsKey(player))) {
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAssignmentMessage").replace("%CREDITS%", credits)));
			    			plugin.addCredits.put(player, "Archery");
			    			player.closeInventory();
			    		} else {
			    			String skill = plugin.addCredits.get(player);
			    			player.closeInventory();
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CreditInProgressMessage").replace("%SKILL%", skill).replace("%CREDITS%", credits)));
			    		}
		    		}
		    	} else if (DisplayName.equals(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Menu.Axes.Name")))) {
		    		event.setCancelled(true);
		    		if (skillDisable == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Axes")) {
		    			event.setCancelled(true);
		    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("SkillDisabledMessage")));
		    		} else {
			    		if (!(plugin.addCredits.containsKey(player))) {
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAssignmentMessage").replace("%CREDITS%", credits)));
			    			plugin.addCredits.put(player, "Axes");
			    			player.closeInventory();
			    		} else {
			    			String skill = plugin.addCredits.get(player);
			    			player.closeInventory();
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CreditInProgressMessage").replace("%SKILL%", skill).replace("%CREDITS%", credits)));
			    		}
		    		}
		    	} else if (DisplayName.equals(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Menu.Excavation.Name")))) {
		    		event.setCancelled(true);
		    		if (skillDisable == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Excavation")) {
		    			event.setCancelled(true);
		    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("SkillDisabledMessage")));
		    		} else {
			    		if (!(plugin.addCredits.containsKey(player))) {
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAssignmentMessage").replace("%CREDITS%", credits)));
			    			plugin.addCredits.put(player, "Excavation");
			    			player.closeInventory();
			    		} else {
			    			String skill = plugin.addCredits.get(player);
			    			player.closeInventory();
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CreditInProgressMessage").replace("%SKILL%", skill).replace("%CREDITS%", credits)));
			    		}
		    		}
		    	} else if (DisplayName.equals(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Menu.Fishing.Name")))) {
		    		event.setCancelled(true);
		    		if (skillDisable == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Fishing")) {
		    			event.setCancelled(true);
		    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("SkillDisabledMessage")));
		    		} else {
			    		if (!(plugin.addCredits.containsKey(player))) {
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAssignmentMessage").replace("%CREDITS%", credits)));
			    			plugin.addCredits.put(player, "Fishing");
			    			player.closeInventory();
			    		} else {
			    			String skill = plugin.addCredits.get(player);
			    			player.closeInventory();
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CreditInProgressMessage").replace("%SKILL%", skill).replace("%CREDITS%", credits)));
			    		}
		    		}
		    	} else if (DisplayName.equals(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Menu.Herbalism.Name")))) {
		    		event.setCancelled(true);
		    		if (skillDisable == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Herbalism")) {
		    			event.setCancelled(true);
		    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("SkillDisabledMessage")));
		    		} else {
			    		if (!(plugin.addCredits.containsKey(player))) {
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAssignmentMessage").replace("%CREDITS%", credits)));
			    			plugin.addCredits.put(player, "Herbalism");
			    			player.closeInventory();
			    		} else {
			    			String skill = plugin.addCredits.get(player);
			    			player.closeInventory();
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CreditInProgressMessage").replace("%SKILL%", skill).replace("%CREDITS%", credits)));
			    		}
		    		}
		    	} else if (DisplayName.equals(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Menu.Mining.Name")))) {
		    		event.setCancelled(true);
		    		if (skillDisable == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Mining")) {
		    			event.setCancelled(true);
		    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("SkillDisabledMessage")));
		    		} else {
			    		if (!(plugin.addCredits.containsKey(player))) {
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAssignmentMessage").replace("%CREDITS%", credits)));
			    			plugin.addCredits.put(player, "Mining");
			    			player.closeInventory();
			    		} else {
			    			String skill = plugin.addCredits.get(player);
			    			player.closeInventory();
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CreditInProgressMessage").replace("%SKILL%", skill).replace("%CREDITS%", credits)));
			    		}
		    		}
		    	} else if (DisplayName.equals(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Menu.Repair.Name")))) {
		    		event.setCancelled(true);
		    		if (skillDisable == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Repair")) {
		    			event.setCancelled(true);
		    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("SkillDisabledMessage")));
		    		} else {
			    		if (!(plugin.addCredits.containsKey(player))) {
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAssignmentMessage").replace("%CREDITS%", credits)));
			    			plugin.addCredits.put(player, "Repair");
			    			player.closeInventory();
			    		} else {
			    			String skill = plugin.addCredits.get(player);
			    			player.closeInventory();
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CreditInProgressMessage").replace("%SKILL%", skill).replace("%CREDITS%", credits)));
			    		}
		    		}
		    	} else if (DisplayName.equals(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Menu.Swords.Name")))) {
		    		event.setCancelled(true);
		    		if (skillDisable == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Swords")) {
		    			event.setCancelled(true);
		    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("SkillDisabledMessage")));
		    		} else {
			    		if (!(plugin.addCredits.containsKey(player))) {
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAssignmentMessage").replace("%CREDITS%", credits)));
			    			plugin.addCredits.put(player, "Swords");
			    			player.closeInventory();
			    		} else {
			    			String skill = plugin.addCredits.get(player);
			    			player.closeInventory();
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CreditInProgressMessage").replace("%SKILL%", skill).replace("%CREDITS%", credits)));
			    		}
		    		}
		    	} else if (DisplayName.equals(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Menu.Taming.Name")))) {
		    		event.setCancelled(true);
		    		if (skillDisable == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Taming")) {
		    			event.setCancelled(true);
		    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("SkillDisabledMessage")));
		    		} else {
			    		if (!(plugin.addCredits.containsKey(player))) {
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAssignmentMessage").replace("%CREDITS%", credits)));
			    			plugin.addCredits.put(player, "Taming");
			    			player.closeInventory();
			    		} else {
			    			String skill = plugin.addCredits.get(player);
			    			player.closeInventory();
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CreditInProgressMessage").replace("%SKILL%", skill).replace("%CREDITS%", credits)));
			    		}
		    		}
		    	} else if (DisplayName.equals(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Menu.Unarmed.Name")))) {
		    		event.setCancelled(true);
		    		if (skillDisable == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Unarmed")) {
		    			event.setCancelled(true);
		    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("SkillDisabledMessage")));
		    		} else {
			    		if (!(plugin.addCredits.containsKey(player))) {
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAssignmentMessage").replace("%CREDITS%", credits)));
			    			plugin.addCredits.put(player, "Unarmed");
			    			player.closeInventory();
			    		} else {
			    			String skill = plugin.addCredits.get(player);
			    			player.closeInventory();
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CreditInProgressMessage").replace("%SKILL%", skill).replace("%CREDITS%", credits)));
			    		}
		    		}
		    	} else if (DisplayName.equals(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Menu.Woodcutting.Name")))) {
		    		event.setCancelled(true);
		    		if (skillDisable == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Woodcutting")) {
		    			event.setCancelled(true);
		    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("SkillDisabledMessage")));
		    		} else {
			    		if (!(plugin.addCredits.containsKey(player))) {
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAssignmentMessage").replace("%CREDITS%", credits)));
			    			plugin.addCredits.put(player, "Woodcutting");
			    			player.closeInventory();
			    		} else {
			    			String skill = plugin.addCredits.get(player);
			    			player.closeInventory();
			    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CreditInProgressMessage").replace("%SKILL%", skill).replace("%CREDITS%", credits)));
			    		}
		    		}
		    	}
		    	event.setCancelled(true);
	    	} else {
	    		event.setCancelled(true);
	    	}
		}
	}
}
