package org.amqptest.command;

import org.amqptest.frame.MethodFrame;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseCommandFactory<C extends AmqpRequestCommand> implements CommandFactory<C> {
    private static final Logger logger = LoggerFactory.getLogger(BaseCommandFactory.class);

    @Override
    public C createCommand(MethodFrame frame) {
        C command = createCommand(frame.getChannel());
        command.fillArguments(frame.getCommandPayload());
        if (logger.isDebugEnabled()) {
            logger.debug("Command {} created in channel {} with payload: {}", command.getClass().getSimpleName(),
                    frame.getChannel(), Hex.encodeHexString(frame.getCommandPayload()));
        }
        return command;
    }

    protected abstract C createCommand(short channel);
}
