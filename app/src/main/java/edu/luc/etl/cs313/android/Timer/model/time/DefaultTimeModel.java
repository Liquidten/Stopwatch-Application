package edu.luc.etl.cs313.android.Timer.model.time;

import static edu.luc.etl.cs313.android.Timer.common.Constants.*;

/**
 * An implementation of the stopwatch data model.
 */
public class DefaultTimeModel implements TimeModel {

    private int runningTime = 0;
    private int tick;

    @Override
    public void incIdleTime() {
        tick += SEC_PER_TICK;
    }

    @Override
    public int getIdleTime() {
        return tick;
    }

    @Override
    public void resetIdleTime() {
        tick = 0;
    }

    @Override
    public boolean isIdleTimeMax() {
        return tick >= MAX_IDLE_TIME;
    }

    @Override
    public void incRuntime() {
        if(!isFull()) { runningTime += TIME_INC_PER_CLICK;}
    }

    @Override
    public void decRuntime() {
        if(!isEmpty()) { runningTime += SEC_PER_TICK;}
    }

    @Override
    public int getRuntime() {return  runningTime;}

    @Override
    public void resetRuntime(){
        runningTime = 0;
    }

    @Override
    public boolean isFull() {return runningTime >= MAX_COUNT;}

    @Override
    public boolean isEmpty() {
        return runningTime <= MIN_COUNT;
    }

    // runningTime @param input

    @Override
    public void getInput(int input) {runningTime = input;}


}