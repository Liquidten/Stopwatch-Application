package edu.luc.etl.cs313.android.Timer.test.model.state;

import org.junit.After;
import org.junit.Before;

/**
 * Empty reference subclass of the real test so it gets picked up by the Gradle unitTest task.
 */
public class Test extends DefaultTimerStateMachineTest {
    @Before
    public void setUp() throws Exception {
        super.setUp();
        setModel(new DefaultTimerStateMachine(getDependency(), getDependency()));
    }

    @After
    public void tearDown() {
        setModel(null);
        super.tearDown();
    }
}
