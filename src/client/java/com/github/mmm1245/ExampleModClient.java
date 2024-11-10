package com.github.mmm1245;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ExampleModClient implements ClientModInitializer {
	public static KeyBinding openEditorKeyBind;
	@Override
	public void onInitializeClient() {
		System.out.println("hereeeee");
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		openEditorKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.clientscript.openeditor", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_U, // The keycode of the key
				"category.clientscript" // The translation key of the keybinding's category.
		));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (openEditorKeyBind.wasPressed()) {
				client.setScreen(new EditorScreen());
			}
		});
		
	}
}