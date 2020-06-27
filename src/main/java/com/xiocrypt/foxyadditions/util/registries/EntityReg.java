package com.xiocrypt.foxyadditions.util.registries;

import com.xiocrypt.foxyadditions.FoxyAdditionsMod;
import com.xiocrypt.foxyadditions.entities.TamedFox;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityReg {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, FoxyAdditionsMod.MOD_ID);

    public static final RegistryObject<EntityType<TamedFox>> TAMEDFOX = ENTITY_TYPES.register("tamedfox",
            () ->EntityType.Builder.create(TamedFox::new, EntityClassification.CREATURE)
            .size(0.7F,0.6F)
            .build(new ResourceLocation(FoxyAdditionsMod.MOD_ID,"tamedfox").toString()));
}
