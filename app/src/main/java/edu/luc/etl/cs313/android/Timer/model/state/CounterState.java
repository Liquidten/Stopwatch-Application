package edu.luc.etl.cs313.android.Timer.model.state;

import edu.luc.etl.cs313.android.timer.R;

class CounterState implements TimerState {

    public CounterState(final TimerSMStateView sm) {
        this.sm = sm;
    }

    private final TimerSMStateView sm;

    @Override
    public void onStartStop() {
        sm.actionIncCounter();
        sm.actionResetTick();

    }

    @Override
    public void onDisplay(Integer InputCount) {
    }

    @Override
    public void onTick() {
        sm.actionIncTick();
        if (sm.isTickMax()) {
            sm.actionBeep();
            sm.toRunningState();
        }   //isTickMax = 3 (Timer starts to run)

    }

    @Override
    public void updateView() {
        sm.updateUICountTime();
    }

    @Override
    public int getId() {
        return R.string.COUNT;
    }
}
