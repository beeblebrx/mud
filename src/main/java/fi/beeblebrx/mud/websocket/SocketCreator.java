package fi.beeblebrx.mud.websocket;

import com.google.inject.Injector;
import org.eclipse.jetty.websocket.api.WebSocketListener;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

public class SocketCreator implements WebSocketCreator {

    private final Injector injector;

    public SocketCreator(final Injector injector) {
        this.injector = injector;
    }

    @Override
    public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
        return injector.getInstance(WebSocketListener.class);
    }
}
