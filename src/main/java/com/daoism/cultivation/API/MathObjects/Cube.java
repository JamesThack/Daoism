package com.daoism.cultivation.API.MathObjects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

/**
 * A math object that stores a cube within MC
 */
public class Cube {

    private int size;
    private float[] cubeCenter;
    private World world;

    /**
     * Constructor
     * @param size The radius of the cube
     * @param cubeCenter The coordinates for the center of the cube
     * @param world The world the cube is stored in
     */
    public Cube(int size, float[] cubeCenter, World world) {
        this.size = size;
        this.cubeCenter = cubeCenter;
        this.world = world;
    }

    /**
     * Returns a list of all the entities in a cube
     * @param player The player to be ignored
     * @return A list of all entities within a cube
     */
    public List<Entity> getEntitiesInCube(EntityPlayer player) {
        AxisAlignedBB radius = new AxisAlignedBB(cubeCenter[0] + size, cubeCenter[1] +  size, cubeCenter[2] + size, cubeCenter[0] - size, cubeCenter[1] -  size, cubeCenter[2] - size);
        return world.getEntitiesWithinAABBExcludingEntity(player, radius);
    }

}
