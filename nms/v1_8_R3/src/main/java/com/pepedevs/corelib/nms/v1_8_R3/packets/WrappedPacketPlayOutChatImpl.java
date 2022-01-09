package com.pepedevs.corelib.nms.v1_8_R3.packets;

import com.pepedevs.corelib.adventure.AdventureUtils;
import com.pepedevs.corelib.nms.packets.WrappedPacketPlayOutChat;
import com.pepedevs.corelib.nms.v1_8_R3.NMSImpl;
import net.kyori.adventure.text.Component;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class WrappedPacketPlayOutChatImpl extends PacketPlayOutChat implements WrappedPacketPlayOutChat {

    public WrappedPacketPlayOutChatImpl(String component) {
        super((IChatBaseComponent) NMSImpl.INSTANCE.craftChatMessageFromString(component)[0]);
    }

    public WrappedPacketPlayOutChatImpl(Component component) {
        super((IChatBaseComponent) AdventureUtils.asVanilla(component));
    }

}
