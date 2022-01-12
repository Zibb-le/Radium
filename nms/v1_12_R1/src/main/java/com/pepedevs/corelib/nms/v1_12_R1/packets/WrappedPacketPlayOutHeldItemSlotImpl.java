package com.pepedevs.corelib.nms.v1_12_R1.packets;

import com.pepedevs.corelib.nms.objects.WrappedPacketDataSerializer;
import com.pepedevs.corelib.nms.packets.WrappedPacketPlayOutHeldItemSlot;
import com.pepedevs.corelib.nms.v1_12_R1.NMSProviderImpl;
import net.minecraft.server.v1_12_R1.PacketDataSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutHeldItemSlot;

import java.io.IOException;

public class WrappedPacketPlayOutHeldItemSlotImpl implements WrappedPacketPlayOutHeldItemSlot {

    private int slot;

    public WrappedPacketPlayOutHeldItemSlotImpl(int slot) {
        this.slot = slot;
    }

    public WrappedPacketPlayOutHeldItemSlotImpl(WrappedPacketDataSerializer serializer) {
        this.slot = ((PacketDataSerializer) serializer).readShort();
    }

    @Override
    public int getSlot() {
        return slot;
    }

    @Override
    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public WrappedPacketDataSerializer buildData() {
        WrappedPacketDataSerializer serializer = NMSProviderImpl.INSTANCE.getDataSerializer();
        serializer.serializeShort(this.slot);
        return serializer;
    }

    @Override
    public Object buildPacket() {
        PacketPlayOutHeldItemSlot packet =  new PacketPlayOutHeldItemSlot();
        try {
            packet.a((PacketDataSerializer) buildData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return packet;
    }
}
