package com.daoism.cultivation.Registration;

import com.daoism.cultivation.EntityData.CommonProxy;
import com.daoism.cultivation.ReadWrite.Entity.CultivationCapability;
import com.daoism.cultivation.ReadWrite.Entity.CultivationControl;
import com.daoism.cultivation.ReadWrite.Entity.Storage;
import com.daoism.cultivation.ReadWrite.item.CoreCapability;
import com.daoism.cultivation.ReadWrite.item.CoreControl;
import com.daoism.cultivation.ReadWrite.item.CoreStorage;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ServerProxy extends CommonProxy {

    @Override
    public void init(FMLInitializationEvent event) {
        CapabilityManager.INSTANCE.register(CultivationCapability.class, new Storage(), CultivationControl.CultivationHandler::new);
        CapabilityManager.INSTANCE.register(CoreCapability.class, new CoreStorage(), CoreControl::new);
    }
}
