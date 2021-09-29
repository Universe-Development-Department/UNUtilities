package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.MobCapturer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;

import static io.github.thebusybiscuit.slimefun4.utils.NumberUtils.roundDecimalNumber;
import static org.bukkit.ChatColor.*;

public interface MobAdapter<T extends LivingEntity> extends PersistentDataType<String, JsonObject> {
    Class<T> getEntityClass();

    default List<String> getLore(JsonObject json) {
        List<String> l = new LinkedList<>();
        l.add("");
        l.add(GRAY + "Health: " + GREEN + roundDecimalNumber(json.get("_health").getAsDouble()));
        if (!json.get("_customName").isJsonNull())
            l.add(GRAY + "Name: " + RESET + json.get("_customName").getAsString());
        int fireTicks = json.get("_fireTicks").getAsInt();
        if (fireTicks > 0) l.add(GRAY + "On Fire: " + RESET + "true");
        return l;
    }

    @Nonnull
    @Override
    default Class<String> getPrimitiveType() {
        return String.class;
    }

    @Nonnull
    @Override
    default Class<JsonObject> getComplexType() {
        return JsonObject.class;
    }

    @Nonnull
    @Override
    default String toPrimitive(JsonObject json, @Nonnull PersistentDataAdapterContext context) {
        return json.toString();
    }

    @Nonnull
    @Override
    @ParametersAreNonnullByDefault
    default JsonObject fromPrimitive(String primitive, PersistentDataAdapterContext context) {
        return new JsonParser().parse(primitive).getAsJsonObject();
    }

    default void apply(T entity, JsonObject json) {
        // We need to apply Attributes before the health.
        JsonObject attributes = json.getAsJsonObject("_attributes");
        for (Map.Entry<String, JsonElement> entry : attributes.entrySet()) {
            AttributeInstance instance = entity.getAttribute(Attribute.valueOf(entry.getKey()));
            if (instance != null) {
                for (AttributeModifier modifier : new ArrayList<>(instance.getModifiers()))
                    instance.removeModifier(modifier);
                JsonObject attribute = entry.getValue().getAsJsonObject();
                instance.setBaseValue(attribute.get("base").getAsDouble());
                JsonArray modifiers = attribute.getAsJsonArray("modifiers");
                for (JsonElement modifier : modifiers) {
                    JsonObject obj = modifier.getAsJsonObject();
                    String uuid = obj.get("uuid").getAsString();
                    String name = obj.get("name").getAsString();
                    double amount = obj.get("amount").getAsDouble();
                    int operation = obj.get("operation").getAsInt();
                    AttributeModifier mod = new AttributeModifier(UUID.fromString(uuid), name, amount, Operation.values()[operation]);
                    instance.addModifier(mod);
                }
            }
        }

        entity.setHealth(json.get("_health").getAsDouble());
        entity.setAbsorptionAmount(json.get("_absorption").getAsDouble());
        entity.setRemoveWhenFarAway(json.get("_removeWhenFarAway").getAsBoolean());
        if (!json.get("_customName").isJsonNull()) entity.setCustomName(json.get("_customName").getAsString());
        entity.setCustomNameVisible(json.get("_customNameVisible").getAsBoolean());
        entity.setAI(json.get("_ai").getAsBoolean());
        entity.setSilent(json.get("_silent").getAsBoolean());
        entity.setGlowing(json.get("_glowing").getAsBoolean());
        entity.setInvulnerable(json.get("_invulnerable").getAsBoolean());
        entity.setCollidable(json.get("_collidable").getAsBoolean());
        entity.setGravity(json.get("_gravity").getAsBoolean());
        entity.setFireTicks(json.get("_fireTicks").getAsInt());
        JsonObject effects = json.getAsJsonObject("_effects");
        for (Map.Entry<String, JsonElement> entry : effects.entrySet()) {
            PotionEffectType type = PotionEffectType.getByName(entry.getKey());
            if (type != null) {
                JsonObject obj = entry.getValue().getAsJsonObject();
                int duration = obj.get("duration").getAsInt();
                int amplifier = obj.get("amplifier").getAsInt();
                boolean ambient = obj.get("ambient").getAsBoolean();
                boolean particles = obj.get("particles").getAsBoolean();
                boolean icon = obj.get("icon").getAsBoolean();
                entity.addPotionEffect(new PotionEffect(type, duration, amplifier, ambient, particles, icon));
            }
        }
        JsonArray tags = json.getAsJsonArray("_scoreboardTags");
        for (JsonElement tag : tags) entity.addScoreboardTag(tag.getAsString());
    }

    default JsonObject saveData(T entity) {
        JsonObject json = new JsonObject();
        json.addProperty("_type", entity.getType().toString());
        json.addProperty("_health", entity.getHealth());
        json.addProperty("_absorption", entity.getAbsorptionAmount());
        json.addProperty("_removeWhenFarAway", entity.getRemoveWhenFarAway());
        json.addProperty("_customName", entity.getCustomName());
        json.addProperty("_customNameVisible", entity.isCustomNameVisible());
        json.addProperty("_ai", entity.hasAI());
        json.addProperty("_silent", entity.isSilent());
        json.addProperty("_glowing", entity.isGlowing());
        json.addProperty("_invulnerable", entity.isInvulnerable());
        json.addProperty("_collidable", entity.isCollidable());
        json.addProperty("_gravity", entity.hasGravity());
        json.addProperty("_fireTicks", entity.getFireTicks());
        JsonObject attributes = new JsonObject();
        for (Attribute attribute : Attribute.values()) {
            AttributeInstance instance = entity.getAttribute(attribute);
            if (instance != null) {
                JsonObject obj = new JsonObject();
                obj.addProperty("base", instance.getBaseValue());
                JsonArray modifiers = new JsonArray();
                for (AttributeModifier modifier : instance.getModifiers()) {
                    JsonObject mod = new JsonObject();
                    mod.addProperty("uuid", modifier.getUniqueId().toString());
                    mod.addProperty("name", modifier.getName());
                    mod.addProperty("operation", modifier.getOperation().ordinal());
                    mod.addProperty("amount", modifier.getAmount());
                    modifiers.add(mod);
                }
                obj.add("modifiers", modifiers);
                attributes.add(attribute.toString(), obj);
            }
        }
        json.add("_attributes", attributes);
        JsonObject effects = new JsonObject();
        for (PotionEffect effect : entity.getActivePotionEffects()) {
            JsonObject obj = new JsonObject();
            obj.addProperty("duration", effect.getDuration());
            obj.addProperty("amplifier", effect.getAmplifier());
            obj.addProperty("ambient", effect.isAmbient());
            obj.addProperty("particles", effect.hasParticles());
            obj.addProperty("icon", effect.hasIcon());
            effects.add(effect.getType().getName(), obj);
        }
        json.add("_effects", effects);
        JsonArray tags = new JsonArray();
        for (String tag : entity.getScoreboardTags()) tags.add(tag);
        json.add("_scoreboardTags", tags);
        return json;
    }
}