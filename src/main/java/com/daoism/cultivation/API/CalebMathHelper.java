package com.daoism.cultivation.API;

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

}
