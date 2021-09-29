package id.universenetwork.utilities.Bukkit.Enums.Features;

public enum SkriptAddons {
    // Skript Addons Variable Settings
    ENABLED("Features.SkriptAddons.enabled"),
    ADDONS("Features.SkriptAddons.Addons."),
    ADDONSSETTINGS("Features.SkriptAddons.Settings.");
    final String configPath;

    SkriptAddons(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return configPath;
    }
}