package net.darkblader24.simplesignedit.signedit;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutOpenSignEditor;
import net.minecraft.server.v1_8_R3.PacketPlayOutUpdateSign;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.TileEntitySign;

import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class SignEdit_1_8_R3 implements SignEdit {
	
    @Override
	public void editSign(Player player, Sign sign) {
	    
	    IChatBaseComponent[] lines = new IChatBaseComponent[4];
	    for (int i = 0; i < sign.getLines().length; i++)
	    	if (sign.getLine(i) != null) 
	    		lines[i] = new ChatComponentText(sign.getLine(i).replaceAll("§", "&"));
	    
	    TileEntitySign tes = (TileEntitySign)((CraftWorld)sign.getWorld()).getHandle().getTileEntity(new BlockPosition(sign.getX(), sign.getY(), sign.getZ()));
	    tes.isEditable = true;
	    tes.a(((CraftHumanEntity)player).getHandle());
	    
	    PacketPlayOutUpdateSign packet1 = new PacketPlayOutUpdateSign(tes.getWorld(), tes.getPosition(), lines);
	    PacketPlayOutOpenSignEditor packet2 = new PacketPlayOutOpenSignEditor(tes.getPosition());
	    PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
	    connection.sendPacket(packet1);
	    connection.sendPacket(packet2);
        
	}

}
