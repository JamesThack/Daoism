package com.daoism.cultivation.Registration;

import com.daoism.cultivation.EntityData.EntitySpirit;
import com.daoism.cultivation.EntityData.RenderSpirit;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {

    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntitySpirit.class, new IRenderFactory<EntitySpirit>() {

            @Override
            public Render<? super EntitySpirit> createRenderFor(RenderManager manager) {
                return new RenderSpirit(manager);
            }
        });
    }
}
