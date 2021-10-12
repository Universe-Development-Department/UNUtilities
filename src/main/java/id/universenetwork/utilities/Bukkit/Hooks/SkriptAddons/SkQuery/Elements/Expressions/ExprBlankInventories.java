package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.asArray;

@Patterns({"inventory of %inventorytype%", "chest with %number% row[s]", "chest with %number% row[s] named %string%"})
public class ExprBlankInventories extends SimpleExpression<Inventory> {
    public Expression<InventoryType> type;
    public Expression<Number> rows;
    public Expression<String> title;
    int matchType;

    @Override
    protected Inventory[] get(Event event) {
        if (matchType == 0) {
            InventoryType t = type.getSingle(event);
            if (t == null) return null;
            return asArray(Bukkit.createInventory(null, t));
        } else if (matchType == 1) {
            Number n = rows.getSingle(event);
            if (n == null) return null;
            return asArray(Bukkit.createInventory(null, n.intValue() * 9));
        } else if (matchType == 2) {
            Number n = rows.getSingle(event);
            String s = title.getSingle(event);
            if (n == null || s == null) return null;
            return asArray(Bukkit.createInventory(null, n.intValue() * 9, s));
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Inventory> getReturnType() {
        return Inventory.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "inventory";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        matchType = i;
        switch (matchType) {
            case 0:
                type = (Expression<InventoryType>) expressions[0];
                break;
            case 2:
                title = (Expression<String>) expressions[1];
            case 1:
                rows = (Expression<Number>) expressions[0];
                break;
        }
        return true;
    }
}