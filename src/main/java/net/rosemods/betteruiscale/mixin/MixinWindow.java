package net.rosemods.betteruiscale.mixin;

import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Window.class)
public class MixinWindow {
    @Shadow
    private int framebufferWidth;

    @Shadow
    private int framebufferHeight;

    @Shadow
	private double scaleFactor;

    @Shadow
	private int scaledWidth;

    @Shadow
	private int scaledHeight;

    /**
     * @author Rose
	 * @reason Modifies gui scaling
     */
    @Overwrite
    public int calculateScaleFactor(int guiScale, boolean forceUnicodeFont) {
		int i;
		for(i = 1; i != guiScale && i < this.framebufferWidth && i < this.framebufferHeight && this.framebufferWidth / (i + 1) >= 40 && this.framebufferHeight / (i + 1) >= 30; ++i) {
		}

		if (forceUnicodeFont && i % 2 != 0) {
			++i;
		}

		return i;
	}

	/**
	 * @author Rose
	 * @reason Modifies gui scaling
	 */
	@Overwrite
	public void setScaleFactor(double scaleFactor) {
		if(scaleFactor > 2) scaleFactor = 1.5 * (0.3 + Math.log10(scaleFactor));
		else scaleFactor = 1 + (scaleFactor * 0.075);
		//scaleFactor = (scaleFactor + 3) / 4;
		this.scaleFactor = scaleFactor;
		int i = (int)((double)this.framebufferWidth / scaleFactor);
		this.scaledWidth = (double)this.framebufferWidth / scaleFactor > (double)i ? i + 1 : i;
		int j = (int)((double)this.framebufferHeight / scaleFactor);
		this.scaledHeight = (double)this.framebufferHeight / scaleFactor > (double)j ? j + 1 : j;
	}
}
