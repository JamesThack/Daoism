package com.daoism.cultivation.Registration;

import net.minecraft.item.Item;

public class ItemBase extends Item {



    public ItemBase(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);

        ItemInit.ITEMS.add(this);

    }

}
