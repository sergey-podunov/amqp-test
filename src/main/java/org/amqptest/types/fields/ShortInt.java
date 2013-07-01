package org.amqptest.types.fields;

import org.amqptest.types.AmqpType;

public class ShortInt implements AmqpType<Short> {
    private Short content;

    public ShortInt(Short content) {
        this.content = content;
    }

    @Override
    public Short getContent() {
        return content;
    }
}
