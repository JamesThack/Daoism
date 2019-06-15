package com.daoism.cultivation.Registration;

import com.daoism.cultivation.Daoism;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray((new Item[0])));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Item item : ItemInit.ITEMS) {
            try {
                Daoism.proxy.registerItemRenderer(item, 0 , "inventories");
            } catch(Exception e) {
                //Really fucking hope the code doesn't get here lmao
            }
        }
    }
}
