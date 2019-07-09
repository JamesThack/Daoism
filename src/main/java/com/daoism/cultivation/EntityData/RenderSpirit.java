package com.daoism.cultivation.EntityData;

import com.daoism.cultivation.EntityData.Models.ModelSpirit;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

/**
 *This class handles the rendering for the spirit entity
 */
public class RenderSpirit extends RenderLiving<EntitySpirit> {

    /**
     * The location of the texture
     */
    public static final ResourceLocation TEXTURES = new ResourceLocation("daoism" + ":textures/entity/spirit.png");

    /**
     * Constructs the render manager
     * @param manager The render manager
     */
    public RenderSpirit(RenderManager manager) {
        super(manager, new ModelSpirit(), 0.5F);
    }

    /**
     * Method to return entity textures
     * @param entity The entity
     * @return The resource location of the textures
     */
    @Override
    protected ResourceLocation getEntityTexture(EntitySpirit entity) {
        return TEXTURES;
    }

    /**
     * Method to apply rotations
     * @param entityLiving The entity
     * @param p_77043_2_ Random thing idk, stop looking here lol you absolute nerd.
     * @param rotationYaw The rotation yaw
     * @param partialTicks No idea what this is, seriously why you looking here lol?
     */
    @Override
    protected void applyRotations(EntitySpirit entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }

}
