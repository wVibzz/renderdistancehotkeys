package net.vibzz.renderdistancehotkeys;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
//? if >=26.1
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
//? if <26.1
//import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;

public class Renderdistancehotkeys implements ClientModInitializer {
	//? if >=1.21.11
	public static final KeyMapping CYCLE_RENDER_DISTANCE = new KeyMapping("key.debug.cycleRenderDistance", InputConstants.KEY_F, KeyMapping.Category.DEBUG);

	@Override
	public void onInitializeClient() {
		//? if >=26.1 {
		KeyMappingHelper.registerKeyMapping(CYCLE_RENDER_DISTANCE);
		//?} else if >=1.21.11 {
		/*KeyBindingHelper.registerKeyBinding(CYCLE_RENDER_DISTANCE);
		*///?}
	}
}
