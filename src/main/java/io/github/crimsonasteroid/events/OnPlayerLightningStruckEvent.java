package io.github.crimsonasteroid.events;

import io.github.crimsonasteroid.CrimsonAsteroid;
import io.github.crimsonasteroid.init.BlockInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;



@Mod.EventBusSubscriber(modid = CrimsonAsteroid.MOD_ID, bus = Bus.FORGE)
public class OnPlayerLightningStruckEvent {

@SubscribeEvent
 public static void OnPlayerLightningStruckEvent(EntityStruckByLightningEvent event) 
{
	LivingEntity living;
	Entity entity = event.getEntity();
    World world = entity.getEntityWorld();
    if(entity instanceof LivingEntity) {
    	  living = (LivingEntity) entity;
    	  living.addPotionEffect(new EffectInstance(Effects.SPEED, 50000000, 50));
    	  living.setGlowing(true);
    	}
    
    
    
    
    
}
 
}
