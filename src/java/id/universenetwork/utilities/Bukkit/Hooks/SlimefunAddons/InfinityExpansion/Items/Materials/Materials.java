package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Materials;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Groups.Groups;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Machines.VoidHarvester;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import lombok.experimental.UtilityClass;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Groups.Groups.MAIN_MATERIALS;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Blocks.InfinityWorkbench.TYPE;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.COPPER_INGOT;
import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.*;
import static org.bukkit.Material.*;

@UtilityClass
public final class Materials {
    public static final SlimefunItemStack INFINITE_CIRCUIT = new SlimefunItemStack("INFINITE_MACHINE_CIRCUIT", DIAMOND, "&bInfinite &6Machine Circuit", "&7Machine Component");
    public static final SlimefunItemStack INFINITE_CORE = new SlimefunItemStack("INFINITE_MACHINE_CORE", DIAMOND_BLOCK, "&bInfinite Machine Core", "&7Machine Component");
    public static final SlimefunItemStack MAGSTEEL_PLATE = new SlimefunItemStack("MAGSTEEL_PLATE", NETHERITE_SCRAP, "&4MagSteel Plate", "&7Machine Component");
    public static final SlimefunItemStack MACHINE_PLATE = new SlimefunItemStack("MACHINE_PLATE", PAPER, "&fMachine Plate", "&7Machine Component");
    public static final SlimefunItemStack MACHINE_CIRCUIT = new SlimefunItemStack("MACHINE_CIRCUIT", GOLD_INGOT, "&6Machine Circuit", "&7Machine Component");
    public static final SlimefunItemStack MACHINE_CORE = new SlimefunItemStack("MACHINE_CORE", IRON_BLOCK, "&fMachine Core", "&7Machine Component");
    public static final SlimefunItemStack VOID_BIT = new SlimefunItemStack("VOID_BIT", IRON_NUGGET, "&8Void Bit", "&7&oIt feels... empty");
    public static final SlimefunItemStack VOID_DUST = new SlimefunItemStack("VOID_DUST", GUNPOWDER, "&8Void Dust", "&7&oIts starting to take form...");
    public static final SlimefunItemStack VOID_INGOT = new SlimefunItemStack("VOID_INGOT", NETHERITE_INGOT, "&8Void Ingot", "&7&oThe emptiness of the cosmos", "&7&oin the palm of your hand");
    public static final SlimefunItemStack COBBLE_1 = new SlimefunItemStack("COMPRESSED_COBBLESTONE_1", ANDESITE, "&7Single Compressed Cobblestone", "&89 cobblestone combined");
    public static final SlimefunItemStack COBBLE_2 = new SlimefunItemStack("COMPRESSED_COBBLESTONE_2", ANDESITE, "&7Double Compressed Cobblestone", "&881 cobblestone combined");
    public static final SlimefunItemStack COBBLE_3 = new SlimefunItemStack("COMPRESSED_COBBLESTONE_3", STONE, "&7Triple Compressed Cobblestone", "&8729 cobblestone combined");
    public static final SlimefunItemStack COBBLE_4 = new SlimefunItemStack("COMPRESSED_COBBLESTONE_4", STONE, "&7Quadruple Compressed Cobblestone", "&86,561 cobblestone combined");
    public static final SlimefunItemStack COBBLE_5 = new SlimefunItemStack("COMPRESSED_COBBLESTONE_5", POLISHED_ANDESITE, "&7Quintuple Compressed Cobblestone", "&859,049 cobblestone combined");
    public static final SlimefunItemStack MAGSTEEL = new SlimefunItemStack("MAGSTEEL", BRICK, "&4MagSteel");
    public static final SlimefunItemStack MAGNONIUM = new SlimefunItemStack("MAGNONIUM", NETHER_BRICK, "&5Magnonium");
    public static final SlimefunItemStack TITANIUM = new SlimefunItemStack("TITANIUM", IRON_INGOT, "&7Titanium");
    public static final SlimefunItemStack MYTHRIL = new SlimefunItemStack("MYTHRIL", IRON_INGOT, "&bMythril");
    public static final SlimefunItemStack ADAMANTITE = new SlimefunItemStack("ADAMANTITE", BRICK, "&dAdamantite");
    public static final SlimefunItemStack INFINITE_INGOT = new SlimefunItemStack("INFINITE_INGOT", IRON_INGOT, "&bInfinity Ingot", "&7&oThe fury of the cosmos", "&7&oin the palm of your hand");
    public static final SlimefunItemStack FORTUNE_SINGULARITY = new SlimefunItemStack("FORTUNE_SINGULARITY", NETHER_STAR, "&6Fortune Singularity");
    public static final SlimefunItemStack EARTH_SINGULARITY = new SlimefunItemStack("EARTH_SINGULARITY", NETHER_STAR, "&aEarth Singularity");
    public static final SlimefunItemStack METAL_SINGULARITY = new SlimefunItemStack("METAL_SINGULARITY", NETHER_STAR, "&8Metal Singularity");
    public static final SlimefunItemStack MAGIC_SINGULARITY = new SlimefunItemStack("MAGIC_SINGULARITY", NETHER_STAR, "&dMagic Singularity");
    public static final SlimefunItemStack ENDER_ESSENCE = new SlimefunItemStack("END_ESSENCE", BLAZE_POWDER, "&5Ender Essence", "&8&oFrom the depths of the end...");
    public static final SlimefunItemStack COPPER_SINGULARITY = new SlimefunItemStack("COPPER_SINGULARITY", BRICKS, "&6Copper Singularity");
    public static final SlimefunItemStack ZINC_SINGULARITY = new SlimefunItemStack("ZINC_SINGULARITY", IRON_BLOCK, "&7Zinc Singularity");
    public static final SlimefunItemStack TIN_SINGULARITY = new SlimefunItemStack("TIN_SINGULARITY", IRON_BLOCK, "&7Tin Singularity");
    public static final SlimefunItemStack ALUMINUM_SINGULARITY = new SlimefunItemStack("ALUMINUM_SINGULARITY", IRON_BLOCK, "&7Aluminum Singularity");
    public static final SlimefunItemStack SILVER_SINGULARITY = new SlimefunItemStack("SILVER_SINGULARITY", IRON_BLOCK, "&7Silver Singularity");
    public static final SlimefunItemStack MAGNESIUM_SINGULARITY = new SlimefunItemStack("MAGNESIUM_SINGULARITY", NETHER_BRICKS, "&5Magnesium Singularity");
    public static final SlimefunItemStack LEAD_SINGULARITY = new SlimefunItemStack("LEAD_SINGULARITY", IRON_BLOCK, "&8Lead Singularity");
    public static final SlimefunItemStack GOLD_SINGULARITY = new SlimefunItemStack("GOLD_SINGULARITY", GOLD_BLOCK, "&6Gold Singularity");
    public static final SlimefunItemStack IRON_SINGULARITY = new SlimefunItemStack("IRON_SINGULARITY", IRON_BLOCK, "&7Iron Singularity");
    public static final SlimefunItemStack DIAMOND_SINGULARITY = new SlimefunItemStack("DIAMOND_SINGULARITY", DIAMOND_BLOCK, "&bDiamond Singularity");
    public static final SlimefunItemStack EMERALD_SINGULARITY = new SlimefunItemStack("EMERALD_SINGULARITY", EMERALD_BLOCK, "&aEmerald Singularity");
    public static final SlimefunItemStack NETHERITE_SINGULARITY = new SlimefunItemStack("NETHERITE_SINGULARITY", NETHERITE_BLOCK, "&4Netherite Singularity");
    public static final SlimefunItemStack COAL_SINGULARITY = new SlimefunItemStack("COAL_SINGULARITY", COAL_BLOCK, "&8Coal Singularity");
    public static final SlimefunItemStack REDSTONE_SINGULARITY = new SlimefunItemStack("REDSTONE_SINGULARITY", REDSTONE_BLOCK, "&cRedstone Singularity");
    public static final SlimefunItemStack LAPIS_SINGULARITY = new SlimefunItemStack("LAPIS_SINGULARITY", LAPIS_BLOCK, "&9Lapis Singularity");
    public static final SlimefunItemStack QUARTZ_SINGULARITY = new SlimefunItemStack("QUARTZ_SINGULARITY", QUARTZ_BLOCK, "&fQuartz Singularity");
    public static final SlimefunItemStack INFINITY_SINGULARITY = new SlimefunItemStack("INFINITY_SINGULARITY", SMOOTH_QUARTZ, "&bInfinity Singularity");
    public static final SlimefunItemStack BASIC_STRAINER = new SlimefunItemStack("BASIC_STRAINER", FISHING_ROD, "&9Basic Strainer", "&7Collects materials from flowing water", "", LoreBuilder.speed(1));
    public static final SlimefunItemStack ADVANCED_STRAINER = new SlimefunItemStack("ADVANCED_STRAINER", FISHING_ROD, "&cAdvanced Strainer", "&7Collects materials from flowing water", "", LoreBuilder.speed(4));
    public static final SlimefunItemStack REINFORCED_STRAINER = new SlimefunItemStack("REINFORCED_STRAINER", FISHING_ROD, "&fReinforced Strainer", "&7Collects materials from flowing water", "", LoreBuilder.speed(20));

