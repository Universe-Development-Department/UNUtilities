package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.iterator.EmptyIterator;
import ch.njol.util.coll.iterator.IteratorIterable;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Region.CuboidIterator;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Patterns("blocks within %chunk%")
public class ExprBlockChunk extends SimpleExpression<Block> {
    Expression<Chunk> chunk;

    @Override
    protected Block[] get(Event event) {
        Chunk c = chunk.getSingle(event);
        if (c == null) return null;
        List<Block> list = new ArrayList<>();
        for (Block b : new IteratorIterable<>(iterator(event))) list.add(b);
        return list.toArray(new Block[0]);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Block> getReturnType() {
        return Block.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "cuboid, chunk";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        chunk = (Expression<Chunk>) expressions[0];
        return true;
    }

    @Override
    public boolean isLoopOf(String s) {
        return s.equalsIgnoreCase("block");
    }

    @Override
    public Iterator<Block> iterator(Event e) {
        Chunk c = chunk.getSingle(e);
        if (c == null) return new EmptyIterator<>();
        return new CuboidIterator(c);
    }
}