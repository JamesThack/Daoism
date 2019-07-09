package com.daoism.cultivation.ReadWrite.item;

/**
 * Interface that is used to control the player's cultivation
 */
public interface CoreCapability {

    /**
     * Returns the cultivation level of an item.
     * @return Int of the cultivation level
     */
    int getLevels();

    /**
     * Sets the cultivation level
     * @param levels The cultivation level
     */
    void setLevels(int levels);

}
