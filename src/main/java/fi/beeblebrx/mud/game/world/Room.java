package fi.beeblebrx.mud.game.world;

import fi.beeblebrx.mud.Api.Command.CommandType;
import fi.beeblebrx.mud.game.GameObject;
import fi.beeblebrx.mud.game.results.Result;
import fi.beeblebrx.mud.game.results.RoomLookResult;
import fi.beeblebrx.mud.player.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Room extends GameObject {
    private final Map<Integer, Exit> exits = new HashMap<>();

    public Room(final int id, final String description, final Set<Exit> exits) {
        super(id, description);
        for (Exit exit : exits) {
            this.exits.put(exit.getId(), exit);
        }
    }

    public Map<Integer, Exit> getExits() {
        return exits;
    }

    @Override
    public Result accept(final CommandType command, final Player player) {
        switch (command) {
            case LOOK: return new RoomLookResult(getDescription(), getExits(), Collections.EMPTY_SET);
            case MOVE: return acceptPlayer(player);
            default: return null;
        }
    }

    private Result acceptPlayer(final Player player) {
        player.setLocation(getId());
        return new RoomLookResult(getDescription(), getExits(), Collections.EMPTY_SET);
    }
}
