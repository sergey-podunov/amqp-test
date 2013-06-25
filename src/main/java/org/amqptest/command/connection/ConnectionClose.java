package org.amqptest.command.connection;

import org.amqptest.ConnectionHandler;
import org.amqptest.command.AmqpRequestCommand;
import org.amqptest.command.AmqpResponseCommand;
import org.amqptest.exception.ProtocolException;
import org.amqptest.types.ValueReader;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionClose implements AmqpRequestCommand {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionClose.class);
    private Short replyCode;
    private String replyText;
    private Short replyClassId;
    private Short replyMethodId;

    @Override
    public AmqpResponseCommand execute(ConnectionHandler connectionHandler) throws ProtocolException {
        logger.debug("Connection close with reply code {}, reply text '{}', classId {} and methodId {}",
                replyCode, replyText, replyClassId, replyMethodId);   //todo move to toString method
        connectionHandler.setWork(false);
        return new ConnectionCloseOk();
    }

    @Override
    public void fillArguments(byte[] commandPayload) {
        ValueReader valueReader = new ValueReader(commandPayload);
        replyCode = valueReader.readShort();
        replyText = valueReader.readShortString().getContent();
        replyClassId = valueReader.readShort();
        replyMethodId = valueReader.readShort();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).
                append("replyCode", replyCode).
                append("replyText", replyText).
                append("replyClassId", replyClassId).
                append("replyMethodId", replyMethodId).
                toString();
    }
}
