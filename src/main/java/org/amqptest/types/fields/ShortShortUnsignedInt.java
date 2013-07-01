package org.amqptest.types.fields;

import org.amqptest.types.AmqpType;
import org.amqptest.utils.IntUtils;

public class ShortShortUnsignedInt implements AmqpType<Integer> {
    private Integer content;

    public ShortShortUnsignedInt(Byte byteContent) {
        this.content = IntUtils.convertToUnsignedInt(byteContent);
    }

    @Override
    public Integer getContent() {
        return content;
    }
}
