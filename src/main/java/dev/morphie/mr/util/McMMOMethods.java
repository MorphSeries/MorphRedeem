package dev.morphie.mr.util;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.api.SkillAPI;
import com.gmail.nossr50.datatypes.skills.PrimarySkillType;
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
		if (SkillAPI.getSkills().contains(skill.toUpperCase())) {
            return ExperienceAPI.getLevel(player, PrimarySkillType.valueOf(skill.toUpperCase()));
		} else {
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

	public boolean doesSkillExist(String skill) {
		return ExperienceAPI.isValidSkillType(skill);
	}
	

	public void applyXP(Player player, String skill, int xp) {
		ExperienceAPI.addModifiedXP(player, skill, xp);
	}
}
