package com.pepedevs.radium.nms.v1_8_R3.packets;

import com.pepedevs.radium.nms.objects.WrappedPacketDataSerializer;
import com.pepedevs.radium.nms.packets.WrappedPacketPlayOutEntityTeleport;
import com.pepedevs.radium.nms.v1_8_R3.NMSProviderImpl;
import net.minecraft.server.v1_8_R3.MathHelper;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import org.bukkit.Location;

import java.io.IOException;

public class WrappedPacketPlayOutEntityTeleportImpl implements WrappedPacketPlayOutEntityTeleport {

    private int entityId;
    private double locX;
    private double locY;
    private double locZ;
    private float yaw;
    private float pitch;
    private boolean onGround;

    public WrappedPacketPlayOutEntityTeleportImpl(int entityId, int locX, int locY, int locZ, float yaw, float pitch, boolean onGround) {
        this.entityId = entityId;
        this.locX = locX;
        this.locY = locY;
        this.locZ = locZ;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }

    public WrappedPacketPlayOutEntityTeleportImpl(int entityId, Location location, boolean onGround) {
        this(entityId, location.getBlockX(), location.getBlockY(), location.getBlockZ(), location.getYaw(), location.getPitch(), onGround);
    }

    public WrappedPacketPlayOutEntityTeleportImpl(int entityId, int locX, int locY, int locZ, float yaw, float pitch) {
        this(entityId, locX, locY, locZ, yaw, pitch, true);
    }

    public WrappedPacketPlayOutEntityTeleportImpl(int entityId, Location location) {
        this(entityId, location, true);
    }

    public WrappedPacketPlayOutEntityTeleportImpl(WrappedPacketDataSerializer serializer) {
        PacketDataSerializer dataSerializer = (PacketDataSerializer) serializer;
        this.entityId = dataSerializer.readInt();
        this.locX = dataSerializer.readInt() / 32;
        this.locY = dataSerializer.readInt() / 32;
        this.locZ = dataSerializer.readInt() / 32;
        this.yaw = dataSerializer.readByte() / 256.0F * 360.0F;
        this.pitch = dataSerializer.readByte() / 256.0F * 360.0F;
        this.onGround = dataSerializer.readBoolean();
    }

    @Override
    public int getEntityId() {
        return entityId;
    }

    @Override
    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    @Override
    public double getLocX() {
        return locX;
    }

    @Override
    public void setLocX(double locX) {
        this.locX = locX;
    }

    @Override
    public double getLocY() {
        return locY;
    }

    @Override
    public void setLocY(double locY) {
        this.locY = locY;
    }

    @Override
    public double getLocZ() {
        return locZ;
    }

    @Override
    public void setLocZ(double locZ) {
        this.locZ = locZ;
    }

    @Override
    public float getYaw() {
        return yaw;
    }

    @Override
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    @Override
    public float getPitch() {
        return pitch;
    }

    @Override
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    @Override
    public boolean isOnGround() {
        return onGround;
    }

    @Override
    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    @Override
    public WrappedPacketDataSerializer buildData() {
        WrappedPacketDataSerializer serializer = NMSProviderImpl.INSTANCE.getDataSerializer();
        serializer.serializeVarInt(this.entityId)
                .serializeInt(MathHelper.floor(this.locX * 32.0D))
                .serializeInt(MathHelper.floor(this.locY * 32.0D))
                .serializeInt(MathHelper.floor(this.locZ * 32.0D))
                .serializeByte(MathHelper.d(this.yaw * 256.0F / 360.0F))
                .serializeByte(MathHelper.d(this.pitch * 256.0F / 360.0F))
                .serializeBoolean(this.onGround);

        return serializer;
    }

    @Override
    public Object buildPacket() {
        PacketPlayOutEntityTeleport packet = new PacketPlayOutEntityTeleport();
        try {
            packet.a((PacketDataSerializer) buildData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return packet;
    }

}