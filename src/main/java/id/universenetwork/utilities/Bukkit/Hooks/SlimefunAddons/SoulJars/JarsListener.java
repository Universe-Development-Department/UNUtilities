package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SoulJars;

import id.universenetwork.utilities.Bukkit.Manager.Color;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListener;

class JarsListener implements Listener {
    final SlimefunItem emptyJar;
    final SoulJars Main;

    public JarsListener(SoulJars Main) {
        this.Main = Main;
        registerListener(this);
        this.emptyJar = SlimefunItem.getById("SOUL_JAR");
    }

    @EventHandler
    public void onKill(EntityDeathEvent e) {
        Map<EntityType, Integer> mobs = Main.getRequiredSouls();
        if (!mobs.containsKey(e.getEntityType()) || e.getEntity().getKiller() == null) return;
        Player killer = e.getEntity().getKiller();
        SlimefunItem jar = SlimefunItem.getById(e.getEntityType().name() + "_SOUL_JAR");
        SlimefunItem filledJar = SlimefunItem.getById("FILLED_" + e.getEntityType().name() + "_SOUL_JAR");

        // Filling all existing Jars
        for (int slot = 0; slot < killer.getInventory().getSize(); slot++) {
            ItemStack stack = killer.getInventory().getItem(slot);
            if (jar.isItem(stack)) {
                List<String> lore = stack.getItemMeta().getLore();
                int souls = Integer.parseInt(ChatColor.stripColor(lore.get(1)).split(": ")[1]) + 1;
                int requiredSouls = mobs.get(e.getEntityType());
                if (souls >= requiredSouls) {
                    if (stack.getAmount() > 1) {
                        ItemUtils.consumeItem(stack, false);
                        killer.getInventory().addItem(filledJar.getItem().clone());
                    } else killer.getInventory().setItem(slot, filledJar.getItem().clone());
                } else {
                    lore.set(1, Color.Translator(lore.get(1).split(": ")[0] + ": &e" + souls));
                    if (stack.getAmount() > 1) {
                        stack.setAmount(stack.getAmount() - 1);
                        stack = stack.clone();
                        stack.setAmount(1);
                        ItemMeta im = stack.getItemMeta();
                        im.setLore(lore);
                        stack.setItemMeta(im);
                        killer.getInventory().addItem(stack);
                    } else {
                        ItemMeta im = stack.getItemMeta();
                        im.setLore(lore);
                        stack.setItemMeta(im);
                    }
                }
                return;
            }
        }

        // Creating new Jars
        for (int slot = 0; slot < killer.getInventory().getSize(); slot++) {
            ItemStack stack = killer.getInventory().getItem(slot);
            if (emptyJar.isItem(stack)) {
                ItemUtils.consumeItem(stack, false);
                killer.getWorld().dropItemNaturally(e.getEntity().getLocation(), jar.getItem().clone());
                return;
            }
        }
    }
}