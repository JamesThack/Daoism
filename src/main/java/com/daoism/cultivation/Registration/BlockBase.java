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

/**
 * This class handles the creation of block creation and all code related to placing/breaking blocks
 */
public class BlockBase {

    /**
     * This class handles all of the block related events
     */
    public static class BreakHandler {

        /**
         * This event runs whenever a player breaks a block
         * @param e The harvest event
         */
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
