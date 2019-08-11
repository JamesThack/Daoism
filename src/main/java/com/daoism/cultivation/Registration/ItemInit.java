package com.daoism.cultivation.Registration;

import com.daoism.cultivation.ItemBlockData.Abilities.Attraction;
import com.daoism.cultivation.ItemBlockData.Abilities.Blink;
import com.daoism.cultivation.ItemBlockData.Food.GoldenCore;
import com.daoism.cultivation.ItemBlockData.Misc.SpiritualIdentifier;
import com.daoism.cultivation.ItemBlockData.Sword.FlameTalisman;
import com.daoism.cultivation.ItemBlockData.Sword.FlyingSword;
import com.daoism.cultivation.ItemBlockData.Sword.ParalysisTalisman;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import scala.tools.nsc.settings.Final;

import java.util.ArrayList;

/**
 * Where new items are defined and also the location of the array list containing all items
 */
public class ItemInit {

    //Food Items
    public static final ArrayList<ItemFood> ITEMS = new ArrayList<>();
    public static final Item AQUA_PILL = new ItemFoodBase(0,0,false,"food_aquapill").setAlwaysEdible().setCreativeTab(CreativeTabs.FOOD).setMaxStackSize(1);
    public static final Item GOLDEN_CORE = new GoldenCore(0, 0, false, "food_golden_core").setAlwaysEdible().setCreativeTab(CreativeTabs.FOOD).setMaxStackSize(1);

    //Normal Items
    public static final ArrayList<Item> ITEMS_REGULAR = new ArrayList<>();
    public static final Item CULTIVATION_IDENTIFIER = new SpiritualIdentifier("misc_magnifying_glass").setCreativeTab(CreativeTabs.MISC);
    public static final Item BLINK_ABILITY = new Blink("misc_blink_ability").setCreativeTab(CreativeTabs.COMBAT);
    public static final Item ATTRACTION_ABILITY = new Attraction("misc_attraction_ability").setCreativeTab(CreativeTabs.COMBAT);
    public static final Item FLYING_SWORD = new FlyingSword("weapon_flying_sword").setCreativeTab(CreativeTabs.COMBAT);
    public static final Item FLAME_TALISMAN = new FlameTalisman("weapon_flame_talisman").setCreativeTab(CreativeTabs.COMBAT);
    public static final Item PARALYSIS_TALISMAN = new ParalysisTalisman("weapon_paralysis_talisman").setCreativeTab(CreativeTabs.COMBAT);

}
