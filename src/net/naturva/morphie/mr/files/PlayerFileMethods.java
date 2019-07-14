package net.naturva.morphie.mr.files;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.naturva.morphie.mr.MorphRedeem;


public class PlayerFileMethods {
	private MorphRedeem plugin;
	  
	public PlayerFileMethods(MorphRedeem plugin) {
		this.plugin = plugin;
	}
	
  public String getStat(UUID uuid, String string) {
    File file = getPlayerFile(uuid);
    FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
    return fc.getString(string);
  }
  
  public boolean getBoolean(UUID uuid, String string) {
	    File file = getPlayerFile(uuid);
	    FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
	    return fc.getBoolean(string);
   }
  
  public ItemStack getMaterial(UUID uuid, String string) {
	    File file = getPlayerFile(uuid);
	    FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
	    return fc.getItemStack(string);
 }
  
  public File getPlayerFile(UUID uuid) {
    File playerFile = new File(this.plugin.getDataFolder() + File.separator + "PlayerData", uuid + ".yml");
    FileConfiguration playerCFG = YamlConfiguration.loadConfiguration(playerFile);
    if (!playerFile.exists()) {
      try {
        playerCFG.save(playerFile);
      }
      catch (IOException e1) {
        e1.printStackTrace();
      }
    }
    return playerFile;
  }
  
	public void setData(UUID uuid, String string, int i) {
	    File file = getPlayerFile(uuid);
	    FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
	    fc.set(string, Integer.valueOf(fc.getInt(string) + i));
	    try
	    {
	      fc.save(file);
	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	    }
	}
	
	public void setBoolean(UUID uuid, String string, Boolean b) {
	    File file = getPlayerFile(uuid);
	    FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
	    fc.set(string, Boolean.valueOf(b));
	    try
	    {
	      fc.save(file);
	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	    }
	}
	
	public void updateCredits(UUID uuid, String string, int i) {
	    File file = getPlayerFile(uuid);
	    FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
	    fc.set(string, Integer.valueOf(fc.getInt(string) + i));
	    try
	    {
	      fc.save(file);
	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	    }
	}
}