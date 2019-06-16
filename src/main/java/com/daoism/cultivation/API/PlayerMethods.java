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

    public static void killPlayer(EntityPlayer player) {
       player.setDead();
    }

    public static CultivationCapability getCultivationInstance(EntityPlayer player) {
        return player.getCapability(CultivationHandler.CULTIVATION_CAPABILITY, null);
    }

    public static void setPlayerCultivator(EntityPlayer player, boolean cultivation) {
        getCultivationInstance(player).setCultivate(cultivation);
    }

    public static boolean isPlayerCultivator(EntityPlayer player) {
        return getCultivationInstance(player).canCultivate();
    }

}
