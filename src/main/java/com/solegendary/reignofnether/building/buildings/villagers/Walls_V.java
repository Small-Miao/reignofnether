package com.solegendary.reignofnether.building.buildings.villagers;

import com.solegendary.reignofnether.building.buildings.shared.AbstractWalls;
import com.solegendary.reignofnether.resources.ResourceCost;
import com.solegendary.reignofnether.resources.ResourceCosts;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;
import java.util.ArrayList;
import com.solegendary.reignofnether.building.BuildingBlock;
import com.solegendary.reignofnether.building.BuildingBlockData;
import net.minecraft.world.level.LevelAccessor;
import com.solegendary.reignofnether.hud.AbilityButton;
import com.solegendary.reignofnether.keybinds.Keybinding;
import com.solegendary.reignofnether.building.BuildingClientEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Style;
import net.minecraft.world.level.block.Blocks;
import java.util.List;


import static com.solegendary.reignofnether.building.BuildingUtils.getAbsoluteBlockData;
public class Walls_V extends AbstractWalls {
    public final static String buildingName = "Walls_V";
    public final static String structureName = "walls";
    public final static ResourceCost cost = ResourceCosts.WALLS;

    public Walls_V(Level level, BlockPos originPos, Rotation rotation, String ownerName) {
        super(level, originPos, rotation, ownerName, getAbsoluteBlockData(getRelativeBlockData(level), level, originPos, rotation), false);
        this.name = buildingName;
        this.ownerName = ownerName;
        this.portraitBlock = Blocks.STONE_BRICKS;
        this.icon = new ResourceLocation("minecraft", "textures/block/stone_bricks.png");

        this.foodCost = cost.food;
        this.woodCost = cost.wood;
        this.oreCost = cost.ore;
        this.popSupply = cost.population;
        this.buildTimeModifier = 1.0f;
        this.explodeChance = 0.2f;
        this.setMinBlocksPercent(0.5f);
        this.startingBlockTypes.add(Blocks.STONE_BRICKS);
        this.startingBlockTypes.add(Blocks.STONE_BRICK_SLAB);
    }

    public static ArrayList<BuildingBlock> getRelativeBlockData(LevelAccessor level) {
        return BuildingBlockData.getBuildingBlocks(structureName, level);
    }

    public static AbilityButton getBuildButton(Keybinding hotkey) {
        return new AbilityButton(
            buildingName,
            new ResourceLocation("minecraft", "textures/block/stone_bricks.png"),
            hotkey,
            ()->BuildingClientEvents.getBuildingToPlace() == Walls_V.class,
            ()->{return false;},
            ()->{return true;},
            () -> BuildingClientEvents.setBuildingToPlace(Walls_V.class),
            null,
            List.of(
                FormattedCharSequence.forward(I18n.get("buildings.neutral.reignofnether.walls"), Style.EMPTY.withBold(true)),
                ResourceCosts.getFormattedCost(cost),
                FormattedCharSequence.forward(I18n.get("buildings.neutral.reignofnether.walls.tooltip1"), Style.EMPTY.withBold(true))
            ),
            null
        );
    }
}
