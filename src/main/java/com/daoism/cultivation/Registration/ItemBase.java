package com.daoism.cultivation.Registration;

import ca.weblite.objc.Client;
import com.daoism.cultivation.API.PlayerMethods;
import com.daoism.cultivation.EntityData.CultivationCapability;
import ibxm.Player;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Vector;

/**
 * Item Base for normal items, contains registration and event that happens when player clicks
 */
public class ItemBase extends Item {

    /**
     * Constructor, sets the unlocalised name and registers it
     *
     * @param name Unlocalised name
     */
    public ItemBase(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);

        ItemInit.ITEMS_REGULAR.add(this);
    }

    /**
     * Inner class to deal with clicking. Seperated from main events class for simplicity
     */
    public static class ItemEventsHandler {

        /**
         * Event that runs whenever player clicks
         *
         * @param e The event data
         */
        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onInteract(PlayerInteractEvent.RightClickItem e) {
            if (!e.getEntity().getEntityWorld().isRemote) {
            }
        }

    }

}
