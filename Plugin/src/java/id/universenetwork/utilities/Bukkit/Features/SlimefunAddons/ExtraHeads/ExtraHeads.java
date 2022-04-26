package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.ExtraHeads;

import id.universenetwork.utilities.Bukkit.UNUtilities;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

import static io.github.thebusybiscuit.slimefun4.api.MinecraftVersion.*;
import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getMinecraftVersion;

public class ExtraHeads extends id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.SFInstance {
    final Map<EntityType, ItemStack> mobs = new java.util.EnumMap<>(EntityType.class);
    ItemGroup itemGroup;
    RecipeType recipeType;

    @Override
    public void Load() {
        itemGroup = new ItemGroup(UNUtilities.createKey("heads"), new CustomItemStack(io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead.getItemStack(io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin.fromHashCode("5f1379a82290d7abe1efaabbc70710ff2ec02dd34ade386bc00c930c461cf932")), "&7Extra Heads", "", "&a> Click to open"), 1);
        recipeType = new RecipeType(UNUtilities.createKey("decapitation"), new CustomItemStack(org.bukkit.Material.IRON_SWORD, "&6Kill the specified Mob"));
        registerHead("Bat Head", EntityType.BAT, "2796aa6d18edc5b724bd89e983bc3215a41bf775d112635e9b5835d1b8ad20cb");
        registerHead("Blaze Head", EntityType.BLAZE, "b78ef2e4cf2c41a2d14bfde9caff10219f5b1bf5b35a49eb51c6467882cb5f0");
        registerHead("Cave Spider Head", EntityType.CAVE_SPIDER, "41645dfd77d09923107b3496e94eeb5c30329f97efc96ed76e226e98224");
        registerHead("Chicken Head", EntityType.CHICKEN, "1638469a599ceef7207537603248a9ab11ff591fd378bea4735b346a7fae893");
        registerHead("Cow Head", EntityType.COW, "5d6c6eda942f7f5f71c3161c7306f4aed307d82895f9d2b07ab4525718edc5");
        registerHead("Dolphin Head", EntityType.DOLPHIN, "cefe7d803a45aa2af1993df2544a28df849a762663719bfefc58bf389ab7f5");
        registerHead("Drowned Head", EntityType.DROWNED, "c84df79c49104b198cdad6d99fd0d0bcf1531c92d4ab6269e40b7d3cbbb8e98c");
        registerHead("Elder Guardian Head", EntityType.ELDER_GUARDIAN, "4adc4a6f53afa116027b51d6f2e433ee7afa5d59b2ffa04780be464fa5d61a");
        registerHead("Enderman Head", EntityType.ENDERMAN, "7a59bb0a7a32965b3d90d8eafa899d1835f424509eadd4e6b709ada50b9cf");
        registerHead("Evoker Head", EntityType.EVOKER, "d954135dc82213978db478778ae1213591b93d228d36dd54f1ea1da48e7cba6");
        registerHead("Ghast Head", EntityType.GHAST, "8b6a72138d69fbbd2fea3fa251cabd87152e4f1c97e5f986bf685571db3cc0");
        registerHead("Guardian Head", EntityType.GUARDIAN, "932c24524c82ab3b3e57c2052c533f13dd8c0beb8bdd06369bb2554da86c123");
        registerHead("Horse Head", EntityType.HORSE, "61902898308730c4747299cb5a5da9c25838b1d059fe46fc36896fee662729");
        registerHead("Husk Head", EntityType.HUSK, "d674c63c8db5f4ca628d69a3b1f8a36e29d8fd775e1a6bdb6cabb4be4db121");
        registerHead("Illusioner Head", EntityType.ILLUSIONER, "2f2882dd09723e47c0ab9663eab083d6a5969273706110c82910e61bf8a8f07e");
        registerHead("Iron Golem Head", EntityType.IRON_GOLEM, "89091d79ea0f59ef7ef94d7bba6e5f17f2f7d4572c44f90f76c4819a714");
        registerHead("Llama Head", EntityType.LLAMA, "2a5f10e6e6232f182fe966f501f1c3799d45ae19031a1e4941b5dee0feff059b");
        registerHead("Magma Cube Head", EntityType.MAGMA_CUBE, "38957d5023c937c4c41aa2412d43410bda23cf79a9f6ab36b76fef2d7c429");
        registerHead("Mooshroom Head", EntityType.MUSHROOM_COW, "d0bc61b9757a7b83e03cd2507a2157913c2cf016e7c096a4d6cf1fe1b8db");
        registerHead("Ocelot Head", EntityType.OCELOT, "5657cd5c2989ff97570fec4ddcdc6926a68a3393250c1be1f0b114a1db1");
        registerHead("Parrot Head", EntityType.PARROT, "a4ba8d66fecb1992e94b8687d6ab4a5320ab7594ac194a2615ed4df818edbc3");
        registerHead("Pig Head", EntityType.PIG, "621668ef7cb79dd9c22ce3d1f3f4cb6e2559893b6df4a469514e667c16aa4");
        registerHead("Polar Bear Head", EntityType.POLAR_BEAR, "442123ac15effa1ba46462472871b88f1b09c1db467621376e2f71656d3fbc");
        registerHead("Rabbit Head", EntityType.RABBIT, "ff1559194a175935b8b4fea6614bec60bf81cf524af6f564333c555e657bc");
        registerHead("Sheep Head", EntityType.SHEEP, "f31f9ccc6b3e32ecf13b8a11ac29cd33d18c95fc73db8a66c5d657ccb8be70");
        registerHead("Shulker Head", EntityType.SHULKER, "b1d3534d21fe8499262de87affbeac4d25ffde35c8bdca069e61e1787ff2f");
        registerHead("Slime Head", EntityType.SLIME, "16ad20fc2d579be250d3db659c832da2b478a73a698b7ea10d18c9162e4d9b5");
        registerHead("Spider Head", EntityType.SPIDER, "cd541541daaff50896cd258bdbdd4cf80c3ba816735726078bfe393927e57f1");
        registerHead("Squid Head", EntityType.SQUID, "01433be242366af126da434b8735df1eb5b3cb2cede39145974e9c483607bac");
        registerHead("Stray Head", EntityType.STRAY, "78ddf76e555dd5c4aa8a0a5fc584520cd63d489c253de969f7f22f85a9a2d56");
        registerHead("Turtle Head", EntityType.TURTLE, "0a4050e7aacc4539202658fdc339dd182d7e322f9fbcc4d5f99b5718a");
        registerHead("Vex Head", EntityType.VEX, "c2ec5a516617ff1573cd2f9d5f3969f56d5575c4ff4efefabd2a18dc7ab98cd");
        registerHead("Villager Head", EntityType.VILLAGER, "822d8e751c8f2fd4c8942c44bdb2f5ca4d8ae8e575ed3eb34c18a86e93b");
        registerHead("Vindicator Head", EntityType.VINDICATOR, "6deaec344ab095b48cead7527f7dee61b063ff791f76a8fa76642c8676e2173");
        registerHead("Witch Head", EntityType.WITCH, "ddedbee42be472e3eb791e7dbdfaf18c8fe593c638ba1396c9ef68f555cbce");
        registerHead("Wither Head", EntityType.WITHER, "cdf74e323ed41436965f5c57ddf2815d5332fe999e68fbb9d6cf5c8bd4139f");
        registerHead("Zombie Villager Head", EntityType.ZOMBIE_VILLAGER, "a6224941314bca2ebbb66b10ffd94680cc98c3435eeb71a228a08fd42c24db");
        if (getMinecraftVersion().isAtLeast(MINECRAFT_1_14)) {
            registerHead("Ravager Head", EntityType.RAVAGER, "1cb9f139f9489d86e410a06d8cbc670c8028137508e3e4bef612fe32edd60193");
            registerHead("Pillager Head", EntityType.PILLAGER, "4aee6bb37cbfc92b0d86db5ada4790c64ff4468d68b84942fde04405e8ef5333");
            registerHead("Fox Head", EntityType.FOX, "46cff7a19e683a08e4587ea1457880313d5f341f346ceb5b0551195d810e3");
            registerHead("Panda Head", EntityType.PANDA, "7818b681cace1c641919f53edadecb142330d089a826b56219138c33b7a5e0db");
            registerHead("Wandering Trader Head", EntityType.WANDERING_TRADER, "5f1379a82290d7abe1efaabbc70710ff2ec02dd34ade386bc00c930c461cf932");
        }
        if (getMinecraftVersion().isAtLeast(MINECRAFT_1_15))
            registerHead("Bee Head", EntityType.BEE, "4420c9c43e095880dcd2e281c81f47b163b478f58a584bb61f93e6e10a155f31");
        if (getMinecraftVersion().isBefore(MINECRAFT_1_16))
            registerHead("Zombie Pigman Head", EntityType.valueOf("PIG_ZOMBIE"), "74e9c6e98582ffd8ff8feb3322cd1849c43fb16b158abb11ca7b42eda7743eb");
        else {
            registerHead("Piglin Head", EntityType.PIGLIN, "11d18bbd0d795b9ac8efaad655e3d0c59fcbb9b964c2a9948ef537f4a3fbbf87");
            registerHead("Zombified Piglin Head", EntityType.ZOMBIFIED_PIGLIN, "e935842af769380f78e8b8a88d1ea6ca2807c1e5693c2cf797456620833e936f");
            registerHead("Strider", EntityType.STRIDER, "18a9adf780ec7dd4625c9c0779052e6a15a451866623511e4c82e9655714b3c1");
        }
        if (getMinecraftVersion().isAtLeast(MINECRAFT_1_17)) {
            registerHead("Axolotl Head", EntityType.AXOLOTL, "5c138f401c67fc2e1e387d9c90a9691772ee486e8ddbf2ed375fc8348746f936");
            registerHead("Glow Squid Head", EntityType.GLOW_SQUID, "57327ee11812b764c7ade70b282cce4c58e635b2015244081d1490543da7280e");
            registerHead("Goat Head", EntityType.GOAT, "457a0d538fa08a7affe312903468861720f9fa34e86d44b89dcec5639265f03");
        }
        id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListeners(new HeadListener(this));
    }

    void registerHead(String name, EntityType type, String texture) {
        try {
            double chance = UNUtilities.cfg.getOrSetDefault(configPath + "chances." + type.toString(), 5.0);
            SlimefunItemStack item = new SlimefunItemStack(type + "_HEAD", texture, "&r" + name);
            new MobHead(itemGroup, item, recipeType, new CustomItemStack(item, "&rKill 1 " + io.github.thebusybiscuit.slimefun4.utils.ChatUtils.humanize(type.name()), "&7Chance: &e" + chance + "%")).register(this, () -> mobs.put(type, item));
        } catch (Exception x) {
            getLogger().log(java.util.logging.Level.WARNING, x, () -> UNUtilities.prefix + " §eCould not load Mob Head for Entity: " + type);
        }
    }

    public Map<EntityType, ItemStack> getMobDrops() {
        return mobs;
    }
}