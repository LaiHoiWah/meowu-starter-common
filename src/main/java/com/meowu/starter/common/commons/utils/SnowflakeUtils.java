package com.meowu.starter.common.commons.utils;

import com.meowu.starter.common.commons.security.exception.SnowflakeException;
import org.apache.commons.lang3.time.DateUtils;

public class SnowflakeUtils{

    private static final long SEQUENCE_BITS  = 12L;
    private static final long CENTER_ID_BITS = 5L;
    private static final long WORKER_ID_BITS = 5L;

    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);
    private static final long MAX_CENTER_ID = ~(-1L << CENTER_ID_BITS);
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + CENTER_ID_BITS;
    private static final long CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;

    private final long centerId;
    private final long workerId;

    private long lastTimestamp;
    private long sequence;

    public SnowflakeUtils(long workerTotal){
        this(workerTotal / (MAX_WORKER_ID + 1), workerTotal % (MAX_WORKER_ID + 1));
    }

    public SnowflakeUtils(long centerId, long workerId){
        AssertUtils.isTrue(centerId >= 0 && centerId <= MAX_CENTER_ID, "SnowflakeUtils: CENTER_ID must between 0 and " + MAX_CENTER_ID);
        AssertUtils.isTrue(workerId >= 0 && workerId <= MAX_WORKER_ID, "SnowflakeUtils: WORKER_ID must between 0 and " + MAX_WORKER_ID);

        this.centerId = centerId;
        this.workerId = workerId;

        this.lastTimestamp = -1L;
        this.sequence = 0L;
    }

    public synchronized long nextId(){
        long timestamp = timeGen();

        // check current timestamp
        if(timestamp < lastTimestamp){
            throw new SnowflakeException("Clock moved backward");
        }
        // in same milliseconds
        if(lastTimestamp == timestamp){
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if(sequence == 0){
                timestamp = tilNextMillis(lastTimestamp);
            }
        }else{
            sequence = 0L;
        }

        // update lastTimestamp
        lastTimestamp = timestamp;

        // combine each part
        return (timestamp << TIMESTAMP_SHIFT)
                | (centerId << CENTER_ID_SHIFT)
                | (workerId << WORKER_ID_SHIFT)
                | sequence;
    }

    private long tilNextMillis(long lastTimestamp){
        long timestamp = timeGen();
        // get the next timestamp
        while(timestamp <= lastTimestamp){
            timestamp = timeGen();
        }

        return timestamp;
    }

    private long timeGen(){
        return System.currentTimeMillis();
    }
}
