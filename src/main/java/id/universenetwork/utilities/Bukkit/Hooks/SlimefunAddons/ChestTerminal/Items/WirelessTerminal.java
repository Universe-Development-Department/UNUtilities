package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ChestTerminal.Items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public abstract class WirelessTerminal extends SimpleSlimefunItem<ItemUseHandler> implements Rechargeable {
    public WirelessTerminal(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public abstract int getRange();

    @NotNull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.getInteractEvent().setCancelled(true);
            ItemStack stack = e.getItem();
            ItemMeta im = stack.getItemMeta();
            List<String> lore = im.getLore();
            if (Objects.requireNonNull(lore).isEmpty()) return;
            if (e.getClickedBlock().isPresent() && e.getSlimefunBlock().isPresent()) {
                Player p = e.getPlayer();
                Block b = e.getClickedBlock().get();
                if (e.getSlimefunBlock().get() instanceof AccessTerminal) {
                    lore.set(0, ChatColors.color("&8\u21E8 &7Linked to: &8") + b.getWorld().getName() + " X: " + b.getX() + " Y: " + b.getY() + " Z: " + b.getZ());
                    p.sendMessage(ChatColors.color("&bLink established!"));
                    im.setLore(lore);
                    stack.setItemMeta(im);
                    p.getInventory().setItemInMainHand(stack);
                } else openRemoteTerminal(p, stack, lore.get(0), getRange());
                e.cancel();
            } else openRemoteTerminal(e.getPlayer(), stack, lore.get(0), getRange());
        };
    }

    void openRemoteTerminal(Player p, ItemStack stack, String loc, int range) {
        if (loc.equals(ChatColors.color("&8\u21E8 &7Linked to: &cNowhere"))) {
            p.sendMessage(ChatColors.color("&4Failed &c- This Device has not been linked to a Chest Terminal!"));
            return;
        }
        loc = loc.replace(ChatColors.color("&8\u21E8 &7Linked to: &8"), "");
        World world = Bukkit.getWorld(loc.split(" X: ")[0]);
        if (world == null) {
            p.sendMessage(ChatColors.color("&4Failed &c- The Chest Terminal that this Device has been linked to no longer exists!"));
            return;
        }
        int x = Integer.parseInt(loc.split(" X: ")[1].split(" Y: ")[0]);
        int y = Integer.parseInt(loc.split(" Y: ")[1].split(" Z: ")[0]);
        int z = Integer.parseInt(loc.split(" Z: ")[1]);
        Block block = world.getBlockAt(x, y, z);
        if (!Slimefun.getProtectionManager().hasPermission(p, block.getLocation(), Interaction.INTERACT_BLOCK)) {
            p.sendMessage(ChatColors.color("&4You are not permitted to access this terminal in that area!"));
            return;
        }
        if (!BlockStorage.check(block, "CHEST_TERMINAL")) {
            p.sendMessage(ChatColors.color("&4Failed &c- The Chest Terminal that this Device has been linked to no longer exists!"));
            return;
        }
        float charge = getItemCharge(stack);
        if (charge < 0.5F) {
            p.sendMessage(ChatColors.color("&4Failed &c- You are out of Energy!"));
            return;
        }
        if (range > 0 && !world.getUID().equals(p.getWorld().getUID())) {
            p.sendMessage(ChatColors.color("&4Failed &c- You are out of Range!"));
            return;
        }
        if (range > 0 && block.getLocation().distance(p.getLocation()) > range) {
            p.sendMessage(ChatColors.color("&4Failed &c- You are out of Range!"));
            return;
        }
        removeItemCharge(stack, 0.5F);
        BlockStorage.getInventory(block).open(p);
    }
}