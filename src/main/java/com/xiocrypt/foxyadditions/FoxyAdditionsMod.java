package com.xiocrypt.foxyadditions;

import com.xiocrypt.foxyadditions.util.registries.BlockReg;
import com.xiocrypt.foxyadditions.util.registries.EntityReg;
import com.xiocrypt.foxyadditions.util.registries.ItemReg;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
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
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);

        ItemReg.ITEMS.register(modEventBus);
        //BlockReg.BLOCKS.register(modEventBus);
        EntityReg.ENTITY_TYPES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    /*@SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        BlockReg.BLOCKS.getEntries().stream()
                .filter(block -> !(block.get() instanceof ExampleCrop) && !(block.get() instanceof FlowingFluidBlock))
                .map(RegistryObject::get).forEach(block -> {
            final Item.Properties properties = new Item.Properties().group(TutorialItemGroup.instance);
            final BlockItem blockItem = new BlockItem(block, properties);
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        });

        LOGGER.debug("Registered BlockItems!");
    }*/

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
            return new ItemStack(ItemReg.GOLDEN_SWEET_BERRIES.get());
        }
    };
}
