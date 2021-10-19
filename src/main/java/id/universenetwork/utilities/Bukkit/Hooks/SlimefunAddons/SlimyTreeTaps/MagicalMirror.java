package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SlimyTreeTaps;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

class MagicalMirror extends SimpleSlimefunItem<ItemUseHandler> implements NotPlaceable {
    final NamespacedKey mirrorLocation;

    public MagicalMirror(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        mirrorLocation = new NamespacedKey(plugin, "mirror_location");
    }

    @NotNull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();
            e.getPlayer().sendMessage(ChatColor.GREEN + "Give your Location a name! Type it in chat.");
            ChatUtils.awaitInput(e.getPlayer(), name -> setLocation(e.getPlayer(), e.getItem(), name, e.getPlayer().getLocation()));
        };
    }

    public void teleport(Player p, ItemStack item) {
        if (!p.getInventory().containsAtLeast(new ItemStack(Material.ENDER_PEARL), 1)) {
            p.sendMessage(ChatColor.RED + "You need at least one Ender Pearl to use the magical Mirror!");
            return;
        }
        Optional<Location> location = getLocation(item);
        if (location.isPresent()) {
            if (p.getInventory().removeItem(new ItemStack(Material.ENDER_PEARL)).isEmpty()) {
                PaperLib.teleportAsync(p, location.get()).thenAccept(hasTeleported -> {
                    if (hasTeleported) {
                        p.sendTitle(item.getItemMeta().getDisplayName(), ChatColor.GRAY + "- Magical Mirror -", 20, 60, 20);
                    } else {
                        p.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
                        p.sendMessage(ChatColor.RED + "Teleportation was cancelled!");
                    }
                });
            } else p.sendMessage(ChatColor.RED + "You need at least one Ender Pearl to teleport!");
        } else p.sendMessage(ChatColor.RED + "This Magical Mirror does not seem to have a valid destination!");
    }

    void setLocation(Player p, ItemStack item, String name, Location l) {
        ItemMeta meta = item.getItemMeta();
        JsonObject json = new JsonObject();
        json.addProperty("world", l.getWorld().getUID().toString());
        json.addProperty("x", l.getX());
        json.addProperty("y", l.getY());
        json.addProperty("z", l.getZ());
        json.addProperty("pitch", l.getPitch());
        json.addProperty("yaw", l.getYaw());
        meta.getPersistentDataContainer().set(mirrorLocation, PersistentDataType.STRING, json.toString());
        meta.setDisplayName(ChatColor.AQUA + ChatUtils.removeColorCodes(name));
        item.setItemMeta(meta);
        p.sendMessage(ChatColor.GREEN + "Successfully set your mirror location!");
    }

    Optional<Location> getLocation(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        String data = meta.getPersistentDataContainer().get(mirrorLocation, PersistentDataType.STRING);
        if (data != null) {
            JsonObject json = new JsonParser().parse(data).getAsJsonObject();
            UUID uuid = UUID.fromString(json.get("world").getAsString());
            World world = Bukkit.getWorld(uuid);
            if (world != null) {
                double x = json.get("x").getAsDouble();
                double y = json.get("y").getAsDouble();
                double z = json.get("z").getAsDouble();
                float pitch = json.get("pitch").getAsFloat();
                float yaw = json.get("yaw").getAsFloat();
                Location loc = new Location(world, x, y, z, yaw, pitch);
                return Optional.of(loc);
            } else return Optional.empty();
        } else return Optional.empty();
    }
}