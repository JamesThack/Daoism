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

                    /**
                     * The code that handles the blink tome
                     */
                } else if (PlayerMethods.isInHand(e.getEntityPlayer(), "item.misc_blink_ability", e)) {
                    EntityPlayer player = e.getEntityPlayer();
                    if(PlayerMethods.getEntityCultivationLevel(player) < 200) {
                        PlayerMethods.sendMsgToPlayer(player, "You do not have enough cultivation to use this tome");
                    } else {
                        int traceDistance = 20 + ((PlayerMethods.getEntityCultivationLevel(player) - 200) /4 );
                        RayTraceResult MOP = player.rayTrace(traceDistance, 1.0F);
                        if(!player.getEntityWorld().getBlockState(MOP.getBlockPos()).getBlock().getUnlocalizedName().equals("tile.air")) {
                              player.setPositionAndUpdate(MOP.getBlockPos().getX(), (MOP.getBlockPos().getY() + 1), MOP.getBlockPos().getZ());
                    }
                    }
                    /**
                     * The code that handles the attraction tome
                     */
                } else if(PlayerMethods.isInHand(e.getEntityPlayer(), "item.misc_attraction_ability", e)) {
                    Entity entity = PlayerMethods.entityPlayerIsLookingAt(e.getEntityPlayer(), (PlayerMethods.getEntityCultivationLevel(e.getEntityPlayer() )/ 25));
                    if (entity != null) {
                        Vec3d lookVec = e.getEntityPlayer().getLookVec();
                        double maxer = 4000;
                        int topper = 8000;
                        if (entity instanceof  EntityLiving) {
                            maxer = ((EntityLiving) entity).getMaxHealth() * 200;
                            System.out.println(maxer);
                            topper = (int) maxer * 2;
                            System.out.println(topper);
                        }
                        double x = ((lookVec.x * 0.3) * (PlayerMethods.getEntityCultivationLevel(e.getEntityPlayer(), topper) / maxer));
                        double y = (((lookVec.y + 0.5) * 0.6) * (PlayerMethods.getEntityCultivationLevel(e.getEntityPlayer(), topper) / maxer));
                        double z = ((lookVec.z * 0.3) * (PlayerMethods.getEntityCultivationLevel(e.getEntityPlayer(), topper) / maxer));
                        if (e.getEntityPlayer().isSneaking()) {
                            entity.setVelocity(-x, (-y * 2), -z);
                        } else {
                            entity.setVelocity(x, y, z);
                        }
                    }
                }
            }
        }

    }

}
