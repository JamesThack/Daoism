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
public class ItemFoodBase extends ItemFood {

    /**
     * Constructor that creates all the item attributes and adds it to the items array
     * @param amount How much the food restores hunger
     * @param saturation The saturation (How long the food lasts)
     * @param isWolfFood Boolean to say if the food can be fed to a wolf
     * @param name Unlocalised Name
     */
    public ItemFoodBase(int amount, float saturation, boolean isWolfFood, String name) {
        super(amount, saturation, isWolfFood);
        setUnlocalizedName(name);
        setRegistryName(name);
        ItemInit.ITEMS.add(this);
    }

    /**
     * Event that happens whenever a player eats the food
     * @param stack The item of food that was eaten
     * @param worldIn The world that the player is in
     * @param player The player that ate the food
     */
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
        } else {
            if (stack.getItem().getUnlocalizedName().equalsIgnoreCase("item.food_aquapill")) {
                if(PlayerMethods.isPlayerCultivator(player)) {
                    if (!player.isSneaking()) {
                        PlayerMethods.killPlayer(player);
                    }
                }
            }
        }
    }

}
