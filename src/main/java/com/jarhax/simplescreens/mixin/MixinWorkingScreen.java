package com.jarhax.simplescreens.mixin;

import com.jarhax.simplescreens.SimpleScreens;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.WorkingScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorkingScreen.class)
public abstract class MixinWorkingScreen extends Screen {

    @Inject(method = "render", at = @At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lnet/minecraft/client/gui/screen/WorkingScreen;renderBackground(Lcom/mojang/blaze3d/matrix/MatrixStack;)V"))
    public void simplescreens$render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {

        SimpleScreens.renderBackground(this.minecraft, matrixStack);
    }

    protected MixinWorkingScreen() {
        super(null);
    }
}
