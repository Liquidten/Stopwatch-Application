package edu.luc.etl.cs313.android.Timer.model.state;

import edu.luc.etl.cs313.android.timer.R;

class RunningState implements TimerState {

    public RunningState(final TimerSMStateView sm) {
        this.sm = sm;
    }

    private final TimerSMStateView sm;

    @Override
    public void onStartStop() {
        sm.actionStop();    //stops clock
        sm.toStoppedState();    //stops current running
        sm.actionResetTick();   //reset tick to 00
        sm.toStoppedState();    //reverts back to stopped state
    }

    @Override
    public void onDisplay(Integer InputCount) {
        onTick();
    }

    @Override
    public void onTick() {
        sm.actionDecCounter();
        if(sm.isEmpty()){
        sm.toAlarmState();}
    }

    @Override
    public void updateView() {
        sm.updateUICountTime();
    }

    @Override
    public int getId() {
        return R.string.RUNNING;
    }
}
