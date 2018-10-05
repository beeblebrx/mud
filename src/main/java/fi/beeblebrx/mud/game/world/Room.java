package fi.beeblebrx.mud.game.world;

import fi.beeblebrx.mud.Api.Command.CommandType;
import fi.beeblebrx.mud.game.GameObject;
import fi.beeblebrx.mud.game.results.Event;
import fi.beeblebrx.mud.game.results.RoomLookResult;
import fi.beeblebrx.mud.player.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Room extends GameObject {
    private final Map<Integer, Exit> exits = new HashMap<>();

    public Room(final int id, final String description) {
        super(id, description);
    }

    public Map<Integer, Exit> getExits() {
        return exits;
    }

    @Override
    public Event accept(final CommandType command, final Player player) {
        switch (command) {
            case LOOK: return new RoomLookResult(this, Collections.EMPTY_SET);
            case MOVE: return acceptPlayer(player);
            default: return null;
        }
    }

    private Event acceptPlayer(final Player player) {
        player.setLocation(getId());
        return new RoomLookResult(this, Collections.EMPTY_SET);
    }

    public void setExits(Set<Exit> exits) {
        for (final Exit exit : exits) {
            this.exits.put(exit.getId(), exit);
        }
    }
}
