package com.daoism.cultivation.Registration;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static final List<Block> blocks = new ArrayList<Block>();

    public static final Block pill_furnace = new BlockPillFurnace("pill_furnace");
}
