package com.daoism.cultivation.ItemBlockData.Sword;

import com.daoism.cultivation.API.PlayerMethods;
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
     *
     * @param name Unlocalised name
     */
    public FlyingSword(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(!worldIn.isRemote) {
                Vec3d lookVec = playerIn.getLookVec();
                PlayerFly flying = new PlayerFly(playerIn);
                if(PlayerMethods.getEntityCultivationLevel(playerIn) > 10000) {
                    double maxer = 10000;
                    int topper = 100000;
                    double x = ((lookVec.x * 0.3) * (PlayerMethods.getEntityCultivationLevel(playerIn, topper) / maxer));
                    double y = (((lookVec.y * 0.6)) * (PlayerMethods.getEntityCultivationLevel(playerIn, topper) / maxer));
                    double z = ((lookVec.z * 0.3) * (PlayerMethods.getEntityCultivationLevel(playerIn, topper) / maxer));
                    playerIn.fallDistance = 0;
                    if (y < -0.5) {
                        y += ((-0.5 - y) / 2);
                    }
                    System.out.println(x);
                    System.out.println(y);
                    System.out.println(z);
                    System.out.println("Lmao");
                    playerIn.setVelocity(x, y, z);
                    playerIn.velocityChanged = true;
                } else {
                    EntityItem drop = new EntityItem(worldIn, playerIn.getPosition().getX(), playerIn.getPosition().getY() + 2, playerIn.getPosition().getZ(), playerIn.getHeldItem(handIn));
                    drop.setPickupDelay(20);
                    worldIn.spawnEntity(drop);
                    playerIn.setHeldItem(handIn, new ItemStack(Items.AIR, 1));
                }

        }
        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    public class PlayerFly implements Runnable {

        String uuid;
        World world;

        public PlayerFly(EntityPlayer player) {
            this.uuid = player.getUniqueID().toString();
            this.world = player.getEntityWorld();
        }

        @Override
        public void run() {
            try {
                EntityPlayer player = world.getPlayerEntityByUUID(UUID.fromString(uuid));
                PlayerMethods.sendMsgToPlayer(player, "Hey");
            } catch (Exception e) {
                return;
            }
        }
    }

}
