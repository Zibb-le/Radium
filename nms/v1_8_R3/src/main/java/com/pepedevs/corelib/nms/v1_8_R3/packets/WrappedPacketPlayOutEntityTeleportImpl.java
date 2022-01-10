package com.pepedevs.corelib.nms.v1_8_R3.packets;

import com.pepedevs.corelib.nms.packets.WrappedPacketDataSerializer;
import com.pepedevs.corelib.nms.packets.WrappedPacketPlayOutEntityTeleport;
import com.pepedevs.corelib.nms.v1_8_R3.NMSImpl;
import net.minecraft.server.v1_8_R3.MathHelper;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import org.bukkit.Location;

import java.io.IOException;

public class WrappedPacketPlayOutEntityTeleportImpl extends PacketPlayOutEntityTeleport implements WrappedPacketPlayOutEntityTeleport {

    public WrappedPacketPlayOutEntityTeleportImpl(int entityId, Location location, boolean onGround) {
        WrappedPacketDataSerializer serializer = NMSImpl.INSTANCE.getDataSerializer();
        serializer.serializeIntToByte(entityId)
                .serializeInt(MathHelper.floor(location.getX() * 32.0D))
                .serializeInt(MathHelper.floor(location.getY() * 32.0D))
                .serializeInt(MathHelper.floor(location.getZ() * 32.0D))
                .serializeByte(MathHelper.d(location.getYaw() * 256.0F / 360.0F))
                .serializeByte(MathHelper.d(location.getPitch() * 256.0F / 360.0F))
                .serializeBoolean(onGround);

        try {
            this.a((PacketDataSerializer) serializer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WrappedPacketPlayOutEntityTeleportImpl(int entityId, Location location) {
        this(entityId, location, true);
    }

}