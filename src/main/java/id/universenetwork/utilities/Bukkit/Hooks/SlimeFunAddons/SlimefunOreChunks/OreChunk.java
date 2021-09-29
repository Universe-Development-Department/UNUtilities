package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.SlimefunOreChunks;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.Locale;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

class OreChunk extends SlimefunItem {
    final int amplifier;
    final String name;
    final MultiBlockMachine machine;
    final ItemStack output;

    public OreChunk(ItemGroup itemGroup, String id, String name, int amplifier, String texture, ItemStack output) {
        this(itemGroup, id, name, "&7Use an Ore Crusher to turn this into Dust", amplifier, texture, RecipeType.ORE_CRUSHER, output);
    }

    public OreChunk(ItemGroup itemGroup, String id, String name, String lore, int amplifier, String texture, RecipeType machine, ItemStack output) {
        super(itemGroup, new SlimefunItemStack(id, texture, "&r" + name, lore), RecipeType.GEO_MINER, new ItemStack[0]);
        this.amplifier = amplifier;
        this.name = name;
        this.machine = (MultiBlockMachine) machine.getMachine();
        this.output = output;
        (new OreResource(new NamespacedKey(plugin, id.toLowerCase(Locale.ROOT)), this)).register();
    }

    public String getName() {
        return this.name;
    }

    public int getAmplifier() {
        return this.amplifier;
    }

    @Override
    public void postRegister() {
        if (!machine.isDisabled()) {
            machine.addRecipe(new ItemStack[]{this.getItem()}, this.output);
        }
    }
}
