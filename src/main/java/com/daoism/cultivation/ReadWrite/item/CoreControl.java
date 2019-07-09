package com.daoism.cultivation.ReadWrite.item;

public class CoreControl implements CoreCapability {

    private int level;

    @Override
    public int getLevels() {
        return this.level;
    }

    @Override
    public void setLevels(int levels) {
        this.level = levels;
    }

}
