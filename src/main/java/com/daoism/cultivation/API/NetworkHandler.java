package com.daoism.cultivation.API;

import com.daoism.cultivation.ReadWrite.Entity.CultivationCapability;
import com.daoism.cultivation.ReadWrite.Entity.CultivationControl;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;

public class NetworkHandler {

    private ArrayList<CultivationCapability> playerData;

    public NetworkHandler() {
        playerData = new ArrayList<>();
    }

    public void sendToNetwork(CultivationCapability con) {
        for (int i = 0; i < playerData.size(); i++) {
            CultivationCapability current = playerData.get(i);
            if (current.getName() != null && con.getName() !=null &&  current.getName().equals(con.getName())) {
                playerData.remove(i);
                i -=1;
            }

        } playerData.add(con);
    }

    public CultivationCapability getFromNetwork(EntityPlayer player) {
        for (CultivationCapability current: playerData) {
            if (current.getName().equals(player.getUniqueID().toString())) {
                return current;
            }
        } return null;
    }
}
