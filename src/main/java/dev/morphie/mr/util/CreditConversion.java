package dev.morphie.mr.util;

import com.gmail.nossr50.api.ExperienceAPI;
import dev.morphie.mr.MorphRedeem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CreditConversion {

    private MorphRedeem plugin;

    public CreditConversion(MorphRedeem plugin) {
        this.plugin = plugin;
    }

    public void ApplyCredit(String TYPE, Player player, int CREDITS, String SKILL) {
        UUID uuid = player.getUniqueId();
        if (TYPE.equals("LEVEL")) {
            int cap = ExperienceAPI.getLevelCap(SKILL);
            if (ExperienceAPI.getLevel(player, SKILL) + CREDITS >= cap) {
                String message = this.plugin.getMessage("SkillCapReached");
                if (message.contains("%SKILL%")) {
                    message = message.replaceAll("%SKILL%", SKILL);
                }
                if (message.contains("%CAP%")) {
                    message = message.replaceAll("%CAP%", "" + cap);
                }
                if (message.contains("%LEVEL%")) {
                    message = message.replaceAll("%LEVEL%", "" + (ExperienceAPI.getLevel(player, SKILL) + CREDITS));
                }
                player.sendMessage(new StringUtils().addColor(this.plugin.getMessage("ErrorPrefix") + message));
            } else {
                new DataManager(this.plugin).updateData(uuid, +CREDITS, "Credits_Spent", "add");
                new DataManager(this.plugin).updateData(uuid, -CREDITS, "Credits", "remove");

                ExperienceAPI.addLevel(player, SKILL, CREDITS);
                String message = this.plugin.getMessage("CreditAssignmentSuccess");
                if (message.contains("%SKILL%")) {
                    message = message.replaceAll("%SKILL%", SKILL);
                }
                if (message.contains("%CREDITS%")) {
                    message = message.replaceAll("%CREDITS%", "" + CREDITS);
                }
                player.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + message));
            }
        } else if (TYPE.equals("EXPERIENCE")) {
            int xp = this.plugin.getConfig().getInt("Settings.mcMMOSkillXP.XPpercredit");
            new DataManager(this.plugin).updateData(uuid, +CREDITS, "Credits_Spent", "add");
            new DataManager(this.plugin).updateData(uuid, -CREDITS, "Credits", "remove");
            if (this.plugin.getConfig().getBoolean("Settings.mcMMOSkillXP.Enabled")) {
                Bukkit.getScheduler().runTask(this.plugin, () -> {
                    new McMMOMethods().applyXP(player, SKILL, CREDITS*xp);
                });
            } else {
                ExperienceAPI.addLevel(player, SKILL, CREDITS);
            }
            String message = this.plugin.getMessage("CreditAssignmentSuccess");
            if (message.contains("%SKILL%")) {
                message = message.replaceAll("%SKILL%", SKILL);
            }
            if (message.contains("%CREDITS%")) {
                message = message.replaceAll("%CREDITS%", "" + CREDITS);
            }
            player.sendMessage(new StringUtils().addColor(this.plugin.getMessage("Prefix") + message));
        }
    }
}
