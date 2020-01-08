package com.daoism.cultivation.ItemBlockData.Abilities;

import com.daoism.cultivation.API.CalebMathHelper;
import com.daoism.cultivation.API.MathObjects.Cube;
import com.daoism.cultivation.API.PlayerMethods;
import com.daoism.cultivation.Registration.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
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
            Cube cube = CalebMathHelper.generateNewCube(5, player);
            List<Entity> entitiesInArea = cube.getEntitiesInCube(player);
            for (Entity entity: entitiesInArea) {
                double maxer = 4000;
                int topper = 8000;
                if (entity instanceof EntityLiving) {
                    maxer = ((EntityLiving) entity).getMaxHealth() * 200;
                    topper = (int) maxer * 2;
                }
                double y = 0;
                if (entity.getPosition().getY() - player.getPosition().getY() > -1) {
                    y = 2;
                } else {
                    y = -1;
                }
                double x = ((entity.getPosition().getX() - player.getPosition().getX())  * 0.1) * (PlayerMethods.getEntityCultivationLevel(player, topper) / maxer);
                y = (y * 0.1)  * (PlayerMethods.getEntityCultivationLevel(player, topper) / maxer);
                double z = ((entity.getPosition().getZ() - player.getPosition().getZ())  * 0.1) * (PlayerMethods.getEntityCultivationLevel(player, topper) / maxer);
                entity.fallDistance = 0;
                if (player.isSneaking()) {
                    entity.setVelocity(-x, (-y * 2), -z);
                } else {
                    entity.setVelocity(x, y, z);
                }
            }

        }
        return new ActionResult<>(EnumActionResult.SUCCESS, curItem);
    }
}
