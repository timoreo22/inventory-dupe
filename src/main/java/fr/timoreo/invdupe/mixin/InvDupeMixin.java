package fr.timoreo.invdupe.mixin;

import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookProvider;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryScreen.class)
public abstract class InvDupeMixin extends AbstractInventoryScreen<PlayerScreenHandler> implements RecipeBookProvider {


    public InvDupeMixin(PlayerScreenHandler screenHandler, PlayerInventory playerInventory, Text text) {
        super(screenHandler, playerInventory, text);
    }

    @Inject(
        method = {"init"},
        at = {@At("TAIL")}
    )
    protected void init(CallbackInfo ci) {
        this.addDrawableChild(new ButtonWidget(this.x + 130, this.height / 2 - 24, 40, 20, new LiteralText("Dupe"), (b) -> {
            Slot outputSlot = this.handler.slots.get(0);
            this.onMouseClick(outputSlot, outputSlot.id, 0, SlotActionType.THROW);
        }));
    }
}
