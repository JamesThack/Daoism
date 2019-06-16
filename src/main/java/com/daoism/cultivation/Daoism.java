package com.daoism.cultivation;

import com.daoism.cultivation.EntityData.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Daoism.MODID, name = Daoism.NAME, version = Daoism.VERSION)

/**
 * The main class, the mod starts here
 */
public class Daoism {

    public static final String MODID = "daoism"; //The mod ID
    public static final String NAME = "Daoism"; //The mod name
    public static final String VERSION = "1.0"; //The mod version

    /**
     * This code handles the logic of the client and server side relations, for example the sharing of NBTTags
     */
    @SidedProxy(clientSide = "com.daoism.cultivation.EntityData.CommonProxy", serverSide = "com.daoism.cultivation.EntityData.CommonProxy")
    public static CommonProxy proxy;

    /**
     * All of the logic to happen at pre-initialisation
     * @param event The event data
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    }

    /**
     * All of the logic to happen at initialisation
     * @param event The event data
     */
    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

}
