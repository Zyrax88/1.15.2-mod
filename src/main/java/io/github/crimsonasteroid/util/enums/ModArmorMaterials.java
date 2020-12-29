package io.github.crimsonasteroid.util.enums;

import java.util.function.Supplier;

import io.github.crimsonasteroid.CrimsonAsteroid;
import io.github.crimsonasteroid.init.ItemInit;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;



	public enum ModArmorMaterials implements IArmorMaterial {
		ABYSSIUM(CrimsonAsteroid.MOD_ID + ":test", 8, new int[] { 50, 60, 70, 60 }, 420,
				SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 20.0f, () -> {
					return Ingredient.fromItems(ItemInit.ABYSSIUM_INGOT.get());
				});

		private static final int[] max_damage_array = new int[] {50,60,70,60};
		private final String name;
		private final int maxdamagefactor;
		private final int[] damagereductionamountarray;
		private final int enchantability;
		private SoundEvent soundEvent;
		private final float toughness;
		private final LazyValue<Ingredient> repairMaterial;

		private ModArmorMaterials(String nameIn, int maxDamageFactor, int[] damagereductionamountIn,
				int enchantabilityIn, SoundEvent soundeventIn, float toughnessIn, Supplier<Ingredient> repairmaterialIn) {
			this.name = nameIn;
			this.maxdamagefactor = maxDamageFactor;
			this.damagereductionamountarray = damagereductionamountIn;
			this.enchantability = enchantabilityIn;
			this.soundEvent = soundeventIn;
			this.toughness = toughnessIn;
			this.repairMaterial = new LazyValue(repairmaterialIn);
		}

		@Override
		public int getDurability(EquipmentSlotType slotIn) {
			// TODO Auto-generated method stub
			return max_damage_array[slotIn.getIndex()]*this.maxdamagefactor;
		}

		@Override
		public int getDamageReductionAmount(EquipmentSlotType slotIn) {
			// TODO Auto-generated method stub
			return this.damagereductionamountarray[slotIn.getIndex()];
		}

		@Override
		public int getEnchantability() {
			// TODO Auto-generated method stub
			return this.enchantability;
		}

		@Override
		public SoundEvent getSoundEvent() {
			// TODO Auto-generated method stub
			return this.soundEvent;
		}

		@Override
		public Ingredient getRepairMaterial() {
			// TODO Auto-generated method stub
			return this.repairMaterial.getValue();
		}
		@OnlyIn(Dist.CLIENT)
		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return this.name;
		}

		@Override
		public float getToughness() {
			// TODO Auto-generated method stub
			return this.toughness;
		}
		

	}
