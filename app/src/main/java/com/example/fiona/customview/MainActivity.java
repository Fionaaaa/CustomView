package com.example.fiona.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    PieView pieView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pieView= (PieView) findViewById(R.id.view);
    }

    public void draw(View view) {
        String input= String.valueOf(editText.getText());
        if(!TextUtils.isEmpty(input)){
            int n=Integer.parseInt(input);
            float[] data=new float[n];
            for(int i=0;i<n;i++){
                data[i]= (float)(Math.random()*10);
            }
            pieView.setData(data);
        }
    }
}
