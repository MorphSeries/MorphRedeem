package net.naturva.morphie.mr.util;

import com.gmail.nossr50.api.ExperienceAPI;
import org.bukkit.entity.Player;

public class McMMOMethods {

	public int getSkillCap(String skill) {
		try {
			int num = ExperienceAPI.getLevelCap(skill);
			return num;
		} catch (NullPointerException e1) {
			return 0;
		}
	}

	public int getSkillLevel(Player player, String skill) {
		try {
			int num = ExperienceAPI.getLevel(player, skill);
			return num;
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
}
