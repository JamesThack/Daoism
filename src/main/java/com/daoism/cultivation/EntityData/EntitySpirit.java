package com.daoism.cultivation.EntityData;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the entity spirit, the mob that spawns and drops a cultivation core.
 */
public class EntitySpirit extends EntityZombie {

    /**
     * Constructs the spirit entity
     * @param worldIn The world they are located in
     */
    public EntitySpirit(World worldIn) {
        super(worldIn);
    }

}