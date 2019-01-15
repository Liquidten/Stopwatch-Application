package edu.luc.etl.cs313.android.Timer.model.state;

import edu.luc.etl.cs313.android.Timer.common.TimerUIUpdateSource;
import edu.luc.etl.cs313.android.Timer.common.TimerUIListener;
import edu.luc.etl.cs313.android.Timer.model.clock.OnTickListener;


public interface TimerStateMachine extends TimerUIListener, TimerUIUpdateSource, OnTickListener, TimerSMStateView{ }

