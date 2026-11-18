package net.vibzz.renderdistancehotkeys.mixin;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
//? if >=1.21.9
import net.minecraft.client.input.KeyEvent;
//? if <1.21.9
//import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.vibzz.renderdistancehotkeys.Renderdistancehotkeys;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(KeyboardHandler.class)
public abstract class KeyboardHandlerMixin {
	@Shadow @Final private Minecraft minecraft;

	@Shadow
	private void debugFeedbackComponent(Component component) {}

	@Inject(method = "handleDebugKeys", at = @At("RETURN"), cancellable = true)
	private void cycleRenderDistance(/*? >=1.21.9 {*/KeyEvent event/*?} else {*//*int key*//*?}*/, CallbackInfoReturnable<Boolean> cir) {
		//? if >=1.21.9 && <1.21.11
		//int key = event.key();

		//? if <1.21.11 {
		/*if (cir.getReturnValueZ() && key == InputConstants.KEY_Q) {
			this.minecraft.gui.getChat().addMessage(Component.translatable("debug.cycle_renderdistance.help"));
		}
		*///?}

		if (!cir.getReturnValueZ() && /*? >=1.21.11 {*/Renderdistancehotkeys.CYCLE_RENDER_DISTANCE.matches(event)/*?} else {*//*key == InputConstants.KEY_F*//*?}*/) {
			OptionInstance<Integer> renderDistance = this.minecraft.options.renderDistance();
			OptionInstance.IntRange range = (OptionInstance.IntRange) renderDistance.values();
			renderDistance.set(Mth.clamp(renderDistance.get() + (/*? >=1.21.9 {*/event.hasShiftDown()/*?} else {*//*Screen.hasShiftDown()*//*?}*/ ? -1 : 1), range.minInclusive(), range.maxInclusive()));
			this.debugFeedbackComponent(Component.translatable("debug.cycle_renderdistance.message", renderDistance.get()));
			cir.setReturnValue(true);
		}
	}
}
