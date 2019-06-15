package com.daoism.cultivation.EntityData;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CultivationHandler implements ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(CultivationCapability.class)
    public static final Capability<CultivationCapability> CULTIVATION_CAPABILITY = null;

    CultivationCapability instance = CULTIVATION_CAPABILITY.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {

        return capability == CULTIVATION_CAPABILITY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {

        return hasCapability(capability, facing) ? CULTIVATION_CAPABILITY.<T>cast(instance) : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {

        return (NBTTagCompound) CULTIVATION_CAPABILITY.getStorage().writeNBT(CULTIVATION_CAPABILITY, instance, null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {

        CULTIVATION_CAPABILITY.getStorage().readNBT(CULTIVATION_CAPABILITY, instance, null, nbt);
    }
}