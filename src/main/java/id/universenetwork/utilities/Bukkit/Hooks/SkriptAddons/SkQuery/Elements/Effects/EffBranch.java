package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects;

import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.TriggerItem;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Examples;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.event.Event;

@Name("Branch")
@Description("Execute the following code X times. Useful for testing probabilities without requiring a loop and indentation.")
@Examples({"on script load:", "	branch 10", "	message \"This message will pop up 10 times\" to console"})
@Patterns("branch %number%")
public class EffBranch extends Effect {
    Expression<Number> branch;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        branch = (Expression<Number>) expressions[0];
        return true;
    }

    @Override
    protected void execute(Event event) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected TriggerItem walk(Event event) {
        debug(event, false);
        Number jumps = branch.getSingle(event);
        if (jumps == null) return null;
        for (int i = 0; i < jumps.intValue(); i++) TriggerItem.walk(getNext(), event);
        return null;
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "branch " + branch.toString(event, debug);
    }
}