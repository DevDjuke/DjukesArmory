package com.djuke.armory.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class ModConfigurations {
  public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
  
  public static final ForgeConfigSpec SPEC;
  
  static {
    BUILDER.push("Config for Djuke's Armory Mod!");
  }
  
  public static final ForgeConfigSpec.ConfigValue<Boolean> put_armor_on_mobs = (ForgeConfigSpec.ConfigValue<Boolean>)BUILDER.comment("Works only in game with the difficulty set to hard. This is an boolean. Default value is true.").define("Put Armor on Mobs", true);
  
  public static final ForgeConfigSpec.ConfigValue<Float> put_armor_chance = BUILDER.comment("Works only if the game is set to the hard difficulty. Affects the chance that mobs will wear armor from the mod. This is an float. Default value is 0.5, Maximum value is 1.0.").define("Put Armor Chance", Float.valueOf(0.5F));
  
  public static final ForgeConfigSpec.ConfigValue<Boolean> decorations_for_all_armor = (ForgeConfigSpec.ConfigValue<Boolean>)BUILDER.comment("If true, allows wearing decorations on any type of armor, not only from this mod. This is an boolean. Default value is true.").define("Decorations for All Armor", true);
  
  public static final ForgeConfigSpec.ConfigValue<Boolean> steel_enabled = (ForgeConfigSpec.ConfigValue<Boolean>)BUILDER.comment("Doesn't change anything yet! This is an boolean. Default value is true.").define("Steel Enabled", true);
  
  static {
    BUILDER.pop();
    SPEC = BUILDER.build();
  }
}
