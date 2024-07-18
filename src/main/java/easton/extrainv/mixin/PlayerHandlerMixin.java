package easton.extrainv.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerScreenHandler.class)
public abstract class PlayerHandlerMixin extends ScreenHandler {

    private int additional_slot = 36;
    private int extra_inventory_rows = 4;
    private int extra_inventory_column = 9;

    protected PlayerHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 39))
    private int armorIndexChange(int og) {
        return(og + additional_slot);
    }

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 40))
    private int offhandIndexChange(int og) {
        return(og + additional_slot);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void addMoreRows(PlayerInventory inventory, boolean onServer, PlayerEntity owner, CallbackInfo info) {
        for (int n = 0; n < extra_inventory_rows; ++n)
            for (int m = 0; m < extra_inventory_column; ++m)
                this.addSlot(new Slot(inventory, m + (n + 1) * 9 + 27, 8 + m * 18, 174 + n * 18));
    }

}
