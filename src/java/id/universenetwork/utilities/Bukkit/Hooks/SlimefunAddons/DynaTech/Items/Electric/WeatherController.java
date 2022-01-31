package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Material.*;

public class WeatherController extends id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Abstracts.AMachine implements io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem {
    static final int[] BORDER = new int[]{1, 2, 6, 7, 9, 10, 11, 15, 16, 17, 19, 20, 24, 25};
    static final int[] BORDER_IN = new int[]{3, 4, 5, 12, 14, 21, 22, 23};
    static final int[] BORDER_OUT = new int[]{0, 8, 18, 26};

    public WeatherController(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemHandler(onBlockBreak());
    }

    @Override
    public void tick(org.bukkit.block.Block b) {
        if (getCharge(b.getLocation()) < getEnergyConsumption()) return;
        me.mrCookieSlime.Slimefun.api.inventory.BlockMenu menu = me.mrCookieSlime.Slimefun.api.BlockStorage.getInventory(b);
        ItemStack item = menu.getItemInSlot(getInputSlots()[0]);
        if (item != null && (item.getType() == SUNFLOWER || item.getType() == LILAC || item.getType() == CREEPER_HEAD)) {
            if (item.getType() == SUNFLOWER) {
                if (b.getWorld().isClearWeather()) return;
                b.getWorld().setClearWeatherDuration(1200);
                removeCharge(b.getLocation(), getEnergyConsumption());
            }
            if (item.getType() == LILAC) {
                if (b.getWorld().hasStorm()) return;
                b.getWorld().setStorm(true);
                b.getWorld().setWeatherDuration(1200);
                removeCharge(b.getLocation(), getEnergyConsumption());
            }
            if (item.getType() == CREEPER_HEAD) {
                if (b.getWorld().isThundering()) return;
                id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.DynaTech.runSync(() -> {
                    b.getWorld().setThundering(true);
                    b.getWorld().setThunderDuration(1200);
                    removeCharge(b.getLocation(), getEnergyConsumption());
                });
            }
        }
    }

    public io.github.thebusybiscuit.slimefun4.api.items.ItemHandler onBlockBreak() {
        return new io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(org.bukkit.event.block.BlockBreakEvent event, ItemStack item, List<ItemStack> drops) {
                event.getBlock().getWorld().setClearWeatherDuration(60);
            }
        };
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> items = new ArrayList<>();
        items.add(new ItemStack(SUNFLOWER));
        items.add(new CustomItemStack(DIAMOND, "&fMakes its sunny in philadelphia."));
        items.add(new ItemStack(LILAC));
        items.add(new CustomItemStack(DIAMOND, "&fMakes its rain while the old man snores"));
        items.add(new ItemStack(CREEPER_HEAD));
        items.add(new CustomItemStack(DIAMOND, "&fMakes it thunder."));
        return items;
    }

    @Override
    public String getMachineIdentifier() {
        return "WEATHER_CONTROLLER";
    }

    @Override
    public List<int[]> getBorders() {
        List<int[]> borders = new ArrayList<>();
        borders.add(BORDER);
        borders.add(BORDER_IN);
        borders.add(BORDER_OUT);
        return borders;
    }

    @Override
    public int[] getInputSlots() {
        return new int[]{13};
    }

    @Override
    public int[] getOutputSlots() {
        return new int[]{13};
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(SUNFLOWER);
    }

    @Override
    public int getProgressBarSlot() {
        return 4;
    }
}