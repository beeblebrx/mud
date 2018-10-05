package fi.beeblebrx.mud.game;

import com.google.inject.Inject;
import fi.beeblebrx.mud.Api;
import fi.beeblebrx.mud.Api.Command;
import fi.beeblebrx.mud.game.results.ErrorResult;
import fi.beeblebrx.mud.game.results.Event;
import fi.beeblebrx.mud.game.world.GameWorld;
import fi.beeblebrx.mud.player.Player;

public class CommandDispatcher {
    private static final Event UNKNOWN_TARGET_RESULT = new ErrorResult("Unknown target.");
    private static final Event COMMAND_NOT_ACCEPTED_RESULT = new ErrorResult("huh?");
    private final GameWorld world;
    private final UntargetedCommandHandler untargetedCommandHandler;

    @Inject
    public CommandDispatcher(final GameWorld world, final UntargetedCommandHandler untargetedCommandHandler) {
        this.world = world;
        this.untargetedCommandHandler = untargetedCommandHandler;
    }

    public Event dispatch(final Command.CommandType command, final Api.GameObject target,
                          final Player player) {
        if (target.isInitialized()) {
            final GameObject object = world.get(target, player);
            if (object == null) {
                return UNKNOWN_TARGET_RESULT;
            }

            final Event event = object.accept(command, player);
            if (event == null) {
                return COMMAND_NOT_ACCEPTED_RESULT;
            }

            return event;
        } else {
            return untargetedCommandHandler.handle(command, player, world);
        }
    }
}
