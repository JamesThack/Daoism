package com.daoism.cultivation.ItemBlockData.Abilities;

import com.daoism.cultivation.Registration.ItemBase;

public class QiShield extends ItemBase {
    /**
     * Constructor, sets the unlocalised name and registers it
     *
     * @param name Unlocalised name
     */
    public QiShield(String name) {
        super(name);
        this.setMaxStackSize(1);
    }
}
