package com.daoism.cultivation.Registration;

import com.daoism.cultivation.API.PlayerMethods;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * This is the class that all items are built from
 */
public class ItemBase extends ItemFood {

    /**
     * The constructor that sets the name and registery name as well as adding it to the
     * array list of items
     * @param name Name of the item (local name)
     */

    public ItemBase(int amount, float saturation, boolean isWolfFood, String name) {
        super(amount, saturation, isWolfFood);

        setUnlocalizedName(name);
        setRegistryName(name);
        ItemInit.ITEMS.add(this);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        super.onFoodEaten(stack, worldIn, player);
        if(!worldIn.isRemote) {
            if (stack.getItem().getUnlocalizedName().equalsIgnoreCase("item.food_aquapill")) {
                if(PlayerMethods.isPlayerCultivator(player)) {
                    if (player.isSneaking()) {
                        PlayerMethods.setPlayerCultivator(player, false);
                    } else {
                        PlayerMethods.killPlayer(player);
                    }
                } else {
                    PlayerMethods.setPlayerCultivator(player, true);
                }
            }
        }
    }
}
