package com.pepedevs.corelib.nms.v1_8_R3.packets;

import com.pepedevs.corelib.nms.packets.WrappedPacketDataSerializer;
import com.pepedevs.corelib.nms.packets.WrappedPacketPlayOutEntityMetadata;
import com.pepedevs.corelib.nms.v1_8_R3.NMSImpl;
import net.minecraft.server.v1_8_R3.DataWatcher;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;

import java.io.IOException;
import java.util.List;

public class WrappedPacketPlayOutEntityMetadataImpl extends PacketPlayOutEntityMetadata implements WrappedPacketPlayOutEntityMetadata {

    public WrappedPacketPlayOutEntityMetadataImpl(int entityId, Object watchableObjects) {
        WrappedPacketDataSerializer serializer = NMSImpl.INSTANCE.getDataSerializer();
        serializer.serializeIntToByte(entityId);

        List<DataWatcher.WatchableObject> watchableObjectList = (List<DataWatcher.WatchableObject>) watchableObjects;
        try {
            DataWatcher.a(watchableObjectList, (PacketDataSerializer) serializer);
            this.a((PacketDataSerializer) serializer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}