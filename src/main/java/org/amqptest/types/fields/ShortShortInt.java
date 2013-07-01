package org.amqptest.types.fields;

import org.amqptest.types.AmqpType;

public class ShortShortInt implements AmqpType<Byte> {
    private Byte content;

    public ShortShortInt(Byte content) {
        this.content = content;
    }

    @Override
    public Byte getContent() {
        return content;
    }
}
