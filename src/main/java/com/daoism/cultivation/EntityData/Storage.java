package com.daoism.cultivation.EntityData;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

/**
 * Handles the reading and writing of NBTTags to the cultivation capability
 */
public class Storage implements Capability.IStorage<CultivationCapability> {

    @Override
    public NBTBase writeNBT(Capability<CultivationCapability> capability, CultivationCapability instance, EnumFacing side) {
        final NBTTagCompound tag = new NBTTagCompound();
        tag.setBoolean("canCultivate", instance.canCultivate());
        tag.setInteger("cultivationLevel", instance.getCultivationLevel());
        return tag;
    }

    @Override
    public void readNBT(Capability<CultivationCapability> capability, CultivationCapability instance, EnumFacing side, NBTBase nbt) {
        final NBTTagCompound tag = (NBTTagCompound) nbt;
        instance.setCultivate(tag.getBoolean("canCultivate"));
        instance.addCultivation(tag.getInteger("cultivationLevel"));
    }

}