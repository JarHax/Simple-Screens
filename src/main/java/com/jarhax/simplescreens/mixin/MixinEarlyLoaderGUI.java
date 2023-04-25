package com.jarhax.simplescreens.mixin;

import com.jarhax.simplescreens.SimpleScreens;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.LoadingGui;
import net.minecraft.client.gui.ResourceLoadProgressGui;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ResourceLoadProgressGui.class)
public abstract class MixinEarlyLoaderGUI extends LoadingGui {

    @Unique
    private static ResourceLocation backgroundTexture = null;

    @Shadow
    @Final
    private Minecraft mc;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V"))
    public void simplescreens$render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {

        if (backgroundTexture == null) {
            backgroundTexture = SimpleScreens.getGameLoadTexture();
        }

        if (backgroundTexture != null) {

            Minecraft.getInstance().getTextureManager().bindTexture(backgroundTexture);
            final int width = this.mc.getMainWindow().getScaledWidth();
            final int height = this.mc.getMainWindow().getScaledHeight();
            blit(matrixStack, 0, 0, width, height, 0, 0.0F, 120, 120, 120, 120);
        }
    }
}