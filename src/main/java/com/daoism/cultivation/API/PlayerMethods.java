package com.daoism.cultivation.API;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

public class PlayerMethods {

    public static void sendMsgToPlayer(EntityPlayer player, String text)
    {
        player.sendMessage(new TextComponentString(text) {});
    }
}
