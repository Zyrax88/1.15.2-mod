package io.github.crimsonasteroid.init;

import java.util.function.Supplier;

import javax.naming.directory.ModificationItem;

import io.github.crimsonasteroid.CrimsonAsteroid;
import io.github.crimsonasteroid.CrimsonAsteroid.CrimsonItemGroup;
import io.github.crimsonasteroid.objects.items.AbyssiumWand;
import io.github.crimsonasteroid.objects.items.Dicyanoacetylene;
import io.github.crimsonasteroid.util.enums.ModArmorMaterials;
import io.github.crimsonasteroid.util.enums.modItemTiers;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Food;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemTier;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.LazyValue;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS,
			CrimsonAsteroid.MOD_ID);

	public static final RegistryObject<Item> KUNAI = ITEMS.register("kunai",
			() -> new Item(new Item.Properties().group(CrimsonItemGroup.instance).maxStackSize(1).defaultMaxDamage(5)));
	public static final RegistryObject<Item> ABYSSIUM_INGOT = ITEMS.register("abyssium_ingot",
			() -> new Item(new Item.Properties().group(CrimsonItemGroup.instance).maxStackSize(64)));

	public static final RegistryObject<Item> ASTRONAUT_ICE_CREAM = ITEMS
			.register("astronauticecream",
					() -> new Item(new Properties().group(CrimsonItemGroup.instance)
							.food(new Food.Builder().hunger(6).saturation(1.2f)
									.effect(new EffectInstance(Effects.INSTANT_DAMAGE, 1, 1), 0.3f).build())
							.maxStackSize(64)));
	public static final RegistryObject<Item> dicyanoacetylene = ITEMS.register("dicyanoacetylene", () -> new Dicyanoacetylene(new Item.Properties().group(CrimsonItemGroup.instance).maxStackSize(64)));

	public static final RegistryObject<Item> abyssium_wand = ITEMS.register("abyssium_wand",
			() -> new AbyssiumWand(new Item.Properties().group(CrimsonItemGroup.instance)));

	
	// Armor

	public static final RegistryObject<Item> abyssium_helmet = ITEMS.register("abyssium_helmet",
			() -> new ArmorItem(ModArmorMaterials.ABYSSIUM, EquipmentSlotType.HEAD,
					new Item.Properties().group(CrimsonItemGroup.instance)));
	public static final RegistryObject<Item> abyssium_chestplate = ITEMS.register("abyssium_chestplate",
			() -> new ArmorItem(ModArmorMaterials.ABYSSIUM, EquipmentSlotType.CHEST,
					new Item.Properties().group(CrimsonItemGroup.instance)));
	public static final RegistryObject<Item> abyssium_leggings = ITEMS.register("abyssium_leggings",
			() -> new ArmorItem(ModArmorMaterials.ABYSSIUM, EquipmentSlotType.LEGS,
					new Item.Properties().group(CrimsonItemGroup.instance)));
	public static final RegistryObject<Item> abyssium_boots = ITEMS.register("abyssium_boots",
			() -> new ArmorItem(ModArmorMaterials.ABYSSIUM, EquipmentSlotType.FEET,
					new Item.Properties().group(CrimsonItemGroup.instance)));

	// tools

	public static final RegistryObject<PickaxeItem> ABYSSAL_PICKAXE = ITEMS.register("abyssal_pickaxe",
			() -> new PickaxeItem(modItemTiers.ABYSSAL, 40, 10, new Item.Properties().group(CrimsonItemGroup.instance)));
	public static final RegistryObject<AxeItem> ABYSSAL_AXE = ITEMS.register("abyssal_axe",
			() -> new AxeItem(modItemTiers.ABYSSAL, 90, 6.0f, new Item.Properties().group(CrimsonItemGroup.instance)));
	public static final RegistryObject<ShovelItem> ABYSSAL_SHOVEL = ITEMS.register("abyssal_shovel",
			() -> new ShovelItem(modItemTiers.ABYSSAL, 30, 9, new Item.Properties().group(CrimsonItemGroup.instance)));
	public static final RegistryObject<HoeItem> ABYSSAL_HOE = ITEMS.register("abyssal_hoe",
			() -> new HoeItem(modItemTiers.ABYSSAL, 35, new Item.Properties().group(CrimsonItemGroup.instance)));
	public static final RegistryObject<SwordItem> KATANA = ITEMS.register("abyssal_katana",
			() -> new SwordItem(modItemTiers.ABYSSAL, 100, 6.0f,
					new Item.Properties().group(CrimsonItemGroup.instance)));

	

}
