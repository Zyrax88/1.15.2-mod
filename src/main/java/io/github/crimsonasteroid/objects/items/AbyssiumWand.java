package io.github.crimsonasteroid.objects.items;

import java.util.List;

import io.github.crimsonasteroid.init.BlockInit;
import io.github.crimsonasteroid.util.helpers.KeyboardUtil;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AbyssiumWand extends Item {

	float absorbedammount = 0;
	World world;
	PlayerEntity player;
	int factor = 2;
	boolean foundnoBlock = true;
	BlockPos theblock;

	public AbyssiumWand(Properties properties) {
		super(properties);

	}

	public void AbsorbAbyssium(BlockPos pos, World world, Entity entityIn) {
		if (absorbedammount < 1000) {

			absorbedammount += 0.1;

			System.out.println(absorbedammount);
			
			
				
		}
	}

	public void CastSpell() {
		world.setBlockState(player.getPosition().add(2, 0, 0), Blocks.OBSIDIAN.getDefaultState());
	}

	@Override
	public boolean hasEffect(ItemStack stack) {

		return true;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (KeyboardUtil.isHoldingShift()) {
			tooltip.add(new StringTextComponent(
					"The Wands of ethernal anguish were created milenia ago. The wand has brought the world many cataclystic events. The first user of this wand was the legendary witch morgan le fay. This wand is truly malevolant. Can you use it for good?" + "\n" + " RightClick: fire \n Shift+right click to create thunder. \n ctrl+right click to create lava"));

		} else {
			tooltip.add(new StringTextComponent("Stand close to a abyssium ore or block, to absorb abyssic mana " + "Hold" + "\u00A7e" + " Shift " + "\u00A7e" + "For more information"));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	public void decreasseFactor() {
		if (factor >= 2)
			factor--;
	}

	public void increaseFactor() {
		if (factor < 15)
			factor++;
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (KeyboardUtil.PressedV()) {
			increaseFactor();
			System.out.print("increassed Factor");
		}
		if (KeyboardUtil.PressedC()) {
			decreasseFactor();
			System.out.print("decreassed factor");
		}

		if (absorbedammount <= 1000) {
		if(foundnoBlock) {
			
				for (int x = -5; x < 5; x++) {
					for (int y = -5; y < 5; y++) {
						for (int z = -5; z < 5; z++) {
							if (worldIn.getBlockState(entityIn.getPosition().add(x, y, z)) == BlockInit.abyssium_block
									.get().getDefaultState()) {
								
								theblock = entityIn.getPosition().add(x, y, z);
								AbsorbAbyssium(theblock, worldIn, entityIn);
								
								foundnoBlock = false;
								break;
							}
						}
					}
				}
			}
		 else if(!foundnoBlock) {
			if(theblock.distanceSq(entityIn.getPosition()) < 2){
				AbsorbAbyssium(theblock, worldIn, entityIn);
			} else {
				foundnoBlock = true;
			}
		}
	}
			
		
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}
	

	@Override

	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		Vec3d lookingat = playerIn.getLookVec().mul(factor, factor, factor);

		if (KeyboardUtil.isHoldingShift()) {
			if(!worldIn.isRemote) {
				ServerWorld serverworld = (ServerWorld)worldIn;
			
			LightningBoltEntity bolt = new LightningBoltEntity(worldIn, playerIn.getPosition().getX() + lookingat.x,
					playerIn.getPosition().getY() + lookingat.y, playerIn.getPosition().getZ() + lookingat.z, false);
			bolt.setMotion(lookingat.x * 3.0D, lookingat.y * 3.0D, lookingat.z * 3.0D);
			bolt.setGlowing(true);

			serverworld.addLightningBolt(bolt);
			}

		} else if (KeyboardUtil.isHoldingCtrl()) {

			worldIn.setBlockState(playerIn.getPosition().add(lookingat.getX(), 0, lookingat.getZ()),
					Blocks.LAVA.getDefaultState());
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

}
