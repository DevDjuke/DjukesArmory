package com.djuke.armory.init;

import java.util.ArrayList;

import com.djuke.armory.item.interfaces.IHasModelProperty;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.minecraft.world.item.Items;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
    static IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    public static final CreativeModeTab TAB_ARMOR = new CreativeModeTab("djukesarmory.armor") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.IRON_HELMET);
        }
    };

    public static final ArrayList<IHasModelProperty> customizableItems = new ArrayList<>();
}
