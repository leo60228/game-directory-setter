package dev.vriska.gamedirectorysetter.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.client.Minecraft;
import java.io.File;

@Mixin(Minecraft.class)
public class ClientMixin {
	@Accessor("gameDirectory")
	public static void setGameDirectory(File gameDirectory) {
		throw new AssertionError();
	}

	@Inject(at = @At("HEAD"), method = "getGameDirectory()Ljava/io/File;")
	private static void getGameDirectory(CallbackInfoReturnable<File> info) {	
		String targetDir = System.getProperty("minecraft.applet.TargetDirectory");
		if (targetDir != null) {
			System.out.printf("Setting game directory to %s\n", targetDir);
			setGameDirectory(new File(targetDir));
		} else {
			System.out.println("TargetDirectory not set");
		}
	}
}
