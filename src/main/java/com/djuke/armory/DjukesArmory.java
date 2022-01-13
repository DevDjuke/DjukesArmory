package com.djuke.armory;

import com.djuke.armory.client.proxy.ClientProxy;
import com.djuke.armory.config.ModConfigurations;
import com.djuke.armory.event.EventHandler;
import com.djuke.armory.init.ModItems;
import com.djuke.armory.item.interfaces.IHasModelProperty;
import com.djuke.armory.network.PacketHandler;
import com.djuke.armory.proxy.interfaces.IProxy;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("djukesarmory")
public class DjukesArmory {
  public static final String ID = "djukesarmory";
  
  public static final IProxy PROXY = (IProxy)DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> com.djuke.armory.proxy.ServerProxy::new);
  
  public DjukesArmory() throws IOException {
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
    PROXY.setup(modEventBus, forgeEventBus);
    modEventBus.addListener(this::commonSetup);
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    // FMLJavaModLoadingContext.get().getModEventBus().addListener(this::addLayers);
    MinecraftForge.EVENT_BUS.register(this);
    MinecraftForge.EVENT_BUS.register(EventHandler.class);
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, (IConfigSpec)ModConfigurations.SPEC, "djukesarmory.toml");
    PacketHandler.init();
  }
  
  private void commonSetup(FMLCommonSetupEvent event) {}
  
  public static final Logger LOGGER = LogManager.getLogger();
  
  private void setup(FMLCommonSetupEvent event) {
    LOGGER.info("HELLO FROM PREINIT");
  }
  
  private void doClientStuff(FMLClientSetupEvent event) {
    for (IHasModelProperty item : ModItems.customizableItems) {
      Objects.requireNonNull(item);
      event.enqueueWork(item::registerModelProperty);
    } 
  }
  
  private void enqueueIMC(InterModEnqueueEvent event) {
    InterModComms.sendTo("examplemod", "helloworld", () -> {
          LOGGER.info("Hello world from the MDK");
          return "Hello world";
        });
  }
  
  private void processIMC(InterModProcessEvent event) {
    LOGGER.info("Got IMC {}", event.getIMCStream()
        .map(m -> m.getMessageSupplier().get())
        .collect(Collectors.toList()));
  }
  
  @SubscribeEvent
  public void onServerStarting(ServerStartingEvent event) {
    LOGGER.info("HELLO from server starting");
  }
  
//   public void addLayers(EntityRenderersEvent.AddLayers event) {
//     if (PROXY instanceof ClientProxy)
//       ClientProxy.onLateInit(event); 
//   }
}

