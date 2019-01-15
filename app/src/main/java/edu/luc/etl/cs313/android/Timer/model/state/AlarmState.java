package edu.luc.etl.cs313.android.Timer.model.state;
import edu.luc.etl.cs313.android.timer.R;

public class AlarmState implements TimerState {

    /** Dynamic state machine interface for Timer  @param sm*/

    public AlarmState(final TimerSMStateView sm){
        this.sm = sm;
    }

    private final TimerSMStateView sm;

    @Override
    public void onStartStop() {
        sm.actionStopAlarm();
        sm.actionReset();
        sm.actionResetTick();
        sm.toStoppedState();
    }

    /** Current Running time @param InputCount */

    @Override
    public void onDisplay(Integer InputCount) {throw new UnsupportedOperationException("onAlarm");}

    @Override
    public void onTick(){
        sm.actionStartAlarm();
        sm.actionStop();         //this is stop clock
    }

    @Override
    public void updateView(){
        sm.updateUICountTime();
    }

    @Override
    public int getId() {
        return R.string.ALARMING;
    }
}
