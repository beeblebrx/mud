package fi.beeblebrx.mud.game.world;

import fi.beeblebrx.mud.Api.Command.CommandType;
import fi.beeblebrx.mud.game.GameObject;
import fi.beeblebrx.mud.game.results.Result;
import fi.beeblebrx.mud.player.Player;

public class Exit extends GameObject {
    private final int exitsTo;

    public Exit(final int id, final String description, final int exitsTo) {
        super(id, description);
        this.exitsTo = exitsTo;
    }

    @Override
    public Result accept(CommandType command, Player player) {
        return null;
    }

    public int getExitsTo() {
        return exitsTo;
    }
}
