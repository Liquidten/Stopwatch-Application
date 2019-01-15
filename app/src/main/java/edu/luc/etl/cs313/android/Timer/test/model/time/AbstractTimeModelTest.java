package edu.luc.etl.cs313.android.Timer.test.model.time;

import android.view.View;

import static edu.luc.etl.cs313.android.Timer.common.Constants.TIME_INC_PER_CLICK;
import static edu.luc.etl.cs313.android.Timer.common.Constants.MAX_IDLE_TIME;
import static edu.luc.etl.cs313.android.Timer.common.Constants.SEC_PER_TICK;
import static edu.luc.etl.cs313.android.Timer.common.Constants.MAX_COUNT;
import static edu.luc.etl.cs313.android.Timer.common.Constants.MIN_COUNT;
import android.widget.Button;
import android.widget.TextView;
import edu.luc.etl.cs313.android.Timer.R;
import edu.luc.etl.cs313.android.Timer.android.TimerAdapter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.luc.etl.cs313.android.Timer.model.time.TimeModel;

/**
 * Testcase superclass for the time model abstraction.
 * This is a simple unit test of an object without dependencies.
 *
 * @author laufer
 * @see http://xunitpatterns.com/Testcase%20Superclass.html
 */
public abstract class AbstractTimeModelTest {

    private TimeModel model;

    /**
     * Setter for dependency injection. Usually invoked by concrete testcase
     * subclass.
     *
     * @param model
     */
    protected void setModel(final TimeModel model) {
        this.model = model;
    }

    /**
     * Verifies that runtime and laptime are initially 0 or less.
     */
    @Test
    public void testPreconditions() {
        assertEquals(0, model.getRuntime());
        assertTrue(model.getIdleTime() <= 0); //was model.getlaptime()
    }

    /**
     * Verifies that runtime is incremented correctly.
     */
    @Test
    public void testIncrementRuntimeOne() {
        final int rt = model.getRuntime();
        final int it = model.getIdleTime();
        model.incRuntime();
        assertEquals(1,model.getRuntime());
        //assertEquals((rt + SEC_PER_TICK) % MAX_COUNT, model.getRuntime());
        assertEquals(it, 0);
    }

    /**
     * Verifies that runtime turns over correctly.
     */
    @Test
    public void testIncrementRuntimeMany() {
        final int rt = model.getRuntime();
        final int it = model.getIdleTime();
        for (int i = 0; i < 3; i ++) {
            model.incRuntime();
        }
        assertEquals(3, rt);
        assertEquals(0, it);
    }

    /**
     * Verifies that laptime works correctly.
     */
    @Test
    /*
    public void testLaptime() {
        final int rt = model.getRuntime();
        final int lt = model.getLaptime();
        for (int i = 0; i < 5; i ++) {
            model.incRuntime();
        }
        assertEquals(rt + 5, model.getRuntime());
        assertEquals(lt, model.getLaptime());
        model.setLaptime();
        assertEquals(rt + 5, model.getLaptime());
        for (int i = 0; i < 5; i ++) {
            model.incRuntime();
        }
        assertEquals(rt + 10, model.getRuntime());
        assertEquals(rt + 5, model.getLaptime());
        */

    /*
    public void testTick() throws Throwable{
        getActivity().runOnUiThread(() -> {
            assertEquals(0, getDisplayedValue());
            assertTrue(getStartStopButton().performClick());
        });
        Thread.sleep(5500); // <-- do not run this in the UI thread!
        runUiThreadTasks();
        getActivity().runOnUiThread(() -> {
            assertEquals(5, getDisplayedValue());
            assertTrue(getResetLapButton().performClick());
    }
    /*

}
