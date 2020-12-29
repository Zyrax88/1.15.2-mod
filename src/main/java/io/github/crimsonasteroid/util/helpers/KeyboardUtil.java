package io.github.crimsonasteroid.util.helpers;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class KeyboardUtil {
	private static final long window = Minecraft.getInstance().getMainWindow().getHandle();
	
	@OnlyIn(Dist.CLIENT)
	public static boolean isHoldingShift() {
		return InputMappings.isKeyDown(window, GLFW.GLFW_KEY_LEFT_SHIFT);

	}
	
	
	public static boolean isHoldingCtrl() {
		return InputMappings.isKeyDown(window, GLFW.GLFW_KEY_LEFT_CONTROL);

	}
	
	public static boolean PressedV() {
		return InputMappings.isKeyDown(window, GLFW.GLFW_KEY_V);
	}
	public static boolean PressedC() {
		return InputMappings.isKeyDown(window, GLFW.GLFW_KEY_C);
	}
}
