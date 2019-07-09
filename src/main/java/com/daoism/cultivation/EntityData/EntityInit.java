package com.daoism.cultivation.EntityData;

import com.daoism.cultivation.API.Reference;
import com.daoism.cultivation.Daoism;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

/**
 * This class handles the initiation of entities
 */
public class EntityInit {

    /**
     * All entities to be registered are done here
     */
    public static void registerEntities() {
        registerEntity("Spirit", EntitySpirit.class, Reference.ENTITY_SPIRIT, 50, 000000, 000000);
    }

    /**
     * Method run to create a new resource location and store entity
     * @param name The name of the entity
     * @param entity The class with the entity data
     * @param id The entity reference number
     * @param range The range
     * @param color1 Colour 1 of the spawn egg
     * @param color2 Colour 2 of the spawn egg
     */
    public static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2) {
        EntityRegistry.registerModEntity(new ResourceLocation("daoism" + ":" + name), entity, name, id, Daoism.instance, range, 1, true, color1, color2);
    }

}
