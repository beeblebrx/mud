package fi.beeblebrx.mud.game.results;

import fi.beeblebrx.mud.Api;
import fi.beeblebrx.mud.Api.Event.EventType;
import fi.beeblebrx.mud.Api.GameObject.Type;
import fi.beeblebrx.mud.game.GameObject;
import fi.beeblebrx.mud.game.world.Exit;
import fi.beeblebrx.mud.game.world.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class RoomLookResult implements Event {
    private final Room room;
    private final Set<GameObject> objects;

    public RoomLookResult(final Room room, final Set<GameObject> objects) {
        this.room = room;
        this.objects = objects;
    }

    @Override
    public byte[] toProtoBufBytes() {
        return Api.Event.newBuilder()
                .setText(room.getDescription())
                .addAllObjects(roomObjectsToList())
                .setType(EventType.SUCCESSFUL_RESPONSE)
                .build()
                .toByteArray();
    }

    private List<Api.GameObject> roomObjectsToList() {
        final Map<Integer, Exit> exits = this.room.getExits();
        final List<Api.GameObject> protoObjects = new ArrayList<>(exits.size() + objects.size());

        protoObjects.add(Api.GameObject.newBuilder()
            .setType(Type.ROOM)
            .setId(room.getId())
            .setDescription(room.getDescription())
            .build());
        exits.values().forEach(exit -> protoObjects.add(Api.GameObject.newBuilder()
            .setId(exit.getId())
            .setType(Type.EXIT)
            .setDescription(exit.getDescription())
            .build()));
        objects.forEach(obj -> protoObjects.add(Api.GameObject.newBuilder()
            .setId(obj.getId())
            .setType(Type.ITEM)
            .setDescription(obj.getDescription())
            .build()));

        return protoObjects;
    }
}
