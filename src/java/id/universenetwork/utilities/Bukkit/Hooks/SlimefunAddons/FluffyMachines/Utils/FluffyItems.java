package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Utils;

import dev.j3fftw.extrautils.utils.LoreBuilderDynamic;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Items.Barrel;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Items.FireproofRune;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Items.Tools.FluffyWrench;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Items.Tools.PortableCharger;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Machines.*;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Objects.AutoCrafter;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import io.github.thebusybiscuit.slimefun4.utils.itemstack.ColoredFireworkStar;
import org.bukkit.Color;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead.getItemStack;
import static io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin.fromHashCode;
import static org.bukkit.Material.*;

public class FluffyItems {
    FluffyItems() {
    }

    // Item Group
    public static final ItemGroup fluffymachines = new ItemGroup(new NamespacedKey(plugin, "fluffymachines"), new CustomItemStack(SMOKER, "&6Fluffy Machines"));
    public static final ItemGroup fluffybarrels = new ItemGroup(new NamespacedKey(plugin, "fluffybarrels"), new CustomItemStack(BARREL, "&6Fluffy Barrels"));

    // Barrels
    public static final SlimefunItemStack SMALL_FLUFFY_BARREL = new SlimefunItemStack("SMALL_FLUFFY_BARREL", BEEHIVE, "&eSmall Fluffy Barrel", "", "&7Stores a large amount of an item", "&cDon't break with explosive tools...", "&cUnless you want to lose everything inside", "", "&bCapacity: &e" + Barrel.SMALL_BARREL_SIZE + " Items");
    public static final SlimefunItemStack MEDIUM_FLUFFY_BARREL = new SlimefunItemStack("MEDIUM_FLUFFY_BARREL", BARREL, "&6Medium Fluffy Barrel", "", "&7Stores a large amount of an item", "&cDon't break with explosive tools...", "&cUnless you want to lose everything inside", "", "&bCapacity: &e" + Barrel.MEDIUM_BARREL_SIZE + " Items");
    public static final SlimefunItemStack BIG_FLUFFY_BARREL = new SlimefunItemStack("BIG_FLUFFY_BARREL", SMOKER, "&bBig Fluffy Barrel", "", "&7Stores a large amount of an item", "&cDon't break with explosive tools...", "&cUnless you want to lose everything inside", "", "&bCapacity: &e" + Barrel.BIG_BARREL_SIZE + " Items");
    public static final SlimefunItemStack LARGE_FLUFFY_BARREL = new SlimefunItemStack("LARGE_FLUFFY_BARREL", LODESTONE, "&aLarge Fluffy Barrel", "", "&7Stores a large amount of an item", "&cDon't break with explosive tools...", "&cUnless you want to lose everything inside", "", "&bCapacity: &e" + Barrel.LARGE_BARREL_SIZE + " Items");
    public static final SlimefunItemStack MASSIVE_FLUFFY_BARREL = new SlimefunItemStack("MASSIVE_FLUFFY_BARREL", CRYING_OBSIDIAN, "&5Massive Fluffy Barrel", "", "&7Stores a large amount of an item", "&cDon't break with explosive tools...", "&cUnless you want to lose everything inside", "", "&bCapacity: &e" + Barrel.MASSIVE_BARREL_SIZE + " Items");
    public static final SlimefunItemStack BOTTOMLESS_FLUFFY_BARREL = new SlimefunItemStack("BOTTOMLESS_FLUFFY_BARREL", RESPAWN_ANCHOR, "&cBottomless Fluffy Barrel", "", "&7Stores a large amount of an item", "&cDon't break with explosive tools...", "&cUnless you want to lose everything inside", "", "&bCapacity: &e" + Barrel.BOTTOMLESS_BARREL_SIZE + " Items");

