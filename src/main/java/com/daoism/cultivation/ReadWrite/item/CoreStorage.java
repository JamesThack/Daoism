package com.daoism.cultivation.ReadWrite.item;

import com.daoism.cultivation.ReadWrite.Entity.CultivationCapability;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class CoreStorage implements Capability.IStorage<CoreCapability> {
    @Override
    public NBTBase writeNBT(Capability<CoreCapability> capability, CoreCapability instance, EnumFacing side) {
        final NBTTagCompound tag = new NBTTagCompound();
        //tag.setBoolean("canCultivate", instance.canCultivate());
        tag.setInteger("levels", instance.getLevels());
        return tag;
    }

    @Override
    public void readNBT(Capability<CoreCapability> capability, CoreCapability instance, EnumFacing side, NBTBase nbt) {
        final NBTTagCompound tag = (NBTTagCompound) nbt;
        instance.setLevels(tag.getInteger("levels"));
    }
}
