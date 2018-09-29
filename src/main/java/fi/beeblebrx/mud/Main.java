package fi.beeblebrx.mud;

import com.google.inject.Guice;
import com.google.inject.Injector;
import fi.beeblebrx.mud.game.GameModule;
import fi.beeblebrx.mud.websocket.SocketCreator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class Main {
	public static void main(String[] args) throws Exception
    {
        final Injector injector = Guice.createInjector(new GameModule());

        final Server server = new Server(8080);
        final WebSocketHandler wsHandler = new WebSocketHandler() {
            @Override
            public void configure(WebSocketServletFactory factory) {
                factory.setCreator(new SocketCreator(injector));
            }
        };

        server.setHandler(wsHandler);
        server.start();
        server.join();
    }
}

