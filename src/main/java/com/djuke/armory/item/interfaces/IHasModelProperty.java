package com.djuke.armory.item.interfaces;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface IHasModelProperty {
  @OnlyIn(Dist.CLIENT)
  void registerModelProperty();
}
