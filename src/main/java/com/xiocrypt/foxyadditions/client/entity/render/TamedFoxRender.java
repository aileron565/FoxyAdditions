package com.xiocrypt.foxyadditions.client.entity.render;

import com.xiocrypt.foxyadditions.FoxyAdditionsMod;
import com.xiocrypt.foxyadditions.client.entity.model.TamedFoxModel;
import com.xiocrypt.foxyadditions.entities.TamedFox;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class TamedFoxRender extends MobRenderer<TamedFox, TamedFoxModel<TamedFox>> {

    public TamedFoxRender(EntityRendererManager renderManagerIn){
        super(renderManagerIn, new TamedFoxModel<TamedFox>(),0.4F);
    }

    private ResourceLocation getFoxTexture(String fileName) {

        return new ResourceLocation(FoxyAdditionsMod.MOD_ID, "textures/entity/" + fileName + ".png");
    }

    @Override
    public ResourceLocation getEntityTexture(TamedFox entity) {
        String name = entity.getName().getString().toLowerCase().trim();
        /*if (name.equals("red")) {
            return this.getFoxTexture("red");
        }*/
        return entity.isChild() ? this.getFoxTexture("tamedfox_child") : this.getFoxTexture("tamedfox");
    }
}
