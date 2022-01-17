package com.solegendary.ageofcraft.units;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nullable;

public interface Unit {

    void setMoveToBlock(BlockPos bp);

    // TODO: remove invincibility frames from being attacked on all mobs
    void setAttackTarget(@Nullable LivingEntity target);
}
