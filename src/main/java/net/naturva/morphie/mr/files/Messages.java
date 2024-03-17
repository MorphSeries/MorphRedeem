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
	private MorphRedeem plugin;
	public FileConfiguration messagesCFG;
	public File messagesFile;

	public Messages(MorphRedeem plugin) {
		this.plugin = plugin;
	}
	  
	public void setup() {
		if (!this.plugin.getDataFolder().exists()) {
			this.plugin.getDataFolder().mkdir();
	    }
	    this.messagesFile = new File(this.plugin.getDataFolder(), "messages.yml");
	    if (!this.messagesFile.exists()) {
	    	try {
	    		this.messagesFile.createNewFile();
	        
	    		this.messagesCFG = YamlConfiguration.loadConfiguration(this.messagesFile);

	    		this.addDefaults(this.messagesCFG);

	    		this.messagesCFG.options().copyDefaults(true);
	    		saveMessages();
	    	} catch (IOException e) {
	    		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not create the messages.yml file");
	    	}
	    }
	    this.messagesCFG = YamlConfiguration.loadConfiguration(this.messagesFile);

	    this.addDefaults(this.messagesCFG);
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
    	this.addDefaults(this.messagesCFG);
    }
    
    private void addDefaults(FileConfiguration cfg) {
		cfg.addDefault("Commands.Header", "&7----------- &9&lMorphRedeem Commands &7----------");
		cfg.addDefault("Commands.Footer", "&7--------------------- &8[&9&l!&8] &7---------------------");
		cfg.addDefault("Commands.Help", "&b/mr help &8- &7Shows this text menu.");
		cfg.addDefault("Commands.MR", "&b/mr &8- &7Opens the redeem menu.");
		cfg.addDefault("Commands.MRSkill", "&b/mr <skill> <num> &8- &7Redeem credits into a specified skill.");
		cfg.addDefault("Commands.Credits", "&b/mr credits &8- &7Shows your credit count.");
		cfg.addDefault("Commands.CreditsOthers", "&b/mr credits <player> &8- &7See a players credit count.");
		cfg.addDefault("Commands.Send", "&b/mr send <player> <num> &8- &7Send a player credits.");
		cfg.addDefault("Commands.Add", "&9[Admin] &b/mr add <player> <num> &8- &7Add credits to a players credit balance.");
		cfg.addDefault("Commands.Remove", "&9[Admin] &b/mr remove <player> <num>&8- &7Remove credits to a players credit balance.");
		cfg.addDefault("Commands.Reload", "&9[Admin] &b/mr reload &8- &7Reloads the plugins files.");
		cfg.addDefault("Commands.Set", "&9[Admin] &b/mr set <player> <num> &8- &7Set a players credit balance.");
		cfg.addDefault("Commands.Reset", "&9[Admin] &b/mr reset <player> &8- &7Reset a players credit balance.");
		cfg.addDefault("CorrectUsage.Add", "&bCorrect Ussage&8: &7/mr add <player> <number>");
		cfg.addDefault("CorrectUsage.Remove", "&bCorrect Ussage&8: &7/mr remove <player> <number>");
		cfg.addDefault("CorrectUsage.Reset", "&bCorrect Ussage&8: &7/mr reset <player>");
		cfg.addDefault("CorrectUsage.Set", "&bCorrect Ussage&8: &7/mr set <player> <number>");
		cfg.addDefault("CorrectUsage.Send", "&bCorrect Ussage&8: &7/mr send <player> <number>");
		cfg.addDefault("CreditAddMessage", "&7You have been given &b%CREDITS% &7credits!");
		cfg.addDefault("CreditAddSuccessMessage", "&7Credit assignment successfull!");
		cfg.addDefault("CreditAssignmentCanceled", "&7Credit assignment canceled successfully!");
		cfg.addDefault("CreditAssignmentMessage", "&7Please specify the amount of credits you would like to add. Write 0 in chat to cancel! &8(&bCredits&8: &7%CREDITS%&8)");
		cfg.addDefault("CreditAssignmentSuccess", "&7You successfully applied &b%CREDITS%&7 credits, to the &b%SKILL% &7skill!");
		cfg.addDefault("CreditInProgressMessage", "&7You're currently assigning credits to &b%SKILL%&7. Write 0 in chat to cancel! &8(&bCredits&8: &7%CREDITS%&8)");
		cfg.addDefault("CreditRemoveMessage", "&7&b%CREDITS% &7credits have been removed from you!");
		cfg.addDefault("CreditRemoveSuccessMessage", "&7Credit removal successfull!");
		cfg.addDefault("CreditResetMessage", "&7Your credits have been reset!");
		cfg.addDefault("CreditResetSuccessMessage", "&7Credits successfully reset!");
		cfg.addDefault("CreditSendMessage", "&7You have been sent &b%CREDITS% &7credits from &b%SENDER%&7.");
		cfg.addDefault("CreditSendSuccessMessage", "&7You sent &b%CREDITS% &7credits to &b%TARGET%&7.");
		cfg.addDefault("CreditSetMessage", "&7Your credits have been set to &b%CREDITS%&7.");
		cfg.addDefault("CreditSetSuccessMessage", "&7Credits successfull set!");
		cfg.addDefault("ErrorPrefix", "&8[&9&l!&8] ");
		cfg.addDefault("IgnoreFormat", "[X]");
		cfg.addDefault("InvalidArgsMessage", "&7Invalid arguments! &b/mr help &7to view all commands.");
		cfg.addDefault("InvalidCredits", "&7You do not have the valid credits for this! Canceling credit assignment.");
		cfg.addDefault("InvalidNumber", "&7The message entered was not recognized as a number! Canceling credit assignment.");
		cfg.addDefault("InvalidNumberNegative", "&7The number you entered was not positive! Canceling credit assignment.");
		cfg.addDefault("InvalidPlayer", "&7Cannot find that player!");
		cfg.addDefault("InvalidSkill", "&7The argument entered was not recognized as a skill!");

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
		list4.add("&9&lVersion&8: &7%VERSION%");
		list4.add(" ");
		list4.add("&bCode Contributors&8:");
		list4.add("&8- &7Morphie");
		list4.add("&8- &7therbz");
		list4.add("&8- &7Eleksploded");
		list4.add(" ");
		list4.add("&b&oClick for spigot link!");

		cfg.addDefault("McMMOPlayerNotLoadedMessage", "&7Your &bmcMMO player file &7has not been loaded yet! Please try again in a &bfew seconds&7.");
		cfg.addDefault("Menu.Title", "&9&lMorphRedeem&8&l:");
		cfg.addDefault("Menu.Acrobatics.Name", "&9&lAcrobatics&8&l:");
		cfg.addDefault("Menu.Acrobatics.Lore", list);
		cfg.addDefault("Menu.Alchemy.Name", "&9&lAlchemy&8&l:");
		cfg.addDefault("Menu.Alchemy.Lore", list);
		cfg.addDefault("Menu.Archery.Name", "&9&lArchery&8&l:");
		cfg.addDefault("Menu.Archery.Lore", list);
		cfg.addDefault("Menu.Axes.Name", "&9&lAxes&8&l:");
		cfg.addDefault("Menu.Axes.Lore", list);
		cfg.addDefault("Menu.Excavation.Name", "&9&lExcavation&8&l:");
		cfg.addDefault("Menu.Excavation.Lore", list);
		cfg.addDefault("Menu.Fishing.Name", "&9&lFishing&8&l:");
		cfg.addDefault("Menu.Fishing.Lore", list);
		cfg.addDefault("Menu.Herbalism.Name", "&9&lHerbalism&8&l:");
		cfg.addDefault("Menu.Herbalism.Lore", list);
		cfg.addDefault("Menu.Mining.Name", "&9&lMining&8&l:");
		cfg.addDefault("Menu.Mining.Lore", list);
		cfg.addDefault("Menu.Repair.Name", "&9&lRepair&8&l:");
		cfg.addDefault("Menu.Repair.Lore", list);
		cfg.addDefault("Menu.Swords.Name", "&9&lSwords&8&l:");
		cfg.addDefault("Menu.Swords.Lore", list);
		cfg.addDefault("Menu.Taming.Name", "&9&lTaming&8&l:");
		cfg.addDefault("Menu.Taming.Lore", list);
		cfg.addDefault("Menu.Unarmed.Name", "&9&lUnarmed&8&l:");
		cfg.addDefault("Menu.Unarmed.Lore", list);
		cfg.addDefault("Menu.Woodcutting.Name", "&9&lWoodcutting&8&l:");
		cfg.addDefault("Menu.Woodcutting.Lore", list);
		cfg.addDefault("Menu.mcMMOCredits.Name", "&9&lmcMMO Credits&8&l:");
		cfg.addDefault("Menu.mcMMOCredits.Lore", list2);
		cfg.addDefault("Menu.CreditsSpent.Name", "&9&lCredits Spent&8&l:");
		cfg.addDefault("Menu.CreditsSpent.Lore", list3);
		cfg.addDefault("Menu.PluginCredits.Name", "&9&lPlugin Credits&8&l:");
		cfg.addDefault("Menu.PluginCredits.Lore", list4);
		cfg.addDefault("NoPermsMessage", "&7You don't have permission to do this!");
		cfg.addDefault("NoSkillCap", "&bNone");
		cfg.addDefault("OtherPlayerCreditMessage", "&b%PLAYER% &7currently has &b%CREDITS% &7credits.");
		cfg.addDefault("PlayerCreditsMessage", "&7You currently have &b%CREDITS% &7credits.");
		cfg.addDefault("Prefix", "&9&lMorphRedeem &8&l➙ ");
		cfg.addDefault("ReloadMessage", "&7Plugin files successfully reloaded!");
		cfg.addDefault("SkillCapReached", "&7You tried to get to level &b%LEVEL% &7in &b%SKILL%&7, but the skill cap is &b%CAP%&7.");
		cfg.addDefault("SkillDisabledMessage", "&7This skill has been disabled by administration!");
		cfg.addDefault("SpigotLink", "&7https://www.spigotmc.org/resources/morphredeem-mcmmo-credits-1-14.67435/");
	}
}
