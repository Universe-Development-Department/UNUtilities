package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ChestTerminal.Items;

import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.MenuClickHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Material.*;

public class ImportBus extends io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem {
    static final int[] border = {0, 1, 3, 4, 5, 6, 9, 10, 11, 12, 13, 14, 15, 18, 22, 24, 27, 31, 33, 34, 35, 36, 40, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};

    public ImportBus(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, org.bukkit.inventory.ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        new BlockMenuPreset(getId(), "&3CT Import Bus") {
            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            public void newInstance(me.mrCookieSlime.Slimefun.api.inventory.BlockMenu menu, Block b) {
                if (!BlockStorage.hasBlockInfo(b) || BlockStorage.getLocationInfo(b.getLocation(), "filter-type") == null || BlockStorage.getLocationInfo(b.getLocation(), "filter-type").equals("whitelist")) {
                    menu.replaceExistingItem(23, new CustomItemStack(WHITE_WOOL, "&7Type: &fWhitelist", "", "&e> Click to change it to Blacklist"));
                    menu.addMenuClickHandler(23, (p, slot, item, action) -> {
                        BlockStorage.addBlockInfo(b, "filter-type", "blacklist");
                        newInstance(menu, b);
                        return false;
                    });
                } else {
                    menu.replaceExistingItem(23, new CustomItemStack(BLACK_WOOL, "&7Type: &8Blacklist", "", "&e> Click to change it to Whitelist"));
                    menu.addMenuClickHandler(23, (p, slot, item, action) -> {
                        BlockStorage.addBlockInfo(b, "filter-type", "whitelist");
                        newInstance(menu, b);
                        return false;
                    });
                }

                if (!BlockStorage.hasBlockInfo(b) || BlockStorage.getLocationInfo(b.getLocation(), "filter-durability") == null || BlockStorage.getLocationInfo(b.getLocation(), "filter-durability").equals("false")) {
                    menu.replaceExistingItem(41, new CustomItemStack(STONE_SWORD, "&7Include Sub-IDs/Durability: &4\u2718", "", "&e> Click to toggle whether the Durability has to match"));
                    menu.addMenuClickHandler(41, (p, slot, item, action) -> {
                        BlockStorage.addBlockInfo(b, "filter-durability", "true");
                        newInstance(menu, b);
                        return false;
                    });
                } else {
                    menu.replaceExistingItem(41, new CustomItemStack(GOLDEN_SWORD, "&7Include Sub-IDs/Durability: &2\u2714", "", "&e> Click to toggle whether the Durability has to match"));
                    menu.addMenuClickHandler(41, (p, slot, item, action) -> {
                        BlockStorage.addBlockInfo(b, "filter-durability", "false");
                        newInstance(menu, b);
                        return false;
                    });
                }

                if (!BlockStorage.hasBlockInfo(b) || BlockStorage.getLocationInfo(b.getLocation(), "filter-lore") == null || BlockStorage.getLocationInfo(b.getLocation(), "filter-lore").equals("true")) {
                    menu.replaceExistingItem(32, new CustomItemStack(MAP, "&7Include Lore: &2\u2714", "", "&e> Click to toggle whether the Lore has to match"));
                    menu.addMenuClickHandler(32, (p, slot, item, action) -> {
                        BlockStorage.addBlockInfo(b, "filter-lore", "false");
                        newInstance(menu, b);
                        return false;
                    });
                } else {
                    menu.replaceExistingItem(32, new CustomItemStack(MAP, "&7Include Lore: &4\u2718", "", "&e> Click to toggle whether the Lore has to match"));
                    menu.addMenuClickHandler(32, (p, slot, item, action) -> {
                        BlockStorage.addBlockInfo(b, "filter-lore", "true");
                        newInstance(menu, b);
                        return false;
                    });
                }
            }

            @Override
            public boolean canOpen(Block b, org.bukkit.entity.Player p) {
                String owner = BlockStorage.getLocationInfo(b.getLocation(), "owner");
                return (owner != null && owner.equals(p.getUniqueId().toString())) || p.hasPermission("slimefun.cargo.bypass");
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow flow) {
                return new int[0];
            }
        };
        addItemHandler(new CTBlockBreakHandler(getInputSlots()));
        addItemHandler(new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(@NotNull org.bukkit.event.block.BlockPlaceEvent e) {
                Block b = e.getBlock();
                BlockStorage.addBlockInfo(b, "owner", e.getPlayer().getUniqueId().toString());
                BlockStorage.addBlockInfo(b, "index", "0");
                BlockStorage.addBlockInfo(b, "filter-type", "whitelist");
                BlockStorage.addBlockInfo(b, "filter-lore", "true");
                BlockStorage.addBlockInfo(b, "filter-durability", "false");
            }
        });
    }

    protected void constructMenu(BlockMenuPreset preset) {
        MenuClickHandler click = (p, slot, item, action) -> false;
        for (int i : border) preset.addItem(i, new CustomItemStack(CYAN_STAINED_GLASS_PANE, " "), click);
        preset.addItem(7, new CustomItemStack(ORANGE_STAINED_GLASS_PANE, " "), click);
        preset.addItem(8, new CustomItemStack(ORANGE_STAINED_GLASS_PANE, " "), click);
        preset.addItem(16, new CustomItemStack(ORANGE_STAINED_GLASS_PANE, " "), click);
        preset.addItem(25, new CustomItemStack(ORANGE_STAINED_GLASS_PANE, " "), click);
        preset.addItem(26, new CustomItemStack(ORANGE_STAINED_GLASS_PANE, " "), click);
        preset.addItem(2, new CustomItemStack(PAPER, "&3Items", "", "&bPut in all Items you want to", "&bblacklist/whitelist"), click);
    }

    public int[] getInputSlots() {
        return new int[]{19, 20, 21, 28, 29, 30, 37, 38, 39};
    }
}