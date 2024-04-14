package net.naturva.morphie.mr.menus;

import java.util.ArrayList;
import java.util.UUID;

import com.gmail.nossr50.util.player.UserManager;
import net.naturva.morphie.mr.util.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import net.naturva.morphie.mr.MorphRedeem;
import net.naturva.morphie.mr.util.McMMOMethods;
import net.naturva.morphie.mr.util.DataManager;



public class RedeemMenu implements Listener {
	
	private MorphRedeem plugin;
	  
	public RedeemMenu(MorphRedeem plugin) {
		this.plugin = plugin;
	}
	
	public void openGUIRedeem(Player player) {
		Inventory Redeem = Bukkit.createInventory(null, 45, new StringUtils().addColor(this.plugin.getMessage("Menu.Title")));
	
		UUID uuid = player.getUniqueId();
		boolean skillDisable = this.plugin.getConfig().getBoolean("Settings.DisabledSkills.Enabled");
		boolean replaceItem = this.plugin.getConfig().getBoolean("Settings.DisabledSkills.ReplaceGUIItem.Enabled");
		
		ArrayList<String> Acrobatics = new ArrayList<>();
	    for (String s : plugin.getMessageList("Menu.Acrobatics.Lore")) {
	    	if (new McMMOMethods().getSkillCap("Acrobatics") != 2147483647) {
		    	Acrobatics.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Acrobatics"))
		    			.replace("%LEVELCAP%", "" + new McMMOMethods().getSkillCap("Acrobatics"))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Acrobatics"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Acrobatics")));
	    	} else {
		    	Acrobatics.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Acrobatics"))
		    			.replace("%LEVELCAP%", new StringUtils().addColor(plugin.getMessage("NoSkillCap")))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Acrobatics"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Acrobatics")));
	    	}
		}
	    
	    if (skillDisable && replaceItem && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Acrobatics")) {
	    	Redeem.setItem(10, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.DisabledSkills.ReplaceGUIItem.ItemName"), 1, this.plugin.getMessage("Menu.Acrobatics.Name"), Acrobatics, false));
	    } else {
			Redeem.setItem(10, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.Acrobatics.ItemName"), 1, this.plugin.getMessage("Menu.Acrobatics.Name"), Acrobatics, false));
	    }
	    
		ArrayList<String> Alchemy = new ArrayList<>();
	    for (String s : plugin.getMessageList("Menu.Alchemy.Lore")) {
	    	if (new McMMOMethods().getSkillCap("Alchemy") != 2147483647) {
	    		Alchemy.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Alchemy"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Alchemy"))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Alchemy"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Alchemy")));

	    	} else {
	    		Alchemy.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Alchemy"))
		    			.replace("%LEVELCAP%", new StringUtils().addColor(plugin.getMessage("NoSkillCap")))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Alchemy"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Alchemy")));
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
	    		Archery.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Archery"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Archery"))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Archery"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Archery")));
	    	} else {
	    		Archery.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Archery"))
		    			.replace("%LEVELCAP%", "" + new StringUtils().addColor(plugin.getMessage("NoSkillCap")))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Archery"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Archery")));
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
	    		Axes.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Axes"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Axes"))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Axes"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Axes")));
	    	} else {
	    		Axes.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Axes"))
		    			.replace("%LEVELCAP%", "" + new StringUtils().addColor(plugin.getMessage("NoSkillCap")))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Axes"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Axes")));
	    	}
		}
	
	    if (skillDisable == true && replaceItem == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Axes")) {
	    	Redeem.setItem(13, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.DisabledSkills.ReplaceGUIItem.ItemName"), 1, this.plugin.getMessage("Menu.Axes.Name"), Axes, false));
	    } else {
	    	Redeem.setItem(13, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.Axes.ItemName"), 1, this.plugin.getMessage("Menu.Axes.Name"), Axes, false));
	    }

		ArrayList<String> Crossbows = new ArrayList();
		for (String s : plugin.getMessageList("Menu.Crossbows.Lore")) {
			if (new McMMOMethods().getSkillCap("Crossbows") != 2147483647) {
				Crossbows.add(new StringUtils().addColor(s)
						.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Crossbows"))
						.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Crossbows"))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Crossbows"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Crossbows")));
			} else {
				Crossbows.add(new StringUtils().addColor(s)
						.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Crossbows"))
						.replace("%LEVELCAP%", "" + new StringUtils().addColor(plugin.getMessage("NoSkillCap")))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Crossbows"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Crossbows")));
			}
		}

		if (skillDisable == true && replaceItem == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Crossbows")) {
			Redeem.setItem(14, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.DisabledSkills.ReplaceGUIItem.ItemName"), 1, this.plugin.getMessage("Menu.Crossbows.Name"), Crossbows, false));
		} else {
			Redeem.setItem(14, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.Crossbows.ItemName"), 1, this.plugin.getMessage("Menu.Crossbows.Name"), Crossbows, false));
		}
	    
		ArrayList<String> Excavation = new ArrayList();
	    for (String s : plugin.getMessageList("Menu.Excavation.Lore")) {
	    	if (new McMMOMethods().getSkillCap("Excavation") != 2147483647) {
	    		Excavation.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Excavation"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Excavation"))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Excavation"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Excavation")));
	    	} else {
	    		Excavation.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Excavation"))
		    			.replace("%LEVELCAP%", "" + new StringUtils().addColor(plugin.getMessage("NoSkillCap")))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Excavation"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Excavation")));
	    	}
		}
	
	    if (skillDisable == true && replaceItem == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Excavation")) {
	    	Redeem.setItem(19, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.DisabledSkills.ReplaceGUIItem.ItemName"), 1, this.plugin.getMessage("Menu.Excavation.Name"), Excavation, false));
	    } else {
	    	Redeem.setItem(19, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.Excavation.ItemName"), 1, this.plugin.getMessage("Menu.Excavation.Name"), Excavation, false));
	    }
	    
		ArrayList<String> Fishing = new ArrayList();
	    for (String s : plugin.getMessageList("Menu.Fishing.Lore")) {
	    	if (new McMMOMethods().getSkillCap("Fishing") != 2147483647) {
	    		Fishing.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Fishing"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Fishing"))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Fishing"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Fishing")));
	    	} else {
	    		Fishing.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Fishing"))
		    			.replace("%LEVELCAP%", "" + new StringUtils().addColor(plugin.getMessage("NoSkillCap")))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Fishing"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Fishing")));
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
	    		Herbalism.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Herbalism"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Herbalism"))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Herbalism"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Herbalism")));
	    	} else {
	    		Herbalism.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Herbalism"))
		    			.replace("%LEVELCAP%", "" + new StringUtils().addColor(plugin.getMessage("NoSkillCap")))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Herbalism"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Herbalism")));
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
	    		Mining.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Mining"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Mining"))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Mining"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Mining")));
	    	} else {
	    		Mining.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Mining"))
		    			.replace("%LEVELCAP%", "" + new StringUtils().addColor(plugin.getMessage("NoSkillCap")))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Mining"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Mining")));
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
	    		Repair.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Repair"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Repair"))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Repair"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Repair")));
	    	} else {
	    		Repair.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Repair"))
		    			.replace("%LEVELCAP%", "" + new StringUtils().addColor(plugin.getMessage("NoSkillCap")))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Repair"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Repair")));
	    	}
		}
	
	    if (skillDisable == true && replaceItem == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Repair")) {
	    	Redeem.setItem(23, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.DisabledSkills.ReplaceGUIItem.ItemName"), 1, this.plugin.getMessage("Menu.Repair.Name"), Repair, false));
	    } else {
	    	Redeem.setItem(23, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.Repair.ItemName"), 1, this.plugin.getMessage("Menu.Repair.Name"), Repair, false));
	    }
	    
		ArrayList<String> Swords = new ArrayList();
	    for (String s : plugin.getMessageList("Menu.Swords.Lore")) {
	    	if (new McMMOMethods().getSkillCap("Swords") != 2147483647) {
	    		Swords.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Swords"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Swords"))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Swords"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Swords")));
	    	} else {
	    		Swords.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Swords"))
		    			.replace("%LEVELCAP%", "" + new StringUtils().addColor(plugin.getMessage("NoSkillCap")))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Swords"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Swords")));
	    	}
		}
	
	    if (skillDisable == true && replaceItem == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Swords")) {
	    	Redeem.setItem(28, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.DisabledSkills.ReplaceGUIItem.ItemName"), 1, this.plugin.getMessage("Menu.Swords.Name"), Swords, false));
	    } else {
	    	Redeem.setItem(28, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.Swords.ItemName"), 1, this.plugin.getMessage("Menu.Swords.Name"), Swords, false));
	    }
	    
		ArrayList<String> Taming = new ArrayList();
	    for (String s : plugin.getMessageList("Menu.Taming.Lore")) {
	    	if (new McMMOMethods().getSkillCap("Taming") != 2147483647) {
	    		Taming.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Taming"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Taming"))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Taming"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Taming")));
	    	} else {
	    		Taming.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Taming"))
		    			.replace("%LEVELCAP%", "" + new StringUtils().addColor(plugin.getMessage("NoSkillCap")))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Taming"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Taming")));
	    	}
		}
	
	    if (skillDisable == true && replaceItem == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Taming")) {
	    	Redeem.setItem(29, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.DisabledSkills.ReplaceGUIItem.ItemName"), 1, this.plugin.getMessage("Menu.Taming.Name"), Taming, false));
	    } else {
	    	Redeem.setItem(29, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.Taming.ItemName"), 1, this.plugin.getMessage("Menu.Taming.Name"), Taming, false));
	    }

		ArrayList<String> Tridents = new ArrayList();
		for (String s : plugin.getMessageList("Menu.Tridents.Lore")) {
			if (new McMMOMethods().getSkillCap("Tridents") != 2147483647) {
				Tridents.add(new StringUtils().addColor(s)
						.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Tridents"))
						.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Tridents"))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Tridents"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Tridents")));
			} else {
				Tridents.add(new StringUtils().addColor(s)
						.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Tridents"))
						.replace("%LEVELCAP%", "" + new StringUtils().addColor(plugin.getMessage("NoSkillCap")))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Tridents"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Tridents")));
			}
		}

		if (skillDisable == true && replaceItem == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Tridents")) {
			Redeem.setItem(30, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.DisabledSkills.ReplaceGUIItem.ItemName"), 1, this.plugin.getMessage("Menu.Tridents.Name"), Tridents, false));
		} else {
			Redeem.setItem(30, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.Tridents.ItemName"), 1, this.plugin.getMessage("Menu.Tridents.Name"), Tridents, false));
		}
	    
		ArrayList<String> Unarmed = new ArrayList();
	    for (String s : plugin.getMessageList("Menu.Unarmed.Lore")) {
	    	if (new McMMOMethods().getSkillCap("Unarmed") != 2147483647) {
	    		Unarmed.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Unarmed"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Unarmed"))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Unarmed"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Unarmed")));
	    	} else {
	    		Unarmed.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Unarmed"))
		    			.replace("%LEVELCAP%", "" + new StringUtils().addColor(plugin.getMessage("NoSkillCap")))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Unarmed"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Unarmed")));
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
	    		Woodcutting.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Woodcutting"))
		    			.replace("%LEVELCAP%", "" + + new McMMOMethods().getSkillCap("Woodcutting"))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Woodcutting"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Woodcutting")));
	    	} else {
	    		Woodcutting.add(new StringUtils().addColor(s)
		    			.replace("%SKILLLEVEL%", "" + new McMMOMethods().getSkillLevel(player, "Woodcutting"))
		    			.replace("%LEVELCAP%", "" + new StringUtils().addColor(plugin.getMessage("NoSkillCap")))
						.replace("%SKILLXP%", "" + new McMMOMethods().getSkillXP(player, "Woodcutting"))
						.replace("%SKILLXP_NEEDED%", "" + new McMMOMethods().getSkillXPNeeded(player, "Woodcutting")));
	    	}
		}
	
	    if (skillDisable == true && replaceItem == true && this.plugin.getConfig().getStringList("Settings.DisabledSkills.SkillsToDisable").contains("Woodcutting")) {
	    	Redeem.setItem(32, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.DisabledSkills.ReplaceGUIItem.ItemName"), 1, this.plugin.getMessage("Menu.Woodcutting.Name"), Woodcutting, false));
	    } else {
	    	Redeem.setItem(32, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.Woodcutting.ItemName"), 1, this.plugin.getMessage("Menu.Woodcutting.Name"), Woodcutting, false));
	    }
	    
		ArrayList<String> mcMMOCredits = new ArrayList();
	    for (String s : plugin.getMessageList("Menu.mcMMOCredits.Lore")) {
	    	mcMMOCredits.add(new StringUtils().addColor(s)
	    			.replace("%MCMMOCREDITS%", "" + new DataManager(plugin).getData(uuid, "Credits")));
		}
	
		Redeem.setItem(16, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.mcMMOCredits.ItemName"), 1, this.plugin.getMessage("Menu.mcMMOCredits.Name"), mcMMOCredits, false));

		ArrayList<String> CreditsSpent = new ArrayList();
	    for (String s : plugin.getMessageList("Menu.CreditsSpent.Lore")) {
	    	CreditsSpent.add(new StringUtils().addColor(s)
	    			.replace("%CREDITSSPENT%", "" + new DataManager(plugin).getData(uuid, "Credits_Spent")));
		}
	
		Redeem.setItem(25, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.CreditsSpent.ItemName"), 1, this.plugin.getMessage("Menu.CreditsSpent.Name"), CreditsSpent, false));

		ArrayList<String> PluginCredits = new ArrayList();
	    for (String s : plugin.getMessageList("Menu.PluginCredits.Lore")) {
			if (s.contains("%VERSION%")) {
				PluginCredits.add(new StringUtils().addColor(s.replace("%VERSION%", this.plugin.getDescription().getVersion())));
			} else {
				PluginCredits.add(new StringUtils().addColor(s));
			}
		}
	    
	    int glassInt = this.plugin.getConfig().getInt("Settings.BackgroundGlassColor");
	
	    if (this.plugin.getConfig().getBoolean("Settings.PluginCredits.Enabled") == true) {
	    	Redeem.setItem(34, this.plugin.createInventoryItem(this.plugin.getConfig().getString("Settings.GUI.PluginCredits.ItemName"), 1, this.plugin.getMessage("Menu.PluginCredits.Name"), PluginCredits, false));	
	    } else {
	    	Redeem.setItem(34, this.plugin.createInventoryItem("BLACK_STAINED_GLASS_PANE", 1, " ", null, false));
	    }
		
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
		Redeem.setItem(37, this.plugin.createInventoryItem("BLACK_STAINED_GLASS_PANE", 1, " ", null, false));
		Redeem.setItem(38, this.plugin.createInventoryItem("BLACK_STAINED_GLASS_PANE", 1, " ", null, false));
		Redeem.setItem(39, this.plugin.createInventoryItem("BLACK_STAINED_GLASS_PANE", 1, " ", null, false));
		Redeem.setItem(40, this.plugin.createInventoryItem("BLACK_STAINED_GLASS_PANE", 1, " ", null, false));
		Redeem.setItem(41, this.plugin.createInventoryItem("BLACK_STAINED_GLASS_PANE", 1, " ", null, false));
		Redeem.setItem(43, this.plugin.createInventoryItem("BLACK_STAINED_GLASS_PANE", 1, " ", null, false));
		
		player.openInventory(Redeem);
	}
}
