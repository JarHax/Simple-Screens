package com.jarhax.simplescreens;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ResourceLocationException;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.List;

@Mod("simplescreens")
public class SimpleScreens {

    private static Config config;
    public static ResourceLocation backgroundTexture = null;

    public SimpleScreens() {

        final ModLoadingContext ctx = ModLoadingContext.get();
        ctx.registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
    }

    private static Config getConfig() {

        if (config == null) {
            config = Config.load();
        }

        return config;
    }

    @Nullable
    public static ResourceLocation getGameLoadTexture() {

        return getTexture(getConfig().gameLoadScreenTextures);
    }

    @Nullable
    public static ResourceLocation getWorldLoadTexture() {

        return getTexture(getConfig().worldLoadScreenTextures);
    }

    @Nullable
    private static ResourceLocation getTexture(List<String> textures) {

        if (!textures.isEmpty()) {

            final int index = Constants.RNG.nextInt(textures.size());
            final String texturePath = textures.get(index);

            try {

                return new ResourceLocation(texturePath);
            }

            catch (ResourceLocationException e) {

                Constants.LOG.error("Failed to load texture. Invalid configuration format!");
                Constants.LOG.error(e);
            }
        }

        return null;
    }

    public static void renderBackground(Minecraft mc, MatrixStack matrixStack) {

        if (backgroundTexture == null) {
            backgroundTexture = getWorldLoadTexture();
        }

        if (backgroundTexture != null) {

            Minecraft.getInstance().getTextureManager().bindTexture(backgroundTexture);
            final int width = mc.getMainWindow().getScaledWidth();
            final int height = mc.getMainWindow().getScaledHeight();
            Screen.blit(matrixStack, 0, 0, width, height, 0, 0.0F, 120, 120, 120, 120);
        }
    }
}