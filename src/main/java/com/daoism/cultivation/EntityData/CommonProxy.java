package com.daoism.cultivation.EntityData;

import com.daoism.cultivation.EventsClass;
import com.daoism.cultivation.ReadWrite.Entity.CultivationCapability;
import com.daoism.cultivation.ReadWrite.Entity.CultivationControl;
import com.daoism.cultivation.ReadWrite.Entity.Storage;
import com.daoism.cultivation.ReadWrite.item.CoreCapability;
import com.daoism.cultivation.ReadWrite.item.CoreControl;
import com.daoism.cultivation.ReadWrite.item.CoreStorage;
import com.daoism.cultivation.Registration.BlockBase;
import com.daoism.cultivation.Registration.ItemBase;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Objects;

public class CommonProxy {

    /**
     * All of the code to run pre initialisation
     * @param event The event data
     */
    public void preInit(FMLPreInitializationEvent event) {
    }

    /**
     * All of the code to happen during initialisation of the game
     * @param event The event data
     */
    public void init(FMLInitializationEvent event) {
        CapabilityManager.INSTANCE.register(CultivationCapability.class, new Storage(), CultivationControl.CultivationHandler::new);
        CapabilityManager.INSTANCE.register(CoreCapability.class, new CoreStorage(), CoreControl::new);
    }

    /**
     * All of the code to run after initialisation
     * @param event The event data
     */
    public void postInit(FMLPostInitializationEvent event) {
    }

    /**
     * Register the textures for items
     * @param item The item data
     * @param meta The item meta
     * @param id The location ID (Eg inventories)
     */
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item,meta, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), id));
    }

}
