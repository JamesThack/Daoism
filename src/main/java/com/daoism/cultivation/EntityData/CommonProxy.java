package com.daoism.cultivation.EntityData;

import com.daoism.cultivation.EventsClass;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CommonProxy {

    public void init(){
        CapabilityManager.INSTANCE.register(CultivationCapability.class, new Storage(), CultivationControl.CultivationHandler::new);
        MinecraftForge.EVENT_BUS.register(new EventsClass());
    }
}
