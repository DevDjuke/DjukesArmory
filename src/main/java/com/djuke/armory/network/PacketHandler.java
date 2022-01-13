package com.djuke.armory.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.server.ServerLifecycleHooks;

public class PacketHandler {
  private static final String PROTOCOL_VERSION = "1";
  
  public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation("djukesarmory", "main"), () -> "1", "1"::equals, "1"::equals);
  
  protected static int currentId = 0;
  
  public static void init() {
  }
  
  public static int getNextPacketId() {
    int id = currentId;
    currentId++;
    return id;
  }
  
  public static void sendToServer(Object packet) {
    INSTANCE.sendToServer(packet);
  }
  
  public static void sendTo(Object packet, ServerPlayer player) {
    if (!(player instanceof net.minecraftforge.common.util.FakePlayer))
      INSTANCE.sendTo(packet, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT); 
  }
  
  public static void sendPacketToAll(Object packet) {
    for (ServerPlayer player : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers())
      sendTo(packet, player); 
  }
}
