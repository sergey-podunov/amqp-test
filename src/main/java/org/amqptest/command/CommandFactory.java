package org.amqptest.command;

import org.amqptest.frame.MethodFrame;

public interface CommandFactory<C extends AmqpRequestCommand> {
    C createCommand(MethodFrame frame);
}
