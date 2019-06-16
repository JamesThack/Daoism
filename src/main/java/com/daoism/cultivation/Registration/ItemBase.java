package com.daoism.cultivation.Registration;

import ca.weblite.objc.Client;
import com.daoism.cultivation.API.PlayerMethods;
import com.daoism.cultivation.EntityData.CultivationCapability;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Item Base for normal items, contains registration and event that happens when player clicks
 */
public class ItemBase extends Item {

    /**
     * Constructor, sets the unlocalised name and registers it
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
         * @param e The event data
         */
        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onInteract(PlayerInteractEvent.RightClickItem e) {
            if(!e.getEntity().getEntityWorld().isRemote) {
                if ((e.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND).getItem().getUnlocalizedName().equalsIgnoreCase("item.misc_magnifying_glass") && e.getHand().equals(EnumHand.MAIN_HAND)) || (e.getEntityPlayer().getHeldItem(EnumHand.OFF_HAND).getItem().getUnlocalizedName().equalsIgnoreCase("item.misc_magnifying_glass") && e.getHand().equals(EnumHand.OFF_HAND))) {
                    if (PlayerMethods.isPlayerCultivator(e.getEntityPlayer())) {
                        PlayerMethods.sendMsgToPlayer(e.getEntityPlayer(), ("Your current cultivation level is " + PlayerMethods.getEntityCultivationLevel(e.getEntityPlayer())));
                    } else {
                        PlayerMethods.sendMsgToPlayer(e.getEntityPlayer(), "This magnifying glass seems mysterious, maybe if you had more spiritual understanding you could use it");
                        System.out.println(e.getHand().toString());
                    }
                }
            }
        }

    }

}
