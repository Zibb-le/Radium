package com.pepedevs.corelib.gui.inventory.item.voidaction;

import com.pepedevs.corelib.gui.inventory.Item;
import com.pepedevs.corelib.gui.inventory.action.ItemClickAction;
import org.bukkit.inventory.ItemStack;

/** Class for Filler/Void Items in ItemMenu. */
public class VoidActionItem extends Item {

    /**
     * Constructs the Void Action Item.
     *
     * <p>
     *
     * @param name Name of the Item
     * @param icon ItemStack Icon of the Item
     * @param lore Lore of the Item
     */
    public VoidActionItem(String name, ItemStack icon, String... lore) {
        super(name, icon, lore);
    }

    /**
     * Click action for Filler/Void Item.
     *
     * <p>
     *
     * @param action {@link ItemClickAction} for the Item
     */
    @Override
    public final void onClick(ItemClickAction action) {
        /* do nothing */
    }
}
