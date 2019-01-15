package edu.luc.etl.cs313.android.Timer.model.state;

import edu.luc.etl.cs313.android.timer.R;

class StoppedState implements TimerState {

    public StoppedState(final TimerSMStateView sm) {
        this.sm = sm;
    }

    private final TimerSMStateView sm;

    @Override
    public void onStartStop() {
        sm.actionStart();
        sm.actionIncCounter();
        sm.toCounterState();
    }

    @Override
    public void onDisplay(Integer Input) {
        sm.actionStart();
        sm.actionGetInput(Input);
        sm.toRunningState();
    }

    @Override
    public void onTick() {
        throw new UnsupportedOperationException("onTick");
    }

    @Override
    public void updateView() {
        sm.updateUICountTime();
    }

    @Override
    public int getId() {
        return R.string.STOPPED;
    }
}
