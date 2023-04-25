package com.jarhax.simplescreens.mixin;

import com.jarhax.simplescreens.screens.WorldScreenLogic;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.WorldLoadProgressScreen;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = WorldLoadProgressScreen.class)
public abstract class MixinWorldLoadProgressScreen extends Screen {

    @Unique
    private static ResourceLocation backgroundTexture = null;

    @Inject(method = "render", at = @At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lnet/minecraft/client/gui/screen/WorldLoadProgressScreen;renderBackground(Lcom/mojang/blaze3d/matrix/MatrixStack;)V"))
    public void simplescreens$render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {

        WorldScreenLogic.renderBackground(this.minecraft, matrixStack);
    }

    protected MixinWorldLoadProgressScreen() {
        super(null);
    }
}