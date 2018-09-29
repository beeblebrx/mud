package fi.beeblebrx.mud.game;

import fi.beeblebrx.mud.Api;
import fi.beeblebrx.mud.Api.Response;

public class BasicResult implements Result {
    private final String message;

    public BasicResult(final String message) {
        this.message = message;
    }

    public byte[] toProtoBufBytes() {
        return Response.newBuilder()
                .setText(message)
                .build()
                .toByteArray();
    }
}
