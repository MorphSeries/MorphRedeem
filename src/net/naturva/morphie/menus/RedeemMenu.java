package net.naturva.morphie.menus;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import net.naturva.morphie.MorphRedeem;
import net.naturva.morphie.files.PlayerFileMethods;
import net.naturva.morphie.util.McMMOMethods;
import net.naturva.morphie.util.dataManager;



public class RedeemMenu implements Listener {
	
	private MorphRedeem plugin;
	  
	public RedeemMenu(MorphRedeem plugin) {
		this.plugin = plugin;
	}
	
	public void openGUIRedeem(Player player) {
		Inventory Redeem = Bukkit.createInventory(null, 45, ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Menu.Title")));
	
		UUID uuid = player.getUniqueId();
		Boolean skillDisable = this.plugin.getConfig().getBoolean("Settings.DisabledSkills.Enabled");
		Boolean replaceItem = this.plugin.getConfig().getBoolean("Settings.DisabledSkills.ReplaceGUIItem.Enabled");
		
		ArrayList<String> Acrobatics = new ArrayList();
	    for (String s : plugin.getMessageList("Menu.Acrobatics.Lore")) {
	    	if (new McMMOMethods().getSkillCap("Acrobatics") != 2147483647) {
		    	Acrobatics.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Acrobatics"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Acrobatics")));
	    	} else {
		    	Acrobatics.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Acrobatics"))
		    			.replace("%LEVELCAP%", "" + ChatColor.translateAlternateColorCodes('&', plugin.getMessage("NoSkillCap"))));
	    	}
		}
	    
	    if (skillDisable == true && replaceItem == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Acrobatics")) {
	    	Redeem.setItem(10, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.DisabledSkills.ReplaceGUIItem.ItemName"), 1, this.plugin.getMessage("Menu.Acrobatics.Name"), Acrobatics, false));
	    } else {
			Redeem.setItem(10, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.Acrobatics.ItemName"), 1, this.plugin.getMessage("Menu.Acrobatics.Name"), Acrobatics, false));
	    }
	    
		ArrayList<String> Alchemy = new ArrayList();
	    for (String s : plugin.getMessageList("Menu.Alchemy.Lore")) {
	    	if (new McMMOMethods().getSkillCap("Alchemy") != 2147483647) {
	    		Alchemy.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Alchemy"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Alchemy")));
	    	} else {
	    		Alchemy.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Alchemy"))
		    			.replace("%LEVELCAP%", "" + ChatColor.translateAlternateColorCodes('&', plugin.getMessage("NoSkillCap"))));
	    	}
		}
	
	    if (skillDisable == true && replaceItem == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Alchemy")) {
	    	Redeem.setItem(11, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.DisabledSkills.ReplaceGUIItem.ItemName"), 1, this.plugin.getMessage("Menu.Alchemy.Name"), Alchemy, false));
	    } else {
	    	Redeem.setItem(11, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.Alchemy.ItemName"), 1, this.plugin.getMessage("Menu.Alchemy.Name"), Alchemy, false));
	    }
	    
		ArrayList<String> Archery = new ArrayList();
	    for (String s : plugin.getMessageList("Menu.Archery.Lore")) {
	    	if (new McMMOMethods().getSkillCap("Archery") != 2147483647) {
	    		Archery.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Archery"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Archery")));
	    	} else {
	    		Archery.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Archery"))
		    			.replace("%LEVELCAP%", "" + ChatColor.translateAlternateColorCodes('&', plugin.getMessage("NoSkillCap"))));
	    	}
		}
	    
	    if (skillDisable == true && replaceItem == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Archery")) {
	    	Redeem.setItem(12, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.DisabledSkills.ReplaceGUIItem.ItemName"), 1, this.plugin.getMessage("Menu.Archery.Name"), Archery, false));
	    } else {
	    	Redeem.setItem(12, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.Archery.ItemName"), 1, this.plugin.getMessage("Menu.Archery.Name"), Archery, false));
	    }
	    
		ArrayList<String> Axes = new ArrayList();
	    for (String s : plugin.getMessageList("Menu.Axes.Lore")) {
	    	if (new McMMOMethods().getSkillCap("Axes") != 2147483647) {
	    		Axes.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Axes"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Axes")));
	    	} else {
	    		Axes.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Axes"))
		    			.replace("%LEVELCAP%", "" + ChatColor.translateAlternateColorCodes('&', plugin.getMessage("NoSkillCap"))));
	    	}
		}
	
	    if (skillDisable == true && replaceItem == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Axes")) {
	    	Redeem.setItem(13, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.DisabledSkills.ReplaceGUIItem.ItemName"), 1, this.plugin.getMessage("Menu.Axes.Name"), Axes, false));
	    } else {
	    	Redeem.setItem(13, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.Axes.ItemName"), 1, this.plugin.getMessage("Menu.Axes.Name"), Axes, false));
	    }
	    
		ArrayList<String> Excavation = new ArrayList();
	    for (String s : plugin.getMessageList("Menu.Excavation.Lore")) {
	    	if (new McMMOMethods().getSkillCap("Excavation") != 2147483647) {
	    		Excavation.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Excavation"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Excavation")));
	    	} else {
	    		Excavation.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Excavation"))
		    			.replace("%LEVELCAP%", "" + ChatColor.translateAlternateColorCodes('&', plugin.getMessage("NoSkillCap"))));
	    	}
		}
	
	    if (skillDisable == true && replaceItem == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Excavation")) {
	    	Redeem.setItem(14, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.DisabledSkills.ReplaceGUIItem.ItemName"), 1, this.plugin.getMessage("Menu.Excavation.Name"), Excavation, false));
	    } else {
	    	Redeem.setItem(14, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.Excavation.ItemName"), 1, this.plugin.getMessage("Menu.Excavation.Name"), Excavation, false));
	    }
	    
		ArrayList<String> Fishing = new ArrayList();
	    for (String s : plugin.getMessageList("Menu.Fishing.Lore")) {
	    	if (new McMMOMethods().getSkillCap("Fishing") != 2147483647) {
	    		Fishing.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Fishing"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Fishing")));
	    	} else {
	    		Fishing.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Fishing"))
		    			.replace("%LEVELCAP%", "" + ChatColor.translateAlternateColorCodes('&', plugin.getMessage("NoSkillCap"))));
	    	}
		}
	
	    if (skillDisable == true && replaceItem == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Fishing")) {
	    	Redeem.setItem(20, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.DisabledSkills.ReplaceGUIItem.ItemName"), 1, this.plugin.getMessage("Menu.Fishing.Name"), Fishing, false));
	    } else {
	    	Redeem.setItem(20, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.Fishing.ItemName"), 1, this.plugin.getMessage("Menu.Fishing.Name"), Fishing, false));
	    }
	    
		ArrayList<String> Herbalism = new ArrayList();
	    for (String s : plugin.getMessageList("Menu.Herbalism.Lore")) {
	    	if (new McMMOMethods().getSkillCap("Herbalism") != 2147483647) {
	    		Herbalism.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Herbalism"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Herbalism")));
	    	} else {
	    		Herbalism.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Herbalism"))
		    			.replace("%LEVELCAP%", "" + ChatColor.translateAlternateColorCodes('&', plugin.getMessage("NoSkillCap"))));
	    	}
		}
	
	    if (skillDisable == true && replaceItem == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Herbalism")) {
	    	Redeem.setItem(21, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.DisabledSkills.ReplaceGUIItem.ItemName"), 1, this.plugin.getMessage("Menu.Herbalism.Name"), Herbalism, false));
	    } else {
	    	Redeem.setItem(21, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.Herbalism.ItemName"), 1, this.plugin.getMessage("Menu.Herbalism.Name"), Herbalism, false));
	    }
	    
		ArrayList<String> Mining = new ArrayList();
	    for (String s : plugin.getMessageList("Menu.Mining.Lore")) {
	    	if (new McMMOMethods().getSkillCap("Mining") != 2147483647) {
	    		Mining.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Mining"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Mining")));
	    	} else {
	    		Mining.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Mining"))
		    			.replace("%LEVELCAP%", "" + ChatColor.translateAlternateColorCodes('&', plugin.getMessage("NoSkillCap"))));
	    	}
		}
	
	    if (skillDisable == true && replaceItem == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Mining")) {
	    	Redeem.setItem(22, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.DisabledSkills.ReplaceGUIItem.ItemName"), 1, this.plugin.getMessage("Menu.Mining.Name"), Mining, false));
	    } else {
	    	Redeem.setItem(22, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.Mining.ItemName"), 1, this.plugin.getMessage("Menu.Mining.Name"), Mining, false));
	    }
	    
		ArrayList<String> Repair = new ArrayList();
	    for (String s : plugin.getMessageList("Menu.Repair.Lore")) {
	    	if (new McMMOMethods().getSkillCap("Repair") != 2147483647) {
	    		Repair.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Repair"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Repair")));
	    	} else {
	    		Repair.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Repair"))
		    			.replace("%LEVELCAP%", "" + ChatColor.translateAlternateColorCodes('&', plugin.getMessage("NoSkillCap"))));
	    	}
		}
	
	    if (skillDisable == true && replaceItem == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Repair")) {
	    	Redeem.setItem(28, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.DisabledSkills.ReplaceGUIItem.ItemName"), 1, this.plugin.getMessage("Menu.Repair.Name"), Repair, false));
	    } else {
	    	Redeem.setItem(28, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.Repair.ItemName"), 1, this.plugin.getMessage("Menu.Repair.Name"), Repair, false));
	    }
	    
		ArrayList<String> Swords = new ArrayList();
	    for (String s : plugin.getMessageList("Menu.Swords.Lore")) {
	    	if (new McMMOMethods().getSkillCap("Swords") != 2147483647) {
	    		Swords.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Swords"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Swords")));
	    	} else {
	    		Swords.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Swords"))
		    			.replace("%LEVELCAP%", "" + ChatColor.translateAlternateColorCodes('&', plugin.getMessage("NoSkillCap"))));
	    	}
		}
	
	    if (skillDisable == true && replaceItem == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Swords")) {
	    	Redeem.setItem(29, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.DisabledSkills.ReplaceGUIItem.ItemName"), 1, this.plugin.getMessage("Menu.Swords.Name"), Swords, false));
	    } else {
	    	Redeem.setItem(29, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.Swords.ItemName"), 1, this.plugin.getMessage("Menu.Swords.Name"), Swords, false));
	    }
	    
		ArrayList<String> Taming = new ArrayList();
	    for (String s : plugin.getMessageList("Menu.Taming.Lore")) {
	    	if (new McMMOMethods().getSkillCap("Taming") != 2147483647) {
	    		Taming.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Taming"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Taming")));
	    	} else {
	    		Taming.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Taming"))
		    			.replace("%LEVELCAP%", "" + ChatColor.translateAlternateColorCodes('&', plugin.getMessage("NoSkillCap"))));
	    	}
		}
	
	    if (skillDisable == true && replaceItem == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Taming")) {
	    	Redeem.setItem(30, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.DisabledSkills.ReplaceGUIItem.ItemName"), 1, this.plugin.getMessage("Menu.Taming.Name"), Taming, false));
	    } else {
	    	Redeem.setItem(30, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.Taming.ItemName"), 1, this.plugin.getMessage("Menu.Taming.Name"), Taming, false));
	    }
	    
		ArrayList<String> Unarmed = new ArrayList();
	    for (String s : plugin.getMessageList("Menu.Unarmed.Lore")) {
	    	if (new McMMOMethods().getSkillCap("Unarmed") != 2147483647) {
	    		Unarmed.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Unarmed"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Unarmed")));
	    	} else {
	    		Unarmed.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Unarmed"))
		    			.replace("%LEVELCAP%", "" + ChatColor.translateAlternateColorCodes('&', plugin.getMessage("NoSkillCap"))));
	    	}
		}
	
	    if (skillDisable == true && replaceItem == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Unarmed")) {
	    	Redeem.setItem(31, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.DisabledSkills.ReplaceGUIItem.ItemName"), 1, this.plugin.getMessage("Menu.Unarmed.Name"), Unarmed, false));
	    } else {
	    	Redeem.setItem(31, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.Unarmed.ItemName"), 1, this.plugin.getMessage("Menu.Unarmed.Name"), Unarmed, false));
	    }
	    
		ArrayList<String> Woodcutting = new ArrayList();
	    for (String s : plugin.getMessageList("Menu.Woodcutting.Lore")) {
	    	if (new McMMOMethods().getSkillCap("Woodcutting") != 2147483647) {
	    		Woodcutting.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Woodcutting"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Woodcutting")));
	    	} else {
	    		Woodcutting.add(ChatColor.translateAlternateColorCodes('&', s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Woodcutting"))
		    			.replace("%LEVELCAP%", "" + ChatColor.translateAlternateColorCodes('&', plugin.getMessage("NoSkillCap"))));
	    	}
		}
	
	    if (skillDisable == true && replaceItem == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Woodcutting")) {
	    	Redeem.setItem(32, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.DisabledSkills.ReplaceGUIItem.ItemName"), 1, this.plugin.getMessage("Menu.Woodcutting.Name"), Woodcutting, false));
	    } else {
	    	Redeem.setItem(32, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.Woodcutting.ItemName"), 1, this.plugin.getMessage("Menu.Woodcutting.Name"), Woodcutting, false));
	    }
	    
		ArrayList<String> mcMMOCredits = new ArrayList();
	    for (String s : plugin.getMessageList("Menu.mcMMOCredits.Lore")) {
	    	mcMMOCredits.add(ChatColor.translateAlternateColorCodes('&', s)
	    			.replace("%MCMMOCREDITS%", "" + new dataManager(plugin).getData(uuid, "Credits")));
		}
	
		Redeem.setItem(16, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.mcMMOCredits.ItemName"), 1, this.plugin.getMessage("Menu.mcMMOCredits.Name"), mcMMOCredits, false));

		ArrayList<String> CreditsSpent = new ArrayList();
	    for (String s : plugin.getMessageList("Menu.CreditsSpent.Lore")) {
	    	CreditsSpent.add(ChatColor.translateAlternateColorCodes('&', s)
	    			.replace("%CREDITSSPENT%", "" + new dataManager(plugin).getData(uuid, "Credits_Spent")));
		}
	
		Redeem.setItem(25, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.CreditsSpent.ItemName"), 1, this.plugin.getMessage("Menu.CreditsSpent.Name"), CreditsSpent, false));

		ArrayList<String> PluginCredits = new ArrayList();
	    for (String s : plugin.getMessageList("Menu.PluginCredits.Lore")) {
	    	PluginCredits.add(ChatColor.translateAlternateColorCodes('&', s));
		}
	    
	    int glassInt = this.plugin.getConfig().getInt("Settings.BackgroundGlassColor");
	
		Redeem.setItem(34, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.PluginCredits.ItemName"), 1, this.plugin.getMessage("Menu.PluginCredits.Name"), PluginCredits, false));
		
		Redeem.setItem(0, this.plugin.createInventoryGlassItem("LEGACY_STAINED_GLASS_PANE", glassInt, 1, " ", null, false));
		Redeem.setItem(6, this.plugin.createInventoryGlassItem("LEGACY_STAINED_GLASS_PANE", glassInt, 1, " ", null, false));
		Redeem.setItem(8, this.plugin.createInventoryGlassItem("LEGACY_STAINED_GLASS_PANE", glassInt, 1, " ", null, false));
		Redeem.setItem(9, this.plugin.createInventoryGlassItem("LEGACY_STAINED_GLASS_PANE", glassInt, 1, " ", null, false));
		Redeem.setItem(15, this.plugin.createInventoryGlassItem("LEGACY_STAINED_GLASS_PANE", glassInt, 1, " ", null, false));
		Redeem.setItem(17, this.plugin.createInventoryGlassItem("LEGACY_STAINED_GLASS_PANE", glassInt, 1, " ", null, false));
		Redeem.setItem(18, this.plugin.createInventoryGlassItem("LEGACY_STAINED_GLASS_PANE", glassInt, 1, " ", null, false));
		Redeem.setItem(24, this.plugin.createInventoryGlassItem("LEGACY_STAINED_GLASS_PANE", glassInt, 1, " ", null, false));
		Redeem.setItem(26, this.plugin.createInventoryGlassItem("LEGACY_STAINED_GLASS_PANE", glassInt, 1, " ", null, false));
		Redeem.setItem(27, this.plugin.createInventoryGlassItem("LEGACY_STAINED_GLASS_PANE", glassInt, 1, " ", null, false));
		Redeem.setItem(33, this.plugin.createInventoryGlassItem("LEGACY_STAINED_GLASS_PANE", glassInt, 1, " ", null, false));
		Redeem.setItem(35, this.plugin.createInventoryGlassItem("LEGACY_STAINED_GLASS_PANE", glassInt, 1, " ", null, false));
		Redeem.setItem(36, this.plugin.createInventoryGlassItem("LEGACY_STAINED_GLASS_PANE", glassInt, 1, " ", null, false));
		Redeem.setItem(42, this.plugin.createInventoryGlassItem("LEGACY_STAINED_GLASS_PANE", glassInt, 1, " ", null, false));
		Redeem.setItem(44, this.plugin.createInventoryGlassItem("LEGACY_STAINED_GLASS_PANE", glassInt, 1, " ", null, false));
		
		Redeem.setItem(1, this.plugin.createInventoryItem("BLACK_STAINED_GLASS_PANE", 1, " ", null, false));
		Redeem.setItem(2, this.plugin.createInventoryItem("BLACK_STAINED_GLASS_PANE", 1, " ", null, false));
		Redeem.setItem(3, this.plugin.createInventoryItem("BLACK_STAINED_GLASS_PANE", 1, " ", null, false));
		Redeem.setItem(4, this.plugin.createInventoryItem("BLACK_STAINED_GLASS_PANE", 1, " ", null, false));
		Redeem.setItem(5, this.plugin.createInventoryItem("BLACK_STAINED_GLASS_PANE", 1, " ", null, false));
		Redeem.setItem(7, this.plugin.createInventoryItem("BLACK_STAINED_GLASS_PANE", 1, " ", null, false));
		Redeem.setItem(19, this.plugin.createInventoryItem("BLACK_STAINED_GLASS_PANE", 1, " ", null, false));
		Redeem.setItem(23, this.plugin.createInventoryItem("BLACK_STAINED_GLASS_PANE", 1, " ", null, false));
		Redeem.setItem(37, this.plugin.createInventoryItem("BLACK_STAINED_GLASS_PANE", 1, " ", null, false));
		Redeem.setItem(38, this.plugin.createInventoryItem("BLACK_STAINED_GLASS_PANE", 1, " ", null, false));
		Redeem.setItem(39, this.plugin.createInventoryItem("BLACK_STAINED_GLASS_PANE", 1, " ", null, false));
		Redeem.setItem(40, this.plugin.createInventoryItem("BLACK_STAINED_GLASS_PANE", 1, " ", null, false));
		Redeem.setItem(41, this.plugin.createInventoryItem("BLACK_STAINED_GLASS_PANE", 1, " ", null, false));
		Redeem.setItem(43, this.plugin.createInventoryItem("BLACK_STAINED_GLASS_PANE", 1, " ", null, false));
		
		player.openInventory(Redeem);
	}
}
