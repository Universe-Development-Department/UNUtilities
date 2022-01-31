package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Transfer;

import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static org.bukkit.ChatColor.WHITE;

public class WirelessItemInput extends SlimefunItem implements io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent {
    final int capacity;

    public WirelessItemInput(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, int capacity, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.capacity = capacity;
        addItemHandler(onBlockBreak());
        new BlockMenuPreset("WIRELESS_ITEM_INPUT", "Wireless Item Input") {
            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            public boolean canOpen(Block b, Player p) {
                return p.hasPermission("slimefun.inventory.bypass") || Slimefun.getProtectionManager().hasPermission(p, b.getLocation(), Interaction.INTERACT_BLOCK);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                return new int[0];
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(DirtyChestMenu menu, ItemTransportFlow flow, ItemStack item) {
                if (flow == ItemTransportFlow.INSERT) return getInputSlots();
                else return getOutputSlots();
            }
        };
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {
            @Override
            public boolean isSynchronized() {
                return false;
            }

            @Override
            public void tick(Block block, SlimefunItem sfItem, Config data) {
                WirelessItemInput.this.tick(block);
            }
        });
    }

    ItemHandler onBlockBreak() {
        return new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(org.bukkit.event.block.BlockBreakEvent event, ItemStack block, List<ItemStack> drops) {
                BlockMenu inv = BlockStorage.getInventory(event.getBlock());
                if (inv != null) {
                    inv.dropItems(event.getBlock().getLocation(), getInputSlots());
                    inv.dropItems(event.getBlock().getLocation(), getOutputSlots());
                }
                BlockStorage.clearBlockInfo(event.getBlock().getLocation());
            }
        };
    }

    protected void tick(Block b) {
        BlockMenu menu = BlockStorage.getInventory(b);
        updateKnowledgePane(menu, getCharge(b.getLocation()));
    }

    void updateKnowledgePane(BlockMenu menu, int currentCharge) {
        ItemStack knowledgePane = menu.getItemInSlot(4);
        org.bukkit.inventory.meta.ItemMeta im = knowledgePane.getItemMeta();
        List<String> lore = im.hasLore() ? im.getLore() : new java.util.ArrayList<>();
        lore.clear();
        lore.add(" ");
        lore.add(WHITE + "Current Power: " + currentCharge);
        lore.add(WHITE + "Current Status: Interesting.");
        im.setLore(lore);
        knowledgePane.setItemMeta(im);
    }

    // Boilerplate for machines.
    public void constructMenu(BlockMenuPreset preset) {
        preset.drawBackground(ChestMenuUtils.getInputSlotTexture(), getBorder());
        preset.addItem(4, new CustomItemStack(Material.PURPLE_STAINED_GLASS_PANE, "&fKnowledge Pane"), ChestMenuUtils.getEmptyClickHandler());
    }

    public int[] getBorder() {
        return new int[]{0, 1, 2, 3, 5, 6, 7, 8, 45, 46, 47, 48, 49, 50, 51, 52, 53};
    }

    public int[] getInputSlots() {
        return new int[]{9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44};
    }

    public int[] getOutputSlots() {
        return new int[0];
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }
}