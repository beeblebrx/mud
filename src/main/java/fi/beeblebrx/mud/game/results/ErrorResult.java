package fi.beeblebrx.mud.game.results;

import fi.beeblebrx.mud.Api;
import fi.beeblebrx.mud.Api.Event.EventType;

public class ErrorResult implements Event {
    private final String message;

    public ErrorResult(final String message) {
        this.message = message;
    }

    @Override
    public byte[] toProtoBufBytes() {
        return Api.Event.newBuilder()
                .setType(EventType.FAILING_RESPONSE)
                .setText(message)
                .build()
                .toByteArray();
    }
}
