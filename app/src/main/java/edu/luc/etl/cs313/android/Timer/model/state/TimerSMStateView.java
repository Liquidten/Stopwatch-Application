package edu.luc.etl.cs313.android.Timer.model.state;

/**
 * The restricted view states have of their surrounding state machine.
 * This is a client-specific interface in Peter Coad's terminology.
 *
 * @author laufer
 */
interface TimerSMStateView {

    // transitions
    void toRunningState();
    void toStoppedState();
    void toCounterState();
    void toAlarmState();

    // actions
    void actionInit();
    void actionReset();
    void actionStart();
    void actionStop();
    void actionGetInput(int Input);
    void actionUpdateView();
    void actionIncCounter();
    void actionDecCounter();
    void actionResetTick();
    void actionIncTick();
    boolean isTickMax();
    boolean isEmpty();
    void actionStartAlarm();
    void actionStopAlarm();
    void actionBeep();

    // state-dependent UI updates
    void updateUICountTime();

}
