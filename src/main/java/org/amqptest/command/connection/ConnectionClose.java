package org.amqptest.command.connection;

import org.amqptest.ConnectionHandler;
import org.amqptest.command.AmqpRequestCommand;
import org.amqptest.command.AmqpResponseCommand;
import org.amqptest.exception.ProtocolException;
import org.amqptest.types.ShortString;
import org.amqptest.types.ValueReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionClose implements AmqpRequestCommand {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionClose.class);
    private Short replyCode;
    private ShortString replyText;
    private Short replyClassId;
    private Short replyMethodId;

    @Override
    public AmqpResponseCommand execute(ConnectionHandler connectionHandler) throws ProtocolException {
        logger.debug("Connection close with reply code {}, reply text '{}', classId {} and methodId {}",
                replyCode, replyText.getContent(), replyClassId, replyMethodId);   //todo move to toString method
        connectionHandler.setWork(false);
        return new ConnectionCloseOk();
    }

    @Override
    public void fillArguments(byte[] commandPayload) {
        ValueReader valueReader = new ValueReader(commandPayload);
        replyCode = valueReader.readShort();
        replyText = valueReader.readShortString();
        replyClassId = valueReader.readShort();
        replyMethodId = valueReader.readShort();
    }
}
