package com.example.fiona.customview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fiona.customview.homework.HomeworkNextActivity;
import com.example.fiona.customview.homework.RefreshView;
import com.example.fiona.customview.homework.TimerView;

public class HomeworkActivity extends Activity {
    RefreshView refreshView;
    TimerView timerView;
    TextView textView;
    Button button;
    Button button_re;

    int millions = 0;
    int minute = 0;
    int second = 0;

    boolean isStart = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);
        refreshView = (RefreshView) findViewById(R.id.refreshView);
        timerView = (TimerView) findViewById(R.id.timerView);
        textView = (TextView) findViewById(R.id.textView_time);
        button = (Button) findViewById(R.id.button_start);
        button_re = (Button) findViewById(R.id.button_resume);
    }

    Handler handler = new Handler();

    Runnable callback = new Runnable() {
        @Override
        public void run() {
            refreshView.refresh();
            handler.postDelayed(callback, 20);
        }
    };

    Runnable timer = new Runnable() {
        @Override
        public void run() {
            millions += 2;
            if (millions != 0 && millions % 100 == 0) {
                second++;
                if (second != 0 && second % 60 == 0) {
                    minute++;
                }
            }
            textView.setText(String.format("%02d：%02d：%02d", minute % 60, second % 60, millions % 100));
            timerView.callback(millions);

            if (millions >= 100 * 60) {
                millions = 0;
            }
            handler.postDelayed(timer, 2);
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        handler.post(callback);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(timer);
        handler.removeCallbacks(callback);
    }

    public void startTimer(View view) {
        if (isStart) {
            handler.post(timer);
            button.setText("stop");
            isStart = !isStart;
            button_re.setEnabled(false);
            button_re.setVisibility(View.INVISIBLE);
        } else {
            handler.removeCallbacks(timer);
            button.setText("start");
            isStart = !isStart;
            button_re.setEnabled(true);
            button_re.setVisibility(View.VISIBLE);
        }
    }

    public void resume(View view) {
        millions = 0;
        second = 0;
        minute = 0;
        timerView.callback(millions);
        textView.setText(String.format("%02d：%02d：%02d", minute % 60, second % 60, millions % 100));
    }

    public void doNext(View view) {
        Intent intent = new Intent(this, HomeworkNextActivity.class);
        startActivity(intent);
    }
}
