package net.naturva.morphie.mr;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import net.naturva.morphie.mr.events.PlayerFileEvent;
import net.naturva.morphie.mr.events.chat.RedeemChatEvent;
import net.naturva.morphie.mr.events.menus.RedeemMenuEvent;
import net.naturva.morphie.mr.files.Messages;
import net.naturva.morphie.mr.util.MetricsLite;
import net.naturva.morphie.mr.util.MorphRedeemExpansion;
import net.naturva.morphie.mr.util.Database.MySQLConnection;

public class MorphRedeem extends JavaPlugin implements Listener {
	
	public static Logger log = Logger.getLogger("Minecraft");
	public Messages messagescfg;
	public HashMap<Player, String> addCredits = new HashMap<Player, String>();
	public String Version = "1.2.2";
	
	private PlayerFileEvent pe;
	private RedeemMenuEvent me;
	private RedeemChatEvent ce;
	
	public void onEnable() {
		this.pe = new PlayerFileEvent(this);
		this.me = new RedeemMenuEvent(this);
		this.ce = new RedeemChatEvent(this);
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(this.pe, this);
		getServer().getPluginManager().registerEvents(this.me, this);
		getServer().getPluginManager().registerEvents(this.ce, this);
		
		getCommand("mr").setExecutor(new Commands(this));
		
        new MetricsLite(this);
	    
	    getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[----------[&3MorphRedeem&8]----------]"));
	    getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&bVersion&8: &a" + this.Version));
		createConfig();
		loadConfigManager();
		if (this.getConfig().getString("StorageMethod").equals("MySQL")) {
			new MySQLConnection(this).mysqlSetup();
			new MySQLConnection(this).checkStructure();
			getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStorage Type&8: &aMySQL"));
		} else {
			getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStorage Type&8: &aYML"));
		}
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
        	new MorphRedeemExpansion(this).register();
        	getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&bPlaceholderAPI&8: &aHooked"));
        } else {
        	getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&bPlaceholderAPI&8: &cNot found"));
        }
	    getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&bPlugin Status&8: &aEnabled"));
	    getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[----------[&3MorphRedeem&8]----------]"));
	}
	
	public void onDisable(){
		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[----------[&3MorphRedeem&8]----------]"));
		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&bVersion&8: &a" + this.Version));
	    getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&bPlugin Status&8: &cDisabled"));
	    getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[----------[&3MorphRedeem&8]----------]"));
	}
	
	private void createConfig() {
		try {
			if (!getDataFolder().exists()) {
				getDataFolder().mkdirs();
			}
			File file = new File(getDataFolder(), "config.yml");
			if (!file.exists()) {
				getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&bConfig&8: &aGenerating config."));
				getConfig().options().copyDefaults(true);
				saveDefaultConfig();
			} else {
				getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&bConfig&8: &aLoading config."));
			}
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
    public void loadConfigManager() {
        this.messagescfg = new Messages(this);
        this.messagescfg.setup();
      }
  	
    public ItemStack createInventoryItem(String paramString1, int paramInt, String paramString2, ArrayList<String> paramArrayList, boolean paramBoolean) {
    	ItemStack localItemStack = new ItemStack(Material.matchMaterial(paramString1), paramInt);
    	ItemMeta localItemMeta = localItemStack.getItemMeta();
    	localItemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
    	localItemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });
    	localItemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_DESTROYS });
    	if (paramBoolean) {
    		localItemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
    		localItemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
    	}
    	localItemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', paramString2));
    	localItemMeta.setLore(paramArrayList);
    	localItemStack.setItemMeta(localItemMeta);
    	return localItemStack;
    }
    
    public ItemStack createInventoryGlassItem(String paramString1, int glassInt, int paramInt, String paramString2, ArrayList<String> paramArrayList, boolean paramBoolean) {
    	ItemStack localItemStack = new ItemStack(Material.matchMaterial(paramString1), paramInt, (short) glassInt);
    	ItemMeta localItemMeta = localItemStack.getItemMeta();
    	if (paramBoolean) {
    		localItemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
    		localItemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
    	}
    	localItemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', paramString2));
    	localItemMeta.setLore(paramArrayList);
    	localItemStack.setItemMeta(localItemMeta);
    	return localItemStack;
    }

	public String getMessage(String string) {
		String gotString = this.messagescfg.messagesCFG.getString(string);
		if (gotString != null) return gotString;
		return "Null message";
	}
	
	public List<String> getMessageList(String string) {
		return this.messagescfg.messagesCFG.getStringList(string);
	}
}