    // Portable Chargers
    public static final SlimefunItemStack SMALL_PORTABLE_CHARGER = new SlimefunItemStack("SMALL_PORTABLE_CHARGER", BRICK, "&eSmall Portable Charger", "", "&7A handheld charger that holds a lot of power", "", "&eCharge Speed: &7" + PortableCharger.Type.SMALL.chargeSpeed + " J/s", LoreBuilder.powerCharged(0, PortableCharger.Type.SMALL.chargeCapacity));
    public static final SlimefunItemStack MEDIUM_PORTABLE_CHARGER = new SlimefunItemStack("MEDIUM_PORTABLE_CHARGER", IRON_INGOT, "&6Medium Portable Charger", "", "&7A handheld charger that holds a lot of power", "", "&eCharge Speed: &7" + PortableCharger.Type.MEDIUM.chargeSpeed + " J/s", LoreBuilder.powerCharged(0, PortableCharger.Type.MEDIUM.chargeCapacity));
    public static final SlimefunItemStack BIG_PORTABLE_CHARGER = new SlimefunItemStack("BIG_PORTABLE_CHARGER", GOLD_INGOT, "&aBig Portable Charger", "", "&7A handheld charger that holds a lot of power", "", "&eCharge Speed: &7" + PortableCharger.Type.BIG.chargeSpeed + " J/s", LoreBuilder.powerCharged(0, PortableCharger.Type.BIG.chargeCapacity));
    public static final SlimefunItemStack LARGE_PORTABLE_CHARGER = new SlimefunItemStack("LARGE_PORTABLE_CHARGER", NETHER_BRICK, "&2Large Portable Charger", "", "&7A handheld charger that holds a lot of power", "", "&eCharge Speed: &7" + PortableCharger.Type.LARGE.chargeSpeed + " J/s", LoreBuilder.powerCharged(0, PortableCharger.Type.LARGE.chargeCapacity));
    public static final SlimefunItemStack CARBONADO_PORTABLE_CHARGER = new SlimefunItemStack("CARBONADO_PORTABLE_CHARGER", NETHERITE_INGOT, "&4Carbonado Portable Charger", "", "&7A handheld charger that holds a lot of power", "", "&eCharge Speed: &7" + PortableCharger.Type.CARBONADO.chargeSpeed + " J/s", LoreBuilder.powerCharged(0, PortableCharger.Type.CARBONADO.chargeCapacity));

    // Items
    public static final SlimefunItemStack ANCIENT_BOOK = new SlimefunItemStack("ANCIENT_BOOK", BOOK, "&6Ancient Book", "", "&7Used in the &cAdvanced Auto Disenchanter", "", "&6&oContains concentrated amounts of power");
    public static final SlimefunItemStack HELICOPTER_HAT = new SlimefunItemStack("HELICOPTER_HAT", LEATHER_HELMET, Color.AQUA, "&1Helicopter Hat", "", "&7brrrrrrrrRRRRRRRR", "", "&eSneak &7to use");
    public static final SlimefunItemStack WATERING_CAN = new SlimefunItemStack("WATERING_CAN", new CustomItemStack(getItemStack(fromHashCode("6484da45301625dee79ae29ff513efa583f1ed838033f20db80963cedf8aeb0e"))), "&bWatering Can", "", "&fWaters Plants", "", "&7> &eRight Click &7a water to fill your watering can", "&7> &eRight Click &7a plant to speed up growth.", "&7> &eRight Click &7a player to slow them down", "", "&aUses Left: &e0");
    public static final SlimefunItemStack ENDER_CHEST_EXTRACTION_NODE = new SlimefunItemStack("ENDER_CHEST_EXTRACTION_NODE", new CustomItemStack(getItemStack(fromHashCode("e707c7f6c3a056a377d4120028405fdd09acfcd5ae804bfde0f653be866afe39"))), "&6Ender Chest Extraction Node", "", "&7Place this on the side of an &5Ender Chest &7to bind", "", "&7This will move items from the facing &5Ender Chest", "&7to the &6Container &7behind it");
    public static final SlimefunItemStack ENDER_CHEST_INSERTION_NODE = new SlimefunItemStack("ENDER_CHEST_INSERTION_NODE", new CustomItemStack(getItemStack(fromHashCode("7e5dc50c0186d53381d9430a2eff4c38f816b8791890c7471ffdb65ba202bc5"))), "&bEnder Chest Insertion Node", "", "&7Place this on the side of an &5Ender Chest &7to bind", "", "&7This will move items to the facing &5Ender Chest", "&7from the &6Container &7behind it");

