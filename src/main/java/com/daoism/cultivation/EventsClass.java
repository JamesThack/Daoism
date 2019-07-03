package com.daoism.cultivation;

import com.daoism.cultivation.API.ItemMethods;
import com.daoism.cultivation.API.PlayerMethods;
import com.daoism.cultivation.EntityData.EntitySpirit;
import com.daoism.cultivation.ReadWrite.Entity.CultivationCapability;
import com.daoism.cultivation.ReadWrite.Entity.CultivationHandler;
import com.daoism.cultivation.ReadWrite.item.CoreHandler;
import com.daoism.cultivation.Registration.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * This class is the listener for all the events of the mod
 */
public class EventsClass {

    /**
     * This method is important, it ensures that whenever a player logs out/dies/changes dimension the NBT data is
     * copied over and not lost
      * @param e The event data
     */
    @SubscribeEvent
    public void onClone(PlayerEvent.Clone e) {
            EntityPlayer player = e.getEntityPlayer();
            CultivationCapability cult = player.getCapability(CultivationHandler.CULTIVATION_CAPABILITY, null);
            CultivationCapability oldCult = e.getOriginal().getCapability(CultivationHandler.CULTIVATION_CAPABILITY, null);
            cult.setCultivate(oldCult.canCultivate());
            cult.setCultivationLevel(oldCult.getCultivationLevel());
    }

    /**
     * This method ties the entity data to the player
     * @param event The event data
     */
    @SubscribeEvent
    public void onAttach(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(new ResourceLocation(Daoism.MODID, Daoism.NAME), new CultivationHandler());
        }
    }

    @SubscribeEvent
    public void onAttachItem(AttachCapabilitiesEvent<ItemStack> e) {
        if (e.getObject().getItem().equals(ItemInit.GOLDEN_CORE)) {
            e.addCapability(new ResourceLocation(Daoism.MODID, Daoism.NAME), new CoreHandler());
        }

    }

    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent e) {
        if(!e.getEntityLiving().getEntityWorld().isRemote) {
            BlockPos pos = e.getEntityLiving().getPosition();
            //EntitySpirit entity = new EntitySpirit(e.getEntityLiving().getEntityWorld());
            //e.getEntityLiving().getEntityWorld().spawnEntity(entity);
            //entity.setMaxHealth(1);
            //entity.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
            ItemStack item = new ItemStack(ItemInit.GOLDEN_CORE, 1);
            ItemMethods.setLevel(item, (int) e.getEntityLiving().getMaxHealth() * 2);
            item.setStackDisplayName( ("Golden Core Level " + (int) e.getEntityLiving().getMaxHealth() * 2) );
            EntityItem drop = new EntityItem(e.getEntityLiving().getEntityWorld(), pos.getX(), pos.getY(), pos.getZ(), item);
            e.getEntityLiving().getEntityWorld().spawnEntity(drop);
        }
    }

}
