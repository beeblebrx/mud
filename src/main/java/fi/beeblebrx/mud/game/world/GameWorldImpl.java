package fi.beeblebrx.mud.game.world;

import fi.beeblebrx.mud.Api;
import fi.beeblebrx.mud.game.GameObject;
import fi.beeblebrx.mud.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GameWorldImpl implements GameWorld {
    private Map<Integer, Room> rooms = new HashMap<>();

    public GameWorldImpl() {
        final Room first = new Room(1, "The first room");
        final Room second = new Room(2,"The second room");
        final Room third = new Room(3,"The third room");
        first.setExits(Set.of(new Exit(1, "2", second)));
        second.setExits(Set.of(new Exit(2, "3", third)));
        third.setExits(Set.of(new Exit(3, "1", first)));
        rooms.put(1, first);
        rooms.put(2, second);
        rooms.put(3, third);
    }

    @Override
    public GameObject get(final Api.GameObject target, Player player) {
        final int id = target.getId();
        switch (target.getType()) {
            case ROOM: return rooms.get(id);
            case EXIT:
                final Room room = rooms.get(player.getLocation());
                final Map<Integer, Exit> exits = room.getExits();
                return exits.get(id);
        }
        return null;
    }
}
