package com.daoism.cultivation.EntityData;

import com.daoism.cultivation.API.Reference;
import com.daoism.cultivation.Daoism;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {

    public static void registerEntities() {
        registerEntity("Spirit", EntitySpirit.class, Reference.ENTITY_SPIRIT, 50, 000000, 000000);
    }

    public static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2) {
        EntityRegistry.registerModEntity(new ResourceLocation("daoism" + ":" + name), entity, name, id, Daoism.instance, range, 1, true, color1, color2);
    }

}
