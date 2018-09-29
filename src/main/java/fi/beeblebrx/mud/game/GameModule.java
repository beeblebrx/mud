package fi.beeblebrx.mud.game;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import fi.beeblebrx.mud.game.world.GameWorld;
import fi.beeblebrx.mud.game.world.GameWorldImpl;
import fi.beeblebrx.mud.websocket.GameSocket;
import org.eclipse.jetty.websocket.api.WebSocketListener;

public class GameModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(WebSocketListener.class).to(GameSocket.class);
        bind(GameState.class).to(GameStateImpl.class).in(Scopes.SINGLETON);
        bind(GameWorld.class).to(GameWorldImpl.class).in(Scopes.SINGLETON);
    }
}