    public static void setup() {
        new EnderEssence(MAIN_MATERIALS, ENDER_ESSENCE, new NamespacedKey(plugin, "ender_essence")).register(addon);
        registerEnhanced(COBBLE_1, new ItemStack[]{new ItemStack(COBBLESTONE), new ItemStack(COBBLESTONE), new ItemStack(COBBLESTONE), new ItemStack(COBBLESTONE), new ItemStack(COBBLESTONE), new ItemStack(COBBLESTONE), new ItemStack(COBBLESTONE), new ItemStack(COBBLESTONE), new ItemStack(COBBLESTONE)});
        registerEnhanced(COBBLE_2, new ItemStack[]{COBBLE_1, COBBLE_1, COBBLE_1, COBBLE_1, COBBLE_1, COBBLE_1, COBBLE_1, COBBLE_1, COBBLE_1});
        registerEnhanced(COBBLE_3, new ItemStack[]{COBBLE_2, COBBLE_2, COBBLE_2, COBBLE_2, COBBLE_2, COBBLE_2, COBBLE_2, COBBLE_2, COBBLE_2});
        registerEnhanced(COBBLE_4, new ItemStack[]{COBBLE_3, COBBLE_3, COBBLE_3, COBBLE_3, COBBLE_3, COBBLE_3, COBBLE_3, COBBLE_3, COBBLE_3});
        registerEnhanced(COBBLE_5, new ItemStack[]{COBBLE_4, COBBLE_4, COBBLE_4, COBBLE_4, COBBLE_4, COBBLE_4, COBBLE_4, COBBLE_4, COBBLE_4});
        registerEnhanced(VOID_DUST, new ItemStack[]{VOID_BIT, VOID_BIT, VOID_BIT, VOID_BIT, VOID_BIT, VOID_BIT, VOID_BIT, VOID_BIT, VOID_BIT});
        registerEnhanced(VOID_INGOT, new ItemStack[]{VOID_DUST, VOID_DUST, VOID_DUST, VOID_DUST, VOID_DUST, VOID_DUST, VOID_DUST, VOID_DUST, VOID_DUST});
        registerSmeltery(INFINITE_INGOT, EARTH_SINGULARITY, MYTHRIL, FORTUNE_SINGULARITY, MAGIC_SINGULARITY, VOID_INGOT, METAL_SINGULARITY);
        registerSmeltery(FORTUNE_SINGULARITY, GOLD_SINGULARITY, DIAMOND_SINGULARITY, EMERALD_SINGULARITY, NETHERITE_SINGULARITY, ADAMANTITE);
        registerSmeltery(MAGIC_SINGULARITY, REDSTONE_SINGULARITY, LAPIS_SINGULARITY, QUARTZ_SINGULARITY, MAGNESIUM_SINGULARITY, MAGNONIUM);
        registerSmeltery(EARTH_SINGULARITY, COBBLE_4, COAL_SINGULARITY, IRON_SINGULARITY, COPPER_SINGULARITY, LEAD_SINGULARITY);
        registerSmeltery(METAL_SINGULARITY, SILVER_SINGULARITY, ALUMINUM_SINGULARITY, TIN_SINGULARITY, ZINC_SINGULARITY, TITANIUM);
        registerSmeltery(MAGSTEEL, MAGNESIUM_INGOT, STEEL_INGOT, MAGNESIUM_DUST);
        registerSmeltery(TITANIUM, REINFORCED_ALLOY_INGOT, DAMASCUS_STEEL_INGOT, HARDENED_METAL_INGOT);
        registerSmeltery(MYTHRIL, REINFORCED_ALLOY_INGOT, IRON_SINGULARITY, HARDENED_METAL_INGOT);
        registerSmeltery(ADAMANTITE, REDSTONE_ALLOY, DIAMOND_SINGULARITY, MAGSTEEL);
        registerSmeltery(MAGNONIUM, MAGSTEEL, MAGNESIUM_SINGULARITY, ENDER_ESSENCE);
        register(VOID_BIT, VoidHarvester.TYPE, new ItemStack[0]);
        registerEnhanced(MAGSTEEL_PLATE, new ItemStack[]{MAGSTEEL, MAGSTEEL, MAGSTEEL, MAGSTEEL, HARDENED_METAL_INGOT, MAGSTEEL, MAGSTEEL, MAGSTEEL, MAGSTEEL});
        registerEnhanced(MACHINE_CIRCUIT, new ItemStack[]{COPPER_INGOT, ELECTRO_MAGNET, COPPER_INGOT, COPPER_INGOT, SILICON, COPPER_INGOT, COPPER_INGOT, ELECTRO_MAGNET, COPPER_INGOT});
        new Strainer(BASIC_STRAINER, new ItemStack[]{new ItemStack(STICK), new ItemStack(STRING), new ItemStack(STICK), new ItemStack(STRING), new ItemStack(STICK), new ItemStack(STRING), new ItemStack(STICK), new ItemStack(STRING), new ItemStack(STICK),}, 1).register(addon);
        new Strainer(ADVANCED_STRAINER, new ItemStack[]{Materials.MAGSTEEL, new ItemStack(STRING), Materials.MAGSTEEL, new ItemStack(STRING), BASIC_STRAINER, new ItemStack(STRING), Materials.MAGSTEEL, new ItemStack(STRING), Materials.MAGSTEEL}, 4).register(addon);
        new Strainer(REINFORCED_STRAINER, new ItemStack[]{REINFORCED_ALLOY_INGOT, new ItemStack(STRING), REINFORCED_ALLOY_INGOT, new ItemStack(STRING), ADVANCED_STRAINER, new ItemStack(STRING), REINFORCED_ALLOY_INGOT, new ItemStack(STRING), REINFORCED_ALLOY_INGOT}, 20).register(addon);
        registerEnhanced(MACHINE_CORE, new ItemStack[]{TITANIUM, MACHINE_CIRCUIT, TITANIUM, MACHINE_CIRCUIT, MACHINE_PLATE, MACHINE_CIRCUIT, TITANIUM, MACHINE_CIRCUIT, TITANIUM});
        registerEnhanced(MACHINE_PLATE, new ItemStack[]{REINFORCED_ALLOY_INGOT, REINFORCED_PLATE, REINFORCED_ALLOY_INGOT, MAGSTEEL_PLATE, TITANIUM, MAGSTEEL_PLATE, REINFORCED_ALLOY_INGOT, REINFORCED_PLATE, REINFORCED_ALLOY_INGOT});
        register(Groups.INFINITY_CHEAT, INFINITE_CIRCUIT, TYPE, new ItemStack[]{MACHINE_CIRCUIT, INFINITE_INGOT, MACHINE_CIRCUIT, MACHINE_CIRCUIT, INFINITE_INGOT, MACHINE_CIRCUIT, VOID_INGOT, MACHINE_CIRCUIT, VOID_INGOT, VOID_INGOT, MACHINE_CIRCUIT, VOID_INGOT, INFINITE_INGOT, VOID_INGOT, MACHINE_CIRCUIT, MACHINE_CIRCUIT, VOID_INGOT, INFINITE_INGOT, INFINITE_INGOT, VOID_INGOT, MACHINE_CIRCUIT, MACHINE_CIRCUIT, VOID_INGOT, INFINITE_INGOT, VOID_INGOT, MACHINE_CIRCUIT, VOID_INGOT, VOID_INGOT, MACHINE_CIRCUIT, VOID_INGOT, MACHINE_CIRCUIT, INFINITE_INGOT, MACHINE_CIRCUIT, MACHINE_CIRCUIT, INFINITE_INGOT, MACHINE_CIRCUIT});
        register(Groups.INFINITY_CHEAT, INFINITE_CORE, TYPE, new ItemStack[]{MACHINE_PLATE, MACHINE_CORE, INFINITE_INGOT, INFINITE_INGOT, MACHINE_CORE, MACHINE_PLATE, MACHINE_CORE, MACHINE_PLATE, MACHINE_CIRCUIT, MACHINE_CIRCUIT, MACHINE_PLATE, MACHINE_CORE, INFINITE_INGOT, MACHINE_CIRCUIT, INFINITE_INGOT, INFINITE_INGOT, MACHINE_CIRCUIT, INFINITE_INGOT, INFINITE_INGOT, MACHINE_CIRCUIT, INFINITE_INGOT, INFINITE_INGOT, MACHINE_CIRCUIT, INFINITE_INGOT, MACHINE_CORE, MACHINE_PLATE, MACHINE_CIRCUIT, MACHINE_CIRCUIT, MACHINE_PLATE, MACHINE_CORE, MACHINE_PLATE, MACHINE_CORE, INFINITE_INGOT, INFINITE_INGOT, MACHINE_CORE, MACHINE_PLATE});
        new Singularity(COPPER_SINGULARITY, COPPER_INGOT, 3000).register(addon);
        new Singularity(ZINC_SINGULARITY, ZINC_INGOT, 3000).register(addon);
        new Singularity(TIN_SINGULARITY, TIN_INGOT, 3000).register(addon);
        new Singularity(ALUMINUM_SINGULARITY, ALUMINUM_INGOT, 3000).register(addon);
        new Singularity(SILVER_SINGULARITY, SILVER_INGOT, 3000).register(addon);
        new Singularity(MAGNESIUM_SINGULARITY, MAGNESIUM_INGOT, 3000).register(addon);
        new Singularity(LEAD_SINGULARITY, LEAD_INGOT, 3000).register(addon);
        new Singularity(GOLD_SINGULARITY, GOLD_INGOT, 2000).register(addon);
        new Singularity(IRON_SINGULARITY, IRON_INGOT, 2000).register(addon);
        new Singularity(DIAMOND_SINGULARITY, DIAMOND, 500).register(addon);
        new Singularity(EMERALD_SINGULARITY, EMERALD, 500).register(addon);
        new Singularity(NETHERITE_SINGULARITY, NETHERITE_INGOT, 200).register(addon);
        new Singularity(COAL_SINGULARITY, COAL, 1500).register(addon);
        new Singularity(REDSTONE_SINGULARITY, REDSTONE, 1500).register(addon);
        new Singularity(LAPIS_SINGULARITY, LAPIS_LAZULI, 1500).register(addon);
        new Singularity(QUARTZ_SINGULARITY, QUARTZ, 1500).register(addon);
        new Singularity(INFINITY_SINGULARITY, INFINITE_INGOT, 100).register(addon);
    }

    static void registerEnhanced(SlimefunItemStack item, ItemStack[] recipe) {
        register(item, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);
    }

    static void registerSmeltery(SlimefunItemStack itemStack, ItemStack... recipe) {
        register(itemStack, RecipeType.SMELTERY, Arrays.copyOf(recipe, 9));
    }

    static void register(SlimefunItemStack itemStack, RecipeType type, ItemStack[] recipe) {
        register(MAIN_MATERIALS, itemStack, type, recipe);
    }

    static void register(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        new SlimefunItem(category, item, recipeType, recipe).register(addon);
    }
}