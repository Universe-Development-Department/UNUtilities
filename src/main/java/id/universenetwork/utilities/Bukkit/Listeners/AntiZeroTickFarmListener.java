package id.universenetwork.utilities.Bukkit.Listeners;

import id.universenetwork.utilities.Bukkit.Enums.Features.AntiZeroTickFarm;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;

import java.util.ArrayList;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Manager.Config.AZTFBoolean;

public class AntiZeroTickFarmListener implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onPistonOut(BlockPistonExtendEvent e) {
        if (AZTFBoolean(AntiZeroTickFarm.ENABLED)) {
            breakPlantsBeside(e.getBlock(), e.getDirection());
            breakPlantsAbove(e.getBlocks());
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onPistonIn(BlockPistonRetractEvent e) {
        if (AZTFBoolean(AntiZeroTickFarm.ENABLED)) breakPlantsAbove(e.getBlocks());
    }

    void breakPlantsBeside(Block block, BlockFace direction) {

        for (Block b : getNearbyBlocks(block, direction)) {
            if (b.getType() == Material.CACTUS) {
                b.breakNaturally();
            }
        }

    }

    void breakPlantsAbove(List<Block> blockList) {
        for (Block block : blockList) {
            Block target = block.getRelative(BlockFace.UP);
            if (target.getBlockData() instanceof Ageable) {
                target.breakNaturally();
            }
        }

    }

    List<Block> getNearbyBlocks(Block block, BlockFace direction) {
        List<Block> blocks = new ArrayList();
        blocks.add(block.getRelative(direction).getRelative(direction));
        switch (direction) {
            case NORTH:
                blocks.add(block.getRelative(BlockFace.NORTH_WEST));
                blocks.add(block.getRelative(BlockFace.NORTH_EAST));
                break;
            case WEST:
                blocks.add(block.getRelative(BlockFace.NORTH_WEST));
                blocks.add(block.getRelative(BlockFace.SOUTH_WEST));
                break;
            case SOUTH:
                blocks.add(block.getRelative(BlockFace.SOUTH_EAST));
                blocks.add(block.getRelative(BlockFace.SOUTH_WEST));
                break;
            case EAST:
                blocks.add(block.getRelative(BlockFace.NORTH_EAST));
                blocks.add(block.getRelative(BlockFace.SOUTH_EAST));
        }
        return blocks;
    }
}