package fr.eazyender.skyblock.player;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class PlayerStatsObject {
	
	/**Joueur a qui apartient ces stats */
	private Player player;
	/** L'ID du grade qui sera ensuite convertis en un String */
	private int grade_ID;
	/** x = nombres de fois complétés / challenges par ordre croissant*/
	private List<Integer> challenges = new ArrayList<Integer>();

	public PlayerStatsObject(Player player,int grade_ID, List<Integer> challenges) {
	
		this.player = player;
		this.grade_ID = grade_ID;
		this.challenges = challenges;
		
	}

	public int getGrade_ID() {
		return grade_ID;
	}

	public void setGrade_ID(int grade_ID) {
		this.grade_ID = grade_ID;
	}

	public List<Integer> getChallenges() {
		return challenges;
	}

	public void setChallenges(List<Integer> challenges) {
		this.challenges = challenges;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	

}
