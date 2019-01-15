package edu.luc.etl.cs313.android.Timer.android;


import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import edu.luc.etl.cs313.android.Timer.R;
import edu.luc.etl.cs313.android.Timer.common.TimerUIUpdateListener;
import edu.luc.etl.cs313.android.Timer.model.ConcreteTimerModelFacade;
import edu.luc.etl.cs313.android.Timer.model.TimerModelFacade;

/**
 * A thin adapter component for the stopwatch.
 *
 * @author laufer
 */
public class TimerAdapter extends Activity implements TimerUIUpdateListener {

    private static String TAG = "timer-android-activity";

    /**
     * The state-based dynamic model.
     */
    private TimerModelFacade model;
    private MediaPlayer mediaPlayer;
    private int countDown;
    private EditText EditDisplay;

    protected void setModel(final TimerModelFacade model) {
        this.model = model;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        // inject dependency on view so this adapter receives UI events
        setContentView(R.layout.activity_timer);
        // inject dependency on model into this so model receives UI events
        this.setModel(new ConcreteTimerModelFacade());
        // inject dependency on this into model to register for UI updates
        model.setUIUpdateListener(this);
    }



    @Override
    protected void onStart() {
        super.onStart();
        model.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(TAG, "onPause");  //should work
    }

    @Override
    protected void onRestart(){
        super.onRestart();
    }

    @Override
    protected void onResume(){
        super.onResume();           //should work
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }



    // TODO remaining lifecycle methods

    /**
     * Updates the seconds and minutes in the UI.
     * @param stateId
     */
    public void updateState(final int stateId) {
        // UI adapter responsibility to schedule incoming events on UI thread
        runOnUiThread(() -> {
           TextView stateName = (TextView) findViewById(R.id.stateName);
           stateName.setText(getString(stateId));
           if (stateId == 2131099662) {
               EditDisplay.setEnabled(false);
           }
        });
    }

    /**
     * Updates the state name in the UI.
     * @param time
     */
    public void updateTime(final int time) {
        // UI adapter responsibility to schedule incoming events on UI thread
        runOnUiThread(() -> {
            EditDisplay = (EditText) findViewById(R.id.display;
            final TextView CounterDisplay = (TextView) findViewById(R.id.display);
            CounterDisplay.setText(String.format("%02d",time));
            if (time == 0) {
                EditDisplay.setEnabled(true);
            } else {
                EditDisplay.setEnabled(false);
            }
            countDown=time;
        });
    }

    // forward event listener methods to the model
    public void playDefaultAlarm() {
        final Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        mediaPlayer = new MediaPlayer();
        final Context context = getApplicationContext();
        try {
            mediaPlayer.setDataSource(context, defaultRingtoneUri);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void playBeep()  {
        final Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mediaPlayer = new MediaPlayer();
        final Context context = getApplicationContext();

        try {
            mediaPlayer.setDataSource(context, defaultRingtoneUri);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
            mediaPlayer.start();
        } catch(final IOException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void stopDefaultAlarm(){
        mediaPlayer.stop();
        mediaPlayer.release();
        EditDisplay.setEnabled(true);
    }

    // forward event listener methods to the model
    public void onStartStop(final View view) {
        int input = Integer.valueOf(String.valueOf(EditDisplay.getText()));
        boolean CorrectInput = 0 < input && input <= 99;
        if (CorrectInput && countDown==0) {
            model.onDisplay(Integer.valueOf(String.valueOf(EditDisplay.getText())));
            EditDisplay.setEnabled(false);
        } else {
            model.onStartStop();
        }
    }
}
