package fr.eazyender.skyblock.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCHelp implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			
			p.sendMessage("§f§l<§r§6§lSky§r§e§leim§r§f§l> - §e§lCommandes / Aide");
			p.sendMessage("§r§6§l/island(/is)§r§e : Commande principal du serveur vous permettant d'avoir le controle sur votre ile.");
			p.sendMessage("§r§6§l/shop(/boutique)§r§e : Commande pour afficher la boutique(achat/vente).");
			p.sendMessage("§r§6§l/level(/lvl)§r§e : Commande pour actualiser le level de son île.");
			p.sendMessage("§r§6§l/spawn(/sp)§r§e : Commande pour aller au spawn.");
			p.sendMessage("§4§lPROCHAINEMENT§r§6§l/trade(/echange)§r§e : Commande pour échanger avec un joueur.");
			p.sendMessage("§4§lPROCHAINEMENT§r§6§l/donjons(/dj)§r§e : Commande ouvrant le menu des donjons.");
			p.sendMessage("§4§lPROCHAINEMENT§r§6§l/challenge(/chal)§r§e : Commande pour afficher vos défis.");
			p.sendMessage("§4§lPROCHAINEMENT§r§6§l/minions(/mn)§r§e : Commande pour afficher le menu des minions.");
			p.sendMessage("§4§lPROCHAINEMENT§r§6§l/hdv§r§e : Commande pour ouvrir l'hotel des ventes.");
			
			return true;
		}
		
		return false;
	}

}
