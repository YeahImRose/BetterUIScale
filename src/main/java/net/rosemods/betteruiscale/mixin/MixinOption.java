package net.rosemods.betteruiscale.mixin;

import net.minecraft.client.option.Option;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.rosemods.betteruiscale.access.OptionAccess;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Option.class)
public class MixinOption implements OptionAccess {
    @Final
    @Shadow
    private Text key;

    public Text getGenericLabel(int value) {
        return new TranslatableText("options.generic_value", new Object[]{this.key, value});
    }
}
