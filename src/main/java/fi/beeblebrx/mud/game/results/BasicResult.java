package fi.beeblebrx.mud.game.results;

import fi.beeblebrx.mud.Api.Response;
import fi.beeblebrx.mud.game.results.Result;

public class BasicResult implements Result {
    private final String message;

    public BasicResult(final String message) {
        this.message = message;
    }

    public byte[] toProtoBufBytes() {
        return Response.newBuilder()
                .setText(message)
                .setStatus(Response.Status.SUCCESS)
                .build()
                .toByteArray();
    }
}
