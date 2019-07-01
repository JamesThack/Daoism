package com.daoism.cultivation.EntityData;

import com.daoism.cultivation.EntityData.Models.ModelSpirit;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderSpirit extends RenderLiving<EntitySpirit> {


    public static final ResourceLocation TEXTURES = new ResourceLocation("daoism" + ":textures/entity/spirit.png");

    public RenderSpirit(RenderManager manager) {
        super(manager, new ModelSpirit(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntitySpirit entity) {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntitySpirit entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
