package net.naturva.morphie.mr.util;

import com.gmail.nossr50.api.ExperienceAPI;
import org.bukkit.entity.Player;

public class McMMOMethods {

	public int getSkillCap(String skill) {
		try {
            return ExperienceAPI.getLevelCap(skill);
		} catch (NullPointerException e1) {
			return 0;
		}
	}

	public int getSkillLevel(Player player, String skill) {
		try {
            return ExperienceAPI.getLevel(player, skill);
		} catch (NullPointerException e1) {
			return 0;
		}
	}

	public int getSkillXP(Player player, String skill) {
		try {
			return ExperienceAPI.getXP(player, skill);
		} catch (NullPointerException e1) {
			return 0;
		}
	}

	public int getSkillXPNeeded(Player player, String skill) {
		try {
			return ExperienceAPI.getXPToNextLevel(player, skill);
		} catch (NullPointerException e1) {
			return 0;
		}
	}

	public boolean doesSkillExist(Player player, String skill) {
		try {
			ExperienceAPI.getLevel(player, skill);
			return true;
		} catch (NullPointerException e1) {
			return false;
		}
	}
	

	public void applyXP(Player player, String skill, int xp) {
		ExperienceAPI.addModifiedXP(player, skill, xp);
	}
}
