package fi.beeblebrx.mud.game.results;

import fi.beeblebrx.mud.Api;

public class ErrorResult implements Result {
    private final String message;

    public ErrorResult(final String message) {
        this.message = message;
    }

    @Override
    public byte[] toProtoBufBytes() {
        return Api.Response.newBuilder()
                .setStatus(Api.Response.Status.ERROR)
                .setText(message)
                .build()
                .toByteArray();
    }
}
