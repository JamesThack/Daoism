package com.daoism.cultivation.ItemBlockData.Sword;

import com.daoism.cultivation.API.PlayerMethods;
import com.daoism.cultivation.Registration.ItemBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ParalysisTalisman extends ItemBase {

    /**
     * Constructor, sets the unlocalised name and registers it
     *
     * @param name Unlocalised name
     */
    public ParalysisTalisman(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (!player.getEntityWorld().isRemote) {
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase entityLiving = (EntityLivingBase) entity;
                if (PlayerMethods.getEntityCultivationLevel(player) / 10 >= entityLiving.getMaxHealth()) {
                    int timer = (PlayerMethods.getEntityCultivationLevel(player, (2000 + (int) (entityLiving.getMaxHealth() * 10) )) / 100) - ((int) entityLiving.getMaxHealth() /10) + 2;
                    entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(2), timer * 20, 150, false, false));
                    player.inventory.removeStackFromSlot(player.inventory.getSlotFor(stack));
                } else {
                    PlayerMethods.sendMsgToPlayer(player, "You do not have enough cultivation to harm this entity", new Style().setColor(TextFormatting.GOLD));
                }
            }

        } return true;
    }

}


