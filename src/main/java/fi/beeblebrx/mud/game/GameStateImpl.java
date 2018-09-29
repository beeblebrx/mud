package fi.beeblebrx.mud.game;

import fi.beeblebrx.mud.player.Player;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class GameStateImpl implements GameState {
    private ConcurrentMap<Long, Player> players;

    public GameStateImpl() {
        players = new ConcurrentHashMap();
    }

    @Override
    public void addPlayer(Player player) {
        players.putIfAbsent(player.getId(), player);
    }

    @Override
    public void removePlayer(long playerId) {
        players.remove(playerId);
    }

    @Override
    public Player getPlayer(long playerId) {
        return players.get(playerId);
    }

    @Override
    public int getPlayersOnline() {
        return players.size();
    }
}
