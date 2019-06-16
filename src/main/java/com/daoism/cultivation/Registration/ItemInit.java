package com.daoism.cultivation.Registration;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import scala.tools.nsc.settings.Final;

import java.util.ArrayList;

/**
 * Where new items are defined and also the location of the array list containing all items
 */
public class ItemInit {

    public static final ArrayList<ItemFood> ITEMS = new ArrayList<>();
    public static final Item AQUA_PILL = new ItemBase(0,0,false,"food_aquapill").setAlwaysEdible().setCreativeTab(CreativeTabs.FOOD).setMaxStackSize(1);

}
