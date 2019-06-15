package com.daoism.cultivation.API;

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

}
