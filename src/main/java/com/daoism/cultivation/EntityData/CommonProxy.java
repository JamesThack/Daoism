package com.daoism.cultivation.EntityData;

import com.daoism.cultivation.EventsClass;
import com.daoism.cultivation.Registration.BlockBase;
import com.daoism.cultivation.Registration.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;

import java.util.ArrayList;

/**
 * This class is called during preInt and registers the instance for NBTTags to control cultivation and events
 */
public class CommonProxy {

    /**
     * Initialisation Process
     */
    public void init(){
        CapabilityManager.INSTANCE.register(CultivationCapability.class, new Storage(), CultivationControl.CultivationHandler::new);
        MinecraftForge.EVENT_BUS.register(new ItemBase.ItemEventsHandler());
        MinecraftForge.EVENT_BUS.register(new EventsClass());
        MinecraftForge.EVENT_BUS.register(new BlockBase.BreakHandler());
    }

    /**
     * Register the textures for items
     * @param item The item data
     * @param meta The item meta
     * @param id The location ID (Eg inventories)
     */
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item,meta, new ModelResourceLocation(item.getRegistryName(), id));
    }

}
