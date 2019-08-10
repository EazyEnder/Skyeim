package fr.eazyender.skyblock.player;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HDVCustomItem {
	
	private long price;
	private ItemStack item;
	private String date;
	private Player sender;
	
	public HDVCustomItem(ItemStack item,long price,String date,Player sender) {
		
		this.item = item;
		this.price = price;
		this.sender = sender;
		this.date = date;
		
	}
	
	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public ItemStack getItem() {
		return item;
	}

	public void setItem(ItemStack item) {
		this.item = item;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Player getSender() {
		return sender;
	}

	public void setSender(Player sender) {
		this.sender = sender;
	}


}
