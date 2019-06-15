package com.daoism.cultivation;

import com.daoism.cultivation.EntityData.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Daoism.MODID, name = Daoism.NAME, version = Daoism.VERSION)
public class Daoism
{
    public static final String MODID = "examplemod";
    public static final String NAME = "Example Mod";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "com.daoism.cultivation.EntityData.CommonProxy", serverSide = "com.daoism.cultivation.EntityData.CommonProxy")
    public static CommonProxy proxy;



    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }
}
