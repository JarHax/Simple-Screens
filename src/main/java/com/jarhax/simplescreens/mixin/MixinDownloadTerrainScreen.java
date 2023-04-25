package com.jarhax.simplescreens.mixin;

import com.jarhax.simplescreens.screens.WorldScreenLogic;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.DownloadTerrainScreen;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DownloadTerrainScreen.class)
public abstract class MixinDownloadTerrainScreen extends Screen {

    @Inject(method = "render", at = @At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lnet/minecraft/client/gui/screen/DownloadTerrainScreen;renderDirtBackground(I)V"))
    public void simplescreens$render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {

        WorldScreenLogic.renderBackground(this.minecraft, matrixStack);
    }

    protected MixinDownloadTerrainScreen() {
        super(null);
    }
}
