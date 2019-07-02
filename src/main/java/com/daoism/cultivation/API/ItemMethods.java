package com.daoism.cultivation.API;

import com.daoism.cultivation.ReadWrite.Entity.CultivationCapability;
import com.daoism.cultivation.ReadWrite.Entity.CultivationHandler;
import com.daoism.cultivation.ReadWrite.item.CoreCapability;
import com.daoism.cultivation.ReadWrite.item.CoreHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemMethods {

    public static CoreCapability getCultivationInstance(ItemStack item) {
        return item.getCapability(CoreHandler.CORE_CAPABILITY, null);
    }

    public static void setLevel(ItemStack item, int lev) {
        getCultivationInstance(item).setLevels(lev);
    }

    public static int getLevel(ItemStack item) {
        return getCultivationInstance(item).getLevels();
    }
}
