package com.djuke.armory.client.proxy;

import com.djuke.armory.proxy.interfaces.IProxy;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientProxy implements IProxy{

    @Override
    public void setup(IEventBus modEventBus, IEventBus forgeEventBus) {
        modEventBus.addListener(this::clientSetup);      
    }
    
    private void clientSetup(FMLClientSetupEvent event) {}
}
