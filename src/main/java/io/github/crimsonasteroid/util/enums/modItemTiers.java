package io.github.crimsonasteroid.util.enums;

import java.util.function.Supplier;

import io.github.crimsonasteroid.init.ItemInit;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public enum modItemTiers implements IItemTier {
	ABYSSAL(4, 10000, 100f, 100f, 500, () -> {
		return Ingredient.fromItems(ItemInit.ABYSSIUM_INGOT.get());
	});

	private final int harvestlevel;
	private final int maxuses;
	private final float efficiency;
	private final float attackdamge;
	private final int enchantability;
	private LazyValue<Ingredient> repairmaterial;

	private modItemTiers(int harvestlevel, int maxuses, float efficiency, float attackdamage, int enchantabilty,
			Supplier<Ingredient> repairmaterial) {
		this.harvestlevel = harvestlevel;
		this.maxuses = maxuses;
		this.efficiency = efficiency;
		this.attackdamge = attackdamage;
		this.enchantability = enchantabilty;
		this.repairmaterial = new LazyValue<>(repairmaterial);
	}

	@Override
	public int getMaxUses() {
		// TODO Auto-generated method stub
		return this.maxuses;
	}

	@Override
	public float getEfficiency() {
		// TODO Auto-generated method stub
		return this.efficiency;
	}

	@Override
	public float getAttackDamage() {
		// TODO Auto-generated method stub
		return this.attackdamge;
	}

	@Override
	public int getHarvestLevel() {
		// TODO Auto-generated method stub
		return this.harvestlevel;
	}

	@Override
	public int getEnchantability() {
		// TODO Auto-generated method stub
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairMaterial() {
		// TODO Auto-generated method stub
		return this.repairmaterial.getValue();
	}
}

