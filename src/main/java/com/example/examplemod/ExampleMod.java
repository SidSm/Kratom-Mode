package com.example.examplemod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExampleMod.MODID)
public class ExampleMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "examplemod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);


    public static final RegistryObject<Block> KREJTM_BLOCK = BLOCKS.register("krejtm_block", () -> new Block(BlockBehaviour.Properties
            .of()  // No need to specify material directly.
            .mapColor(MapColor.STONE)  // Specify the map color of the block
            .strength(3.0f, 10.0f)  // Hardness and resistance
            .requiresCorrectToolForDrops()  // Requires the correct tool to drop
    ));
    public static final RegistryObject<Item> KREJTM_BLOCK_ITEM = ITEMS.register("krejtm_block",
            () -> new BlockItem(KREJTM_BLOCK.get(), new Item.Properties())
    );

    public static final RegistryObject<Block> KREJTM_BLOCK_RED = BLOCKS.register("krejtm_block_red",() -> new Block(BlockBehaviour.Properties
            .of()  // No need to specify material directly.
            .mapColor(MapColor.STONE)  // Specify the map color of the block
            .strength(3.0f, 10.0f)  // Hardness and resistance
            .requiresCorrectToolForDrops()  // Requires the correct tool to drop
    ));
    public static final RegistryObject<Item> KREJTM_BLOCK_RED_ITEM = ITEMS.register("krejtm_block_red",
            () -> new BlockItem(KREJTM_BLOCK_RED.get(), new Item.Properties())
    );



    //------------------FOOD----------------
    public static final FoodProperties KREJTM_FOOD = new FoodProperties.Builder()
            .nutrition(1)
            .fast()
            .saturationModifier(0.2f)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 1), 1)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 1), 1)
            .effect(new MobEffectInstance(MobEffects.JUMP, 200, 1), 1)
            .effect(new MobEffectInstance(MobEffects.DIG_SPEED, 200, 1), 1)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1), 1)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1), 1)
            .effect(new MobEffectInstance(MobEffects.LUCK, 200, 1), 1)
            .alwaysEdible()
            .build();

    /*public static final FoodProperties KREJTM_FOOD = new FoodProperties.Builder()
            .nutrition(1)  // Amount of hunger restored (1 unit = 1/2 hunger bar)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 200, 1), 1.0f)  // Potion effect, e.g., Regeneration for 10 seconds
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 1), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200, 1), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 1), 1.0f)
            .alwaysEat()  // Allows the player to eat the item even if not hungry
            .build();*/
    public static final RegistryObject<Item> KREJTM_FOOD_ITEM = ITEMS.register("krejtm_food",
            () -> new Item(new Item.Properties()
                    .food(KREJTM_FOOD)
            )
    );

    public static final FoodProperties KREJTM_FOOD_RED = new FoodProperties.Builder()
            .nutrition(0)
            .fast()
            .saturationModifier(0.2f)
            .effect(new MobEffectInstance(MobEffects.LEVITATION, 200, 1), 1)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 1), 1)
            .effect(new MobEffectInstance(MobEffects.CONFUSION, 200, 1), 1)
            .effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 200, 1), 1)


            .alwaysEdible()
            .build();

    /*public static final FoodProperties KREJTM_FOOD_RED = new FoodProperties.Builder()
            .nutrition(1)  // Amount of hunger restored (1 unit = 1/2 hunger bar)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 200, 1), 1.0f)  // Potion effect, e.g., Regeneration for 10 seconds
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 1), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.LEVITATION, 200, 1), 1.0f)
            .alwaysEat()  // Allows the player to eat the item even if not hungry
            .build();*/

    public static final RegistryObject<Item> KREJTM_FOOD_RED_ITEM = ITEMS.register("krejtm_food_red",
            () -> new Item(new Item.Properties()
                    .food(KREJTM_FOOD_RED)
            )
    );

    public ExampleMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);


        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
        //modEventBus.addListener(this::addCreativeItems);


        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(KREJTM_BLOCK_ITEM);
            event.accept(KREJTM_BLOCK_RED_ITEM);
        } else if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(KREJTM_FOOD_ITEM);
            event.accept(KREJTM_FOOD_RED_ITEM);
        }

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
