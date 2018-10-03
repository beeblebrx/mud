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
        rooms.put(1, new Room(1,"The first room", Set.of(new Exit(1, "2", 2))));
        rooms.put(2, new Room(2,"The second room", Set.of(new Exit(2, "3", 3))));
        rooms.put(3, new Room(3,"The third room", Set.of(new Exit(3, "1", 1))));
    }

    @Override
    public GameObject get(final Api.GameObject target, Player player) {
        final int id = target.getId();
        switch (target.getType()) {
            case ROOM: return rooms.get(id);
            case EXIT: return rooms.get(player.getLocation()).getExits().get(id);
        }
        return null;
    }
}
