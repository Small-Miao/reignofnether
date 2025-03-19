package com.solegendary.reignofnether.research.researchItems;

import com.solegendary.reignofnether.building.BuildingServerboundPacket;
import com.solegendary.reignofnether.building.ProductionBuilding;
import com.solegendary.reignofnether.building.ProductionItem;
import com.solegendary.reignofnether.resources.ResourceCost;
import com.solegendary.reignofnether.resources.ResourceCosts;
import com.solegendary.reignofnether.research.ResearchClient;
import com.solegendary.reignofnether.research.ResearchServerEvents;
import com.solegendary.reignofnether.hud.Button;
import com.solegendary.reignofnether.ReignOfNether;
import com.solegendary.reignofnether.keybinds.Keybinding;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;

import java.util.List;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class ResearchAdvancedToolsLevel3 extends ProductionItem {
    public final static String itemName = "Advanced Tools Level 3";
    public final static ResourceCost cost = ResourceCosts.RESEARCH_ADVANCED_TOOLS_LEVEL_3;

    public ResearchAdvancedToolsLevel3(ProductionBuilding building) {
        super(building, cost.ticks);
        this.onComplete = (Level level) -> {
            if (level.isClientSide()) {
                ResearchClient.addResearch(this.building.ownerName, ResearchAdvancedToolsLevel3.itemName);
            } else {
                ResearchServerEvents.addResearch(this.building.ownerName, ResearchAdvancedToolsLevel3.itemName);
            }
        };
        this.foodCost = cost.food;
        this.woodCost = cost.wood;
        this.oreCost = cost.ore;    
    }

    public String getItemName() {
        return ResearchAdvancedToolsLevel3.itemName;
    }

    public static Button getStartButton(ProductionBuilding prodBuilding, Keybinding hotkey) {
        return new Button(ResearchAdvancedToolsLevel3.itemName,
            14,
            new ResourceLocation("minecraft", "textures/item/diamond_pickaxe.png"),
            new ResourceLocation(ReignOfNether.MOD_ID, "textures/hud/icon_frame_bronze.png"),
            hotkey,
            () -> false,
            () -> ProductionItem.itemIsBeingProduced(ResearchAdvancedToolsLevel3.itemName, prodBuilding.ownerName)
                || ResearchClient.hasResearch(ResearchAdvancedToolsLevel3.itemName)
                || !ResearchClient.hasResearch(ResearchAdvancedToolsLevel2.itemName)
                || !ResearchClient.hasResearch(ResearchAdvancedToolsLevel1.itemName),
            () -> true,
            () -> BuildingServerboundPacket.startProduction(prodBuilding.originPos, itemName),
            null,
            List.of(FormattedCharSequence.forward(I18n.get("research.reignofnether.advanced_tools_level_3"), Style.EMPTY.withBold(true)),
                ResourceCosts.getFormattedCost(cost),
                ResourceCosts.getFormattedTime(cost),
                FormattedCharSequence.forward("", Style.EMPTY),
                FormattedCharSequence.forward(I18n.get("research.reignofnether.advanced_tools_level_3.tooltip1"), Style.EMPTY),
                FormattedCharSequence.forward(I18n.get("research.reignofnether.advanced_tools_level_3.tooltip2"), Style.EMPTY),
                FormattedCharSequence.forward(I18n.get("research.reignofnether.advanced_tools_level_3.tooltip3"), Style.EMPTY)
            )
        );
    }

    public Button getCancelButton(ProductionBuilding prodBuilding, boolean first) {
        return new Button(ResearchAdvancedToolsLevel3.itemName,
            14,
            new ResourceLocation("minecraft", "textures/item/diamond_pickaxe.png"),
            new ResourceLocation(ReignOfNether.MOD_ID, "textures/hud/icon_frame_bronze.png"),
            null,
            () -> false,
            () -> false,
            () -> true,
            () -> BuildingServerboundPacket.cancelProduction(prodBuilding.originPos, itemName, first),
            null,
            null
        );
    }
} 