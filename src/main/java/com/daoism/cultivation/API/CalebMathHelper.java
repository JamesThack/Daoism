package com.daoism.cultivation.API;

public class CalebMathHelper {

    public static int randomNumberGenerator(float min, float max) {
        return (int)( (Math.random() * ((max - min) + 1)) + min);
    }
}
