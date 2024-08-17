package com.example.examplemod;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class KrejtmBlock extends Block{
    public KrejtmBlock() {
        super(BlockBehaviour.Properties
                .of()  // No need to specify material directly.
                .mapColor(MapColor.STONE)  // Specify the map color of the block
                .strength(3.0f, 10.0f)  // Hardness and resistance
                .requiresCorrectToolForDrops()  // Requires the correct tool to drop
        );
    }
}
