package com.daoism.cultivation.ReadWrite.Entity;

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

    /**
     * Set if the player is currently flying with a sword
     * @param flying Boolean (should the player fly)
     */
    void setFlying(boolean flying);

    /**
     * Returns if the player is flying
     * @return Boolean if the player is flying
     */
    boolean isFlying();

    /**
     * Returns the UUID of the player
     * @return The UUID
     */
    String getName();

    /**
     * Sets the UUID of the player
     * @param name The UUID
     */
    void setName(String name);

    /**
     * Set the amount of cultivation the player has access
     * to
     * @param accessCultivation The amount of cultivation
     */
    void setAccessCultivation(int accessCultivation);

    void addAccessCultivation(int addition);

    /**
     * Sets the amount of spiritual power the player outputs
     * @param cultivationOutput The amount of spiritual power
     */
    void setCultivationOutput(int cultivationOutput);

    /**
     * Sets the amount of cultivation the player has access
     * to
     * @return The amount of cultivation
     */
    int getAccessCultivation();

    /**
     * Gets the amount of spirutual power the player outputs
     * @return The amount of spiritual power
     */
    int getCultivationOutput();

}
