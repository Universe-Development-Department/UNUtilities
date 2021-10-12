package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Patterns("groups %number% of %string% matched (to|with|against) %string%")
public class ExprRegexMatches extends SimpleExpression<String> {
    Expression<Number> group;
    Expression<String> text, regex;

    @Override
    protected String[] get(Event e) {
        Number g = group.getSingle(e);
        String t = text.getSingle(e);
        String r = regex.getSingle(e);
        if (g == null || t == null || r == null) return null;
        ArrayList<String> results = new ArrayList<>();
        int groupId = g.intValue();
        Pattern pattern = Pattern.compile(r);
        Matcher matcher = pattern.matcher(t);
        while (matcher.find()) results.add(matcher.group(groupId));
        return results.toArray(new String[0]);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "regex";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        group = (Expression<Number>) exprs[0];
        text = (Expression<String>) exprs[1];
        regex = (Expression<String>) exprs[2];
        return true;
    }
}