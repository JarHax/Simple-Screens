package com.jarhax.simplescreens.screens;

import com.jarhax.simplescreens.SimpleScreens;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;

public class WorldScreenLogic {

    private static ResourceLocation backgroundTexture = null;

    public static void renderBackground(Minecraft mc, MatrixStack matrixStack) {

        if (backgroundTexture == null) {
            backgroundTexture = SimpleScreens.getWorldLoadTexture();
        }

        if (backgroundTexture != null) {

            Minecraft.getInstance().getTextureManager().bindTexture(backgroundTexture);
            final int width = mc.getMainWindow().getScaledWidth();
            final int height = mc.getMainWindow().getScaledHeight();
            Screen.blit(matrixStack, 0, 0, width, height, 0, 0.0F, 120, 120, 120, 120);
        }
    }
}
