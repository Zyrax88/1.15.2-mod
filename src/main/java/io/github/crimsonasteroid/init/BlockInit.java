package io.github.crimsonasteroid.init;

import io.github.crimsonasteroid.CrimsonAsteroid;
import io.github.crimsonasteroid.objects.blocks.AbyssiumOre;
import io.github.crimsonasteroid.objects.blocks.Chair;
import io.github.crimsonasteroid.objects.blocks.Statue;
import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
	public static final DeferredRegister<Block> Blocks = new DeferredRegister<>(ForgeRegistries.BLOCKS,
			CrimsonAsteroid.MOD_ID);
	public static final RegistryObject<Block> abyssium_block = Blocks.register("abyssium_block",
			() -> new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5f, 50.0f)
					.harvestTool(ToolType.PICKAXE).harvestLevel(3)));
	
	public static final RegistryObject<Block> abyssium_ore = Blocks.register("abyssium_ore", () -> new AbyssiumOre(Properties.create(Material.EARTH).hardnessAndResistance(4.5f, 40f).harvestTool(ToolType.PICKAXE).harvestLevel(3)));
	

	public static final RegistryObject<Block> Chair = Blocks.register("chair", () -> new Chair(Block.Properties.create(Material.IRON).hardnessAndResistance(3f, 20f).harvestLevel(1).harvestTool(ToolType.AXE).lightValue(2)));
	
	public static final RegistryObject<Block> statue = Blocks.register("statue", () -> new Statue(Block.Properties.create(Material.EARTH).hardnessAndResistance(100f, 500f).harvestLevel(4)));
}
