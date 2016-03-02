package com.example.fiona.customview.homework;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fiona.customview.R;

public class HomeworkNextActivity extends Activity {
    SensorManager sensorManager;
    TextView textView;
    SensorListener listener;
    CompassView compass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework_next);
        textView = (TextView) findViewById(R.id.textView_compass);

        compass = (CompassView) findViewById(R.id.compassView);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        listener = new SensorListener();

    }

    @Override
    protected void onResume() {
        super.onResume();
        /**
         * 注册传感器
         */
        sensorManager.registerListener(listener,sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listener);
    }

    public class SensorListener implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            compass.callback(values[0]);
            textView.setText(String.format("%.1f",values[0]));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
}
