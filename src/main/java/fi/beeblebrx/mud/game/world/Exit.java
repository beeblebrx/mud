package fi.beeblebrx.mud.game.world;

import fi.beeblebrx.mud.Api.Command.CommandType;
import fi.beeblebrx.mud.game.GameObject;
import fi.beeblebrx.mud.game.results.Event;
import fi.beeblebrx.mud.player.Player;

public class Exit extends GameObject {
    private final Room exitsTo;

    public Exit(final int id, final String description, final Room exitsTo) {
        super(id, description);
        this.exitsTo = exitsTo;
    }

    @Override
    public Event accept(CommandType command, Player player) {
        return getExitsTo().accept(command, player);
    }

    public Room getExitsTo() {
        return exitsTo;
    }
}
