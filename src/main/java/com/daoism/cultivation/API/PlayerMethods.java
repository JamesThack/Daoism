package com.daoism.cultivation.API;

import com.daoism.cultivation.EntityData.CultivationCapability;
import com.daoism.cultivation.EntityData.CultivationHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

/**
 * API Class that has methods involved with interacting directly with an EntityPlayer
 */
public class PlayerMethods {

    /**
     * Method to send messages to the player
     * @param player The player
     * @param text Text to send to player
     */
    public static void sendMsgToPlayer(EntityPlayer player, String text) {
        player.sendMessage(new TextComponentString(text) {});
    }

    public static void sendMsgToPlayer(EntityPlayer player, String text, Style style) {
        player.sendMessage(new TextComponentString(text).setStyle(style));
    }

    /**
     * Method to kill the player
     * @param player The player
     */
    public static void killPlayer(EntityPlayer player) {
       player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(7)),1,30,false,false));
    }

    /**
     * Returns an instance of the CultivationCapability to handle NBTTags
     * @param player The player
     * @return Instance of CultivationCapability
     */
    public static CultivationCapability getCultivationInstance(EntityPlayer player) {
        return player.getCapability(CultivationHandler.CULTIVATION_CAPABILITY, null);
    }

    /**
     * Sets the player or removes player as a cultivator
     * @param player The player
     * @param cultivation True/False if the player should be cultivator
     */
    public static void setPlayerCultivator(EntityPlayer player, boolean cultivation) {
        getCultivationInstance(player).setCultivate(cultivation);
    }

    /**
     * Returns if the player is a cultivator
     * @param player The player
     * @return Boolean if the player is a cultivator
     */
    public static boolean isPlayerCultivator(EntityPlayer player) {
        return getCultivationInstance(player).canCultivate();
    }

    /**
     * Returns the players cultivation level
     * @param player The player
     * @return An int of the cultivation level
     */
    public static int getEntityCultivationLevel(EntityPlayer player) {
        return getCultivationInstance(player).getCultivationLevel();
    }

    public static boolean isInHand(EntityPlayer player, String itemName, PlayerInteractEvent e) {
        return ((player.getHeldItem(EnumHand.MAIN_HAND).getItem().getUnlocalizedName().equalsIgnoreCase(itemName) && e.getHand().equals(EnumHand.MAIN_HAND)) || (player.getHeldItem(EnumHand.OFF_HAND).getItem().getUnlocalizedName().equalsIgnoreCase(itemName) && e.getHand().equals(EnumHand.OFF_HAND)));
    }

    public static void addEntityCultivation(int cult, EntityPlayer player) {
        PlayerMethods.getCultivationInstance(player).addCultivation(cult);
    }

}
