package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.MobCapturer.Mobs;

import com.google.gson.JsonObject;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Spellcaster;
import org.bukkit.entity.Spellcaster.Spell;

import java.util.List;

public class MagicIllagerAdapter<T extends Spellcaster> extends RaiderAdapter<T> {
    public MagicIllagerAdapter(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);
        lore.add(ChatColor.GRAY + "Spell: " + ChatColor.WHITE + ChatUtils.humanize(json.get("spell").getAsString()));
        return lore;
    }

    @Override
    public void apply(T entity, JsonObject json) {
        super.apply(entity, json);
        entity.setSpell(Spell.valueOf(json.get("spell").getAsString()));
    }

    @Override
    public JsonObject saveData(T entity) {
        JsonObject json = super.saveData(entity);
        json.addProperty("spell", entity.getSpell().name());
        return json;
    }
}