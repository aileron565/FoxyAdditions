package com.xiocrypt.foxyadditions.client.entity.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.xiocrypt.foxyadditions.entities.TamedFox;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import javax.annotation.Nonnull;

public class TamedFoxModel<T extends TamedFox> extends AgeableModel<T> {

    private final ModelRenderer Head;
    private final ModelRenderer Body;
    private final ModelRenderer Tail;
    private final ModelRenderer LegBackRight;
    private final ModelRenderer LegBackLeft;
    private final ModelRenderer LegFrontRight;
    private final ModelRenderer LegFrontLeft;

    public TamedFoxModel() {
        super(true, 8.0F, 3.35F);
        this.textureWidth = 48;
        this.textureHeight = 32;

        this.Head = new ModelRenderer(this);
        this.Head.setRotationPoint(0.0F, 16.0F, -6.0F);
        this.Head.setTextureOffset(1, 5).addBox(-4.0F, -2.0F, -5.0F, 8.0F, 6.0F, 6.0F, 0.0F, false);//Skull
        this.Head.setTextureOffset(6, 18).addBox(-2.0F, 2.0F, -8.0F, 4.0F, 2.0F, 3.0F, 0.0F, false);//Snout
        this.Head.setTextureOffset(8, 1).addBox(-4.0F, -4.0F, -4.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);//Right Ear
        this.Head.setTextureOffset(15, 1).addBox(2.0F, -4.0F, -4.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);//Left Ear

        this.Body = new ModelRenderer(this);
        this.Body.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.setRotationAngle(Body, 1.5708F, 0.0F, 0.0F);
        this.Body.setTextureOffset(24, 15).addBox(-3.0F, -5.0F, -3.0F, 6.0F, 11.0F, 6.0F, 0.0F, false);

        this.Tail = new ModelRenderer(this);
        this.Tail.setRotationPoint(0.0F, 6.0F, 0F);
        this.Tail.setTextureOffset(30, 0).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 9.0F, 5.0F, 0.0F, false);
        this.setRotationAngle(Tail, (float)-Math.PI/18, 0.0F, 0.0F);
        this.Body.addChild(Tail);

        this.LegBackRight = new ModelRenderer(this);
        this.LegBackRight.setRotationPoint(-2.0F, 18.0F, 4.0F);
        this.LegBackRight.setTextureOffset(13, 24).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        this.LegBackLeft = new ModelRenderer(this);
        this.LegBackLeft.setRotationPoint(2.0F, 18.0F, 4.0F);
        this.LegBackLeft.setTextureOffset(4, 24).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        this.LegFrontRight = new ModelRenderer(this);
        this.LegFrontRight.setRotationPoint(-2.0F, 18.0F, -3.0F);
        this.LegFrontRight.setTextureOffset(13, 24).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        this.LegFrontLeft = new ModelRenderer(this);
        this.LegFrontLeft.setRotationPoint(2.0F, 18.0F, -3.0F);
        this.LegFrontLeft.setTextureOffset(4, 24).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

    }

    @Override
    @Nonnull
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of(this.Head);
    }

    @Override
    @Nonnull
    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(this.Body,
                this.LegBackRight,
                this.LegBackLeft,
                this.LegFrontRight,
                this.LegFrontLeft);
    }

    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        //if (!entityIn.isSleeping() && !entityIn.isCrouching()) {
        //}
        Head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        Head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);

        this.LegBackRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.LegBackLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.LegFrontRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.LegFrontLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.Tail.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F) * .7F * limbSwingAmount;
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z){
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        super.render(matrixStackIn,bufferIn,packedLightIn,packedOverlayIn,red,green,blue,alpha);
    }

    @Override
    public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);

        //Walking
        //this.LegBackRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        //this.LegBackLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        //this.LegFrontRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        //this.LegFrontLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        //this.Tail.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F) * .7F * limbSwingAmount;

        //Testing
        //Head.rotateAngleX = (float)-Math.PI*(2.5f/18); //Estimated Looking Angle


    }

    /*public ModelRenderer getHead(){
        return Head;
    }
    public ModelRenderer getBody(){
        return Body;
    }
    public ModelRenderer getLegBackRight(){
        return LegBackRight;
    }
    public ModelRenderer getLegBackLeft(){
        return LegBackLeft;
    }
    public ModelRenderer getLegFrontRight(){
        return LegFrontRight;
    }
    public ModelRenderer getLegFrontLeft(){
        return LegFrontLeft;
    }*/

}
