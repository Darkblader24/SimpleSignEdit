package net.darkblader24.simplesignedit;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignEditListener implements Listener {
	
	@EventHandler
	public void interact(PlayerInteractEvent e){
		Player player = e.getPlayer();
		Block block = e.getClickedBlock();

		if(!player.isSneaking())
			return;
		
		if(block == null || ! (block.getState() instanceof Sign))
			return;

		if(!SimpleSignEdit.getPlugin().getConfig().getBoolean("sneakEdit"))
			return;
		
		if(!player.isOp() && !player.hasPermission("simplesignedit.signedit"))
			return;
		
		e.setCancelled(true);
		
		Sign sign = (Sign) block.getState();
		SimpleSignEdit.getSignEdit().editSign(player, sign);
	}
	
}
