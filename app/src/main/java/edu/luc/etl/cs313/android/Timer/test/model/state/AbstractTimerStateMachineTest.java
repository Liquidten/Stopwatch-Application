package edu.luc.etl.cs313.android.Timer.test.model.state;

import android.widget.Button;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.luc.etl.cs313.android.Timer.common.TimerUIUpdateListener;
import edu.luc.etl.cs313.android.Timer.model.clock.ClockModel;
import edu.luc.etl.cs313.android.Timer.model.clock.OnTickListener;
import edu.luc.etl.cs313.android.Timer.model.state.TimerStateMachine;
import edu.luc.etl.cs313.android.Timer.model.time.TimeModel;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AbstractTimerStateMachineTest {

    private TimerStateMachine model;

//    Set dependency
    private UnifiedMockDependency dependency;

    @Before
    public void setUp() throws Exception {
        dependency = new UnifiedMockDependency();
    }

    @After
    public void tearDown() {
        dependency = null;
    }


    protected void setModel(final TimerStateMachine model) {
        this.model = model;
        if (model == null)
            return;
        this.model.setUIUpdateListener((TimerUIUpdateListener) dependency);
        this.model.actionInit();
    }

    protected UnifiedMockDependency getDependency() {
        return dependency;
    }

// (Testing Preconditions) Verify scenario we are initially in the stopped state, tell the listener
    @Test
    public void testPreconditions() {
        assertEquals(R.string.STOPPED, dependency.getState());
        assertEquals(0, dependency.getRuntime());
        model. //TODO (CUSTOM) create device rotation state and test for it
    }

// (Testing RunSceanrio) Verify scenario time is 0, press start (expect time 1 & state count)
    @Test
    public void testScenarioRun() { 
        assertTimeEquals(0);
        // directly invoke the button press event handler methods
        model.onStartStop();
        onTickRepeat(1);
        assertTimeEquals(1);
        assertEquals(R.string.COUNT, dependency.getState());
    }

    //    (Test5) Verify scenario time is 0 press start 3 times (Expect time 3 & state count,
// Wait 4 seconds, expect time 2 & state running, Click again, expect time 0 & state stopped.)

    @Test
    public void test5() {
        assertTimeEquals(0);
        // directly invoke the button press event handler methods
        model.onStartStop();
        onTickRepeat(3);
        assertTimeEquals(3);
        model.onTick();
        assertEquals(R.string.COUNT, dependency.getState());
        onTickRepeat(4);
        assertTimeEquals(2);
        assertEquals(R.string.RUNNING, dependency.getState());
        onTickRepeat(1);
        assertTimeEquals(0);
        //TODO (CUSTOM) assert true with device rotation
    }

//        (Test6) Verify scenario time is 0, click 3 times and wait 7 seconds.
// Expect time 0 and state alarm. Click again, Expect displayed value 0 and state stopped

    @Test
    public void test6() {
        assertTimeEquals(0);
        // directly invoke the button press event handler methods
        model.onStartStop();
        onTickRepeat(3);
        assertTimeEquals(3);
        model.onTick();
        assertEquals(R.string.COUNT, dependency.getState());
        onTickRepeat(7);
        assertTimeEquals(0);
        assertEquals(R.string.ALARMING, dependency.getState());
        model.onStartStop();
        assertTimeEquals(0);
        assertEquals(R.string.STOPPED, dependency.getState());
    }

    @Test
    public void test7(){
        assertTimeEquals(0);
        onTickRepeat(99);
        assertEquals(99, dependency.getRuntime());
        assertEquals(R.string.RUNNING, dependency.getState());
        model.onStartStop();
        assertTrue(dependency.getRuntime() == 0);
        assertEquals(R.string.STOPPED, dependency.getState());
    }



    protected void onTickRepeat(final int n) {
        for (int i = 0; i < n; i++)
            model.onTick();
    }

    protected void assertTimeEquals(final int t) {
        Assert.assertEquals(t, dependency.getTime());
    }

}

class UnifiedMockDependency implements TimeModel, ClockModel, TimerUIUpdateListener {

        private int timeValue = -1, stateId = -1;

        private int runningTime = 0, tick = -1;

        private boolean started = false;

        public int getTime() {
            return timeValue;
        }

        public int getState() {
            return stateId;
        }

        public boolean isStarted() {
            return started;
        }

        @Override
        public void updateTime(final int timeValue) {
            this.timeValue = timeValue;
        }

        @Override
        public void updateState(final int stateId) {
            this.stateId = stateId;
        }

    @Override
    public void playDefaultAlarm() {

    }

    @Override
    public void stopDefaultAlarm() {

    }

    @Override
    public void playBeep() {

    }

    @Override
        public void setOnTickListener(OnTickListener listener) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void start() {
            started = true;
        }

        @Override
        public void stop() {
            started = false;
        }

        @Override
        public void resetRuntime() {
            runningTime = 0;
        }

        @Override
        public void incRuntime() {
            runningTime++;
        }

    @Override
    public void decRuntime() {runningTime--; }

    @Override
        public int getRuntime() {
            return runningTime;
        }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void resetIdleTime() {

    }

    @Override
    public void incIdleTime() {

    }

    @Override
    public int getIdleTime() {
        return 0;
    }

    @Override
    public boolean isIdleTimeMax() {
        return false;
    }

    @Override
    public void getInput(int input) {

    }

}

