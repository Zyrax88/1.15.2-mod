package io.github.crimsonasteroid.objects.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.Properties;

public class Dicyanoacetylene extends Item{
	
	public Dicyanoacetylene(Properties properties) {
		super(properties);
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack) {
		// TODO Auto-generated method stub
		return 600*20;
	}
	
	
	
	
}
