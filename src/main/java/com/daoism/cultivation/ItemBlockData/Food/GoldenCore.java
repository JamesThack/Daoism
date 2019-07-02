package com.daoism.cultivation.ItemBlockData.Food;

import com.daoism.cultivation.API.ItemMethods;
import com.daoism.cultivation.API.PlayerMethods;
import com.daoism.cultivation.Registration.ItemFoodBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class GoldenCore extends ItemFoodBase {

    /**
     * Constructor that creates all the item attributes and adds it to the items array
     *
     * @param amount     How much the food restores hunger
     * @param saturation The saturation (How long the food lasts)
     * @param isWolfFood Boolean to say if the food can be fed to a wolf
     * @param name       Unlocalised Name
     */

    private int coreLevel;

    public GoldenCore(int amount, float saturation, boolean isWolfFood, String name) {
        super(amount, saturation, isWolfFood, name);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if(!worldIn.isRemote) {
            PlayerMethods.sendMsgToPlayer(player, "Ye");
            PlayerMethods.sendMsgToPlayer(player , ("Level: " + ItemMethods.getLevel(stack)));
        }
    }

//    @Override
//    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
//        ItemStack item = playerIn.getHeldItem(handIn);
//        if (!worldIn.isRemote) {
//            ItemMethods.setLevel(item, (int) playerIn.getHealth());
//        }
//        return new ActionResult<>(EnumActionResult.SUCCESS, item);
//    }




}
