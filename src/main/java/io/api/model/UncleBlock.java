package io.api.model;

import io.api.util.BasicUtils;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public class UncleBlock {

    private long blockNumber;
    private BigInteger blockReward;
    private String blockMiner;
    private String timeStamp;
    private LocalDateTime _timeStamp;
    private List<Uncle> uncles;
    private String uncleInclusionReward;

    //<editor-fold desc="Getters">

    public LocalDateTime getTimeStamp() {
        if(_timeStamp == null && !BasicUtils.isEmpty(timeStamp))
            _timeStamp = LocalDateTime.ofEpochSecond(Long.valueOf(timeStamp), 0, ZoneOffset.UTC);
        return _timeStamp;
    }

    public BigInteger getBlockReward() {
        return blockReward;
    }

    public String getBlockMiner() {
        return blockMiner;
    }

    public List<Uncle> getUncles() {
        return uncles;
    }

    public String getUncleInclusionReward() {
        return uncleInclusionReward;
    }

    public long getBlockNumber() {
        return blockNumber;
    }
    //</editor-fold>
}
