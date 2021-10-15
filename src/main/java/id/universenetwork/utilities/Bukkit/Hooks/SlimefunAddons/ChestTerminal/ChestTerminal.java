package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ChestTerminal;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ChestTerminal.Items.*;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.Enabled;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public class ChestTerminal {
    public ChestTerminal() {
        if (Enabled("ChestTerminal")) {
            SlimefunItemStack milkyQuartz = new SlimefunItemStack("MILKY_QUARTZ", Material.QUARTZ, "&fMilky Quartz");
            SlimefunItemStack ctPanel = new SlimefunItemStack("CT_PANEL", "7a44ff3a5f49c69cab676bad8d98a063fa78cfa61916fdef3e267557fec18283", "&3CT Illuminated Panel", "&7Crafting Component");
            SlimefunItemStack chestTerminal = new SlimefunItemStack("CHEST_TERMINAL", "7a44ff3a5f49c69cab676bad8d98a063fa78cfa61916fdef3e267557fec18283", "&3CT Access Terminal", "&7If this Block is connected to a", "&7Cargo Network, it will allow you to remotely", "&7interact with any Items supplied by", "&7Nodes tuned into the ChestTerminal Channel");
            SlimefunItemStack importBus = new SlimefunItemStack("CT_IMPORT_BUS", "113db2e7e72ea4432eefbd6e58a85eaa2423f83e642ca41abc6a9317757b889", "&3CT Import Bus", "&7If this Block is connected to a", "&7Cargo Network, it will pull any Items from", "&7the Inventory it is attached to and place it", "&7into the CT Network Channel");
            SlimefunItemStack exportBus = new SlimefunItemStack("CT_EXPORT_BUS", "113db2e7e72ea4432eefbd6e58a85eaa2423f83e642ca41abc6a9317757b889", "&3CT Export Bus", "&7If this Block is connected to a", "&7Cargo Network, it will pull any Items from", "&7the CT Network Channel and place these", "&7into the Inventory it is attached to");
            SlimefunItemStack wirelessTerminal16 = new SlimefunItemStack("CT_WIRELESS_ACCESS_TERMINAL_16", Material.ITEM_FRAME, "&3CT Wireless Access Terminal &b(16)", "&8\u21E8 &7Linked to: &cNowhere", "&8\u21E8 &7Range: &e16 Blocks", "&c&o&8\u21E8 &e\u26A1 &70 / 10 J", "", "&7If this Block is linked to an Access Terminal", "&7it will be able to remotely access that Terminal", "", "&7&eRight Click on an Access Terminal &7to link", "&7&eRight Click&7 to open the linked Terminal");
            SlimefunItemStack wirelessTerminal64 = new SlimefunItemStack("CT_WIRELESS_ACCESS_TERMINAL_64", Material.ITEM_FRAME, "&3CT Wireless Access Terminal &b(64)", "&8\u21E8 &7Linked to: &cNowhere", "&8\u21E8 &7Range: &e64 Blocks", "&c&o&8\u21E8 &e\u26A1 &70 / 25 J", "", "&7If this Block is linked to an Access Terminal", "&7it will be able to remotely access that Terminal", "", "&7&eRight Click on an Access Terminal &7to link", "&7&eRight Click&7 to open the linked Terminal");
            SlimefunItemStack wirelessTerminal128 = new SlimefunItemStack("CT_WIRELESS_ACCESS_TERMINAL_128", Material.ITEM_FRAME, "&3CT Wireless Access Terminal &b(128)", "&8\u21E8 &7Linked to: &cNowhere", "&8\u21E8 &7Range: &e128 Blocks", "&c&o&8\u21E8 &e\u26A1 &70 / 50 J", "", "&7If this Block is linked to an Access Terminal", "&7it will be able to remotely access that Terminal", "", "&7&eRight Click on an Access Terminal &7to link", "&7&eRight Click&7 to open the linked Terminal");
            SlimefunItemStack wirelessTerminalTransdimensional = new SlimefunItemStack("CT_WIRELESS_ACCESS_TERMINAL_TRANSDIMENSIONAL", Material.ITEM_FRAME, "&3CT Wireless Access Terminal &b(Transdimensional)", "&8\u21E8 &7Linked to: &cNowhere", "&8\u21E8 &7Range: &eUnlimited", "&c&o&8\u21E8 &e\u26A1 &70 / 50 J", "", "&7If this Block is linked to an Access Terminal", "&7it will be able to remotely access that Terminal", "", "&7&eRight Click on an Access Terminal &7to link", "&7&eRight Click&7 to open the linked Terminal");
            ItemGroup itemGroup = new ItemGroup(new NamespacedKey(plugin, "chest_terminal"), new CustomItemStack(chestTerminal, "&5Chest Terminal", "", "&a> Click to open"));
            new SlimefunItem(itemGroup, milkyQuartz, RecipeType.GEO_MINER, new ItemStack[0]).register(addon);
            new SlimefunItem(itemGroup, ctPanel, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{milkyQuartz, SlimefunItems.BLISTERING_INGOT_3, milkyQuartz, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.POWER_CRYSTAL, SlimefunItems.REDSTONE_ALLOY, milkyQuartz, SlimefunItems.BLISTERING_INGOT_3, milkyQuartz}).register(addon);
            new AccessTerminal(itemGroup, chestTerminal, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{milkyQuartz, SlimefunItems.GPS_TRANSMITTER_3, milkyQuartz, SlimefunItems.POWER_CRYSTAL, ctPanel, SlimefunItems.POWER_CRYSTAL, SlimefunItems.PLASTIC_SHEET, SlimefunItems.ENERGY_REGULATOR, SlimefunItems.PLASTIC_SHEET}).register(addon);
            new ImportBus(itemGroup, importBus, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{SlimefunItems.REDSTONE_ALLOY, SlimefunItems.POWER_CRYSTAL, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.CARGO_INPUT_NODE, SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.PLASTIC_SHEET, SlimefunItems.CARGO_MOTOR, SlimefunItems.PLASTIC_SHEET}).register(addon);
            new ExportBus(itemGroup, exportBus, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{null, SlimefunItems.DAMASCUS_STEEL_INGOT, null, SlimefunItems.ALUMINUM_BRONZE_INGOT, importBus, SlimefunItems.ALUMINUM_BRONZE_INGOT, SlimefunItems.PLASTIC_SHEET, SlimefunItems.GOLD_10K, SlimefunItems.PLASTIC_SHEET}).register(addon);
            new WirelessTerminal(itemGroup, wirelessTerminal16, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{milkyQuartz, SlimefunItems.GPS_TRANSMITTER, milkyQuartz, SlimefunItems.COBALT_INGOT, chestTerminal, SlimefunItems.COBALT_INGOT, SlimefunItems.BATTERY, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.BATTERY}) {
                @Override
                public int getRange() {
                    return 16;
                }

                @Override
                public float getMaxItemCharge(ItemStack item) {
                    return 10;
                }
            }.register(addon);
            new WirelessTerminal(itemGroup, wirelessTerminal64, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{milkyQuartz, SlimefunItems.GPS_TRANSMITTER, milkyQuartz, SlimefunItems.COBALT_INGOT, wirelessTerminal16, SlimefunItems.COBALT_INGOT, SlimefunItems.BATTERY, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.BATTERY}) {
                @Override
                public int getRange() {
                    return 64;
                }

                @Override
                public float getMaxItemCharge(ItemStack item) {
                    return 25;
                }
            }.register(addon);
            new WirelessTerminal(itemGroup, wirelessTerminal128, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{milkyQuartz, SlimefunItems.GPS_TRANSMITTER_2, milkyQuartz, SlimefunItems.COBALT_INGOT, wirelessTerminal64, SlimefunItems.COBALT_INGOT, SlimefunItems.BATTERY, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.BATTERY}) {
                @Override
                public int getRange() {
                    return 128;
                }

                @Override
                public float getMaxItemCharge(ItemStack item) {
                    return 50;
                }
            }.register(addon);
            new WirelessTerminal(itemGroup, wirelessTerminalTransdimensional, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{milkyQuartz, SlimefunItems.GPS_TRANSMITTER_4, milkyQuartz, SlimefunItems.COBALT_INGOT, wirelessTerminal128, SlimefunItems.COBALT_INGOT, SlimefunItems.BATTERY, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.BATTERY}) {
                @Override
                public int getRange() {
                    return -1;
                }

                @Override
                public float getMaxItemCharge(ItemStack item) {
                    return 50;
                }
            }.register(addon);
            new MilkyQuartz(milkyQuartz).register();
            System.out.println(prefix + " §bSuccessfully Registered §dChestTerminal §bAddon");
        }
    }
}