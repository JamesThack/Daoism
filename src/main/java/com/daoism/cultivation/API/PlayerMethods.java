package com.daoism.cultivation.API;

import com.daoism.cultivation.EntityData.CultivationCapability;
import com.daoism.cultivation.EntityData.CultivationHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

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

    /**
     * Method to kill the player
     * @param player The player
     */
    public static void killPlayer(EntityPlayer player) {
       player.setDead();
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

}
