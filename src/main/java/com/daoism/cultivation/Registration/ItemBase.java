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

                /**
                 * The code that handles magnifying glass events
                 */
                if (PlayerMethods.isInHand(e.getEntityPlayer(), "item.misc_magnifying_glass", e)) {
                    if(PlayerMethods.isPlayerCultivator(e.getEntityPlayer())) {
                        if (e.getEntityPlayer().isSneaking()) {
                            PlayerMethods.sendMsgToPlayer(e.getEntityPlayer(), ("Your current cultivation level is " + PlayerMethods.getEntityCultivationLevel(e.getEntityPlayer())), new Style().setColor(TextFormatting.GOLD));
                        } else {
                            Entity entity = PlayerMethods.entityPlayerIsLookingAt(e.getEntityPlayer());
                            if(entity != null) {
                                if (entity instanceof EntityPlayer && PlayerMethods.isPlayerCultivator( (EntityPlayer) entity)) {
                                    PlayerMethods.sendMsgToPlayer(e.getEntityPlayer(), ("The player " +
                                            ((EntityPlayer)entity).getDisplayNameString() +
                                            " has a cultivation level of " +
                                            PlayerMethods.getEntityCultivationLevel((EntityPlayer) entity)),
                                            new Style().setColor(TextFormatting.GOLD));
                                } else {
                                    PlayerMethods.sendMsgToPlayer(e.getEntityPlayer(), "This entity does not cultivate", new Style().setColor(TextFormatting.GOLD));
                                }
                            } else {
                                PlayerMethods.sendMsgToPlayer(e.getEntityPlayer(), "No entity found", new Style().setColor(TextFormatting.GOLD));
                            }
                        }
                    } else {
                        PlayerMethods.sendMsgToPlayer(e.getEntityPlayer(), "This magnifying glass seems mysterious, maybe if you had more spiritual understanding you could use it", new Style().setColor(TextFormatting.GOLD));
                    }
                }
            }
        }

    }

}
