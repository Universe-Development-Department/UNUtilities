package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.FluffyMachines.Items;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.FluffyMachines.Utils.Utils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemDropHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static org.bukkit.ChatColor.RED;

public class FireproofRune extends SimpleSlimefunItem<ItemDropHandler> {
    static final double RANGE = 1.5;
    static final NamespacedKey FIREPROOF_KEY = new NamespacedKey(plugin, "fireproof");
    static final String FIREPROOF_LORE = RED + "Fireproof";

    public FireproofRune(ItemGroup itemGroup, SlimefunItemStack item, RecipeType type, ItemStack[] recipe) {
        super(itemGroup, item, type, recipe);
    }

    @Nonnull
    @Override
    public ItemDropHandler getItemHandler() {
        return (e, p, item) -> {
            if (isItem(item.getItemStack())) {
                if (!this.canUse(p, true)) return true;
                Utils.runSync(() -> activate(p, item), 20L);
                return true;
            }
            return false;
        };
    }

    void activate(Player p, Item rune) {
        if (!rune.isValid()) return;
        Location l = rune.getLocation();
        Collection<Entity> entities = l.getWorld().getNearbyEntities(l, RANGE, RANGE, RANGE, this::findCompatibleItem);
        Optional<Entity> optional = entities.stream().findFirst();
        if (optional.isPresent()) {
            Item item = (Item) optional.get();
            ItemStack itemStack = item.getItemStack();
            if (itemStack.getAmount() == 1) {
                // This lightning is just an effect, it deals no damage.
                l.getWorld().strikeLightningEffect(l);
                Utils.runSync(() -> {
                    // Being sure entities are still valid and not picked up or whatsoever.
                    if (rune.isValid() && item.isValid() && itemStack.getAmount() == 1) {
                        l.getWorld().createExplosion(l, 0);
                        l.getWorld().playSound(l, Sound.ENTITY_GENERIC_EXPLODE, 0.3F, 1);
                        item.remove();
                        rune.remove();
                        setFireproof(itemStack);
                        l.getWorld().dropItemNaturally(l, itemStack);
                        Utils.send(p, "&aYour item is now fireproof");
                    } else Utils.send(p, "&cYour item could not be made fireproof");
                }, 10L);
            } else Utils.send(p, "&cYour item could not be made fireproof");
        }
    }

    boolean findCompatibleItem(Entity entity) {
        if (entity instanceof Item) {
            Item item = (Item) entity;
            return !isFireproof(item.getItemStack()) && !isItem(item.getItemStack());
        }
        return false;
    }

    public static void setFireproof(@Nullable ItemStack item) {
        if (item != null && item.getType() != Material.AIR) {
            boolean isFireproof = isFireproof(item);
            ItemMeta meta = item.getItemMeta();
            PersistentDataContainer container = meta.getPersistentDataContainer();
            if (!isFireproof) {
                container.set(FIREPROOF_KEY, PersistentDataType.BYTE, (byte) 1);
                List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
                lore.add(FIREPROOF_LORE);
                meta.setLore(lore);
                item.setItemMeta(meta);
            }
        }
    }

    public static boolean isFireproof(@Nullable ItemStack item) {
        if (item != null && item.getType() != Material.AIR) {
            ItemMeta meta = item.hasItemMeta() ? item.getItemMeta() : null;
            return hasFireproofFlag(meta);
        } else return false;
    }

    static boolean hasFireproofFlag(@Nullable ItemMeta meta) {
        if (meta != null) {
            PersistentDataContainer container = meta.getPersistentDataContainer();
            return container.has(FIREPROOF_KEY, PersistentDataType.BYTE);
        }
        return false;
    }
}