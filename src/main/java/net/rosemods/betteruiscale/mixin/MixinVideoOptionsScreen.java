package net.rosemods.betteruiscale.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.Option;
import net.minecraft.text.Text;
import net.rosemods.betteruiscale.access.OptionAccess;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VideoOptionsScreen.class)
public class MixinVideoOptionsScreen extends GameOptionsScreen {
    @Shadow
    @Mutable @Final
    private static Option[] OPTIONS;

    public MixinVideoOptionsScreen(Screen parent, GameOptions gameOptions, Text title) {
        super(parent, gameOptions, title);
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void injectModifyOptions(CallbackInfo ci) {
        OPTIONS = new Option[]{Option.GRAPHICS, Option.RENDER_DISTANCE, Option.AO, Option.FRAMERATE_LIMIT, Option.VSYNC, Option.VIEW_BOBBING, OptionAccess.DOUBLE_GUI_SCALE, Option.ATTACK_INDICATOR, Option.GAMMA, Option.CLOUDS, Option.FULLSCREEN, Option.PARTICLES, Option.MIPMAP_LEVELS, Option.ENTITY_SHADOWS, Option.DISTORTION_EFFECT_SCALE, Option.ENTITY_DISTANCE_SCALING, Option.FOV_EFFECT_SCALE};
    }

    @Redirect(method = "mouseClicked(DDI)Z",
              at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;onResolutionChanged()V"))
    private void redirectOnResolutionChanged(MinecraftClient client) {}

    @Inject(method = "mouseReleased(DDI)Z", at = @At("TAIL"))
    private void injectMouseReleased(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        this.client.onResolutionChanged();
    }
}
