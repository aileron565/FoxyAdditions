package com.xiocrypt.foxyadditions.util;

import com.xiocrypt.foxyadditions.FoxyAdditionsMod;
import com.xiocrypt.foxyadditions.entities.TamedFox;
import com.xiocrypt.foxyadditions.items.ItemBase;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, FoxyAdditionsMod.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, FoxyAdditionsMod.MOD_ID);

    public static void init() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
    }

    // Items
    public static final RegistryObject<Item> GOLDEN_SWEET_BERRIES = ITEMS.register("golden_sweet_berries", ItemBase::new);

    // Entities
    public static final RegistryObject<EntityType<TamedFox>> TAMEDFOX = ENTITY_TYPES.register("tamedfox", () ->EntityType.Builder.<TamedFox>create(TamedFox::new, EntityClassification.CREATURE).size(0.7F,0.6F).build(new ResourceLocation(FoxyAdditionsMod.MOD_ID,"tamedfox").toString()));
}
