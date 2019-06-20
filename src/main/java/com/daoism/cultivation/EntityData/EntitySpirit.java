package com.daoism.cultivation.EntityData;

import com.daoism.cultivation.API.PlayerMethods;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntitySpirit extends Entity {

    public int cultivationHealth;

    public EntitySpirit(World worldIn) {
        super(worldIn);
    }

    public int getCultivationHealth() {
        return cultivationHealth;
    }

    public void setCultivationHealth(int cultivationHealth) {
        this.cultivationHealth = cultivationHealth;
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        this.setCultivationHealth(compound.getInteger("CultivationLevel"));
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setInteger("CultivationLevel", this.getCultivationHealth());

    }

    @Override
    public void onKillEntity(EntityLivingBase entityLivingIn) {
        super.onKillEntity(entityLivingIn);
        if (entityLivingIn instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLivingIn;
            this.cultivationHealth += PlayerMethods.getEntityCultivationLevel(player);
            PlayerMethods.sendMsgToPlayer(player, "Lol");
        }

    }
}