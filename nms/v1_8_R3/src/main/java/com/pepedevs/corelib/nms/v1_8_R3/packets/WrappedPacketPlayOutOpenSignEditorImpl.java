package com.pepedevs.corelib.nms.v1_8_R3.packets;

import com.pepedevs.corelib.nms.packets.WrappedPacketPlayOutOpenSignEditor;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.PacketPlayOutOpenSignEditor;
import org.bukkit.Location;

public class WrappedPacketPlayOutOpenSignEditorImpl extends PacketPlayOutOpenSignEditor implements WrappedPacketPlayOutOpenSignEditor {

    public WrappedPacketPlayOutOpenSignEditorImpl(Location location) {
        super(new BlockPosition(location.getX(), location.getY(), location.getZ()));
    }

    public WrappedPacketPlayOutOpenSignEditorImpl(Object blockPos) {
        super((BlockPosition) blockPos);
    }

}