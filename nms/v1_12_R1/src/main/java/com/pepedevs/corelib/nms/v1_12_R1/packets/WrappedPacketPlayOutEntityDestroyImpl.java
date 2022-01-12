package com.pepedevs.corelib.nms.v1_12_R1.packets;

import com.pepedevs.corelib.nms.objects.WrappedPacketDataSerializer;
import com.pepedevs.corelib.nms.packets.WrappedPacketPlayOutEntityDestroy;
import com.pepedevs.corelib.nms.v1_12_R1.NMSProviderImpl;
import net.minecraft.server.v1_12_R1.PacketDataSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityDestroy;

import java.io.IOException;

public class WrappedPacketPlayOutEntityDestroyImpl implements WrappedPacketPlayOutEntityDestroy {

    private int[] entityIDs;

    public WrappedPacketPlayOutEntityDestroyImpl(int... entityIDs) {
        WrappedPacketDataSerializer serializer = NMSProviderImpl.INSTANCE.getDataSerializer();
        serializer.serializeIntToByte(entityIDs.length);
        for (int entityID : entityIDs) {
            serializer.serializeIntToByte(entityID);
        }
    }

    public WrappedPacketPlayOutEntityDestroyImpl(WrappedPacketDataSerializer serializer) {
        PacketDataSerializer dataSerializer = (PacketDataSerializer) serializer;
        this.entityIDs = new int[dataSerializer.g()];
        for (int i = 0; i < this.entityIDs.length; i++) {
            this.entityIDs[i] = dataSerializer.g();
        }
    }

    @Override
    public int[] getEntityIDs() {
        return entityIDs;
    }

    @Override
    public void setEntityIDs(int[] entityIDs) {
        this.entityIDs = entityIDs;
    }

    @Override
    public WrappedPacketDataSerializer buildData() {
        WrappedPacketDataSerializer serializer = NMSProviderImpl.INSTANCE.getDataSerializer();
        serializer.serializeIntToByte(this.entityIDs.length);
        for (int entityID : this.entityIDs) {
            serializer.serializeIntToByte(entityID);
        }
        return serializer;
    }

    @Override
    public Object buildPacket() {
        PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy();
        try {
            packet.a((PacketDataSerializer) buildData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return packet;
    }

}
