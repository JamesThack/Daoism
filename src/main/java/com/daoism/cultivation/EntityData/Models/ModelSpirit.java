package com.daoism.cultivation.EntityData.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSpirit extends ModelBase {
    public ModelRenderer LeftArm;
    public ModelRenderer LeftLeg;
    public ModelRenderer Head;
    public ModelRenderer Chest;
    public ModelRenderer RightArm;
    public ModelRenderer RightLeg;

    public ModelSpirit() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Chest = new ModelRenderer(this, 16, 16);
        this.Chest.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Chest.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.RightArm = new ModelRenderer(this, 40, 16);
        this.RightArm.mirror = true;
        this.RightArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.RightArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(RightArm, -0.017453292519943295F, 0.0F, 0.0F);
        this.RightLeg = new ModelRenderer(this, 0, 16);
        this.RightLeg.mirror = true;
        this.RightLeg.setRotationPoint(1.9F, 12.0F, 0.1F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 0, 16);
        this.LeftLeg.setRotationPoint(-1.9F, 12.0F, 0.1F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.LeftArm = new ModelRenderer(this, 40, 16);
        this.LeftArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.LeftArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Chest.render(f5);
        this.RightArm.render(f5);
        this.RightLeg.render(f5);
        this.LeftLeg.render(f5);
        this.Head.render(f5);
        this.LeftArm.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
