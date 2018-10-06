package fi.beeblebrx.mud.websocket;

import com.google.inject.Inject;
import com.google.protobuf.InvalidProtocolBufferException;
import fi.beeblebrx.mud.Api.Command;
import fi.beeblebrx.mud.game.BasicResult;
import fi.beeblebrx.mud.game.CommandDispatcher;
import fi.beeblebrx.mud.game.GameState;
import fi.beeblebrx.mud.game.Result;
import fi.beeblebrx.mud.player.Player;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import static fi.beeblebrx.mud.Api.GameObject;

@WebSocket
public class GameSocket implements WebSocketListener {
    private GameState gameState;
    private final CommandDispatcher commandDispatcher;
    private Player player;
    private Session session;

    @Inject
    public GameSocket(final GameState gameState, final CommandDispatcher commandDispatcher) {
        this.gameState = gameState;
        this.commandDispatcher = commandDispatcher;
    }

    @Override
    public void onWebSocketBinary(byte[] payload, int offset, int len) {
        try {
            final Command command = Command.parseFrom(new ByteArrayInputStream(payload, offset, len));
            final Result result = commandDispatcher.dispatch(command.getType(), command.getTarget(), player);
            session.getRemote().sendBytes(ByteBuffer.wrap(result.toProtoBufBytes()));
        } catch (InvalidProtocolBufferException e) {
            System.err.println("Invalid protobuf: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    @Override
    public void onWebSocketText(String message) {
        System.out.println("Tekstiä tuli: " + message);
    }

    @Override
    public void onWebSocketClose(int statusCode, String reason) {
        System.out.println("Close: statusCode=" + statusCode + ", reason=" + reason + ", player=" + player.getId());
        gameState.removePlayer(player.getId());
    }

    @Override
    public void onWebSocketConnect(Session session) {
        System.out.println("Connection from " + session.getRemoteAddress());
        this.session = session;
        this.player = new Player(gameState.getPlayersOnline());
        this.player.setLocation(1);
        gameState.addPlayer(player);

        try {
            final byte[] gameEnterMsg = new BasicResult("You enter the game.").toProtoBufBytes();
            session.getRemote().sendBytes(ByteBuffer.wrap(gameEnterMsg));
            final GameObject room = GameObject.newBuilder()
                    .setType(GameObject.Type.ROOM)
                    .setId(1)
                    .build();
            final Result result = commandDispatcher.dispatch(Command.CommandType.LOOK, room, player);
            final byte[] protoBufBytes = result.toProtoBufBytes();
            session.getRemote().sendBytes(ByteBuffer.wrap(protoBufBytes));
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    @Override
    public void onWebSocketError(Throwable cause) {
        System.out.println("Virhe tuli: " + cause.getMessage());
    }
}
