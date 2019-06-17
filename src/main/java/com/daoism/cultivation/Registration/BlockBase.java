package com.daoism.cultivation.Registration;

import com.daoism.cultivation.API.CalebMathHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class BlockBase {

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
