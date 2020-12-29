package io.github.crimsonasteroid.objects.blocks;


import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AbyssiumOre extends Block {
	
	public AbyssiumOre(Properties properties) {
		super(properties);
	}
	
	@Override
	public void dropXpOnBlockBreak(World worldIn, BlockPos pos, int amount) {
		
		amount = (int)Math.random() * 5;
		super.dropXpOnBlockBreak(worldIn, pos, amount);
	}

}
