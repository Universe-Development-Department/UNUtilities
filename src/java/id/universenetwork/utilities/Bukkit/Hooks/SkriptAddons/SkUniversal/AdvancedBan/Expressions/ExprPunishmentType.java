package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkUniversal.AdvancedBan.Expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.leoko.advancedban.bukkit.event.PunishmentEvent;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("AdvancedBan - Punishment Type")
@ch.njol.skript.doc.Description("Returns the punish type on Punishment event.")
public class ExprPunishmentType extends SimpleExpression<String> {
    static {
        Skript.registerExpression(ExprPunishmentType.class, String.class, ExpressionType.SIMPLE, "[the] [AdvancedBan] punish[ment] type");
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        if (!ch.njol.skript.ScriptLoader.isCurrentEvent(PunishmentEvent.class)) {
            Skript.error("You can not use punishment type expression in any event but on punish.");
            return false;
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the punishment type";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return new String[]{((PunishmentEvent) e).getPunishment().getType().getName()};
    }
}