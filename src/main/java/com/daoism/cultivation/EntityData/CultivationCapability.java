package com.daoism.cultivation.EntityData;

/**
 * Interface that is used to control the player's cultivation
 */
public interface CultivationCapability {

    /**
     * Method to increase/decrease cultivation
     * @param cult the amount to increase/decrease cultivation
     */
    void addCultivation(int cult);

    /**
     * Set if the player can cultivate or not
     * @param cult true/false if the player can cultivate
     */
    void setCultivate(boolean cult);

    /**
     * Returns if the player is able to cultivate
     * @return returns a boolean if a player can cultivate
     */
    boolean canCultivate();

    /**
     * Method to return cultivation level
     * @return Int of cultivation level
     */
    int getCultivationLevel();

    /**
     * Method to set the players cultivation level
     */
    void setCultivationLevel(int cult);

}