    // Machines
    public static final SlimefunItemStack AUTO_CRAFTING_TABLE = new SlimefunItemStack("AUTO_CRAFTING_TABLE", CRAFTING_TABLE, "&6Auto Crafting Table", "", "&7Automatically crafts &fvanilla &7recipes", "", LoreBuilderDynamic.powerBuffer(AutoCraftingTable.CAPACITY), LoreBuilderDynamic.powerPerTick(AutoCraftingTable.ENERGY_CONSUMPTION));
    public static final SlimefunItemStack AUTO_ANCIENT_ALTAR = new SlimefunItemStack("AUTO_ANCIENT_ALTAR", ENCHANTING_TABLE, "&5Auto Ancient Altar", "", "&7Automatically crafts &5Ancient Altar &7recipes", "", LoreBuilderDynamic.powerBuffer(AutoAncientAltar.CAPACITY), LoreBuilderDynamic.powerPerTick(AutoAncientAltar.ENERGY_CONSUMPTION));
    public static final SlimefunItemStack AUTO_TABLE_SAW = new SlimefunItemStack("AUTO_TABLE_SAW", STONECUTTER, "&6Auto Table Saw", "", "&7Automatically crafts &6Table Saw &7recipes", "", LoreBuilderDynamic.powerBuffer(AutoTableSaw.CAPACITY), LoreBuilderDynamic.powerPerTick(AutoTableSaw.ENERGY_CONSUMPTION));
    public static final SlimefunItemStack WATER_SPRINKER = new SlimefunItemStack("WATER_SPRINKLER", new CustomItemStack(getItemStack(fromHashCode("d6b13d69d1929dcf8edf99f3901415217c6a567d3a6ead12f75a4de3ed835e85")), "Water Sprinkler"), "&bWater Sprinkler", "", "&7Sprinkly sprinkly", "", LoreBuilderDynamic.powerBuffer(WaterSprinkler.CAPACITY), LoreBuilderDynamic.powerPerTick(WaterSprinkler.ENERGY_CONSUMPTION) + " per crop");
    public static final SlimefunItemStack ITEM_OVERSTACKER = new SlimefunItemStack("ITEM_OVERSTACKER", PISTON, "&eItem Overstacker", "", "&7Compresses nonstackable items");
    public static final SlimefunItemStack GENERATOR_CORE = new SlimefunItemStack("GENERATOR_CORE", BLAST_FURNACE, "&7Generator Core", "", "&7Multiblock component of generators");
    public static final SlimefunItemStack CRANK_GENERATOR = new SlimefunItemStack("CRANK_GENERATOR", BLAST_FURNACE, "&7Crank Generator", "", "&eRight click &7the lever to generate power", "", Utils.multiBlockWarning());
    public static final SlimefunItemStack FOUNDRY = new SlimefunItemStack("FOUNDRY", BLAST_FURNACE, "&cFoundry", "", "&eMelts and stores dusts and ingots", "&7Stores 138,240 dust (40 Double Chests)", "", Utils.multiBlockWarning());
    public static final SlimefunItemStack BACKPACK_UNLOADER = new SlimefunItemStack("BACKPACK_UNLOADER", BROWN_STAINED_GLASS, "&eBackpack Unloader", "", "&7Empties the contents of backpacks", "", LoreBuilderDynamic.powerBuffer(BackpackUnloader.CAPACITY), LoreBuilderDynamic.powerPerTick(BackpackUnloader.ENERGY_CONSUMPTION));
    public static final SlimefunItemStack BACKPACK_LOADER = new SlimefunItemStack("BACKPACK_LOADER", ORANGE_STAINED_GLASS, "&eBackpack Loader", "", "&7Moves items from inventory to backpack", "", LoreBuilderDynamic.powerBuffer(BackpackLoader.CAPACITY), LoreBuilderDynamic.powerPerTick(BackpackLoader.ENERGY_CONSUMPTION));
    public static final SlimefunItemStack UPGRADED_EXPLOSIVE_PICKAXE = new SlimefunItemStack("UPGRADED_EXPLOSIVE_PICKAXE", DIAMOND_PICKAXE, "&e&lUpgraded Explosive Pickaxe", "", "&7Breaks all mineable blocks in a 5x5 radius");
    public static final SlimefunItemStack UPGRADED_EXPLOSIVE_SHOVEL = new SlimefunItemStack("UPGRADED_EXPLOSIVE_SHOVEL", DIAMOND_SHOVEL, "&e&lUpgraded Explosive Shovel", "", "&7Breaks all shovelable blocks in a 5x5 radius");
    public static final SlimefunItemStack FIREPROOF_RUNE = new SlimefunItemStack("FIREPROOF_RUNE", new ColoredFireworkStar(Color.fromRGB(255, 165, 0), "&7Ancient Rune &8&l[&c&lFireproof&8&l]", "", "&eDrop this rune onto a dropped item to", "&emake it &cfireproof", ""));
    public static final SlimefunItemStack SUPERHEATED_FURNACE = new SlimefunItemStack("SUPERHEATED_FURNACE", BLAST_FURNACE, "&cSuper Heated Furnace", "", "&7Multiblock component of the Foundry", "&cMust be used in the Foundry", "&cDO NOT BREAK USING EXPLOSIVE TOOLS!");
    public static final SlimefunItemStack AUTO_MAGIC_WORKBENCH = new SlimefunItemStack("AUTO_MAGIC_WORKBENCH", BOOKSHELF, "&6Auto Magic Workbench", "", "&7Automatically crafts &6Magic Workbench &7recipes", "", LoreBuilderDynamic.powerBuffer(AutoCrafter.CAPACITY), LoreBuilderDynamic.powerPerTick(AutoCrafter.ENERGY_CONSUMPTION));
    public static final SlimefunItemStack AUTO_ARMOR_FORGE = new SlimefunItemStack("AUTO_ARMOR_FORGE", SMITHING_TABLE, "&7Auto Armor Forge", "", "&7Automatically crafts Armor Forge recipes", "", LoreBuilderDynamic.powerBuffer(AutoCrafter.CAPACITY), LoreBuilderDynamic.powerPerTick(AutoCrafter.ENERGY_CONSUMPTION));
    public static final SlimefunItemStack ADVANCED_AUTO_DISENCHANTER = new SlimefunItemStack("ADVANCED_AUTO_DISENCHANTER", ENCHANTING_TABLE, "&cAdvanced Auto Disenchanter", "", "&7Removes one enchant from an item", "&7Requires an &6Ancient Book &7to operate", "", LoreBuilderDynamic.powerBuffer(AdvancedAutoDisenchanter.CAPACITY), LoreBuilderDynamic.powerPerTick(AdvancedAutoDisenchanter.ENERGY_CONSUMPTION));
    public static final SlimefunItemStack SCYTHE = new SlimefunItemStack("SCYTHE", IRON_HOE, "&eScythe", "", "&7Breaks 5 crops at once");
    public static final SlimefunItemStack UPGRADED_LUMBER_AXE = new SlimefunItemStack("UPGRADED_LUMBER_AXE", DIAMOND_AXE, "&6&lUpgraded Lumber Axe", "", "&7Chops down an entire tree at once", "&72 block reach and works on diagonal blocks too");
    public static final SlimefunItemStack DOLLY = new SlimefunItemStack("DOLLY", MINECART, "&bDolly", "", "&7Right click a chest to pick it up", "", "&7ID: <ID>");
    public static final SlimefunItemStack WARP_PAD = new SlimefunItemStack("WARP_PAD", SMOKER, "&6Warp Pad", "", "&eCrouch &7on this block to teleport to", "&7the linked destination pad", "", "&7Use a Warp Pad Configurator to link Warp Pads");
    public static final SlimefunItemStack WARP_PAD_CONFIGURATOR = new SlimefunItemStack("WARP_PAD_CONFIGURATOR", BLAZE_ROD, "&6Warp Pad Configurator", "", "&eSneak and Right Click &7on a Warp Pad to set the destination", "&eRight Click &7on a Warp Pad to set the origin", "", "&eLinked Coordinates: &7None");
    public static final SlimefunItemStack ELECTRIC_DUST_FABRICATOR = new SlimefunItemStack("ELECTRIC_DUST_FABRICATOR", BLAST_FURNACE, "&6Electric Dust Fabricator", "", "&7An all-in-one machine that grinds, pans, and washes", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(10), LoreBuilderDynamic.powerBuffer(ElectricDustFabricator.CAPACITY), LoreBuilderDynamic.powerPerTick(ElectricDustFabricator.ENERGY_CONSUMPTION));
    public static final SlimefunItemStack ELECTRIC_DUST_RECYCLER = new SlimefunItemStack("ELECTRIC_DUST_RECYCLER", IRON_BLOCK, "&fElectric Dust Recycler", "", "&7Recycles dust back into sifted ore", LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE), LoreBuilder.speed(1), LoreBuilderDynamic.powerBuffer(ElectricDustRecycler.CAPACITY), LoreBuilderDynamic.powerPerTick(ElectricDustRecycler.ENERGY_CONSUMPTION));
    public static final SlimefunItemStack ALTERNATE_ELEVATOR_PLATE = new SlimefunItemStack("ALTERNATE_ELEVATOR_PLATE", POLISHED_BLACKSTONE_PRESSURE_PLATE, "&3Alternate Elevator Plate", "", "&fPlace an Elevator Plate on every floor", "&fand you will be able to teleport between them.", "", "&eRight Click this Block &7to name it", "&7Uses a Chest GUI instead of a Book GUI");
    public static final SlimefunItemStack FLUFFY_WRENCH = new SlimefunItemStack("FLUFFY_WRENCH", FluffyWrench.Wrench.DEFAULT.getMaterial(), "&6Fluffy Wrench", "", "&7Used to quickly remove Slimefun cargo nodes", "&7and electricity components", "", "&eLeft&7/&eRight Click &7a compatible block to break it");
    public static final SlimefunItemStack REINFORCED_FLUFFY_WRENCH = new SlimefunItemStack("REINFORCED_FLUFFY_WRENCH", FluffyWrench.Wrench.REINFORCED.getMaterial(), "&bReinforced Fluffy Wrench", "", "&7Used to quickly remove Slimefun cargo nodes", "&7and electricity components", "", "&eLeft&7/&eRight Click &7a compatible block to break it");
    public static final SlimefunItemStack CARBONADO_FLUFFY_WRENCH = new SlimefunItemStack("CARBONADO_FLUFFY_WRENCH", FluffyWrench.Wrench.CARBONADO.getMaterial(), "&7Carbonado Fluffy Wrench", "", "&7Used to quickly remove Slimefun cargo nodes", "&7and electricity components", "", "&eLeft&7/&eRight Click &7a compatible block to break it", "", LoreBuilder.powerCharged(0, FluffyWrench.Wrench.CARBONADO.getMaxCharge()));
    public static final SlimefunItemStack PAXEL = new SlimefunItemStack("PAXEL", DIAMOND_PICKAXE, "&bPaxel", "", "&7A pickaxe, axe, and shovel in one tool!");
    public static final SlimefunItemStack ADVANCED_CHARGING_BENCH = new SlimefunItemStack("ADVANCED_CHARGING_BENCH", SMITHING_TABLE, "&cAdvanced Charging Bench", "", "&7Charges items", "&7Can be upgraded using an &6ACB Upgrade Card");
    public static final SlimefunItemStack ACB_UPGRADE_CARD = new SlimefunItemStack("ACB_UPGRADE_CARD", PAPER, "&6ACB Upgrade Card", "", "&eRight Click &7onto an &cAdvanced Charging Bench", "", "&6Charge Speed &a+" + AdvancedChargingBench.CHARGE + "J", "&6Capacity &a+" + AdvancedChargingBench.CAPACITY + "J", "&6Energy Consumption &c+" + AdvancedChargingBench.ENERGY_CONSUMPTION + "J");
    static final Enchantment glowEnchant = Enchantment.getByKey(Constants.GLOW_ENCHANT);

    static {
        FireproofRune.setFireproof(FIREPROOF_RUNE);
        SMALL_PORTABLE_CHARGER.addEnchantment(glowEnchant, 1);
        MEDIUM_PORTABLE_CHARGER.addEnchantment(glowEnchant, 1);
        BIG_PORTABLE_CHARGER.addEnchantment(glowEnchant, 1);
        LARGE_PORTABLE_CHARGER.addEnchantment(glowEnchant, 1);
        CARBONADO_PORTABLE_CHARGER.addEnchantment(glowEnchant, 1);
    }
}