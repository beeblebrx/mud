package fi.beeblebrx.mud.game.results;

import fi.beeblebrx.mud.Api;
import fi.beeblebrx.mud.Api.Event.EventType;

public class BasicEvent implements Event {
    private final String message;

    public BasicEvent(final String message) {
        this.message = message;
    }

    public byte[] toProtoBufBytes() {
        return Api.Event.newBuilder()
                .setText(message)
                .setType(EventType.GENERIC)
                .build()
                .toByteArray();
    }
}
