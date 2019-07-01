package com.daoism.cultivation.ItemBlockData.Abilities;

import com.daoism.cultivation.API.PlayerMethods;
import com.daoism.cultivation.Registration.ItemBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Attraction extends ItemBase {
    /**
     * Constructor, sets the unlocalised name and registers it
     *
     * @param name Unlocalised name
     */
    public Attraction(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
        ItemStack curItem = player.getHeldItem(handIn);
        if (!worldIn.isRemote) {
            Entity entity = PlayerMethods.entityPlayerIsLookingAt(player, (PlayerMethods.getEntityCultivationLevel(player )/ 25));
            if (entity != null) {
                Vec3d lookVec = player.getLookVec();
                double maxer = 4000;
                int topper = 8000;
                if (entity instanceof EntityLiving) {
                    maxer = ((EntityLiving) entity).getMaxHealth() * 200;
                    System.out.println(maxer);
                    topper = (int) maxer * 2;
                    System.out.println(topper);
                }
                double x = ((lookVec.x * 0.3) * (PlayerMethods.getEntityCultivationLevel(player, topper) / maxer));
                double y = (((lookVec.y + 0.5) * 0.6) * (PlayerMethods.getEntityCultivationLevel(player, topper) / maxer));
                double z = ((lookVec.z * 0.3) * (PlayerMethods.getEntityCultivationLevel(player, topper) / maxer));
                entity.fallDistance = 0;
                if (player.isSneaking()) {
                    entity.setVelocity(-x, (-y * 2), -z);
                } else {
                    entity.setVelocity(x, y, z);
                }
            }
        } return new ActionResult<>(EnumActionResult.SUCCESS, curItem);
    }
}
