package net.rosemods.betteruiscale.mixin.compat;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Pseudo
@Mixin(targets = "me.jellysquid.mods.sodium.client.gui.SodiumGameOptionPages", remap = false)
public class MixinSodiumGameOptionPages {
    @ModifyArg(method = "lambda$general$8(Lme/jellysquid/mods/sodium/client/gui/options/OptionImpl;)Lme/jellysquid/mods/sodium/client/gui/options/control/Control;",
    at = @At(value = "INVOKE", target = "Lme/jellysquid/mods/sodium/client/gui/options/control/SliderControl;<init> (Lme/jellysquid/mods/sodium/client/gui/options/Option;IIILme/jellysquid/mods/sodium/client/gui/options/control/ControlValueFormatter;)V"),
    index = 2)
    private static int modifyMax(int max) {
        return 30;
    }
}
