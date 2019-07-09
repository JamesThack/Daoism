package com.daoism.cultivation.API;

import com.daoism.cultivation.ReadWrite.Entity.CultivationCapability;
import com.daoism.cultivation.ReadWrite.Entity.CultivationHandler;
import com.daoism.cultivation.ReadWrite.item.CoreCapability;
import com.daoism.cultivation.ReadWrite.item.CoreHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemMethods {

    /**
     * Returns a new instance of the cultivation core that can be modified
     * @param item Item Stack for data
     * @return Item Data
     */
    public static CoreCapability getCultivationInstance(ItemStack item) {
        return item.getCapability(CoreHandler.CORE_CAPABILITY, null);
    }

    /**
     * Sets the core level for a golden core
     * @param item The core
     * @param lev The level
     */
    public static void setLevel(ItemStack item, int lev) {
        getCultivationInstance(item).setLevels(lev);
    }

    /**
     * Returns the level of an item
     * @param item The item
     * @return The cultivation level
     */
    public static int getLevel(ItemStack item) {
        return getCultivationInstance(item).getLevels();
    }

}
