package com.daoism.cultivation.ItemBlockData.Abilities;

import com.daoism.cultivation.API.CalebMathHelper;
import com.daoism.cultivation.API.MathObjects.Cube;
import com.daoism.cultivation.API.PlayerMethods;
import com.daoism.cultivation.Registration.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.List;

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

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
        ItemStack curItem = player.getHeldItem(handIn);
        if (!worldIn.isRemote) {
            Block block = PlayerMethods.blockPlayerIsLookingAt(player, 3000);
            if (block !=null) {

            }
            Cube cube = CalebMathHelper.generateNewCube(5, player);
            List<Entity> entitiesInArea = cube.getEntitiesInCube(player);
            for (Entity current: entitiesInArea) {
                System.out.println(current.getName());
            }

        }
        return new ActionResult<>(EnumActionResult.SUCCESS, curItem);
    }
}
