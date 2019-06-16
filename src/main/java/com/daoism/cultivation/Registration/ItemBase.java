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

public class ItemBase extends Item {


    public ItemBase(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);

        ItemInit.ITEMS_REGULAR.add(this);
    }

    public static class ItemEventsHandler {

        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onInteract(PlayerInteractEvent e) {
            if (e.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND).getItem().getUnlocalizedName().equalsIgnoreCase("item.misc_magnifying_glass")) {
                if (PlayerMethods.isPlayerCultivator(e.getEntityPlayer())) {
                    PlayerMethods.sendMsgToPlayer(e.getEntityPlayer(), ("Your current cultivation level is " + PlayerMethods.getEntityCultivationLevel(e.getEntityPlayer())));
                } else {
                    PlayerMethods.sendMsgToPlayer(e.getEntityPlayer(), "This magnifying glass seems mysterious, maybe if you had more spiritual understanding you could use it");
                }
            }

        }
    }
}
