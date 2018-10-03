package fi.beeblebrx.mud.game;

import com.google.inject.Inject;
import fi.beeblebrx.mud.Api;
import fi.beeblebrx.mud.Api.Command;
import fi.beeblebrx.mud.game.results.ErrorResult;
import fi.beeblebrx.mud.game.results.Result;
import fi.beeblebrx.mud.game.world.GameWorld;
import fi.beeblebrx.mud.player.Player;

public class CommandDispatcher {
    private static final Result UNKNOWN_TARGET_RESULT = new ErrorResult("Unknown target.");
    private static final Result COMMAND_NOT_ACCEPTED_RESULT = new ErrorResult("huh?");
    private final GameWorld world;
    private final UntargetedCommandHandler untargetedCommandHandler;

    @Inject
    public CommandDispatcher(final GameWorld world, final UntargetedCommandHandler untargetedCommandHandler) {
        this.world = world;
        this.untargetedCommandHandler = untargetedCommandHandler;
    }

    public Result dispatch(final Command.CommandType command, final Api.GameObject target,
                           final Player player) {
        if (target.isInitialized()) {
            final GameObject object = world.get(target, player);
            if (object == null) {
                return UNKNOWN_TARGET_RESULT;
            }

            final Result result = object.accept(command, player);
            if (result == null) {
                return COMMAND_NOT_ACCEPTED_RESULT;
            }

            return result;
        } else {
            return untargetedCommandHandler.handle(command, player, world);
        }
    }
}
