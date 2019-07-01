package com.daoism.cultivation.ItemBlockData.Misc;

import com.daoism.cultivation.API.PlayerMethods;
import com.daoism.cultivation.Registration.BlockBase;
import com.daoism.cultivation.Registration.ItemBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class SpiritualIdentifier extends ItemBase {

    /**
     * Constructor, sets the unlocalised name and registers it
     *
     * @param name Unlocalised name
     */
    public SpiritualIdentifier(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
        ItemStack item = player.getHeldItem(handIn);
        if(!worldIn.isRemote) {
            if(PlayerMethods.isPlayerCultivator(player)) {
                if (player.isSneaking()) {
                    PlayerMethods.sendMsgToPlayer(player, ("Your current cultivation level is " + PlayerMethods.getEntityCultivationLevel(player)), new Style().setColor(TextFormatting.GOLD));
                } else {
                    Entity entity = PlayerMethods.entityPlayerIsLookingAt(player);
                    if(entity != null) {
                        if (entity instanceof EntityPlayer && PlayerMethods.isPlayerCultivator( (EntityPlayer) entity)) {
                            PlayerMethods.sendMsgToPlayer(player, ("The player " +
                                            ((EntityPlayer)entity).getDisplayNameString() +
                                            " has a cultivation level of " +
                                            PlayerMethods.getEntityCultivationLevel((EntityPlayer) entity)),
                                    new Style().setColor(TextFormatting.GOLD));
                        } else {
                            PlayerMethods.sendMsgToPlayer(player, "This entity does not cultivate", new Style().setColor(TextFormatting.GOLD));
                        }
                    } else {
                        PlayerMethods.sendMsgToPlayer(player, "No entity found", new Style().setColor(TextFormatting.GOLD));
                    }
                }
            } else {
                PlayerMethods.sendMsgToPlayer(player, "This magnifying glass seems mysterious, maybe if you had more spiritual understanding you could use it", new Style().setColor(TextFormatting.GOLD));
            }
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, item);
    }
}
