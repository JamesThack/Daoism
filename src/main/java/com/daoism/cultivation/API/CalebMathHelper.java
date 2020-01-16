package com.daoism.cultivation.API;

import com.daoism.cultivation.API.MathObjects.Cube;
import ibxm.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Class to assist with mathmatical operations
 */
public class CalebMathHelper {

    /**
     * Random number generator between range
     * @param min Minimum number
     * @param max Maximum number
     * @return Random number
     */
    public static int randomNumberGenerator(float min, float max) {
        return (int)( (Math.random() * ((max - min) + 1)) + min);
    }

    /**
     * Random number generator for floats
     * @param min Minimum value
     * @param max Maximum value
     * @return New random float value
     */
    public static float randomFloatGenerator(float min, float max) {
        return (float) ( (Math.random() * ((max - min))) + min);
    }

    /**
     * Creates a cube object (see MathObjects)
     * @param size The radius from the center of the cube
     * @param coordinates The center block of the cube
     * @param world The world to store the cube in
     * @return A newly generated Cube
     */
    public static Cube generateNewCube(int size, float[] coordinates, World world) {
        if (coordinates.length < 3) {
            System.out.println("ERROR: Cube has been given size that is not 3! Only 3 coordinates allowed");
            return null;
        } else {
            return new Cube(size, coordinates, world);
        }
    }

    /**
     * Returns a cube but uses the player for the coordinates and world information
     * @param size The radius of the cube
     * @param player The player
     * @return A new cube object
     */
    public static Cube generateNewCube(int size, EntityPlayer player) {
        return generateNewCube(size, new float[]{player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ()}, player.getEntityWorld());
    }

}
