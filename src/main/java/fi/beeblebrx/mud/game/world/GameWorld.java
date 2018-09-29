package fi.beeblebrx.mud.game.world;

import fi.beeblebrx.mud.Api;
import fi.beeblebrx.mud.game.GameObject;
import fi.beeblebrx.mud.player.Player;

public interface GameWorld {
    GameObject get(Api.GameObject target, Player player);
}
