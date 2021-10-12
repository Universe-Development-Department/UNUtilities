package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyFrom;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyTo;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.UsePropertyPatterns;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.asArray;

@UsePropertyPatterns
@PropertyFrom("worlds")
@PropertyTo("world[ ]border[s]")
public class ExprBorder extends SimplePropertyExpression<World, WorldBorder> {
    @Override
    public Class<? extends WorldBorder> getReturnType() {
        return WorldBorder.class;
    }

    @Override
    protected String getPropertyName() {
        return "world border";
    }

    @Override
    @Nullable
    public WorldBorder convert(World world) {
        return world.getWorldBorder();
    }

    @Override
    public Class<?>[] acceptChange(ChangeMode mode) {
        if (mode == ChangeMode.RESET || mode == ChangeMode.DELETE) return asArray(Object.class);
        return null;
    }

    @Override
    public void change(Event event, Object[] delta, ChangeMode mode) {
        for (World world : getExpr().getArray(event)) world.getWorldBorder().reset();
    }
}