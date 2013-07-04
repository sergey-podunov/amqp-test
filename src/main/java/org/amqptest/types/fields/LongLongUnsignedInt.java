package org.amqptest.types.fields;

import org.amqptest.types.AmqpType;
import org.amqptest.utils.IntUtils;

import java.math.BigInteger;

//probably AmqpType<Long> is enough
public class LongLongUnsignedInt implements AmqpType<BigInteger> {

    private BigInteger content;

    public LongLongUnsignedInt(Long rawContent) {
        this.content = IntUtils.convertToUnsignedBigint(rawContent);
    }

    @Override
    public BigInteger getContent() {
        return content;
    }
}
