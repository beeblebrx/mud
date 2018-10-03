package fi.beeblebrx.mud.game.results;

import fi.beeblebrx.mud.Api;
import fi.beeblebrx.mud.game.GameObject;
import fi.beeblebrx.mud.game.world.Exit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static fi.beeblebrx.mud.Api.Response.Status.*;

public class RoomLookResult implements Result {
    private final String roomDescription;
    private final Map<Integer, Exit> exits;
    private final Set<GameObject> objects;

    public RoomLookResult(final String roomDescription, final Map<Integer, Exit> exits,
                          final Set<GameObject> objects) {
        this.roomDescription = roomDescription;
        this.exits = exits;
        this.objects = objects;
    }

    @Override
    public byte[] toProtoBufBytes() {
        return Api.Response.newBuilder()
                .setText(roomDescription)
                .addAllObjects(roomObjectsToList())
                .setStatus(SUCCESS)
                .build()
                .toByteArray();
    }

    private List<Api.GameObject> roomObjectsToList() {
        final List<Api.GameObject> protoObjects = new ArrayList<>(exits.size() + objects.size());

        exits.values().forEach(exit -> protoObjects.add(Api.GameObject.newBuilder()
            .setId(exit.getId())
            .setType(Api.GameObject.Type.EXIT)
            .setDescription(exit.getDescription())
            .build()));
        objects.forEach(obj -> protoObjects.add(Api.GameObject.newBuilder()
            .setId(obj.getId())
            .setType(Api.GameObject.Type.ITEM)
            .setDescription(obj.getDescription())
            .build()));

        return protoObjects;
    }
}
