package edu.luc.etl.cs313.android.Timer.model.state;



import edu.luc.etl.cs313.android.Timer.common.TimerUIUpdateListener;
import edu.luc.etl.cs313.android.Timer.model.clock.ClockModel;
import edu.luc.etl.cs313.android.Timer.model.time.TimeModel;


public class DefaultTimerStateMachine implements TimerStateMachine {

    public DefaultTimerStateMachine(final TimeModel timeModel, final ClockModel clockModel) {
        this.timeModel = timeModel;
        this.clockModel = clockModel;
    }

    private final TimeModel timeModel;
    private final ClockModel clockModel;
    private TimerState state;


    public void setState(final TimerState state) {
        this.state = state;
        if (uiUpdateListener != null) uiUpdateListener.updateState(state.getId());

    }

    protected TimerState getState() {
        return this.state;
    }

    private TimerUIUpdateListener uiUpdateListener;

    @Override
    public void setUIUpdateListener(final TimerUIUpdateListener uiUpdateListener) {
        this.uiUpdateListener = uiUpdateListener;
    }
    // forward event
    @Override
    public synchronized void onTick() {state.onTick();}

    @Override
    public synchronized void onStartStop() {state.onStartStop();}


    // current runningTime @param n
    @Override public synchronized  void  onDisplay(Integer n) {
        state.onStartStop();
        actionGetInput(n);
        toRunningState();
        actionDecCounter();
    }

    @Override public void updateUICountTime() {if (uiUpdateListener != null) uiUpdateListener.updateTime(timeModel.getRuntime());}

    // known stated
    private final TimerState STOPPED = new StoppedState(this);
    private final TimerState RUNNING = new RunningState(this);
    private final TimerState COUNTING = new CounterState(this);
    private final TimerState ALARM = new AlarmState(this);

    // transitions
    @Override public void toRunningState() { setState(RUNNING);}
    @Override public void toStoppedState() { setState(STOPPED);}
    @Override public void toCounterState() { setState(COUNTING);}
    @Override public void toAlarmState() { setState(ALARM);}

    //actions
    @Override public void actionInit() { toStoppedState(); actionReset();}
    @Override public void actionReset() { timeModel.resetRuntime(); actionUpdateView();}
    @Override public void actionStart() { clockModel.start();}
    @Override public void actionStop() { clockModel.stop();}

    // Current runningTime @param Input

    @Override public void actionGetInput(int Input) {timeModel.getInput(Input);}
    @Override public void actionUpdateView() {state.updateView();}
    @Override public void actionIncCounter() {timeModel.incRuntime(); actionUpdateView();}
    @Override public void actionDecCounter() {timeModel.decRuntime(); actionUpdateView();}
    @Override public void actionResetTick() {timeModel.resetIdleTime();}
    @Override public void actionIncTick() {timeModel.incIdleTime();}
    @Override public void actionStartAlarm() {uiUpdateListener.playDefaultAlarm();}
    @Override public void actionStopAlarm() { uiUpdateListener.stopDefaultAlarm();}
    @Override public void actionBeep() {uiUpdateListener. playBeep();}
    @Override public boolean isTickMax() { return timeModel.isIdleTimeMax();}
    @Override public boolean isEmpty() { return timeModel.isEmpty();}


}
