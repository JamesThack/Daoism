package com.daoism.cultivation.Registration;

import com.daoism.cultivation.Daoism;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Another events class for initialisation of items
 */
@Mod.EventBusSubscriber
public class RegistryHandler {

    /**
     * This event is called whenever an item needs to be registered, this event registers
     * all items in the array list
     * @param event The event data
     */
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray((new Item[0])));
        event.getRegistry().registerAll(ItemInit.ITEMS_REGULAR.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> e) {
        e.getRegistry().registerAll(BlockInit.blocks.toArray(new Block[0]));
    }

    /**
     * Method is called to render models using proxy method
     * @param event The event data
     */
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Item item : ItemInit.ITEMS) {
            try {
                Daoism.proxy.registerItemRenderer(item, 0 , "inventories");
            } catch(Exception e) {
                //Really fucking hope the code doesn't get here lmao
            }
        }

        for (Item item : ItemInit.ITEMS_REGULAR) {
            try {
                Daoism.proxy.registerItemRenderer(item, 0 , "inventories");
            } catch(Exception e) {
                //Really fucking hope the code doesn't get here lmao
            }
        }

        for(Block block : BlockInit.blocks) {
            try {
                Daoism.proxy.registerItemRenderer(Item.getItemFromBlock(block), 0 , "inventories");
            }catch(Exception e) {
                //Really fucking hope the code doesn't get here lmao
            }
        }

    }

}
