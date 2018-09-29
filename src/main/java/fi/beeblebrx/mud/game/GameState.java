package fi.beeblebrx.mud.game;

import fi.beeblebrx.mud.player.Player;

public interface GameState {
    void addPlayer(Player player);
    void removePlayer(long playerId);
    Player getPlayer(long playerID);
    int getPlayersOnline();
}
