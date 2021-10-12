package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects.Base;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.parser.ParserInstance;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Events.Lang.ScriptOptionsEvent;

public abstract class OptionsPragma extends Pragma {
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        if (!ParserInstance.get().isCurrentEvent(ScriptOptionsEvent.class)) {
            Skript.error("Pragma cannot be declared outside of script options.");
            return false;
        }
        super.init(expressions, i, kleenean, parseResult);
        return true;
    }
}