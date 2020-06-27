package com.xiocrypt.foxyadditions.util.registries;

import com.xiocrypt.foxyadditions.FoxyAdditionsMod;
import com.xiocrypt.foxyadditions.items.ItemBase;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemReg {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, FoxyAdditionsMod.MOD_ID);

    public static final RegistryObject<Item> GOLDEN_SWEET_BERRIES = ITEMS.register("golden_sweet_berries", ItemBase::new);
}
