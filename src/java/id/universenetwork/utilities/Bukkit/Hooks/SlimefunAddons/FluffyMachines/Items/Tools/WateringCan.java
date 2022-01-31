package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Items.Tools;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Utils.CancelPlace;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Utils.Constants;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Utils.Utils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.RayTraceResult;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class WateringCan extends SimpleSlimefunItem<ItemUseHandler> implements CancelPlace {
    public final ItemSetting<Integer> maxUses = new ItemSetting<>(this, "max-uses", 10);
    public final ItemSetting<Double> sugarCaneSuccessChance = new ItemSetting<>(this, "sugar-cane-success-chance", 0.3);
    public final ItemSetting<Double> cropSuccessChance = new ItemSetting<>(this, "crop-success-chance", 0.3);
    public final ItemSetting<Double> treeSuccessChance = new ItemSetting<>(this, "tree-success-chance", 0.3);
    public final ItemSetting<Double> exoticGardenSuccessChance = new ItemSetting<>(this, "exotic-garden-success-chance", 0.3);
    static final int USE_INDEX = 7;
    static final int MAX_SUGAR_GROW_HEIGHT = 5;
    static final NamespacedKey usageKey = new NamespacedKey(plugin, "watering_can_usage");

    public WateringCan(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemSetting(maxUses);
        addItemSetting(sugarCaneSuccessChance);
        addItemSetting(cropSuccessChance);
        addItemSetting(treeSuccessChance);
        addItemSetting(exoticGardenSuccessChance);
    }

    @NotNull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            Player p = e.getPlayer();
            if (p.getInventory().getItemInMainHand().getType() != Material.PLAYER_HEAD) {
                Utils.send(p, "&cThis item is outdated! Please use /fm replace while holding the watering can.");
                return;
            }
            if (!isItem(p.getInventory().getItemInMainHand())) return;
            e.cancel();
            RayTraceResult rayResult = p.rayTraceBlocks(5d, FluidCollisionMode.SOURCE_ONLY);
            if (rayResult != null) {
                Block b = rayResult.getHitBlock();
                Location blockLocation = b.getLocation();
                if (Slimefun.getProtectionManager().hasPermission(e.getPlayer(), blockLocation, Interaction.BREAK_BLOCK)) {
                    ItemStack item = e.getItem();
                    BlockData blockData = b.getBlockData();

                    // Fill if it hits water
                    if (b.getType() == Material.WATER) {
                        updateUses(this, p, item, 2);

                        // Sugar Cane
                    } else if (b.getType() == Material.SUGAR_CANE) {
                        int distance = 2;
                        Block above = b.getRelative(BlockFace.UP);
                        while (above.getType() == Material.SUGAR_CANE) {

                            // Failsafe
                            if (distance >= MAX_SUGAR_GROW_HEIGHT) return;
                            above = b.getRelative(BlockFace.UP, distance);
                            distance++;
                        }

                        if (above.getType() == Material.AIR) {
                            if (!updateUses(this, p, item, 1)) return;
                            blockLocation.getWorld().spawnParticle(Particle.WATER_SPLASH, blockLocation, 0);
                            double random = ThreadLocalRandom.current().nextDouble();
                            if (random <= sugarCaneSuccessChance.getValue()) {
                                above.setType(Material.SUGAR_CANE);
                                blockLocation.getWorld().playEffect(blockLocation, Effect.VILLAGER_PLANT_GROW, 0);
                            }
                        }

                        // Crops
                    } else if (blockData instanceof Ageable) {
                        Ageable crop = (Ageable) blockData;
                        int currentAge = crop.getAge();
                        int maxAge = crop.getMaximumAge();
                        if (currentAge < maxAge) {
                            if (updateUses(this, p, item, 1)) {
                                blockLocation.getWorld().spawnParticle(Particle.WATER_SPLASH, blockLocation, 0);
                                double random = ThreadLocalRandom.current().nextDouble();
                                if (random <= cropSuccessChance.getValue()) {
                                    crop.setAge(currentAge + 1);
                                    blockLocation.getWorld().playEffect(blockLocation, Effect.VILLAGER_PLANT_GROW, 0);
                                }
                            }
                        } else return;
                        b.setBlockData(blockData);

                        // Trees
                    } else if (Tag.SAPLINGS.isTagged(b.getType())) {
                        if (!updateUses(this, p, item, 1)) return;
                        blockLocation.getWorld().spawnParticle(Particle.WATER_SPLASH, blockLocation, 0);
                        double random = ThreadLocalRandom.current().nextDouble();
                        Material saplingMaterial = b.getType();
                        if (BlockStorage.hasBlockInfo(b)) {
                            if (random <= exoticGardenSuccessChance.getValue()) {
                                Bukkit.getPluginManager().callEvent(new StructureGrowEvent(b.getLocation(), getTreeFromSapling(saplingMaterial), false, p, Collections.singletonList(b.getState())));
                                blockLocation.getWorld().playEffect(blockLocation, Effect.VILLAGER_PLANT_GROW, 0);
                            }
                        } else {
                            if (Constants.SERVER_VERSION < 1163) {
                                if (random <= treeSuccessChance.getValue()) {
                                    b.setType(Material.AIR);
                                    if (!blockLocation.getWorld().generateTree(blockLocation, getTreeFromSapling(saplingMaterial)))
                                        b.setType(saplingMaterial);
                                    blockLocation.getWorld().playEffect(blockLocation, Effect.VILLAGER_PLANT_GROW, 0);
                                }
                            } else b.applyBoneMeal(p.getFacing());
                        }
                    }
                }
            }
        };
    }

    public static boolean updateUses(WateringCan can, Player p, ItemStack item, int updateType) {
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        int usesLeft = meta.getPersistentDataContainer().getOrDefault(usageKey, PersistentDataType.INTEGER, 0);
        if (updateType == 1) {
            if (usesLeft == 0) {
                Utils.send(p, "&cYou need to refill your Watering Can!");
                return false;
            }
            p.playSound(p.getLocation(), Sound.ENTITY_DROWNED_AMBIENT_WATER, 0.5F, 1F);
            usesLeft--;
        } else if (updateType == 2) {
            p.playSound(p.getLocation(), Sound.ENTITY_DROWNED_DEATH_WATER, 0.5F, 1F);
            Utils.send(p, "&aYou have filled your Watering Can");
            usesLeft = can.getUses().getValue();
        } else if (updateType == 3) {
            if (usesLeft == 0) {
                Utils.send(p, "&cYou need to refill your Watering Can!");
                return false;
            }
            usesLeft = 0;
            p.playSound(p.getLocation(), Sound.ITEM_BUCKET_EMPTY, 0.5F, 1F);
        } else p.sendMessage("Error");
        lore.set(USE_INDEX, ChatColors.color("&aUses Left: &e" + usesLeft));
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(usageKey, PersistentDataType.INTEGER, usesLeft);
        item.setItemMeta(meta);
        return true;
    }

    static TreeType getTreeFromSapling(Material m) {
        TreeType treeType = TreeType.TREE;
        String parseSapling = m.toString().replace("_SAPLING", "");
        if (!parseSapling.equals("OAK")) {
            if (parseSapling.equals("JUNGLE")) parseSapling = "SMALL_JUNGLE";
            return TreeType.valueOf(parseSapling);
        }
        return treeType;
    }

    public ItemSetting<Integer> getUses() {
        return this.maxUses;
    }
}