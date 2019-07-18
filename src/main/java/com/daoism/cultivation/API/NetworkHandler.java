package com.daoism.cultivation.API;

import com.daoism.cultivation.ReadWrite.Entity.CultivationCapability;
import com.daoism.cultivation.ReadWrite.Entity.CultivationControl;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;

/**
 * This class stores a copy of player capability data locally during runtime to be accessed when sharing
 * accross client and server
 */
public class NetworkHandler {

    private ArrayList<CultivationCapability> playerData;

    /**
     * Constructs a list of player data
     */
    public NetworkHandler() {
        playerData = new ArrayList<>();
    }

    /**
     * Sends and updates all capability data, this method is usually called when a new player joins or data is updated
     * @param con A capability of cultivation data
     */
    public void sendToNetwork(CultivationCapability con) {
        boolean found = false;
        for (int i = 0; i < playerData.size(); i++) {
            CultivationCapability current = playerData.get(i);
            if (current.getName() != null && con.getName() !=null &&  current.getName().equals(con.getName())) {
                found = true;
                current.setCultivate(con.canCultivate());
                current.setCultivationLevel(con.getCultivationLevel());
                current.setFlying(con.isFlying());
                current.setName(con.getName());
            }

        } if (!found) {
            playerData.add(con);
        }
    }

    /**
     * Returns a players cultivation data from the network, if it is not found returns null
     * @param player The player
     * @return Capability data for cultivation
     */
    public CultivationCapability getFromNetwork(EntityPlayer player) {
        for (CultivationCapability current: playerData) {
            if (current.getName() !=null && current.getName().equals(player.getUniqueID().toString())) {
                return current;
            }
        } return null;
    }

}
