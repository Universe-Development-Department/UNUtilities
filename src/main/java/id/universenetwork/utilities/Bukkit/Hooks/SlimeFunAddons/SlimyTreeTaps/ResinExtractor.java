package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.SlimyTreeTaps;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

abstract class ResinExtractor extends AContainer implements RecipeDisplayItem {
    public ResinExtractor(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Nonnull
    @Override
    public String getInventoryTitle() {
        return "Resin Extractor";
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.DIAMOND_HOE);
    }

    @Nonnull
    @Override
    public String getMachineIdentifier() {
        return "RESIN_EXTRACTOR";
    }
}