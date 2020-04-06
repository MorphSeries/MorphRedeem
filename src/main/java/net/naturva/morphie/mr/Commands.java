package net.naturva.morphie.mr;

import java.io.File;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.api.exceptions.McMMOPlayerNotFoundException;

import net.md_5.bungee.api.ChatColor;
import net.naturva.morphie.mr.files.PlayerFileMethods;
import net.naturva.morphie.mr.menus.RedeemMenu;
import net.naturva.morphie.mr.util.McMMOMethods;
import net.naturva.morphie.mr.util.dataManager;

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
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("McMMOPlayerNotLoadedMessage")));
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("NoPermsMessage")));
						return true;
					}
				} else {
					return true;
				}
			} else if (args[0].equalsIgnoreCase("help")) {
				if (sender.hasPermission("morphredeem.help")) {
					sender.sendMessage("");
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Commands.Header")));
					sender.sendMessage("");
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Commands.Help")));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Commands.MR")));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Commands.MRSkill")));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Commands.Credits")));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Commands.CreditsOthers")));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Commands.Send")));
					sender.sendMessage("");
					if (sender.hasPermission("morphredeem.admin") || sender.hasPermission("morphredeem.reload")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Commands.Add")));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Commands.Remove")));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Commands.Set")));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Commands.Reset")));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Commands.Reload")));
					}
					sender.sendMessage("");
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Commands.Footer")));
					sender.sendMessage("");
					return true;
				} else {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("NoPermsMessage")));
					return true;
				}
			} else if (args[0].equalsIgnoreCase("Credits")) {
				Player player = (Player)sender;
				UUID uuid = player.getUniqueId();
				if (args.length == 1) {
					if (sender.hasPermission("morphredeem.credits")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("PlayerCreditsMessage").replace("%CREDITS%", new dataManager(plugin).getData(uuid, "Credits"))));
						return true;
					} else {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("NoPermsMessage")));
						return true;
					}
				} else if (args.length == 2) {
					if (sender.hasPermission("morphredeem.credits.others")) {
						Player target = null;
						OfflinePlayer offTarget = null;
						if (Bukkit.getPlayer(args[1]) != null) {
							target = Bukkit.getPlayer(args[1]);
							String targetCredits = new dataManager(plugin).getData(target.getUniqueId(), "Credits");
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("OtherPlayerCreditMessage").replace("%PLAYER%", target.getName()).replace("%CREDITS%", targetCredits)));
							return true;
						} else {
							offTarget = (OfflinePlayer)Bukkit.getServer().getOfflinePlayer(args[1]);
							if (getFileExists(offTarget.getUniqueId())) {
								String targetCredits = new dataManager(plugin).getData(offTarget.getUniqueId(), "Credits");
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("OtherPlayerCreditMessage").replace("%PLAYER%", offTarget.getName()).replace("%CREDITS%", targetCredits)));
								return true;	
							} else {
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidPlayer")));
								return true;
							}
						}
					} else {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("NoPermsMessage")));
						return true;
					}
				}
			} else if (args[0].equalsIgnoreCase("Add")) {
				if (sender.hasPermission("morphredeem.admin") || sender.hasPermission("morphredeem.addcredits")) {
					if (args.length != 3) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Add")));
						return true;
					}
		        	try {
		        		Integer.parseInt(args[2]);
		            }
		            catch (NumberFormatException e) {
		            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Add")));
		            	return true;
		            }
		        	int amount = Integer.parseInt(args[2]);
		            if (amount <= 0) {
		            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Add")));
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
							new dataManager(plugin).updateData(targetUUID, +amount, "Credits", "add");
			            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAddSuccessMessage")));
			            	target.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAddMessage").replace("%CREDITS%", "" + amount)));
			            	return true;
						} else {
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidPlayer")));
							return true;
						}
			        } else if (checkIfUUID(args[1]) == false && Bukkit.getPlayer(args[1]) == null) {
						offTarget = (OfflinePlayer)Bukkit.getServer().getOfflinePlayer(args[1]);
						targetUUID = offTarget.getUniqueId();
						if (getFileExists(targetUUID)) {
							new dataManager(plugin).updateData(targetUUID, +amount, "Credits", "add");
			            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAddSuccessMessage")));
							return true;
						} else {
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidPlayer")));
							return true;
						}
			        }
					new dataManager(plugin).updateData(targetUUID, +amount, "Credits", "add");
		            if (sender == target) {
		            	target.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAddMessage").replace("%CREDITS%", "" + amount)));
		            	return true;
		            } else {
		            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAddSuccessMessage")));
		            	target.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditAddMessage").replace("%CREDITS%", "" + amount)));
		            	return true;
		            }
		        } else {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("NoPermsMessage")));
					return true;
				}
			} else if (args[0].equalsIgnoreCase("remove")) {
				if (sender.hasPermission("morphredeem.admin") || sender.hasPermission("morphredeem.removecredits")) {
					if (args.length != 3) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Remove")));
						return true;
					}
			        int amount = Integer.parseInt(args[2]);
		        	try {
		        		Integer.parseInt(args[2]);
		            }
		            catch (NumberFormatException e) {
		            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Remove")));
		            	return true;
		            }
		            if (amount <= 0) {
		            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Remove")));
		            	return true;
		            }
					Player target = null;
					OfflinePlayer offTarget = null;
					UUID targetUUID = null;
					int credits = 0;
					if (Bukkit.getPlayer(args[1]) != null) {
			        	target = Bukkit.getPlayer(args[1]);
			        	targetUUID = target.getUniqueId();
			        	credits = Integer.parseInt(new dataManager(plugin).getData(targetUUID, "Credits"));
			            if (amount > credits) {
			            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Remove")));
			            	return true; 
			            }
					} else if (checkIfUUID(args[1]) == true) {
			        	targetUUID = UUID.fromString(args[1]);
			        	target = Bukkit.getPlayer(targetUUID);
			        	credits = Integer.parseInt(new dataManager(plugin).getData(targetUUID, "Credits"));
			            if (amount > credits) {
			            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Remove")));
			            	return true; 
			            }
						if (getFileExists(targetUUID)) {
							new dataManager(plugin).updateData(targetUUID, -amount, "Credits", "remove");
			            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditRemoveSuccessMessage")));
							return true;
						} else {
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidPlayer")));
							return true;
						}
			        } else if (checkIfUUID(args[1]) == false && Bukkit.getPlayer(args[1]) == null) {
						offTarget = (OfflinePlayer)Bukkit.getServer().getOfflinePlayer(args[1]);
						targetUUID = offTarget.getUniqueId();
						credits = Integer.parseInt(new dataManager(plugin).getData(targetUUID, "Credits"));
			            if (amount > credits) {
			            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Remove")));
			            	return true; 
			            }
						if (getFileExists(targetUUID)) {
							new dataManager(plugin).updateData(targetUUID, -amount, "Credits", "remove");
			            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditRemoveSuccessMessage")));
							return true;
						} else {
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidPlayer")));
							return true;
						}
			        }
		            if (amount > credits) {
		            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Remove")));
		            	return true; 
		            }
		            new dataManager(plugin).updateData(targetUUID, -amount, "Credits", "remove");
		            if (sender == target) {
		            	target.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditRemoveMessage").replace("%CREDITS%", "" + amount)));
		            	return true;
		            } else {
		            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditRemoveSuccessMessage")));
		            	target.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditRemoveMessage").replace("%CREDITS%", "" + amount)));
		            	return true;
		            }
		        } else {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("NoPermsMessage")));
					return true;
				}	
			} else if (args[0].equalsIgnoreCase("Set")) {
				if (sender.hasPermission("morphredeem.setcredits")) {
					if (args.length != 3) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Set")));
						return true;
					}
		        	try {
		        		Integer.parseInt(args[2]);
		            }
		            catch (NumberFormatException e) {
		            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Set")));
		            	return true;
		            }
		        	int amount = Integer.parseInt(args[2]);
		            if (amount <= 0) {
		            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Set")));
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
							new dataManager(plugin).updateData(targetUUID, amount, "Credits", "set");
			            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditSetSuccessMessage")));
							return true;
						} else {
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidPlayer")));
							return true;
						}
			        } else if (checkIfUUID(args[1]) == false && Bukkit.getPlayer(args[1]) == null) {
						offTarget = (OfflinePlayer)Bukkit.getServer().getOfflinePlayer(args[1]);
						targetUUID = offTarget.getUniqueId();
						if (getFileExists(targetUUID)) {
							new dataManager(plugin).updateData(targetUUID, amount, "Credits", "set");
			            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditSetSuccessMessage")));
							return true;
						} else {
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidPlayer")));
							return true;
						}
			        }
					new dataManager(plugin).updateData(targetUUID, amount, "Credits", "set");
		            if (sender == target) {
		            	target.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditSetMessage").replace("%CREDITS%", "" + amount)));
		            	return true;
		            } else {
		            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditSetSuccessMessage")));
		            	target.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditSetMessage").replace("%CREDITS%", "" + amount)));
		            	return true;
		            }
				} else {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("NoPermsMessage")));
					return true;
				}
			} else if (args[0].equalsIgnoreCase("Send")) {
				if (sender.hasPermission("morphredeem.sendcredits")) {
					if (args.length != 3) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Send")));
						return true;
					}
		        	try {
		        		Integer.parseInt(args[2]);
		            }
		            catch (NumberFormatException e) {
		            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Send")));
		            	return true;
		            }
		        	Player commandSender = (Player) sender;
		        	int amount = Integer.parseInt(args[2]);
		            if (amount <= 0) {
		            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Send")));
		            	return true;
		            }
		            int senderCreds = Integer.parseInt(new dataManager(plugin).getData(commandSender.getUniqueId(), "Credits"));
		            if (amount > senderCreds) {
		            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidCredits")));
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
							new dataManager(plugin).updateData(targetUUID, +amount, "Credits", "add");
							new dataManager(plugin).updateData(commandSender.getUniqueId(), -amount, "Credits", "remove");
			            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditSendSuccessMessage").replace("%TARGET%", offTarget.getName()).replace("%CREDITS%", "" + amount)));
							return true;
						} else {
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidPlayer")));
							return true;
						}
			        }
					new dataManager(plugin).updateData(targetUUID, +amount, "Credits", "add");
					new dataManager(plugin).updateData(commandSender.getUniqueId(), -amount, "Credits", "remove");
		            if (sender == target) {
		            	target.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditSendMessage").replace("%SENDER%", sender.getName()).replace("%CREDITS%", "" + amount)));
		            	return true;
		            } else {
		            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditSendSuccessMessage").replace("%TARGET%", target.getName()).replace("%CREDITS%", "" + amount)));
		            	target.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditSendMessage").replace("%SENDER%", sender.getName()).replace("%CREDITS%", "" + amount)));
		            	return true;
		            }
				} else {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("NoPermsMessage")));
					return true;
				}
			} else if (args[0].equalsIgnoreCase("Reset")) {
				if (sender.hasPermission("morphredeem.resetcredits")) {
					if (args.length != 2) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("CorrectUsage.Reset")));
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
							new dataManager(plugin).updateData(targetUUID, 0, "Credits", "set");
			            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditResetSuccessMessage")));
							return true;
						} else {
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidPlayer")));
							return true;
						}
			        } else if (checkIfUUID(args[1]) == false && Bukkit.getPlayer(args[1]) == null) {
						offTarget = (OfflinePlayer)Bukkit.getServer().getOfflinePlayer(args[1]);
						targetUUID = offTarget.getUniqueId();
						if (getFileExists(targetUUID)) {
							new dataManager(plugin).updateData(targetUUID, 0, "Credits", "set");
			            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditResetSuccessMessage")));
							return true;
						} else {
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidPlayer")));
							return true;
						}
			        }
					new dataManager(plugin).updateData(targetUUID, 0, "Credits", "set");
		            if (sender == target) {
		            	target.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditResetMessage")));
		            	return true;
		            } else {
		            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditResetSuccessMessage")));
		            	target.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("CreditResetMessage")));
		            	return true;
		            }
				} else {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("NoPermsMessage")));
					return true;
				}
			} else if (args[0].equalsIgnoreCase("reload")) {
				if (sender.hasPermission("morphredeem.admin") || sender.hasPermission("morphredeem.reload")) {
					Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("MorphRedeem");
		            if (this.plugin != null) {
		            	this.plugin.reloadConfig();
		            	this.plugin.getServer().getPluginManager().disablePlugin(plugin);
		            	this.plugin.getServer().getPluginManager().enablePlugin(plugin);
		            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + this.plugin.getMessage("ReloadMessage")));
		            	return true;
		            }
				} else {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("NoPermsMessage")));
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
			        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidNumber")));
			        	return true;
			        }
					int amount2 = Integer.parseInt(args[1]);
					int credits = Integer.parseInt(new dataManager(plugin).getData(uuid, "Credits"));
					if (new McMMOMethods().doesSkillExist(player, skill) == false) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidSkill")));
						return true;
					}
					if (amount2 <= 0) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidNumber")));
						return true;
					}
		            if (amount2 > credits) {
		            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidCredits")));
		            	return true; 
		            }
			        int cap = ExperienceAPI.getLevelCap(skill);
			        if (ExperienceAPI.getLevel(player, skill) + amount2 > cap) {
			        	String message = this.plugin.getMessage("SkillCapReached");
			        	if (message.contains("%SKILL%")) {
			        		message = message.replaceAll("%SKILL%", skill);
			          }
			          if (message.contains("%CAP%")) {
			            message = message.replaceAll("%CAP%", "" + cap);
			          }
			          if (message.contains("%LEVEL%")) {
			        	  message = message.replaceAll("%LEVEL%", "" + (ExperienceAPI.getLevel(player, skill) + amount2));
			          }
			          player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + message));
			          return true;
			        } else {	
			        	new dataManager(plugin).updateData(uuid, +amount2, "Credits_Spent", "add");
			        	new dataManager(plugin).updateData(uuid, -amount2, "Credits", "remove");
		          
			        	ExperienceAPI.addLevel(player, skill, amount2);
			        	String message = this.plugin.getMessage("CreditAssignmentSuccess");
			        	if (message.contains("%SKILL%")) {
			        		message = message.replaceAll("%SKILL%", skill);
			        	}
			        	if (message.contains("%CREDITS%")) {
			        		message = message.replaceAll("%CREDITS%", "" + amount2);
			        	}
			        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("Prefix") + message));
			        	return true;
			        }
				} else {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("NoPermsMessage")));
					return true;
				}
			} else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getMessage("ErrorPrefix") + this.plugin.getMessage("InvalidArgsMessage")));
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