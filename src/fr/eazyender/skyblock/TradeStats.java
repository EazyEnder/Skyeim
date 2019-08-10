package fr.eazyender.skyblock;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TradeStats {
	
	private Inventory inv;
	private int stapeOfDeal;
	private ItemStack[] contentDealSender,contentDealTarget;
	private long moneyContentSender, moneyContentTarget;
	
	public TradeStats(Inventory inv,int stapeOfDeal, ItemStack[] contentDealSender, ItemStack[] contentDealTarget, long moneyContentSender, long moneyContentTarget) {
		
		this.inv = inv;
		this.stapeOfDeal = stapeOfDeal;
		this.contentDealSender = contentDealSender;
		this.contentDealTarget = contentDealTarget;
		this.moneyContentSender = moneyContentSender;
		this.moneyContentTarget = moneyContentTarget;
		
	}
	
	
	public Inventory getInv() {
		return inv;
	}


	public void setInv(Inventory inv) {
		this.inv = inv;
	}


	public int getStapeOfDeal() {
		return stapeOfDeal;
	}

	public void setStapeOfDeal(int stapeOfDeal) {
		this.stapeOfDeal = stapeOfDeal;
	}

	public ItemStack[] getContentDealSender() {
		return contentDealSender;
	}

	public void setContentDealSender(ItemStack[] contentDealSender) {
		this.contentDealSender = contentDealSender;
	}

	public ItemStack[] getContentDealTarget() {
		return contentDealTarget;
	}

	public void setContentDealTarget(ItemStack[] contentDealTarget) {
		this.contentDealTarget = contentDealTarget;
	}

	public long getMoneyContentSender() {
		return moneyContentSender;
	}

	public void setMoneyContentSender(long moneyContentSender) {
		this.moneyContentSender = moneyContentSender;
	}

	public long getMoneyContentTarget() {
		return moneyContentTarget;
	}

	public void setMoneyContentTarget(long moneyContentTarget) {
		this.moneyContentTarget = moneyContentTarget;
	}
	
	

}
