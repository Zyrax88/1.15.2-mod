package io.github.crimsonasteroid.objects.blocks;

import java.util.stream.Stream;

import io.github.crimsonasteroid.init.BlockInit;
import io.github.crimsonasteroid.events.MyRunnable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;


public class Statue extends Block {

	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

	public static final VoxelShape shape_n = Stream
			.of(Block.makeCuboidShape(4, 0, 5, 12, 25, 10), Block.makeCuboidShape(12, 22, 5, 15, 24, 9),
					Block.makeCuboidShape(1, 22, 5, 4, 24, 9), Block.makeCuboidShape(12, 11, 5, 15, 22, 9),
					Block.makeCuboidShape(1, 11, 5, 4, 22, 9), Block.makeCuboidShape(10, 29, 3.5, 11, 30, 4.5),
					Block.makeCuboidShape(5, 29, 3.5, 6, 30, 4.5), Block.makeCuboidShape(3, 25, 4, 13, 32, 11))
			.reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	public static final VoxelShape shape_s = Stream
			.of(Block.makeCuboidShape(4, 0, 5, 12, 25, 10), Block.makeCuboidShape(1, 22, 5, 4, 24, 9),
					Block.makeCuboidShape(12, 22, 5, 15, 24, 9), Block.makeCuboidShape(1, 11, 5, 4, 22, 9),
					Block.makeCuboidShape(12, 11, 5, 15, 22, 9), Block.makeCuboidShape(5, 29, 10.5, 6, 30, 11.5),
					Block.makeCuboidShape(10, 29, 10.5, 11, 30, 11.5), Block.makeCuboidShape(3, 25, 4, 13, 32, 11))
			.reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	public static final VoxelShape shape_e = Stream.of(Block.makeCuboidShape(6.125, 0, 4.125, 11.125, 25, 12.125),
			Block.makeCuboidShape(7.125, 22, 1.125, 11.125, 24, 4.125),
			Block.makeCuboidShape(7.125, 22, 12.125, 11.125, 24, 15.125),
			Block.makeCuboidShape(7.125, 11, 1.125, 11.125, 22, 4.125),
			Block.makeCuboidShape(7.125, 11, 12.125, 11.125, 22, 15.125),
			Block.makeCuboidShape(4.625, 29, 5.125, 5.625, 30, 6.125),
			Block.makeCuboidShape(4.625, 29, 10.125, 5.625, 30, 11.125),
			Block.makeCuboidShape(5.125, 25, 3.125, 12.125, 32, 13.125)).reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	public static final VoxelShape shape_w = Stream.of(Block.makeCuboidShape(6.125, 0, 4.125, 11.125, 25, 12.125),
			Block.makeCuboidShape(7.125, 22, 1.125, 11.125, 24, 4.125),
			Block.makeCuboidShape(7.125, 22, 12.125, 11.125, 24, 15.125),
			Block.makeCuboidShape(7.125, 11, 1.125, 11.125, 22, 4.125),
			Block.makeCuboidShape(7.125, 11, 12.125, 11.125, 22, 15.125),
			Block.makeCuboidShape(4.625, 29, 5.125, 5.625, 30, 6.125),
			Block.makeCuboidShape(4.625, 29, 10.125, 5.625, 30, 11.125),
			Block.makeCuboidShape(5.125, 25, 3.125, 12.125, 32, 13.125)).reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	public Statue(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
		
		
	
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch (state.get(FACING)) {
		case NORTH:
			return shape_n;
		case WEST:
			return shape_w;
		case SOUTH:
			return shape_s;
		case EAST:
			return shape_e;
		default:
			return shape_n;

		}
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {

		if (worldIn.isRemote) {

		}

		float yaw = player.getYaw(1);

		// Make sure yaw is in the range 0 to 360
		while(yaw < 0){yaw+=360;}
		yaw = yaw % 360;

		// The player's yaw is their rotation in the world,
		// so, we can use that to get the right face of a block!
		BlockState rightFace = state;

		// if the player is facing SE to SW
		if(yaw < 45 || yaw >= 315){
		    rightFace = state.with(FACING, Direction.EAST);
		}

		// if the player is facing SW to NW
		else if(yaw < 135){
		    rightFace = state.with(FACING, Direction.EAST);
		}

		// if the player is facing NW to NE
		else if(yaw < 225){
			rightFace = state.with(FACING, Direction.WEST);
		}

		// if the player is facing NE to SE
		else if(yaw < 315){
		    rightFace = state.with(FACING, Direction.NORTH);
		}
		
		Block b;
	

		
		
	    
	    worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
	    
		Thread thread = new Thread(new MyRunnable(worldIn, player, pos, rightFace));
		thread.start();
	   
	  

		return ActionResultType.SUCCESS;

	}

}
