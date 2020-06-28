package com.xiocrypt.foxyadditions.client.entity.render;

import com.xiocrypt.foxyadditions.FoxyAdditionsMod;
import com.xiocrypt.foxyadditions.client.entity.model.TamedFoxModel;
import com.xiocrypt.foxyadditions.entities.TamedFox;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class TamedFoxRender extends MobRenderer<TamedFox, TamedFoxModel<TamedFox>> {

    public TamedFoxRender(EntityRendererManager renderManagerIn){
        super(renderManagerIn, new TamedFoxModel<>(),0.4F);
    }

    private ResourceLocation getFoxTexture(String fileName) {

        return new ResourceLocation(FoxyAdditionsMod.MOD_ID, "textures/entity/" + fileName + ".png");
    }

    @Override
    @Nonnull
    public ResourceLocation getEntityTexture(TamedFox entity) {
        String name = "tamedfox";
        String type_name;
        String child_name;
        if (entity.getVariantType() == TamedFox.Type.RED) { type_name="_red"; }
        else if (entity.getVariantType() == TamedFox.Type.SNOW){ type_name="_snow"; }
        else if (entity.getVariantType() == TamedFox.Type.GREY){ type_name="_grey"; }
        //Bat
        //Silver
        //Fennec
        //Marble
        else{ type_name=""; }

        child_name = entity.isChild() ? "_child": "";

        return this.getFoxTexture(name + type_name + child_name);
    }
}
