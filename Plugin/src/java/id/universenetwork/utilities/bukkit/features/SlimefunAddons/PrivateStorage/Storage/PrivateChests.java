package id.universenetwork.utilities.bukkit.features.SlimefunAddons.PrivateStorage.Storage;

import id.universenetwork.utilities.bukkit.features.SlimefunAddons.PrivateStorage.ChestProtectionLevel;
import id.universenetwork.utilities.bukkit.features.SlimefunAddons.PrivateStorage.PrivateStorage;
import id.universenetwork.utilities.bukkit.features.SlimefunAddons.PrivateStorage.SlimefunChest;
import id.universenetwork.utilities.bukkit.UNUtilities;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class PrivateChests {
    public PrivateChests(PrivateStorage addon, ItemGroup itemGroup) {
        SlimefunItemStack safeOak = new SlimefunItemStack("PRIVATE_SAFE_OAK", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNThjMTk4OGUzM2RjZGZlZmZkNjE5YjkyYWI5ZjQ3Y2Y1YzNjNmQ3ZGRhZDQyNjM3ZDNlYWFhYjI3NTcifX19", "&6Oak Safe", getLore(2, false));
        SlimefunItemStack safeBirch = new SlimefunItemStack("PRIVATE_SAFE_BIRCH", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjdmN2NiMmQ0ZWMwZTBjNjFlNzlhMDZjZjA0YjBkMTYxMDVmNzdkYTk2OTEzYTY4OWE0ZGM5NTI3N2I5MzczYiJ9fX0=", "&6Birch Safe", getLore(2, false));
        SlimefunItemStack safeSpruce = new SlimefunItemStack("PRIVATE_SAFE_SPRUCE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmVmNWIwYTQ1MzA1MTE1N2JkNjRiY2Q4YzcyMGQwNmZlNzhlYmM0ODU1M2M0YzBlNWI5OTMxMjY1YjFhZTc1YyJ9fX0=", "&6Spruce Safe", getLore(2, false));
        SlimefunItemStack safeJungle = new SlimefunItemStack("PRIVATE_SAFE_JUNGLE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjEzMjIxMmE2ZTYwMzMwNzg5NmU3YTQzNTY1OTcyNjU3MDQwMTM2ZjZhODRhYjFhODgyOWVmMDA2MTIzOWNjNSJ9fX0=", "&6Jungle Safe", getLore(2, false));
        SlimefunItemStack safeAcacia = new SlimefunItemStack("PRIVATE_SAFE_ACACIA", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY2OGNkZDhiNDQ3OGM5OWVlNjM0NTA2YThjZjIyNzkyMTRkYjY2ZjUwNWRkOWFmMjU5YWVlN2ZlYTFlZGYwZSJ9fX0=", "&6Acacia Safe", getLore(2, false));
        SlimefunItemStack safeDarkOak = new SlimefunItemStack("PRIVATE_SAFE_DARK_OAK", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmMzOGM5YjJmZDExYzliMDIwODEzYTg3MTBhNjdmYmVhYzU2YjYxZTkyMzVmNWQ3ZDg5ZWQ5YjdhMTU5ZDQ0NSJ9fX0=", "&6Dark Oak Safe", getLore(2, false));
        SlimefunItemStack safeIron = new SlimefunItemStack("PRIVATE_SAFE_IRON", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZThlNTU0NGFmN2Y1NDg5Y2MyNzQ5MWNhNjhmYTkyMzg0YjhlYTVjZjIwYjVjODE5OGFkYjdiZmQxMmJjMmJjMiJ9fX0=", "&6Iron Safe", getLore(3, false));
        SlimefunItemStack safeGold = new SlimefunItemStack("PRIVATE_SAFE_GOLD", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDUyY2VlZDA2NDgzNWVhMjMyZTY1NmE3M2Y4MmVkNzYxODI3ODU5YzkxMzQ0OTMxNGI4ZmQyMWIzZDExZDYifX19", "&6Golden Safe", getLore(4, false));
        SlimefunItemStack safeDiamond = new SlimefunItemStack("PRIVATE_SAFE_DIAMOND", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2FkYmNmYjI4ODIxNWE4ZDE1M2RkZmRkYjM2YmQyZWQ3YTM3YWRkMzU2NjJmODYzM2Y3MTFkMmRmY2ViNDE3YyJ9fX0=", "&6Diamond Safe", getLore(5, false));
        SlimefunItemStack safeEmerald = new SlimefunItemStack("PRIVATE_SAFE_EMERALD", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmFkMzVlYmRiMTI1ZmJhNDIxMjk4ZDQyYzIwMmM3N2M3NWI0MmNjOTljOTQ5MzlmNjM3NjQwYWMxODFmIn19fQ==", "&6Emerald Safe", getLore(6, false));
        SlimefunItemStack safeObsidian = new SlimefunItemStack("PRIVATE_SAFE_OBSIDIAN", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODU1YmU4NzM2NTJjYmZkZjRkODhmYTgxMjc2ZDI0OGQyYjdlZWY3YTZkNGYzZWRjYzkyZmU1NzU4NWJmNGQifX19", "&6Obsidian Safe", getLore(4, true));
        SlimefunItemStack safeSteel = new SlimefunItemStack("PRIVATE_SAFE_STEEL", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjljYjNkMTlkYjUyOWEyMTVjZjYyNjk3NTkxY2MxM2ZiOGM3ODZhOGYyN2I3NTI4YzMyYWMyOTg2Yjk2NzBjNCJ9fX0=", "&6Steel Safe", getLore(5, true));
        new SlimefunChest(ChestProtectionLevel.PRIVATE, 18, true, itemGroup, safeOak, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.OAK_LOG), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.OAK_LOG), SlimefunItems.MAGIC_LUMP_3, new ItemStack(Material.OAK_LOG), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.OAK_LOG), new ItemStack(Material.GOLD_NUGGET)})
                .register(addon);
        new SlimefunChest(ChestProtectionLevel.PRIVATE, 18, true, itemGroup, safeBirch, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.BIRCH_LOG), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.BIRCH_LOG), SlimefunItems.MAGIC_LUMP_3, new ItemStack(Material.BIRCH_LOG), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.BIRCH_LOG), new ItemStack(Material.GOLD_NUGGET)})
                .register(addon);
        new SlimefunChest(ChestProtectionLevel.PRIVATE, 18, true, itemGroup, safeSpruce, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.SPRUCE_LOG), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.SPRUCE_LOG), SlimefunItems.MAGIC_LUMP_3, new ItemStack(Material.SPRUCE_LOG), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.SPRUCE_LOG), new ItemStack(Material.GOLD_NUGGET)})
                .register(addon);
        new SlimefunChest(ChestProtectionLevel.PRIVATE, 18, true, itemGroup, safeJungle, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.JUNGLE_LOG), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.JUNGLE_LOG), SlimefunItems.MAGIC_LUMP_3, new ItemStack(Material.JUNGLE_LOG), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.JUNGLE_LOG), new ItemStack(Material.GOLD_NUGGET)})
                .register(addon);
        new SlimefunChest(ChestProtectionLevel.PRIVATE, 18, true, itemGroup, safeAcacia, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.ACACIA_LOG), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.ACACIA_LOG), SlimefunItems.MAGIC_LUMP_3, new ItemStack(Material.ACACIA_LOG), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.ACACIA_LOG), new ItemStack(Material.GOLD_NUGGET)})
                .register(addon);
        new SlimefunChest(ChestProtectionLevel.PRIVATE, 18, true, itemGroup, safeDarkOak, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.DARK_OAK_LOG), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.DARK_OAK_LOG), SlimefunItems.MAGIC_LUMP_3, new ItemStack(Material.DARK_OAK_LOG), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.DARK_OAK_LOG), new ItemStack(Material.GOLD_NUGGET)})
                .register(addon);
        new SlimefunChest(ChestProtectionLevel.PRIVATE, 27, true, itemGroup, safeIron, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.CHEST), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.GOLD_NUGGET)})
                .register(addon);
        new SlimefunChest(ChestProtectionLevel.PRIVATE, 36, true, itemGroup, safeGold, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{new ItemStack(Material.GOLD_NUGGET), SlimefunItems.GOLD_10K, new ItemStack(Material.GOLD_NUGGET), SlimefunItems.GOLD_10K, safeIron, SlimefunItems.GOLD_10K, new ItemStack(Material.GOLD_NUGGET), SlimefunItems.GOLD_10K, new ItemStack(Material.GOLD_NUGGET)})
                .register(addon);
        new SlimefunChest(ChestProtectionLevel.PRIVATE, 45, true, itemGroup, safeDiamond, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.GLASS), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.DIAMOND), safeGold, new ItemStack(Material.DIAMOND), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.GLASS), new ItemStack(Material.GOLD_NUGGET)})
                .register(addon);
        new SlimefunChest(ChestProtectionLevel.PRIVATE, 54, true, itemGroup, safeEmerald, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.GLASS), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.EMERALD), safeDiamond, new ItemStack(Material.EMERALD), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.GLASS), new ItemStack(Material.GOLD_NUGGET)})
                .register(addon);
        new SlimefunChest(ChestProtectionLevel.PRIVATE, 36, false, itemGroup, safeObsidian, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.OBSIDIAN), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.OBSIDIAN), safeIron, new ItemStack(Material.OBSIDIAN), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.OBSIDIAN), new ItemStack(Material.GOLD_NUGGET)})
                .register(addon);
        new SlimefunChest(ChestProtectionLevel.PRIVATE, 45, false, itemGroup, safeSteel, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{new ItemStack(Material.GOLD_NUGGET), SlimefunItems.STEEL_INGOT, new ItemStack(Material.GOLD_NUGGET), SlimefunItems.STEEL_INGOT, safeObsidian, SlimefunItems.STEEL_INGOT, new ItemStack(Material.GOLD_NUGGET), SlimefunItems.STEEL_INGOT, new ItemStack(Material.GOLD_NUGGET)})
                .register(addon);
        new Research(UNUtilities.createKey("wooden_safes"), 609, "Magical Storage", 8).addItems(safeOak, safeBirch, safeSpruce, safeJungle, safeAcacia, safeDarkOak).register();
        new Research(UNUtilities.createKey("metal_safes"), 610, "Upgraded Storage", 16).addItems(safeIron, safeGold, safeDiamond).register();
        new Research(UNUtilities.createKey("gem_safes"), 611, "Top Tier Storage", 20).addItems(safeEmerald).register();
        new Research(UNUtilities.createKey("hardened_safes"), 612, "Hardened Storage", 24).addItems(safeObsidian, safeSteel).register();
    }

    private String[] getLore(int size, boolean explosions) {
        if (explosions) return new String[]{
                "&7Size: &e" + size + "x9",
                "&bWithstands Explosions",
                "",
                "&rThis Chest can only be opened by you"
        };
        else return new String[]{
                "&7Size: &e" + size + "x9",
                "",
                "&rThis Chest can only be opened by you"
        };
    }
}