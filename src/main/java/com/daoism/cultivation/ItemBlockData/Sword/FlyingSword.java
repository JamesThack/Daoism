package com.daoism.cultivation.ItemBlockData.Sword;

import com.daoism.cultivation.API.PlayerMethods;
import com.daoism.cultivation.Daoism;
import com.daoism.cultivation.ReadWrite.item.CoreStorage;
import com.daoism.cultivation.Registration.ItemBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.UUID;

public class FlyingSword extends ItemBase {

    /**
     * Constructor, sets the unlocalised name and registers it
     * @param name Unlocalised name
     */
    public FlyingSword(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    /**
     * This code runs when the sword is right clicked, enables the capability data to register the player as flying
     * (all of the other flying code is located in events)
     * @param worldIn The world
     * @param playerIn The player
     * @param handIn The hand
     * @return If the action was successful
     */
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(!worldIn.isRemote) {
                if (PlayerMethods.isPlayerFlying(playerIn)) {
                    PlayerMethods.setPlayerFlying(playerIn, false);
                } else {
                    PlayerMethods.setPlayerFlying(playerIn, true);
                }
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

}
