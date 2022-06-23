package id.universenetwork.utilities.Bukkit.Features.ArmorStandArmsAdder;

import id.universenetwork.utilities.Bukkit.Templates.Feature;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityPlaceEvent;

public class Main extends Feature {
    @EventHandler
    public void onEntityPlace(EntityPlaceEvent e) {
        if (e.getEntityType() == EntityType.ARMOR_STAND && cfgSection.getBoolean("enabled"))
            ((ArmorStand) e.getEntity()).setArms(true);
    }
}