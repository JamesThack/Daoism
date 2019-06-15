package com.daoism.cultivation;

import com.daoism.cultivation.EntityData.CultivationCapability;
import com.daoism.cultivation.EntityData.CultivationHandler;
import com.daoism.cultivation.API.PlayerMethods;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventsClass {


    public EventsClass() {
    }

    @SubscribeEvent
    public void playerData(PlayerSleepInBedEvent e) {
        if (e.getEntityLiving() instanceof EntityPlayer) {
            CultivationCapability cult = e.getEntityPlayer().getCapability(CultivationHandler.CULTIVATION_CAPABILITY, null);
            cult.setCultivate(true);
        }
    }

    @SubscribeEvent
    public void interact(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent e) {
        EntityPlayer player = e.player;
        CultivationCapability cult = player.getCapability(CultivationHandler.CULTIVATION_CAPABILITY, null);
        if (cult.canCultivate()) {
                PlayerMethods.sendMsgToPlayer(e.player, "You just cultivated");
        } else {
            PlayerMethods.sendMsgToPlayer(e.player, "You can't cultivate");
        }
        }



    @SubscribeEvent
    public void onClone(PlayerEvent.Clone e) {
            EntityPlayer player = e.getEntityPlayer();
            CultivationCapability cult = player.getCapability(CultivationHandler.CULTIVATION_CAPABILITY, null);
            CultivationCapability oldCult = e.getOriginal().getCapability(CultivationHandler.CULTIVATION_CAPABILITY, null);
            cult.setCultivate(oldCult.canCultivate());
    }



    @SubscribeEvent
    public void onAttach(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(new ResourceLocation(Daoism.MODID, Daoism.NAME), new CultivationHandler());
        }
    }
}
