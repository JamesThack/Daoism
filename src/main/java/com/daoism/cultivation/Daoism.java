package com.daoism.cultivation;

import com.daoism.cultivation.Commands.DaoismCommand;
//import com.daoism.cultivation.API.RenderHandler;
import com.daoism.cultivation.EntityData.CommonProxy;
import com.daoism.cultivation.EntityData.EntityInit;
import com.daoism.cultivation.Registration.ClientProxy;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.handshake.FMLHandshakeMessage;

@Mod(modid = Daoism.MODID, name = Daoism.NAME, version = Daoism.VERSION)

/**
 * The main class, the mod starts here
 */
public class Daoism {

    @Mod.Instance
    public static Daoism instance;

    static final String MODID = "daoism"; //The mod ID
    static final String NAME = "Daoism"; //The mod name
    static final String VERSION = "1.0"; //The mod version

    /**
     * This code handles the logic of the client and server side relations, for example the sharing of NBTTags
     */
    @SidedProxy(clientSide = "com.daoism.cultivation.Registration.ClientProxy", serverSide = "com.daoism.cultivation.Registration.CommonProxy")
    public static CommonProxy proxy;

    /**
     * All of the logic to happen at pre-initialisation
     * @param event The event data
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        EntityInit.registerEntities();
        proxy.preInit(event);
    }

    /**
     * All of the logic to happen at initialisation
     * @param event The event data
     */
    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }

    /**
     * All the logic to happen right when the server starts
     * @param event The event data
     */
    @EventHandler
    public void start(FMLServerStartingEvent event) {
        event.registerServerCommand(new DaoismCommand());
    }

}
