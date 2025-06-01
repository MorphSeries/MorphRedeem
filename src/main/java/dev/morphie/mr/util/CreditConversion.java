package dev.morphie.mr.util;

import com.gmail.nossr50.api.ExperienceAPI;
import dev.morphie.mr.MorphRedeem;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CreditConversion {

    public void ApplyCredit(String TYPE, Player player, int CREDITS, String SKILL) {
        UUID uuid = player.getUniqueId();
        if (TYPE.equals("LEVEL")) {
            int cap = ExperienceAPI.getLevelCap(SKILL);
            if (ExperienceAPI.getLevel(player, SKILL) + CREDITS > cap) {
                String message = new MorphRedeem().getMessage("SkillCapReached");
                if (message.contains("%SKILL%")) {
                    message = message.replaceAll("%SKILL%", SKILL);
                }
                if (message.contains("%CAP%")) {
                    message = message.replaceAll("%CAP%", "" + cap);
                }
                if (message.contains("%LEVEL%")) {
                    message = message.replaceAll("%LEVEL%", "" + (ExperienceAPI.getLevel(player, SKILL) + CREDITS));
                }
                player.sendMessage(new StringUtils().addColor(new MorphRedeem().getMessage("ErrorPrefix") + message));
            } else {
                new DataManager(new MorphRedeem()).updateData(uuid, +CREDITS, "Credits_Spent", "add");
                new DataManager(new MorphRedeem()).updateData(uuid, -CREDITS, "Credits", "remove");

                ExperienceAPI.addLevel(player, SKILL, CREDITS);
                String message = new MorphRedeem().getMessage("CreditAssignmentSuccess");
                if (message.contains("%SKILL%")) {
                    message = message.replaceAll("%SKILL%", SKILL);
                }
                if (message.contains("%CREDITS%")) {
                    message = message.replaceAll("%CREDITS%", "" + CREDITS);
                }
                player.sendMessage(new StringUtils().addColor(new MorphRedeem().getMessage("Prefix") + message));
            }
        } else if (TYPE.equals("EXPERIENCE")) {

        }
    }
}
