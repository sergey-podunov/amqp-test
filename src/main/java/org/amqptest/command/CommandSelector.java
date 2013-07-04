package org.amqptest.command;

import com.google.common.collect.ImmutableMap;
import org.amqptest.command.channel.ChannelCloseFactory;
import org.amqptest.command.channel.ChannelOpenFactory;
import org.amqptest.command.connection.ConnectionCloseFactory;
import org.amqptest.command.connection.ConnectionOpenFactory;
import org.amqptest.command.connection.ConnectionStartOkFactory;
import org.amqptest.command.connection.ConnectionTuneOkFactory;
import org.amqptest.exception.UnsupportedCommandException;
import org.amqptest.frame.MethodFrame;

import java.util.Map;

public class CommandSelector {

    private static final Map<CommandMethodId, CommandFactory> COMMAND_FACTORY_MAP = ImmutableMap.<CommandMethodId, CommandFactory>builder()
            .put(new CommandMethodId(10, 11), new ConnectionStartOkFactory())
            .put(new CommandMethodId(10, 31), new ConnectionTuneOkFactory())
            .put(new CommandMethodId(10, 40), new ConnectionOpenFactory())
            .put(new CommandMethodId(10, 50), new ConnectionCloseFactory())
            .put(new CommandMethodId(20, 10), new ChannelOpenFactory())
            .put(new CommandMethodId(20, 40), new ChannelCloseFactory())
            .build();

    public AmqpRequestCommand getCommand(CommandMethodId commandMethodId, MethodFrame rawCommand) throws UnsupportedCommandException {
        CommandFactory commandFactory = COMMAND_FACTORY_MAP.get(commandMethodId);
        if (commandFactory == null) {
            throw new UnsupportedCommandException(commandMethodId.getCommandId(), commandMethodId.getMethodId());
        }

        return commandFactory.createCommand(rawCommand);
    }

}
