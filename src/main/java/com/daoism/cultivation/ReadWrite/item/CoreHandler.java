package com.daoism.cultivation.ReadWrite.item;

import com.daoism.cultivation.ReadWrite.Entity.CultivationCapability;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import java.util.Objects;

public class CoreHandler implements ICapabilitySerializable<NBTTagCompound> {

    /**
     * Injects the capability to allow for an instance to be extracted
     */
    @CapabilityInject(CoreCapability.class)
    public static final Capability<CoreCapability> CORE_CAPABILITY = null;

    /**
     * Creates a new instance of the capability injection
     */
    private CoreCapability instance = Objects.requireNonNull(CORE_CAPABILITY).getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing facing) {

        return capability == CORE_CAPABILITY;
    }

    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing facing) {

        return hasCapability(capability, facing) ? CORE_CAPABILITY.<T>cast(instance) : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {

        return (NBTTagCompound) CORE_CAPABILITY.getStorage().writeNBT(CORE_CAPABILITY, instance, null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {

        CORE_CAPABILITY.getStorage().readNBT(CORE_CAPABILITY, instance, null, nbt);
    }
}
