package com.jarhax.simplescreens.mixin;

import com.jarhax.simplescreens.SimpleScreens;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.DirtMessageScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.TranslationTextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = DirtMessageScreen.class)
public abstract class MixinDirtMessageScreen extends Screen {

    @Unique
    private Boolean simplescreens$canDisplayOnScreen = null;

    @Inject(method = "render", at = @At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lnet/minecraft/client/gui/screen/DirtMessageScreen;renderDirtBackground(I)V"))
    public void simplescreens$render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {

        if (simplescreens$canDisplayOnScreen == null) {

            simplescreens$canDisplayOnScreen = false;

            if (this.title instanceof TranslationTextComponent) {

                final TranslationTextComponent title = (TranslationTextComponent) this.title;

                if (title.getKey().equalsIgnoreCase("menu.savingLevel") || title.getKey().equalsIgnoreCase("selectWorld.data_read") || title.getKey().equalsIgnoreCase("createWorld.preparing") || title.getKey().equalsIgnoreCase("dataPack.validation.working")) {

                    simplescreens$canDisplayOnScreen = true;
                }
            }
        }

        if (simplescreens$canDisplayOnScreen) {
            SimpleScreens.renderBackground(this.minecraft, matrixStack);
        }
    }

    protected MixinDirtMessageScreen() {
        super(null);
    }
}