package com.jarhax.simplescreens.mixin;

import com.jarhax.simplescreens.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Inject(method = "displayGuiScreen", at = @At("HEAD"))
    public void displayGuiScreen(Screen guiScreenIn, CallbackInfo cbi) {

        Constants.LOG.info("Screen: {}", guiScreenIn);
    }
}
