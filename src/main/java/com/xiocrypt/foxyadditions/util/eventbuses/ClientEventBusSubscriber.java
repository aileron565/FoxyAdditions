package com.xiocrypt.foxyadditions.util.eventbuses;

import com.xiocrypt.foxyadditions.FoxyAdditionsMod;
import com.xiocrypt.foxyadditions.client.entity.render.TamedFoxRender;
import com.xiocrypt.foxyadditions.util.registries.EntityReg;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = FoxyAdditionsMod.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityReg.TAMEDFOX.get(), TamedFoxRender::new);
    }
}
