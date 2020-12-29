package io.github.crimsonasteroid.objects.blocks;

import java.util.stream.Stream;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class Chair extends Block {

	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

	private static final VoxelShape shape_n = Stream
			.of(Block.makeCuboidShape(2, 0, 10, 4, 5, 12), Block.makeCuboidShape(12, 0, 10, 14, 5, 12),
					Block.makeCuboidShape(13, 6, 7, 14, 9, 8), Block.makeCuboidShape(2, 6, 7, 3, 9, 8),
					Block.makeCuboidShape(12, 0, 3, 14, 5, 5), Block.makeCuboidShape(2, 0, 3, 4, 5, 5),
					Block.makeCuboidShape(2, 5, 3, 14, 6, 12), Block.makeCuboidShape(2, 9, 5, 3, 10, 10),
					Block.makeCuboidShape(13, 9, 5, 14, 10, 10), Block.makeCuboidShape(2, 6, 11, 14, 19, 12))
			.reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	private static final VoxelShape shape_s = Stream
			.of(Block.makeCuboidShape(12, 0, 3.8000000000000007, 14, 5, 5.800000000000001),
					Block.makeCuboidShape(2, 0, 3.8000000000000007, 4, 5, 5.800000000000001),
					Block.makeCuboidShape(2, 6, 7.800000000000001, 3, 9, 8.8),
					Block.makeCuboidShape(13, 6, 7.800000000000001, 14, 9, 8.8),
					Block.makeCuboidShape(2, 0, 10.8, 4, 5, 12.8), Block.makeCuboidShape(12, 0, 10.8, 14, 5, 12.8),
					Block.makeCuboidShape(2, 5, 3.8000000000000007, 14, 6, 12.8),
					Block.makeCuboidShape(13, 9, 5.800000000000001, 14, 10, 10.8),
					Block.makeCuboidShape(2, 9, 5.800000000000001, 3, 10, 10.8),
					Block.makeCuboidShape(2, 6, 3.8000000000000007, 14, 19, 4.800000000000001))
			.reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	private static final VoxelShape shape_e = Stream
			.of(Block.makeCuboidShape(3.9000000000000004, 0, 1.9000000000000004, 5.9, 5, 3.9000000000000004),
					Block.makeCuboidShape(3.9000000000000004, 0, 11.9, 5.9, 5, 13.9),
					Block.makeCuboidShape(7.9, 6, 12.9, 8.9, 9, 13.9),
					Block.makeCuboidShape(7.9, 6, 1.9000000000000004, 8.9, 9, 2.9000000000000004),
					Block.makeCuboidShape(10.9, 0, 11.9, 12.9, 5, 13.9),
					Block.makeCuboidShape(10.9, 0, 1.9000000000000004, 12.9, 5, 3.9000000000000004),
					Block.makeCuboidShape(3.9000000000000004, 5, 1.9000000000000004, 12.9, 6, 13.9),
					Block.makeCuboidShape(5.9, 9, 1.9000000000000004, 10.9, 10, 2.9000000000000004),
					Block.makeCuboidShape(5.9, 9, 12.9, 10.9, 10, 13.9),
					Block.makeCuboidShape(3.9000000000000004, 6, 1.9000000000000004, 4.9, 19, 13.9))
			.reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	private static final VoxelShape shape_w = Stream.of(Block.makeCuboidShape(10.1, 0, 11.9, 12.1, 5, 13.9),
			Block.makeCuboidShape(10.1, 0, 1.9000000000000004, 12.1, 5, 3.9000000000000004),
			Block.makeCuboidShape(7.1, 6, 1.9000000000000004, 8.1, 9, 2.9000000000000004),
			Block.makeCuboidShape(7.1, 6, 12.9, 8.1, 9, 13.9),
			Block.makeCuboidShape(3.0999999999999996, 0, 1.9000000000000004, 5.1, 5, 3.9000000000000004),
			Block.makeCuboidShape(3.0999999999999996, 0, 11.9, 5.1, 5, 13.9),
			Block.makeCuboidShape(3.0999999999999996, 5, 1.9000000000000004, 12.1, 6, 13.9),
			Block.makeCuboidShape(5.1, 9, 12.9, 10.1, 10, 13.9),
			Block.makeCuboidShape(5.1, 9, 1.9000000000000004, 10.1, 10, 2.9000000000000004),
			Block.makeCuboidShape(11.1, 6, 1.9000000000000004, 12.1, 19, 13.9)).reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	public Chair(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));

	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

		switch (state.get(FACING)) {
		case NORTH:
			return shape_n;

		case SOUTH:
			return shape_s;

		case EAST:
			return shape_e;

		case WEST:
			return shape_w;

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
		if (!worldIn.isRemote) {
			ServerWorld serverworld = (ServerWorld) worldIn;
			
		}
		
		
		if(state.get(FACING) == Direction.NORTH) {
			player.setPosition(pos.getX()+0.5, pos.getY()+0.5, pos.getZ() +0.5f);
		}
		if(state.get(FACING) == Direction.SOUTH) {
			player.setPosition(pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5f);
		}
		if(state.get(FACING) == Direction.WEST) {
			player.setPosition(pos.getX()+0.5, pos.getY()+0.5, pos.getZ() + 0.5f);
		}
		if(state.get(FACING) == Direction.EAST) {
			player.setPosition(pos.getX()+0.5, pos.getY()+0.5, pos.getZ() + 0.5f);
		}
		
		
//		Vec3i playerpos = player.getPosition();
//		Vec3i newpos = pos;
//		 float motionx = 0;
//		 float motiony = 0;
//		float motionz = 0;
//		
//		while(player.getPosition() != pos.add(0,0.5,0)) {
//		
//		if(newpos.getX()>playerpos.getX()) {
//			motionx = 1;
//			
//		} else if(playerpos.getX() > newpos.getX()) {
//			motionx = -1;
//		}
//		if(newpos.getY()>playerpos.getY() + 0.5) {
//			motiony = 0.5f;
//			
//		} else if(playerpos.getY() > newpos.getY() +0.5) {
//			motiony = -0.5f;
//		}
//		if(newpos.getZ()>playerpos.getZ()) {
//			motionz = 1;
//			
//		} else if(playerpos.getZ() > newpos.getZ()) {
//			motionz = -1;
//		}
//		
//		player.setMotion(motionx, motiony, motionz);
//		
//		}
		
		
		return ActionResultType.SUCCESS;
	}

}
