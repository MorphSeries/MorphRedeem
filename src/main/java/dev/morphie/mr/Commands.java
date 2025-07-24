package dev.morphie.mr;

import java.io.File;
import java.util.UUID;

import dev.morphie.mr.files.Messages;
import dev.morphie.mr.util.CreditConversion;
import dev.morphie.mr.util.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.api.exceptions.McMMOPlayerNotFoundException;

import dev.morphie.mr.menus.RedeemMenu;
import dev.morphie.mr.util.McMMOMethods;
import dev.morphie.mr.util.DataManager;

public class Commands implements CommandExecutor {
	
	private MorphRedeem plugin;
	  
	public Commands(MorphRedeem plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("morphredeem") || cmd.getName().equalsIgnoreCase("redeem") || cmd.getName().equalsIgnoreCase("mr")) {
			if (args.length == 0) {
				Player player = (Player)sender;
				if (!player.isSleeping()) {
					if (sender.hasPermission("morphredeem.redeem")) {
						try {
							new RedeemMenu(this.plugin).openGUIRedeem(player);
							return true;
						} catch (McMMOPlayerNotFoundException e1) {
							sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("McMMOPlayerNotLoadedMessage")));
							return true;
						}
					} else {
						sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("NoPermsMessage")));
						return true;
					}
				} else {
					return true;
				}
			} else if (args[0].equalsIgnoreCase("help")) {
				if (sender.hasPermission("morphredeem.help")) {
					sender.sendMessage("");
					sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Commands.Header")));
					sender.sendMessage("");
					sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Commands.Help")));
					sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Commands.MR")));
					sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Commands.MRSkill")));
					sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Commands.Credits")));
					sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Commands.CreditsOthers")));
					sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Commands.Send")));
					sender.sendMessage("");
					if (sender.hasPermission("morphredeem.admin") || sender.hasPermission("morphredeem.reload")) {
						sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Commands.Add")));
						sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Commands.Remove")));
						sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Commands.Set")));
						sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Commands.Reset")));
						sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Commands.Reload")));
					}
					sender.sendMessage("");
					sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Commands.Footer")));
					sender.sendMessage("");
					return true;
				} else {
					sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("NoPermsMessage")));
					return true;
				}
			} else if (args[0].equalsIgnoreCase("Credits")) {
				Player player = (Player)sender;
				UUID uuid = player.getUniqueId();
				if (args.length == 1) {
					if (sender.hasPermission("morphredeem.credits")) {
						sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("PlayerCreditsMessage").replace("%CREDITS%", new DataManager(plugin).getData(uuid, "Credits"))));
						return true;
					} else {
						sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("NoPermsMessage")));
						return true;
					}
				} else if (args.length == 2) {
					if (sender.hasPermission("morphredeem.credits.others")) {
						Player target = null;
						OfflinePlayer offTarget = null;
						if (Bukkit.getPlayer(args[1]) != null) {
							target = Bukkit.getPlayer(args[1]);
							String targetCredits = new DataManager(plugin).getData(target.getUniqueId(), "Credits");
							sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("OtherPlayerCreditMessage").replace("%PLAYER%", target.getName()).replace("%CREDITS%", targetCredits)));
							return true;
						} else {
							offTarget = (OfflinePlayer)Bukkit.getServer().getOfflinePlayer(args[1]);
							if (getFileExists(offTarget.getUniqueId())) {
								String targetCredits = new DataManager(plugin).getData(offTarget.getUniqueId(), "Credits");
								sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("OtherPlayerCreditMessage").replace("%PLAYER%", offTarget.getName()).replace("%CREDITS%", targetCredits)));
								return true;	
							} else {
								sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidPlayer")));
								return true;
							}
						}
					} else {
						sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("NoPermsMessage")));
						return true;
					}
				}
			} else if (args[0].equalsIgnoreCase("Add")) {
				if (sender.hasPermission("morphredeem.admin") || sender.hasPermission("morphredeem.addcredits")) {
					if (args.length != 3) {
						sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Add")));
						return true;
					}
		        	try {
		        		Integer.parseInt(args[2]);
		            }
		            catch (NumberFormatException e) {
		            	sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Add")));
		            	return true;
		            }
		        	int amount = Integer.parseInt(args[2]);
		            if (amount <= 0) {
		            	sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Add")));
		            	return true;
		            }
					Player target = null;
					OfflinePlayer offTarget = null;
					UUID targetUUID = null;
					if (Bukkit.getPlayer(args[1]) != null) {
			        	target = Bukkit.getPlayer(args[1]);
			        	targetUUID = target.getUniqueId();
					} else if (checkIfUUID(args[1]) == true) {
			        	targetUUID = UUID.fromString(args[1]);
			        	target = Bukkit.getPlayer(targetUUID);
						if (getFileExists(targetUUID)) {
							new DataManager(plugin).updateData(targetUUID, +amount, "Credits", "add");
			            	sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAddSuccessMessage")));
			            	target.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAddMessage").replace("%CREDITS%", "" + amount)));
			            	return true;
						} else {
							sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidPlayer")));
							return true;
						}
			        } else if (checkIfUUID(args[1]) == false && Bukkit.getPlayer(args[1]) == null) {
						offTarget = (OfflinePlayer)Bukkit.getServer().getOfflinePlayer(args[1]);
						targetUUID = offTarget.getUniqueId();
						if (getFileExists(targetUUID)) {
							new DataManager(plugin).updateData(targetUUID, +amount, "Credits", "add");
			            	sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAddSuccessMessage")));
							return true;
						} else {
							sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidPlayer")));
							return true;
						}
			        }
					new DataManager(plugin).updateData(targetUUID, +amount, "Credits", "add");
		            if (sender == target) {
		            	target.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAddMessage").replace("%CREDITS%", "" + amount)));
		            	return true;
		            } else {
		            	sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAddSuccessMessage")));
		            	target.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAddMessage").replace("%CREDITS%", "" + amount)));
		            	return true;
		            }
		        } else {
					sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("NoPermsMessage")));
					return true;
				}
			} else if (args[0].equalsIgnoreCase("remove")) {
				if (sender.hasPermission("morphredeem.admin") || sender.hasPermission("morphredeem.removecredits")) {
					if (args.length != 3) {
						sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Remove")));
						return true;
					}
			        int amount = Integer.parseInt(args[2]);
		        	try {
		        		Integer.parseInt(args[2]);
		            }
		            catch (NumberFormatException e) {
		            	sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Remove")));
		            	return true;
		            }
		            if (amount <= 0) {
						sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Remove")));
		            	return true;
		            }
					Player target = null;
					OfflinePlayer offTarget = null;
					UUID targetUUID = null;
					int credits = 0;
					if (Bukkit.getPlayer(args[1]) != null) {
			        	target = Bukkit.getPlayer(args[1]);
			        	targetUUID = target.getUniqueId();
			        	credits = Integer.parseInt(new DataManager(plugin).getData(targetUUID, "Credits"));
			            if (amount > credits) {
							sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidOtherPlayerCredits").replace("%PLAYER%", target.getName()).replace("%CREDITS%", "" + credits)));
			            	return true; 
			            }
					} else if (checkIfUUID(args[1]) == true) {
			        	targetUUID = UUID.fromString(args[1]);
			        	target = Bukkit.getPlayer(targetUUID);
			        	credits = Integer.parseInt(new DataManager(plugin).getData(targetUUID, "Credits"));
			            if (amount > credits) {
							sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidOtherPlayerCredits").replace("%PLAYER%", target.getName()).replace("%CREDITS%", "" + credits)));
			            	return true; 
			            }
						if (getFileExists(targetUUID)) {
							new DataManager(plugin).updateData(targetUUID, -amount, "Credits", "remove");
			            	sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditRemoveSuccessMessage")));
							return true;
						} else {
							sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidPlayer")));
							return true;
						}
			        } else if (checkIfUUID(args[1]) == false && Bukkit.getPlayer(args[1]) == null) {
						offTarget = (OfflinePlayer)Bukkit.getServer().getOfflinePlayer(args[1]);
						targetUUID = offTarget.getUniqueId();
						credits = Integer.parseInt(new DataManager(plugin).getData(targetUUID, "Credits"));
			            if (amount > credits) {
							sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidOtherPlayerCredits").replace("%PLAYER%", target.getName()).replace("%CREDITS%", "" + credits)));
			            	return true; 
			            }
						if (getFileExists(targetUUID)) {
							new DataManager(plugin).updateData(targetUUID, -amount, "Credits", "remove");
			            	sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditRemoveSuccessMessage")));
							return true;
						} else {
							sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidPlayer")));
							return true;
						}
			        }
		            if (amount > credits) {
						sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidOtherPlayerCredits").replace("%PLAYER%", target.getName()).replace("%CREDITS%", "" + credits)));
		            	return true; 
		            }
		            new DataManager(plugin).updateData(targetUUID, -amount, "Credits", "remove");
		            if (sender == target) {
		            	target.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditRemoveMessage").replace("%CREDITS%", "" + amount)));
		            	return true;
		            } else {
		            	sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditRemoveSuccessMessage")));
		            	target.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditRemoveMessage").replace("%CREDITS%", "" + amount)));
		            	return true;
		            }
		        } else {
					sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("NoPermsMessage")));
					return true;
				}	
			} else if (args[0].equalsIgnoreCase("Set")) {
				if (sender.hasPermission("morphredeem.setcredits")) {
					if (args.length != 3) {
						sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Set")));
						return true;
					}
		        	try {
		        		Integer.parseInt(args[2]);
		            }
		            catch (NumberFormatException e) {
		            	sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Set")));
		            	return true;
		            }
		        	int amount = Integer.parseInt(args[2]);
		            if (amount <= 0) {
		            	sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Set")));
		            	return true;
		            }
					Player target = null;
					OfflinePlayer offTarget = null;
					UUID targetUUID = null;
					if (Bukkit.getPlayer(args[1]) != null) {
			        	target = Bukkit.getPlayer(args[1]);
			        	targetUUID = target.getUniqueId();
					} else if (checkIfUUID(args[1]) == true) {
			        	targetUUID = UUID.fromString(args[1]);
			        	target = Bukkit.getPlayer(targetUUID);
						if (getFileExists(targetUUID)) {
							new DataManager(plugin).updateData(targetUUID, amount, "Credits", "set");
			            	sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditSetSuccessMessage")));
							return true;
						} else {
							sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidPlayer")));
							return true;
						}
			        } else if (checkIfUUID(args[1]) == false && Bukkit.getPlayer(args[1]) == null) {
						offTarget = (OfflinePlayer)Bukkit.getServer().getOfflinePlayer(args[1]);
						targetUUID = offTarget.getUniqueId();
						if (getFileExists(targetUUID)) {
							new DataManager(plugin).updateData(targetUUID, amount, "Credits", "set");
			            	sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditSetSuccessMessage")));
							return true;
						} else {
							sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidPlayer")));
							return true;
						}
			        }
					new DataManager(plugin).updateData(targetUUID, amount, "Credits", "set");
		            if (sender == target) {
		            	target.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditSetMessage").replace("%CREDITS%", "" + amount)));
		            	return true;
		            } else {
		            	sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditSetSuccessMessage")));
		            	target.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditSetMessage").replace("%CREDITS%", "" + amount)));
		            	return true;
		            }
				} else {
					sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("NoPermsMessage")));
					return true;
				}
			} else if (args[0].equalsIgnoreCase("Send")) {
				if (sender.hasPermission("morphredeem.sendcredits")) {
					if (args.length != 3) {
						sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Send")));
						return true;
					}
		        	try {
		        		Integer.parseInt(args[2]);
		            }
		            catch (NumberFormatException e) {
		            	sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Send")));
		            	return true;
		            }
		        	Player commandSender = (Player) sender;
		        	int amount = Integer.parseInt(args[2]);
		            if (amount <= 0) {
		            	sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Send")));
		            	return true;
		            }
		            int senderCreds = Integer.parseInt(new DataManager(plugin).getData(commandSender.getUniqueId(), "Credits"));
		            if (amount > senderCreds) {
		            	sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidCredits")));
		            	return true; 
		            }
					Player target = null;
					OfflinePlayer offTarget = null;
					UUID targetUUID = null;
					if (Bukkit.getPlayer(args[1]) != null) {
			        	target = Bukkit.getPlayer(args[1]);
			        	targetUUID = target.getUniqueId();
			        } else if (Bukkit.getPlayer(args[1]) == null) {
						offTarget = (OfflinePlayer)Bukkit.getServer().getOfflinePlayer(args[1]);
						targetUUID = offTarget.getUniqueId();
						if (getFileExists(targetUUID)) {
							new DataManager(plugin).updateData(targetUUID, +amount, "Credits", "add");
							new DataManager(plugin).updateData(commandSender.getUniqueId(), -amount, "Credits", "remove");
			            	sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditSendSuccessMessage").replace("%TARGET%", offTarget.getName()).replace("%CREDITS%", "" + amount)));
							return true;
						} else {
							sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidPlayer")));
							return true;
						}
			        }
					new DataManager(plugin).updateData(targetUUID, +amount, "Credits", "add");
					new DataManager(plugin).updateData(commandSender.getUniqueId(), -amount, "Credits", "remove");
		            if (sender == target) {
		            	target.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditSendMessage").replace("%SENDER%", sender.getName()).replace("%CREDITS%", "" + amount)));
		            	return true;
		            } else {
		            	sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditSendSuccessMessage").replace("%TARGET%", target.getName()).replace("%CREDITS%", "" + amount)));
		            	target.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditSendMessage").replace("%SENDER%", sender.getName()).replace("%CREDITS%", "" + amount)));
		            	return true;
		            }
				} else {
					sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("NoPermsMessage")));
					return true;
				}
			} else if (args[0].equalsIgnoreCase("Reset")) {
				if (sender.hasPermission("morphredeem.resetcredits")) {
					if (args.length != 2) {
						sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Reset")));
						return true;
					}
					Player target = null;
					OfflinePlayer offTarget = null;
					UUID targetUUID = null;
					if (Bukkit.getPlayer(args[1]) != null) {
			        	target = Bukkit.getPlayer(args[1]);
			        	targetUUID = target.getUniqueId();
					} else if (checkIfUUID(args[1]) == true) {
			        	targetUUID = UUID.fromString(args[1]);
			        	target = Bukkit.getPlayer(targetUUID);
						if (getFileExists(targetUUID)) {
							new DataManager(plugin).updateData(targetUUID, 0, "Credits", "set");
			            	sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditResetSuccessMessage")));
							return true;
						} else {
							sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidPlayer")));
							return true;
						}
			        } else if (checkIfUUID(args[1]) == false && Bukkit.getPlayer(args[1]) == null) {
						offTarget = (OfflinePlayer)Bukkit.getServer().getOfflinePlayer(args[1]);
						targetUUID = offTarget.getUniqueId();
						if (getFileExists(targetUUID)) {
							new DataManager(plugin).updateData(targetUUID, 0, "Credits", "set");
			            	sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditResetSuccessMessage")));
							return true;
						} else {
							sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidPlayer")));
							return true;
						}
			        }
					new DataManager(plugin).updateData(targetUUID, 0, "Credits", "set");
		            if (sender == target) {
		            	target.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditResetMessage")));
		            	return true;
		            } else {
		            	sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditResetSuccessMessage")));
		            	target.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditResetMessage")));
		            	return true;
		            }
				} else {
					sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("NoPermsMessage")));
					return true;
				}
			} else if (args[0].equalsIgnoreCase("reload")) {
				if (sender.hasPermission("morphredeem.admin") || sender.hasPermission("morphredeem.reload")) {
					Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("MorphRedeem");
		            if (plugin != null) {
		            	plugin.reloadConfig();
						this.plugin.reloadMessages();
		            	sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + this.plugin.getMessage("ReloadMessage")));
		            	return true;
		            }
				} else {
					sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("NoPermsMessage")));
					return true;
		        }
			} else if (args.length == 2) {
				if (sender.hasPermission("morphredeem.skillcommand")) {
					Player player = (Player)sender;
					UUID uuid = player.getUniqueId();
					String skill = args[0];
			        try {
			        	Integer.parseInt(args[1]);
			        }
			        catch (NumberFormatException e1) {
			        	player.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidNumber")));
			        	return true;
			        }
					int amount2 = Integer.parseInt(args[1]);
					int credits = Integer.parseInt(new DataManager(plugin).getData(uuid, "Credits"));
					if (!new McMMOMethods().doesSkillExist(skill)) {
						sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidSkill")));
						return true;
					}
					if (amount2 <= 0) {
						sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidNumber")));
						return true;
					}
		            if (amount2 > credits) {
		            	sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidCredits")));
		            	return true; 
		            }
					if (plugin.getConfig().getBoolean("Settings.mcMMOSkillXP.Enabled")) {
						new CreditConversion(plugin).ApplyCredit("EXPERIENCE", player, amount2, skill);
						return true;
					} else {
						new CreditConversion(plugin).ApplyCredit("LEVEL", player, amount2, skill);
						return true;
					}
				} else {
					sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("NoPermsMessage")));
					return true;
				}
			} else {
				sender.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidArgsMessage")));
				return true;
			}
		}
		return false;
	}
	
	public boolean getFileExists(UUID uuid) {
		File playerFile = new File(this.plugin.getDataFolder() + File.separator + "PlayerData", uuid + ".yml");
	    if (!playerFile.exists()) {
	    	return false;
	    }
	    return true;
	  }
	
	public boolean checkIfUUID(String s) {
		try {
			UUID uuid = UUID.fromString(s);
			return true;
		}
		catch(IllegalArgumentException e) {
			return false;
		}
		
	}
}