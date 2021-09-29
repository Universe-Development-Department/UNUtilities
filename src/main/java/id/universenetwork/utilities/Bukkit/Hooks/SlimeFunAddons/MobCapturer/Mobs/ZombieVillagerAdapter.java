package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.MobCapturer.Mobs;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.ZombieVillager;

import java.util.List;
import java.util.UUID;

public class ZombieVillagerAdapter extends ZombieAdapter<ZombieVillager> {
    public ZombieVillagerAdapter() {
        super(ZombieVillager.class);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);
        lore.add(ChatColor.GRAY + "Profession: " + ChatColor.WHITE + ChatUtils.humanize(json.get("profession").getAsString()));
        return lore;
    }

    @Override
    public JsonObject saveData(ZombieVillager entity) {
        JsonObject json = super.saveData(entity);
        json.addProperty("profession", entity.getVillagerProfession().name());
        json.addProperty("conversionPlayer", entity.getConversionPlayer() == null ? null : entity.getConversionPlayer().getUniqueId().toString());
        return json;
    }

    @Override
    public void apply(ZombieVillager entity, JsonObject json) {
        super.apply(entity, json);
        entity.setVillagerProfession(Profession.valueOf(json.get("profession").getAsString()));
        JsonElement player = json.get("conversionPlayer");
        if (!player.isJsonNull())
            entity.setConversionPlayer(Bukkit.getOfflinePlayer(UUID.fromString(player.getAsString())));
    }
}