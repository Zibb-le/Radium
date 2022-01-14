package com.pepedevs.corelib.npc;

import com.pepedevs.corelib.nms.packets.WrappedPacketPlayOutEntityDestroy;
import com.pepedevs.corelib.nms.packets.WrappedPacketPlayOutEntityTeleport;
import com.pepedevs.corelib.nms.packets.WrappedPacketPlayOutSpawnEntityLiving;
import com.pepedevs.corelib.utils.math.Vector;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.UUID;

public class EntityNPC extends NpcBase {

    private final EntityType entityType;

    public EntityNPC(EntityType entityType, Location location) {
        super(location);
        this.entityType = entityType;
    }

    public EntityType getEntityType() {
        return this.entityType;
    }

    @Override
    public void spawn() {
        for (Player player : this.location.getWorld().getPlayers()) {
            this.view(player);
        }
    }

    @Override
    public void teleport(Location location) {
        if (this.location.equals(location))
            return;

        this.location = location;
        for (UUID uuid : this.shown) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null) {
                this.changeLocation(player);
            } else {
                this.shown.remove(uuid);
            }
        }
    }

    @Override
    public void lookAt(Vector direction) {

    }

    @Override
    public void destroy() {
        for (UUID uuid : this.shown) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null) {
                this.destroy(player);
            }
        }
        this.shown.clear();
    }

    @Override
    public void hide(Player player) {

    }

    @Override
    public void show(Player player) {

    }

    @Override
    protected void view(Player player) {
        if (this.shown.contains(player.getUniqueId()))
            return;

        WrappedPacketPlayOutSpawnEntityLiving packet = PACKET_PROVIDER.getNewSpawnEntityLivingPacket(this.getEntityId(), this.getUuid(), this.entityType, this.location, this.location.getYaw(), NMS_PROVIDER.getDataWatcher());
        NMS_PROVIDER.getPlayer(player).sendPacket(packet);
        this.shown.add(player.getUniqueId());
    }

    @Override
    protected void destroy(Player player) {
        if (!this.shown.contains(player.getUniqueId()))
            return;

        WrappedPacketPlayOutEntityDestroy packet = PACKET_PROVIDER.getNewEntityDestroyPacket(this.getEntityId());
        NMS_PROVIDER.getPlayer(player).sendPacket(packet);
        this.shown.remove(player.getUniqueId());
    }

    @Override
    protected void changeLocation(Player player) {
        if (this.shown.contains(player.getUniqueId()))
            return;

        WrappedPacketPlayOutEntityTeleport packet = PACKET_PROVIDER.getNewEntityTeleportPacket(this.getEntityId(), this.location);
        NMS_PROVIDER.getPlayer(player).sendPacket(packet);
    }

}