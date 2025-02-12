package net.momirealms.craftengine.bukkit.api.event;

import net.momirealms.craftengine.bukkit.util.BlockStateUtils;
import net.momirealms.craftengine.core.util.Key;
import net.momirealms.craftengine.core.world.BlockPos;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class BlockStartBreakEvent extends Event implements Cancellable {
    private static final HandlerList handlerList = new HandlerList();
    private boolean cancelled;
    private final Key blockId;
    private final Location location;
    private final Player optionalPlayer;

    public BlockStartBreakEvent(int stateId, Location location, Player optionalPlayer) {
        super(true);
        this.blockId = BlockStateUtils.getRealBlockIdFromStateId(stateId);
        this.location = location;
        this.optionalPlayer = optionalPlayer;
    }

    public BlockPos blockPos() {
        return new BlockPos(this.location.getBlockX(), this.location.getBlockY(), this.location.getBlockZ());
    }

    public Location location() {
        return location;
    }

    public Player optionalPlayer() {
        return optionalPlayer;
    }

    public Key blockId() {
        return blockId;
    }

    public boolean isCustomBlock() {
        return blockId.namespace().equals("craftengine");
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return getHandlerList();
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
