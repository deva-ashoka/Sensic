package com.example.momo.sensic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.momo.sensic.R;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,SensorEventListener {

    public TextView count;
    public TextView mod;
    public TextView cTime;
    public TextView pTime;
    public TextView timeDiff;
    public TextView stepSpeed;
    public SensorManager sensorManager;

    ArrayList mySongs;
    int position;
    Uri u;
    Thread updateSeekBar;
    SeekBar sb;
    Button btPlay, btFF, btFB, btNxt, btPv, btPlayIn;

    boolean activityRunning;

    public float speed;
    float fin, initial, tdiff, sSpeed;
    public SensorEvent event;


    MediaPlayer mySong;
    PlaybackParams params = new PlaybackParams();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count = (TextView) findViewById(R.id.count);
        stepSpeed = (TextView) findViewById(R.id.stepSpeed);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mySong = MediaPlayer.create(this, R.raw.badtameezdil);


    }

    public void PlayMe(View view) {
        mySong.start();
    }

    public void PauseMe(View view)
    {
        mySong.pause();
    }

    public void SpeedUp(View view) {
        params.setSpeed(1.5f);
        mySong.setPlaybackParams(params);
    }

    public void PitchMe(View view) {
        params.setPitch(1.5f);
        mySong.setPlaybackParams(params);
    }

    public void SlowMeDown(View view) {
        params.setSpeed(0.5f);
        mySong.setPlaybackParams(params);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume()

    {
        super.onResume();
        activityRunning = true;

        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {

            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI); // original

        } else {
            Toast.makeText(this, "count sensor not available!", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityRunning = false;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.e("huh", "huh");
        if (activityRunning) {
            fin = initial / 1000000000;

            if (activityRunning) {

                initial = event.timestamp;

                count.setText(String.valueOf(event.values[0])); // normal pedometer

                tdiff = initial / 1000000000 - fin;

                sSpeed = 1 / tdiff;
                speed =sSpeed;
                stepSpeed.setText(String.valueOf(sSpeed));



                if (speed >= 0.0 && speed <= 1.0) {
                    if(mySong.isPlaying())
                    {
                        params.setSpeed(0.55f);
                        params.setPitch(1.0f);
                        mySong.setPlaybackParams(params);
                    }
                    else
                    {
                        Log.e("1st", "1st");
                        params.setSpeed(0.55f);
                        params.setPitch(1.0f);
                        mySong.setPlaybackParams(params);
                        mySong.start();
                    }

                } if (speed > 1.0 && speed <= 1.5 ) {
                    params.setSpeed(0.7f);
                    params.setPitch(1.0f);
                    mySong.setPlaybackParams(params);

                    Log.e("2nd", "2nd");
                } if (speed > 1.5 && speed <= 2.0) {
                    params.setSpeed(0.85f);
                    params.setPitch(1.0f);
                    mySong.setPlaybackParams(params);

                    Log.e("3rd", "3rd");
                } if (speed > 2.0 && speed <= 2.5) {
                    params.setSpeed(1.0f);
                    params.setPitch(1.0f);
                    mySong.setPlaybackParams(params);

                    Log.e("4th", "4th");
                } if (speed > 2.5 && speed <= 3.0) {
                    params.setSpeed(1.15f);
                    params.setPitch(1.0f);
                    mySong.setPlaybackParams(params);

                    Log.e("5th", "5th");
                } if (speed > 3.0 && speed <= 3.5) {
                    params.setSpeed(1.3f);
                    params.setPitch(1.0f);
                    mySong.setPlaybackParams(params);

                    Log.e("6th", "6th");
                }if (speed > 3.5 && speed <= 4.0) {
                    params.setSpeed(1.45f);
                    params.setPitch(1.0f);
                    mySong.setPlaybackParams(params);

                    Log.e("7th", "7th");
                }if (speed > 4.0 && speed <= 4.5) {
                    params.setSpeed(1.6f);
                    params.setPitch(1.0f);
                    mySong.setPlaybackParams(params);

                    Log.e("8th", "8th");
                }if (speed > 4.5) {
                    params.setSpeed(1.6f);
                    params.setPitch(2.0f);
                    mySong.setPlaybackParams(params);

                    Log.e("9th", "9th");
                }
            }
            Log.e("I am out", "I am Out");
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    @Override
    public void onClick (View view)
    {

    }

}




