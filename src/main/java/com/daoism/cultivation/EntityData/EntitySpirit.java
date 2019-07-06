package com.daoism.cultivation.EntityData;

import com.daoism.cultivation.API.ItemMethods;
import com.daoism.cultivation.API.PlayerMethods;
import com.daoism.cultivation.Registration.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EntitySpirit extends EntityZombie {

    private List<ItemStack> stack;

    public EntitySpirit(World worldIn) {
        super(worldIn);
        stack = new ArrayList<>();
    }

    @Override
    public void setDropItemsWhenDead(boolean dropWhenDead) {
        super.setDropItemsWhenDead(dropWhenDead);
    }



}