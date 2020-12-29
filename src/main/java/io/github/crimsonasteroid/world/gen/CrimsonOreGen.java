package io.github.crimsonasteroid.world.gen;

import io.github.crimsonasteroid.init.BlockInit;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class CrimsonOreGen {

	public static void GenerateOre() {
	     for(Biome biome : ForgeRegistries.BIOMES) {
	    	 ConfiguredPlacement customconfig = Placement.COUNT_RANGE.configure((new CountRangeConfig(50, 5, 5, 25)));
	    	 biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig
	    			 (OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.abyssium_ore.get().getDefaultState(), 5))
	    			 .withPlacement(customconfig));
	     
	     
	     }
	}

}
