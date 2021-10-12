package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Events;

import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class EvtBlockLand extends SkriptEvent {
    @Override
    public boolean init(Literal<?>[] literals, int i, ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(Event event) {
        return ((EntityChangeBlockEvent) event).getEntity() instanceof FallingBlock;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "block land";
    }
}