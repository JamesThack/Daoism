package com.daoism.cultivation.Registration;

import net.minecraft.item.Item;

/**
 * This is the class that all items are built from
 */
public class ItemBase extends Item {

    /**
     * The constructor that sets the name and registery name as well as adding it to the
     * array list of items
     * @param name Name of the item (local name)
     */
    public ItemBase(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        ItemInit.ITEMS.add(this);
    }

}
