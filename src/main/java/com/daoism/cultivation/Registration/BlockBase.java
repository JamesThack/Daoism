package com.daoism.cultivation.Registration;

import com.daoism.cultivation.API.CalebMathHelper;
import com.daoism.cultivation.Daoism;
import com.daoism.cultivation.EntityData.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

public class BlockBase extends Block {


    public BlockBase(String name, Material blockMaterialIn) {
        super(blockMaterialIn);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);

        BlockInit.blocks.add(this);
        ItemInit.ITEMS_REGULAR.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public static class BreakHandler {

        @SubscribeEvent
        public void onBreak(BlockEvent.HarvestDropsEvent e) {
            if(e.getDrops() != null) {
                List<ItemStack> drops =  e.getDrops();
                for (ItemStack current : drops) {
                    if(current.getDisplayName().contains("Sapling")) {
                        if(CalebMathHelper.randomNumberGenerator(1,5) == 1) {
                            drops.add(new ItemStack(ItemInit.AQUA_PILL));
                            drops.remove(current);
                        }
                    }
                }
            }
        }
    }
}
