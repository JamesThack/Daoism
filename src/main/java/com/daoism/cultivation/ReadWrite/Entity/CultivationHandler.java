package com.daoism.cultivation.ReadWrite.Entity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Handles the injection of a capability and input/output of NBTTags
 */
public class CultivationHandler implements ICapabilitySerializable<NBTTagCompound> {

    /**
     * Injects the capability to allow for an instance to be extracted
     */
    @CapabilityInject(CultivationCapability.class)
    public static final Capability<CultivationCapability> CULTIVATION_CAPABILITY = null;

    /**
     * Creates a new instance of the capability injection
     */
    private CultivationCapability instance = Objects.requireNonNull(CULTIVATION_CAPABILITY).getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing facing) {

        return capability == CULTIVATION_CAPABILITY;
    }

    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing facing) {

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