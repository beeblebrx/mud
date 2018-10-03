package fi.beeblebrx.mud.game;

import fi.beeblebrx.mud.Api.Command.CommandType;
import fi.beeblebrx.mud.game.results.Result;
import fi.beeblebrx.mud.player.Player;

public abstract class GameObject {
    private final int id;
    private final String description;

    public GameObject(final int id, final String description) {
        this.id = id;
        this.description = description;
    }

    public abstract Result accept(final CommandType command, final Player player);

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }
}
