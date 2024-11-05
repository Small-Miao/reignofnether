package com.solegendary.reignofnether.research.researchItems;

import com.solegendary.reignofnether.ReignOfNether;
import com.solegendary.reignofnether.building.BuildingServerboundPacket;
import com.solegendary.reignofnether.building.ProductionBuilding;
import com.solegendary.reignofnether.building.ProductionItem;
import com.solegendary.reignofnether.hud.Button;
import com.solegendary.reignofnether.keybinds.Keybinding;
import com.solegendary.reignofnether.research.ResearchClient;
import com.solegendary.reignofnether.research.ResearchServerEvents;
import com.solegendary.reignofnether.resources.ResourceCost;
import com.solegendary.reignofnether.resources.ResourceCosts;
import com.solegendary.reignofnether.unit.UnitClientEvents;
import com.solegendary.reignofnether.unit.UnitServerEvents;
import com.solegendary.reignofnether.unit.interfaces.Unit;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import java.util.List;

public class ResearchCollectionEfficiency extends ProductionItem {
    public final static String itemName = "Efficient Collection";
    public final static ResourceCost cost = ResourceCosts.RESEARCH_COLLECTION_EFFICIENCY;

    public ResearchCollectionEfficiency(ProductionBuilding building){
        super(building, cost.ticks);
        this.onComplete = (Level level)->{
            if (level.isClientSide()){
                ResearchClient.addResearch(this.building.ownerName, ResearchCollectionEfficiency.itemName);
                for (LivingEntity unit : UnitClientEvents.getAllUnits()){
                    ((Unit)unit).setupEquipmentAndUpgradesClient();
                }
            }else{
                ResearchServerEvents.addResearch(this.building.ownerName, ResearchCollectionEfficiency.itemName);
                for (LivingEntity unit : UnitServerEvents.getAllUnits()){
                    ((Unit)unit).setupEquipmentAndUpgradesServer();
                }
            }
        };
    }

    public String getItemName(){return ResearchCollectionEfficiency.itemName;}

    public static Button getStartButton(ProductionBuilding prodBuilding, Keybinding hotkey){
        return new Button(ResearchCollectionEfficiency.itemName,
                14,
                new ResourceLocation(ReignOfNether.MOD_ID, "textures/icons/items/axe.png"),
                new ResourceLocation(ReignOfNether.MOD_ID, "textures/hud/icon_frame_bronze.png"),
                hotkey,
                ()->false,
                () -> ProductionItem.itemIsBeingProduced(ResearchCollectionEfficiency.itemName, prodBuilding.ownerName)
                        || ResearchClient.hasResearch(ResearchCollectionEfficiency.itemName),
                ()->true,
                ()-> BuildingServerboundPacket.startProduction(prodBuilding.originPos, itemName),
                null,
                List.of(FormattedCharSequence.forward(I18n.get("research.reignofnether.collection_efficlency"), Style.EMPTY.withBold(true)),
                        ResourceCosts.getFormattedCost(cost),
                        ResourceCosts.getFormattedTime(cost),
                        FormattedCharSequence.forward("", Style.EMPTY),
                        FormattedCharSequence.forward(I18n.get("research.reignofnether.collection_efficlency.tooltip1"),
                                Style.EMPTY
                        ))
        );
    }

    public Button getCancelButton(ProductionBuilding prodBuilding, boolean first) {
        return new Button(ResearchCollectionEfficiency.itemName,
                14,
                new ResourceLocation(ReignOfNether.MOD_ID, "textures/icons/items/chest.png"),
                new ResourceLocation(ReignOfNether.MOD_ID, "textures/hud/icon_frame_bronze.png"),
                null,
                () -> false,
                () -> false,
                () -> true,
                () -> BuildingServerboundPacket.cancelProduction(prodBuilding.minCorner, itemName, first),
                null,
                null
        );
    }
}
