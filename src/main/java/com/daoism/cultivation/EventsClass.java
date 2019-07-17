package com.daoism.cultivation;

import com.daoism.cultivation.API.ItemMethods;
import com.daoism.cultivation.API.PlayerMethods;
import com.daoism.cultivation.EntityData.EntitySpirit;
import com.daoism.cultivation.EntityData.Models.ModelWings;
import com.daoism.cultivation.ReadWrite.Entity.CultivationCapability;
import com.daoism.cultivation.ReadWrite.Entity.CultivationHandler;
import com.daoism.cultivation.ReadWrite.item.CoreHandler;
import com.daoism.cultivation.Registration.ItemInit;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.server.command.ForgeCommand;

import java.util.ArrayList;

/**
 * This class is the listener for all the events of the mod
 */
public class EventsClass {

    public static ArrayList<EntityPlayer> onlinePlayers;

    public EventsClass() {
        onlinePlayers = new ArrayList<>();
    }

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
            cult.setFlying(oldCult.isFlying());
            cult.setName(oldCult.getName());
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
    public void onLogin(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent e) {
        if (!e.player.getEntityWorld().isRemote) {
            PlayerMethods.setEntityUUID(e.player);
            Daoism.handle.sendToNetwork(PlayerMethods.getCultivationInstance(e.player));
            System.out.println(PlayerMethods.getEntityCultivationLevel(e.player));
            onlinePlayers.add(e.player);
        }
    }

    @SubscribeEvent
    public void onLogout(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent e) {
        if (!e.player.getEntityWorld().isRemote && onlinePlayers.contains(e.player)) {
            onlinePlayers.remove(e.player);
        }
    }

    /**
     * This method ties the item data to cores
     * @param e The event data
     */
    @SubscribeEvent
    public void onAttachItem(AttachCapabilitiesEvent<ItemStack> e) {
        if (e.getObject().getItem().equals(ItemInit.GOLDEN_CORE)) {
            e.addCapability(new ResourceLocation(Daoism.MODID, Daoism.NAME), new CoreHandler());
        }

    }

    /**
     * This event directly handles the attacking of an entity. Damage can't be controlled here but the event can be cancelled
     * @param e The event data
     */
    @SubscribeEvent
    public void onEntityAttack(LivingAttackEvent e) {
        if(e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            if (e.getSource().equals(DamageSource.FALL)) {
                float total = e.getAmount();
                for (int i = 0; i < PlayerMethods.getEntityCultivationLevel(player); i+= 1000) {
                    total -=1;
                    System.out.println(total);
                    if (total < 0) {
                        System.out.println("LOL");
                        e.setCanceled(true);
                        break;
                    }
                }
            }
        }
    }

    /**
     * This event handles an entity being damaged, here you can change the damage
     * @param e The event data
     */
    @SubscribeEvent
    public void onEntityDamage(LivingHurtEvent e) {
        if(e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            if (e.getSource().equals(DamageSource.FALL)) {
                float total = e.getAmount();
                for (int i = 0; i < PlayerMethods.getEntityCultivationLevel(player); i+= 1000) {
                    total -=1;
                    System.out.println(total);
                    if (total < 0) {
                        System.out.println("LOL");
                        e.setCanceled(true);
                        total = 0;
                        break;
                    }
                } e.setAmount(total);
            }
        }
        if(e.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer attacker = (EntityPlayer) e.getSource().getTrueSource();
            if(attacker.getHeldItemMainhand().getItem().equals(ItemInit.FLYING_SWORD)) {
                e.setAmount( (int)  (PlayerMethods.getEntityCultivationLevel(attacker) / 1000 ) );
            }
        }
    }

    public static ModelBase modelAngelWings = new ModelWings();

    @SubscribeEvent
    public void renderPlayerPre(RenderPlayerEvent.Pre e) {
        EntityPlayer player = e.getEntityPlayer();
            if (PlayerMethods.isPlayerFlying(player)) {
                PlayerMethods.sendMsgToPlayer(player, "Ok");
                modelAngelWings.render(player, 0, 0, 0, player.renderYawOffset, player.rotationPitch, 0.0625F);
            } else {
                System.out.println(PlayerMethods.getEntityCultivationLevel(player));
                System.out.println(Daoism.handle.getFromNetwork(player).getName());
                CultivationCapability cap = player.getCapability(CultivationHandler.CULTIVATION_CAPABILITY, null);
                System.out.println(cap.getCultivationLevel());
                PlayerMethods.sendMsgToPlayer(player, "Not Ok");
            }

    }

    /**
     * Whenever an entity dies this code is run
     * @param e The event data
     */
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

    @SubscribeEvent
    public void onTick(TickEvent e) {
        for (EntityPlayer playerIn : onlinePlayers) {
            World worldIn = playerIn.getEntityWorld();
            if (!worldIn.isRemote && PlayerMethods.isPlayerFlying(playerIn)) {
                if (!playerIn.isSneaking()) {
                    playerIn.setNoGravity(true);
                    playerIn.setVelocity(0,0,0);
                    playerIn.velocityChanged = true;
                    playerIn.fallDistance = 0;
                } else {
                    playerIn.setNoGravity(false);
                    Vec3d lookVec = playerIn.getLookVec();
                    if (PlayerMethods.getEntityCultivationLevel(playerIn) > 10000) {
                        double maxer = 10000;
                        int topper = 100000;
                        double x = ((lookVec.x * 0.3) * (PlayerMethods.getEntityCultivationLevel(playerIn, topper) / maxer));
                        double y = (((lookVec.y * 0.6)) * (PlayerMethods.getEntityCultivationLevel(playerIn, topper) / maxer));
                        double z = ((lookVec.z * 0.3) * (PlayerMethods.getEntityCultivationLevel(playerIn, topper) / maxer));
                        playerIn.fallDistance = 0;
                        if (y < -0.5) {
                            y += ((-0.5 - y) / 2);
                        }
                        playerIn.setVelocity(x, y, z);
                        playerIn.velocityChanged = true;
                    }
                }
            } else if (!worldIn.isRemote && !PlayerMethods.isPlayerFlying(playerIn)) {
                playerIn.setNoGravity(false);
            }

        }
    }

}
