package com.pepedevs.corelib.nms.packets;

public interface WrappedPacketPlayOutEntityHeadRotation extends WrappedPacket {

    int getEntityId();

    void setEntityId(int entityId);

    float getHeadRotation();

    void setHeadRotation(float headRotation);

}
