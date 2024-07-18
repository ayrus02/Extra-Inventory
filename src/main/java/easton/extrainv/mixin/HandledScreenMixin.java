package easton.extrainv.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(HandledScreen.class)
public class HandledScreenMixin extends Screen {

    private int additional_slot = 36;

    protected HandledScreenMixin(Text title) {
        super(title);
    }

    @ModifyConstant(method = "onMouseClick(I)V", constant = @Constant(intValue = 40))
    private int changeOffhandSlot(int og) {
        return(40 + additional_slot);
    }

    @ModifyConstant(method = "handleHotbarKeyPressed", constant = @Constant(intValue = 40))
    private int changeOffhandSlot2(int og) {
        return(40 + additional_slot);
    }

}
