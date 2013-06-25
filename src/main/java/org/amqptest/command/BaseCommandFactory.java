package org.amqptest.command;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseCommandFactory<C extends AmqpCommand> implements CommandFactory<C> {
    private static final Logger logger = LoggerFactory.getLogger(BaseCommandFactory.class);

    @Override
    public C createCommand(byte[] commandPayload) {
        C command = createCommand();
        command.fillArguments(commandPayload);
        if (logger.isDebugEnabled()) {
            logger.debug("Command {} created with payload: {}", command.getClass().getSimpleName(), Hex.encodeHexString(commandPayload));
        }
        return command;
    }

    protected abstract C createCommand();
}
