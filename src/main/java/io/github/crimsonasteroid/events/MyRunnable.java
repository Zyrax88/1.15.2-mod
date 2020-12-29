package io.github.crimsonasteroid.events;


import java.util.Vector;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.datafix.fixes.ChunkPaletteFormat.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class MyRunnable implements Runnable {

		World worldIn;
		PlayerEntity player;
		BlockPos pos;
		BlockState state;
	
		public MyRunnable(World worldIn, PlayerEntity player, BlockPos pos, BlockState state){
			this.worldIn = worldIn;
			this.player = player;
			this.pos = pos;
			this.state = state;
		}
	

	public void run() {
	
		
		try {
			player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 100, 2));
			Thread.sleep(1000);
			 worldIn.setBlockState(player.getPosition().add(player.getHorizontalFacing().getDirectionVec()), state.getBlockState());
			   
				
				
					Thread.sleep(200);  
				player.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, 255, 255));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}