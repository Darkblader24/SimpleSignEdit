package net.darkblader24.simplesignedit.signedit;

import net.minecraft.server.v1_9_R2.BlockPosition;
import net.minecraft.server.v1_9_R2.PacketPlayOutOpenSignEditor;
import net.minecraft.server.v1_9_R2.PlayerConnection;
import net.minecraft.server.v1_9_R2.TileEntitySign;

import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_9_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class SignEdit_1_9_R2 implements SignEdit {
	
    @Override
	public void editSign(Player player, Sign sign) {
		
		String[] lines = new String[4];
		for (int i = 0; i < sign.getLines().length; i++)
			if (sign.getLine(i) != null)
				lines[i] = sign.getLine(i).replaceAll("§", "&");

		TileEntitySign tes = (TileEntitySign) ((CraftWorld) sign.getWorld()).getHandle().getTileEntity(new BlockPosition(sign.getX(), sign.getY(), sign.getZ()));
		tes.isEditable = true;
		tes.a(((CraftHumanEntity) player).getHandle());

		player.sendSignChange(sign.getLocation(), lines);
		PacketPlayOutOpenSignEditor packet2 = new PacketPlayOutOpenSignEditor(tes.getPosition());
		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
        connection.sendPacket(packet2);
        
	}

}
