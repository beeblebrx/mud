package fi.beeblebrx.mud.game;

import fi.beeblebrx.mud.Api.Command.CommandType;
import fi.beeblebrx.mud.game.world.GameWorld;
import fi.beeblebrx.mud.player.Player;

import java.util.Set;

public class UntargetedCommandHandler {
    private final Set<CommandType> targetedCommands = Set.of(CommandType.MOVE, CommandType.LOOK, CommandType.SPEAK);

    public Result handle(CommandType command, Player player, GameWorld world) {
        // Command type was targeted but target wasn't set.
        if (targetedCommands.contains(command)) {
            return new BasicResult("Command target is missing.");
        }

        // Nothing implemented yet.
        return new BasicResult("huh?");
    }
}
