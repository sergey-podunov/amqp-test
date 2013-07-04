package org.amqptest.command.connection;

import org.amqptest.ConnectionHandler;
import org.amqptest.command.AmqpRequestCommand;
import org.amqptest.command.AmqpResponseCommand;
import org.amqptest.command.BaseAmqpCommand;
import org.amqptest.exception.ProtocolException;
import org.amqptest.types.ValueReader;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ConnectionClose extends BaseAmqpCommand implements AmqpRequestCommand {
    private Short replyCode;
    private String replyText;
    private Short replyClassId;
    private Short replyMethodId;

    protected ConnectionClose(short channel) {
        super(channel);
    }

    @Override
    public AmqpResponseCommand execute(ConnectionHandler connectionHandler) throws ProtocolException {
        connectionHandler.setWork(false);
        return new ConnectionCloseOk((short) 0);
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
