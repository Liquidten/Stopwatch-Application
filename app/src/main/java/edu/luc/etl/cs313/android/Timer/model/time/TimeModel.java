package edu.luc.etl.cs313.android.Timer.model.time;

/**
 * The passive data model of the stopwatch.
 * It does not emit any events.
 *
 * @author laufer
 */
public interface TimeModel {

    void resetRuntime();
    void incRuntime();
    void decRuntime();
    int getRuntime();
    boolean isFull();
    boolean isEmpty();
    void resetIdleTime();
    void incIdleTime();
    int getIdleTime();
    boolean isIdleTimeMax();
    void getInput(int input);

}
