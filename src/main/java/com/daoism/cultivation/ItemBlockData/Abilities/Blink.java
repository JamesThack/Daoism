package com.daoism.cultivation.ItemBlockData.Abilities;

import com.daoism.cultivation.API.PlayerMethods;
import com.daoism.cultivation.Registration.ItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class Blink extends ItemBase {
    /**
     * Constructor, sets the unlocalised name and registers it
     *
     * @param name Unlocalised name
     */
    public Blink(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
        ItemStack curItem = player.getHeldItem(handIn);
        if (!worldIn.isRemote) {
            if (PlayerMethods.getEntityCultivationLevel(player) < 200) {
                PlayerMethods.sendMsgToPlayer(player, "You do not have enough cultivation to use this tome");
                return new ActionResult<>(EnumActionResult.SUCCESS, curItem);
            } else {
                int traceDistance = 20 + ((PlayerMethods.getEntityCultivationLevel(player) - 200) / 40);
                RayTraceResult MOP = player.rayTrace(traceDistance, 1.0F);
                if (!player.getEntityWorld().getBlockState(MOP.getBlockPos()).getBlock().getUnlocalizedName().equals("tile.air")) {
                    player.setPositionAndUpdate(MOP.getBlockPos().getX(), (MOP.getBlockPos().getY() + 1), MOP.getBlockPos().getZ());
                    int cooldown;
                    if(PlayerMethods.getEntityCultivationLevel(player) > 7700) {
                        cooldown = 4 * 20;
                    } else {
                        cooldown = ((8000 - PlayerMethods.getEntityCultivationLevel(player)) / 66) * 20;
                    }
                    player.getCooldownTracker().setCooldown(this, cooldown);
                }
            }
        } return new ActionResult<>(EnumActionResult.SUCCESS, curItem);

    }
}
