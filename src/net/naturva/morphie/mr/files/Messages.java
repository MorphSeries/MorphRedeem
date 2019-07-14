package net.naturva.morphie.mr.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;

import net.naturva.morphie.mr.MorphRedeem;


public class Messages implements Listener {
	private MorphRedeem plugin = (MorphRedeem)MorphRedeem.getPlugin(MorphRedeem.class);
	public FileConfiguration messagesCFG;
	public File messagesFile;
	  
	public void setup() {
		if (!this.plugin.getDataFolder().exists()) {
			this.plugin.getDataFolder().mkdir();
	    }
	    this.messagesFile = new File(this.plugin.getDataFolder(), "messages.yml");
	    if (!this.messagesFile.exists()) {
	    	try {
	    		this.messagesFile.createNewFile();
	        
	    		this.messagesCFG = YamlConfiguration.loadConfiguration(this.messagesFile);
	    		
	    		this.messagesCFG.addDefault("Commands.Header", "&8&m]---------+&r&8[ &9&lMorphRedeem &8]&8&m+---------[");
	    		this.messagesCFG.addDefault("Commands.Footer", "&8&m]---------------+&r &8[&9&l!&8] &8&m+---------------[");
	    		this.messagesCFG.addDefault("Commands.Help", "&b/mr help &8- &7Shows this text menu.");
	    		this.messagesCFG.addDefault("Commands.MR", "&b/mr &8- &7Opens the redeem menu.");
	    		this.messagesCFG.addDefault("Commands.MRSkill", "&b/mr <skill> <num> &8- &7Redeem credits into a specified skill.");
	    		this.messagesCFG.addDefault("Commands.Credits", "&b/mr credits &8- &7Shows your credit count.");
	    		this.messagesCFG.addDefault("Commands.Add", "&9[Admin] &b/mr add <player> <num> &8- &7Add credits to a players credit balance.");
	    		this.messagesCFG.addDefault("Commands.Remove", "&9[Admin] &b/mr remove <player> <num>&8- &7Remove credits to a players credit balance.");
	    		this.messagesCFG.addDefault("Commands.Reload", "&9[Admin] &b/mr reload &8- &7Reloads the plugins files.");
	    		this.messagesCFG.addDefault("CorrectUsage.Add", "&bCorrect Ussage&8: &7/mr add <player> <number>");
	    		this.messagesCFG.addDefault("CorrectUsage.Remove", "&bCorrect Ussage&8: &7/mr remove <player> <number>");
	    		this.messagesCFG.addDefault("CreditAddMessage", "&7You have been given &b%CREDITS% &7credits!");
	    		this.messagesCFG.addDefault("CreditRemoveMessage", "&7&b%CREDITS% &7credits have been removed from you!");
	    		this.messagesCFG.addDefault("CreditAddSuccessMessage", "&7Credit assignment successfull!");
	    		this.messagesCFG.addDefault("CreditRemoveSuccessMessage", "&7Credit removal successfull!");
	    		this.messagesCFG.addDefault("CreditAssignmentCanceled", "&7Credit assignment canceled successfully!");
	    		this.messagesCFG.addDefault("CreditAssignmentMessage", "&7Please specify the ammount of credits you would like to add. Write 0 in chat to cancel! &8(&bCredits&8: &7%CREDITS%&8)");
	    		this.messagesCFG.addDefault("CreditAssignmentSuccess", "&7You successfully applied &b%CREDITS%&7 credits, to the &b%SKILL% &7skill!");
	    		this.messagesCFG.addDefault("CreditInProgressMessage", "&7You're currently assigning credits to &b%SKILL%&7. Write 0 in chat to cancel! &8(&bCredits&8: &7%CREDITS%&8)");
	    		this.messagesCFG.addDefault("ErrorPrefix", "&8[&9&l!&8] ");
	    		this.messagesCFG.addDefault("IgnoreFormat", "[X]");
	    		this.messagesCFG.addDefault("InvalidArgsMessage", "&7Invalid arguments! &b/mr help &7to view all commands.");
	    		this.messagesCFG.addDefault("InvalidCredits", "&7You do not have the valid credits for this! Canceling credit assignment.");
	    		this.messagesCFG.addDefault("InvalidNumber", "&7The message entered was not recognized as a number! Canceling credit assignment.");
	    		this.messagesCFG.addDefault("InvalidNumberNegative", "&7The number you entered was not positive! Canceling credit assignment.");
	    		this.messagesCFG.addDefault("InvalidPlayer", "&7Cannot find that player!");
	    		this.messagesCFG.addDefault("InvalidSkill", "&7The argument entered was not recognized as a skill!");
	   
	    		List<String> list = new ArrayList<String>();
	    		list.add(" ");
	    		list.add("&7Click to assign credits!");
	    		list.add(" ");
	    		list.add("&b➙ &7Level Cap&8: &7%LEVELCAP%");
	    		list.add("&b➙ &7Skill Level&8: &7%SKILLLEVEL%");
	    		
	    		List<String> list2 = new ArrayList<String>();
	    		list2.add("&b➙ &7%MCMMOCREDITS%");
	    		
	    		List<String> list3 = new ArrayList<String>();
	    		list3.add("&b➙ &7%CREDITSSPENT%");
	    		
	    		List<String> list4 = new ArrayList<String>();
	    		list4.add("&9&lVersion&8: &7" + this.plugin.Version);
	    		list4.add(" ");
	    		list4.add("&bCode Contributors&8:");
	    		list4.add("&8- &7Morphie");
	    		list4.add(" ");
	    		list4.add("&b&oClick for spigot link!");
	    		
	    		this.messagesCFG.addDefault("Menu.Title", "&9&lMorphRedeem&8&l:");
	    		this.messagesCFG.addDefault("Menu.Acrobatics.Name", "&9&lAcrobatics&8&l:");
	    		this.messagesCFG.addDefault("Menu.Acrobatics.Lore", list);
	    		this.messagesCFG.addDefault("Menu.Alchemy.Name", "&9&lAlchemy&8&l:");
	    		this.messagesCFG.addDefault("Menu.Alchemy.Lore", list);
	    		this.messagesCFG.addDefault("Menu.Archery.Name", "&9&lArchery&8&l:");
	    		this.messagesCFG.addDefault("Menu.Archery.Lore", list);
	    		this.messagesCFG.addDefault("Menu.Axes.Name", "&9&lAxes&8&l:");
	    		this.messagesCFG.addDefault("Menu.Axes.Lore", list);
	    		this.messagesCFG.addDefault("Menu.Excavation.Name", "&9&lExcavation&8&l:");
	    		this.messagesCFG.addDefault("Menu.Excavation.Lore", list);
	    		this.messagesCFG.addDefault("Menu.Fishing.Name", "&9&lFishing&8&l:");
	    		this.messagesCFG.addDefault("Menu.Fishing.Lore", list);
	    		this.messagesCFG.addDefault("Menu.Herbalism.Name", "&9&lHerbalism&8&l:");
	    		this.messagesCFG.addDefault("Menu.Herbalism.Lore", list);
	    		this.messagesCFG.addDefault("Menu.Mining.Name", "&9&lMining&8&l:");
	    		this.messagesCFG.addDefault("Menu.Mining.Lore", list);
	    		this.messagesCFG.addDefault("Menu.Repair.Name", "&9&lRepair&8&l:");
	    		this.messagesCFG.addDefault("Menu.Repair.Lore", list);
	    		this.messagesCFG.addDefault("Menu.Swords.Name", "&9&lSwords&8&l:");
	    		this.messagesCFG.addDefault("Menu.Swords.Lore", list);
	    		this.messagesCFG.addDefault("Menu.Taming.Name", "&9&lTaming&8&l:");
	    		this.messagesCFG.addDefault("Menu.Taming.Lore", list);
	    		this.messagesCFG.addDefault("Menu.Unarmed.Name", "&9&lUnarmed&8&l:");
	    		this.messagesCFG.addDefault("Menu.Unarmed.Lore", list);
	    		this.messagesCFG.addDefault("Menu.Woodcutting.Name", "&9&lWoodcutting&8&l:");
	    		this.messagesCFG.addDefault("Menu.Woodcutting.Lore", list);
	    		this.messagesCFG.addDefault("Menu.mcMMOCredits.Name", "&9&lmcMMO Credits&8&l:");
	    		this.messagesCFG.addDefault("Menu.mcMMOCredits.Lore", list2);
	    		this.messagesCFG.addDefault("Menu.CreditsSpent.Name", "&9&lCredits Spent&8&l:");
	    		this.messagesCFG.addDefault("Menu.CreditsSpent.Lore", list3);
	    		this.messagesCFG.addDefault("Menu.PluginCredits.Name", "&9&lPlugin Credits&8&l:");
	    		this.messagesCFG.addDefault("Menu.PluginCredits.Lore", list4);
	    		this.messagesCFG.addDefault("NoPermsMessage", "&7You don't have permission to do this!");
	    		this.messagesCFG.addDefault("NoSkillCap", "&bNone");
	    		this.messagesCFG.addDefault("PlayerCreditsMessage", "&7You currently have &b%CREDITS% &7credits.");
	    		this.messagesCFG.addDefault("Prefix", "&9&lMorphRedeem &8&l➙ ");
	    		this.messagesCFG.addDefault("ReloadMessage", "&7Plugin files successfully reloaded!");
	    		this.messagesCFG.addDefault("SkillCapReached", "&7You tried to get to level &b%LEVEL% &7in &b%SKILL%&7, but the skill cap is &b%CAP%&7.");
	    		this.messagesCFG.addDefault("SkillDisabledMessage", "&7This skill has been disabled by administration!");
	    		this.messagesCFG.addDefault("SpigotLink", "&7https://www.spigotmc.org/resources/morphredeem-mcmmo-credits-1-14.67435/");
	        
	    		this.messagesCFG.options().copyDefaults(true);
	    		saveMessages();
	    	} catch (IOException e) {
	    		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not create the messages.yml file");
	    	}
	    }
	    this.messagesCFG = YamlConfiguration.loadConfiguration(this.messagesFile);
	}
	  
    public void saveMessages() {
    	try {
    		this.messagesCFG.save(this.messagesFile);
    	} catch (IOException e) {
    		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not save the messages.yml file");
    	}
    }
	  
    public void reloadMessages() {
    	this.messagesCFG = YamlConfiguration.loadConfiguration(this.messagesFile);
    }
    
}
