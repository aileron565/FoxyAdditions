package com.xiocrypt.foxyadditions;

import com.xiocrypt.foxyadditions.util.RegistryHandler;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("foxyadditions")
public class FoxyAdditionsMod
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "foxyadditions";

    public FoxyAdditionsMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        RegistryHandler.init();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HELLO FROM PREINIT");
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        LOGGER.info("Doing Client Stuff");
    }

    public static final ItemGroup FOXYADDITIONSTAB = new ItemGroup("foxyAdditionsTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(RegistryHandler.GOLDEN_SWEET_BERRIES.get());
        }
    };
}
