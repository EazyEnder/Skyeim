package fr.eazyender.skyblock.utils;

import java.util.HashMap;
import java.util.Map;

public class GradesAChallengesUtils {
	
	 private static Map<Integer, String> gradesIdToStr = new HashMap<Integer, String>();
	 
	 public static void initClass() {
		 
			 
			 gradesIdToStr.put(-1, "§r§c[§r" + "§4§lAdministrateur" + "§r§c]§r");
			 
			 gradesIdToStr.put(0, "");
			 
			 gradesIdToStr.put(1, "§r§f[§r" + "§aVoyageur" + "§r§f]§r");
			 gradesIdToStr.put(2, "§r§f[§r" + "§a§lNovice" + "§r§f]§r");
			 gradesIdToStr.put(3, "§r§f[§r" + "§2Adepte" + "§r§f]§r");
			 
			 gradesIdToStr.put(4, "§r§f[§r" + "§7Aventurier§r§f-" + "§7PR" + "§r§f]§r");
			 gradesIdToStr.put(5, "§r§f[§r" + "§7Aventurier§r§f-" + "§6CU" + "§r§f]§r");
			 gradesIdToStr.put(6, "§r§f[§r" + "§7Aventurier§r§f-" + "§8FE" + "§r§f]§r");
			 gradesIdToStr.put(7, "§r§f[§r" + "§7Aventurier§r§f-" + "§6§lBR" + "§r§f]§r");
			 gradesIdToStr.put(8, "§r§f[§r" + "§7Aventurier§r§f-" + "§7§lAR" + "§r§f]§r");
			 gradesIdToStr.put(9, "§r§f[§r" + "§7Aventurier§r§f-" + "§eOR" + "§r§f]§r");
			 gradesIdToStr.put(10, "§r§f[§r" + "§7Aventurier§r§f-" + "§8§lPL" + "§r§f]§r");
			 gradesIdToStr.put(11, "§r§f[§r" + "§7Aventurier§r§f-" + "§3DA" + "§r§f]§r");
			 gradesIdToStr.put(12, "§r§f[§r" + "§7Aventurier§r§f-" + "§2EM" + "§r§f]§r");
			 gradesIdToStr.put(13, "§r§f[§r" + "§7Aventurier§r§f-" + "§9MY" + "§r§f]§r");
			 gradesIdToStr.put(14, "§r§f[§r" + "§7Aventurier§r§f-" + "§1SA" + "§r§f]§r");
			 gradesIdToStr.put(15, "§r§f[§r" + "§7Aventurier§r§f-" + "§dOS" + "§r§f]§r");
			 gradesIdToStr.put(16, "§r§f[§r" + "§7Aventurier§r§f-" + "§5OR" + "§r§f]§r");
			 gradesIdToStr.put(17, "§r§f[§r" + "§7Aventurier§r§f-" + "§4§lAM" + "§r§f]§r");
			
		 
	 }

	public static Map<Integer, String> getGradesIdToStr() {
		return gradesIdToStr;
	}

}
