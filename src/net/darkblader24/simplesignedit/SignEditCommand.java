package net.darkblader24.simplesignedit;


import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class SignEditCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args){
		final Player player = (Player)sender;
		
		String noPerm = SimpleSignEdit.getPlugin().getConfig().getString("no_permission").replace("&", "§");
		String noSign = SimpleSignEdit.getPlugin().getConfig().getString("no_sign").replace("&", "§");
		String successReload = SimpleSignEdit.getPlugin().getConfig().getString("successful_reload").replace("&", "§");
		
		if(args.length > 0){
			if(args[0].equalsIgnoreCase("reload")){
				if(!player.isOp() && !player.hasPermission("simplesignedit.signedit.reload")){
					player.sendMessage(noPerm);
					return false;
				}
				SimpleSignEdit.getPlugin().getConfig().options().copyDefaults(true);
				SimpleSignEdit.getPlugin().saveConfig();
				SimpleSignEdit.getPlugin().reloadConfig();
				player.sendMessage(successReload);
				return true;
			}
		}
		
		if(!player.isOp() && !player.hasPermission("simplesignedit.signedit")){
			player.sendMessage(noPerm);
			return false;
		}
		
		Block block = player.getTargetBlock( (HashSet<Material>)null, 5 );

		if (block == null || ! (block.getState() instanceof Sign)) {
			player.sendMessage(noSign);
			return false;
		}

		Sign sign = (Sign) block.getState();
		
		SimpleSignEdit.getSignEdit().editSign(player, sign);
		
		return true;
	}
	
}
