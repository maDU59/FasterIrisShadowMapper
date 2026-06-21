package fr.madu59.fism;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FasterIrisShadowMapper.MOD_ID)
public class FasterIrisShadowMapper {
	public static final String MOD_ID = "fism";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogUtils.getLogger();

	public FasterIrisShadowMapper(FMLJavaModLoadingContext context) {
		LOGGER.info("[FISM] Faster Iris Shadow Mapper initialized!");
	}
}