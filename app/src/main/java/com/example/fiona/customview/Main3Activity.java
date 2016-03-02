package com.example.fiona.customview;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;

public class Main3Activity extends Activity {

    SensorManager manager;
    BallView ballView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ballView = (BallView) findViewById(R.id.ballView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        handler.post(callback);

        /**
         * 注册
         */
//        manager.registerListener(listener,manager.getDefaultSensor(Sensor.TYPE_GRAVITY),SensorManager.SENSOR_DELAY_FASTEST);

        /*List<Sensor> list=manager.getSensorList(Sensor.TYPE_ALL);
        int i=0;
        for(Sensor s:list){
            textView.append((String.format("\n%2d %s-->%s",++i,s.getName(),s.getVendor())));
        }*/
    }

    @Override
    protected void onPause() {
        super.onPause();
//        manager.unregisterListener(listener);
        handler.removeCallbacks(callback);
    }

    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            int type = event.sensor.getType();
            float[] data = event.values;

            int x = (int) data[0];
            int y = (int) data[1];
            ballView.move(x, y);

/*            textView.setText("");
            for(float f:data){
                textView.append(String.format("%.2f\n",f));
            }*/

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    /**
     * 在主线程创建   数据主线程
     */
    Handler handler = new Handler();

    Runnable callback = new Runnable() {
        @Override
        public void run() {
            ballView.move();
            handler.postDelayed(this, 20);
        }
    };
}
