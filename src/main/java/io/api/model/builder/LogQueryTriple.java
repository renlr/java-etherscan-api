package io.api.model.builder;

import io.api.error.LogQueryException;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public class LogQueryTriple extends BaseLogQuery implements IQueryBuilder {

    private final String address;
    private final long startBlock, endBlock;
    private final String topic0, topic1, topic2;

    private LogOp topic0_1_opr, topic1_2_opr, topic0_2_opr;

    LogQueryTriple(String address, long startBlock, long endBlock,
                   String topic0, String topic1, String topic2) {
        this.address = address;
        this.startBlock = startBlock;
        this.endBlock = endBlock;
        this.topic0 = topic0;
        this.topic1 = topic1;
        this.topic2 = topic2;
    }

    public LogQueryTriple setOpTopic0_1(LogOp topic0_1_opr) {
        this.topic0_1_opr = topic0_1_opr;
        return this;
    }

    public LogQueryTriple setOpTopic0_2(LogOp topic0_2_opr) {
        this.topic0_2_opr = topic0_2_opr;
        return this;
    }

    public LogQueryTriple setOpTopic1_2(LogOp topic1_2_opr) {
        this.topic1_2_opr = topic1_2_opr;
        return this;
    }

    @Override
    public LogQueryFinal build() throws LogQueryException {
        if(topic0_1_opr == null)
            throw new LogQueryException("topic1_2_opr can not be null.");
        if(topic0_2_opr == null)
            throw new LogQueryException("topic0_2_opr can not be null.");
        if(topic1_2_opr == null)
            throw new LogQueryException("topic0_1_opr can not be null.");
        return null;
    }
}
