package io.github.crimsonasteroid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.crimsonasteroid.init.BlockInit;
import io.github.crimsonasteroid.init.ItemInit;
import io.github.crimsonasteroid.world.gen.CrimsonOreGen;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(CrimsonAsteroid.MOD_ID)
@Mod.EventBusSubscriber(modid = CrimsonAsteroid.MOD_ID, bus = Bus.MOD)
public class CrimsonAsteroid
{
    final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
	public static final String MOD_ID = "crimsonasteroid";
	public static CrimsonAsteroid Instance;
    public static final Logger LOGGER = LogManager.getLogger();

    public CrimsonAsteroid() {
    	
        bus.addListener(this::setup);
        ItemInit.ITEMS.register(bus);
        BlockInit.Blocks.register(bus);
        
        Instance = this;
        MinecraftForge.EVENT_BUS.register(this);
       
    }

    @SubscribeEvent
    public static void OnRegisterItems(final RegistryEvent.Register<Item> event)
    {
    	final IForgeRegistry<Item> registry = event.getRegistry();
    	BlockInit.Blocks.getEntries().stream().map(RegistryObject::get).forEach(block -> {
    	final Item.Properties properties = new Item.Properties().group(ItemGroup.BUILDING_BLOCKS);
    	final BlockItem blockItem = new BlockItem(block, properties);
    	blockItem.setRegistryName(block.getRegistryName());
    	registry.register(blockItem);
    });
    	
    	LOGGER.debug("Registered block Items");
    }
    private void setup(final FMLCommonSetupEvent event)
    {
     
    }
    
    @SubscribeEvent
    public static void LoadCompleteEvent(FMLLoadCompleteEvent event) {
    	CrimsonOreGen.GenerateOre();
    }
  
    public static class CrimsonItemGroup extends ItemGroup{
    	  public static final CrimsonItemGroup instance = new CrimsonItemGroup(ItemGroup.GROUPS.length, "crimsonasteroidTab");
    	    
		private CrimsonItemGroup(int index, String label) {
			super(index, label);
			
		}
    	
		@Override
		public ItemStack createIcon() {
			return new ItemStack(BlockInit.abyssium_block.get());
		}
    }

}
