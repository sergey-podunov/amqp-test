package org.amqptest.command;

import com.google.common.collect.ImmutableMap;
import org.amqptest.command.connection.ConnectionCloseFactory;
import org.amqptest.command.connection.ConnectionOpenFactory;
import org.amqptest.command.connection.ConnectionStartOkFactory;
import org.amqptest.command.connection.ConnectionTuneOkFactory;
import org.amqptest.exception.UnsupportedCommandException;

import java.util.Map;

public class CommandSelector {

    private static final Map<CommandMethodId, CommandFactory> COMMAND_FACTORY_MAP = ImmutableMap.<CommandMethodId, CommandFactory>builder()
            .put(new CommandMethodId(10, 11), new ConnectionStartOkFactory())
            .put(new CommandMethodId(10, 31), new ConnectionTuneOkFactory())
            .put(new CommandMethodId(10, 40), new ConnectionOpenFactory())
            .put(new CommandMethodId(10, 50), new ConnectionCloseFactory())
            .build();

    public AmqpCommand getCommand(CommandMethodId commandMethodId, byte[] commandPayload) throws UnsupportedCommandException {
        CommandFactory commandFactory = COMMAND_FACTORY_MAP.get(commandMethodId);
        if (commandFactory == null) {
            throw new UnsupportedCommandException();
        }

        return commandFactory.createCommand(commandPayload);
    }

    public static class CommandMethodId {
        private int commandId, methodId;

        public CommandMethodId(int commandId, int methodId) {
            this.commandId = commandId;
            this.methodId = methodId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CommandMethodId that = (CommandMethodId) o;

            if (commandId != that.commandId) return false;
            if (methodId != that.methodId) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = commandId;
            result = 31 * result + methodId;
            return result;
        }
    }
}
