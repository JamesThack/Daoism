package com.daoism.cultivation.Registration;

import com.daoism.cultivation.EntityData.CommonProxy;
import com.daoism.cultivation.EntityData.CultivationCapability;
import com.daoism.cultivation.EntityData.CultivationControl;
import com.daoism.cultivation.EntityData.Storage;
import com.daoism.cultivation.EventsClass;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Objects;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
    }
    @Override
    public void init(FMLInitializationEvent event) {
        CapabilityManager.INSTANCE.register(CultivationCapability.class, new Storage(), CultivationControl.CultivationHandler::new);
        MinecraftForge.EVENT_BUS.register(new ItemBase.ItemEventsHandler());
        MinecraftForge.EVENT_BUS.register(new EventsClass());
        MinecraftForge.EVENT_BUS.register(new BlockBase.BreakHandler());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
    }

    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item,meta, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), id));
    }

}
