package com.daoism.cultivation.API;

import com.daoism.cultivation.Daoism;
import com.daoism.cultivation.ReadWrite.Entity.CultivationCapability;
import com.daoism.cultivation.ReadWrite.Entity.CultivationHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * API Class that has methods involved with interacting directly with an EntityPlayer
 */
public class PlayerMethods {

    /**
     * Method to send messages to the player
     *
     * @param player The player
     * @param text   Text to send to player
     */
    public static void sendMsgToPlayer(EntityPlayer player, String text) {
        player.sendMessage(new TextComponentString(text) {
        });
    }

    /**
     * This method sends message to player but can handle style
     *
     * @param player The player
     * @param text   Text to send to player
     * @param style  Style formats
     */
    public static void sendMsgToPlayer(EntityPlayer player, String text, Style style) {
        player.sendMessage(new TextComponentString(text).setStyle(style));
    }

    /**
     * Method to kill the player
     *
     * @param player The player
     */
    public static void killPlayer(EntityPlayer player) {
        player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(7)), 1, 30, false, false));
    }

    /**
     * Returns an instance of the CultivationCapability to handle NBTTags
     *
     * @param player The player
     * @return Instance of CultivationCapability
     */
    public static CultivationCapability getCultivationInstance(EntityPlayer player) {
        return player.getCapability(CultivationHandler.CULTIVATION_CAPABILITY, null);
    }

    /**
     * Sets the player or removes player as a cultivator
     *
     * @param player      The player
     * @param cultivation True/False if the player should be cultivator
     */
    public static void setPlayerCultivator(EntityPlayer player, boolean cultivation) {
        getCultivationInstance(player).setCultivate(cultivation);
        Daoism.handle.sendToNetwork(PlayerMethods.getCultivationInstance(player));
    }

    /**
     * Returns if the player is a cultivator
     *
     * @param player The player
     * @return Boolean if the player is a cultivator
     */
    public static boolean isPlayerCultivator(EntityPlayer player) {
        return getCultivationInstance(player).canCultivate();
    }

    /**
     * Returns the players cultivation level with a maximum value
     *
     * @param player The player
     * @param max The max value
     * @return An int of the cultivation level
     */
    public static int getEntityCultivationLevel(EntityPlayer player, int max) {
        Daoism.handle.sendToNetwork(PlayerMethods.getCultivationInstance(player));
        int val = getCultivationInstance(player).getCultivationLevel();
        if (val >= max) {
            return max;
        }
        return  val;
    }

    public static void setEntityUUID(EntityPlayer player) {
        getCultivationInstance(player).setName(player.getUniqueID().toString());
    }

    /**
     * Returns the entities cultivation level
     * @param player The player
     * @return Cultivation Level
     */
    public static int getEntityCultivationLevel(EntityPlayer player) {
        return getEntityCultivationLevel(player, 2147483647);
    }

    /**
     * Can test if a player is holding an item (On and off hand)
     *
     * @param player   The player
     * @param itemName String of the item name (item.name)
     * @param e        The instance of player interact event
     * @return Boolean if the player is holding an item
     */
    public static boolean isInHand(EntityPlayer player, Item itemName, PlayerInteractEvent e) {
        return ((player.getHeldItem(EnumHand.MAIN_HAND).getItem().equals(itemName) && e.getHand().equals(EnumHand.MAIN_HAND)) || (player.getHeldItem(EnumHand.OFF_HAND).getItem().equals(itemName) && e.getHand().equals(EnumHand.OFF_HAND)));
    }

    /**
     * Adds cultivation on to a player
     *
     * @param cult   The cultivation to add on
     * @param player The player
     */
    public static void addEntityCultivation(int cult, EntityPlayer player) {
        PlayerMethods.getCultivationInstance(player).addCultivation(cult);
        Daoism.handle.sendToNetwork(PlayerMethods.getCultivationInstance(player));
    }

    /**
     * Calls the looking at method with default values
     *
     * @param player The player
     * @return An entity if one visible, null if not
     */
    public static Entity entityPlayerIsLookingAt(EntityPlayer player) {
        return entityPlayerIsLookingAt(player, 300);
    }

    /**
     * This method returns any entity that the player is looking directly at
     * @param player The player
     * @param maxDistance The maximum distance you want to search for
     * @return An entity if there is one visible, null if not
     */
    public static Entity entityPlayerIsLookingAt(EntityPlayer player, int maxDistance) {

        for (int i = 1; i < maxDistance; i++) {
            RayTraceResult mop = player.rayTrace(i, 1.0F);
            try {
                if (!isBlockPassable(player.getEntityWorld().getBlockState(mop.getBlockPos()).getBlock())) {
                    return null;
                }
            } catch (Exception e) {
                return null;
            }
            int x = mop.getBlockPos().getX();
            int y = mop.getBlockPos().getY();
            int z = mop.getBlockPos().getZ();

            AxisAlignedBB radius = new AxisAlignedBB(x, y, z, x + 1, y + 1, z + 1);
            List<Entity> entities = player.getEntityWorld().getEntitiesWithinAABBExcludingEntity(player, radius);
            for (Entity ent : entities) {
                return ent;
            }
        }
        return null;
    }

    public static void setPlayerFlying(EntityPlayer player, boolean flying) {
        getCultivationInstance(player).setFlying(flying);
        Daoism.handle.sendToNetwork(PlayerMethods.getCultivationInstance(player));
    }

    public static boolean isPlayerFlying(EntityPlayer player) {
        return getCultivationInstance(player).isFlying();
    }

    /**
     * This method detects if a block is passable (things such as snow and air)
     * @param block The block
     * @return If the block is passable
     */
    public static boolean isBlockPassable(Block block) {
        ArrayList<Block> thruBlocks = new ArrayList<>();
        thruBlocks.add(Blocks.AIR);
        thruBlocks.add(Blocks.FIRE);
        thruBlocks.add(Blocks.VINE);
        thruBlocks.add(Blocks.TRIPWIRE);
        thruBlocks.add(Blocks.CARPET);
        thruBlocks.add(Blocks.GRASS);
        thruBlocks.add(Blocks.TALLGRASS);
        thruBlocks.add(Blocks.WATER);
        thruBlocks.add(Blocks.FLOWING_WATER);
        thruBlocks.add(Blocks.SNOW_LAYER);
        for (Block current : thruBlocks) {
            if (current.equals(block)) {
                return true;
            }
        }
        return false;
    }

}
