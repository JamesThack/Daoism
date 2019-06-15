package com.daoism.cultivation.Registration;

import net.minecraft.item.Item;
import scala.tools.nsc.settings.Final;

import java.util.ArrayList;

/**
 * Where new items are defined and also the location of the array list containing all items
 */
public class ItemInit {

    public static final ArrayList<Item> ITEMS = new ArrayList<>();
    public static final Item AQUA_PILL = new ItemBase("food_aquapill");

}
