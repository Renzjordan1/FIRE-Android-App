package com.example.fireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ResultActivity extends AppCompatActivity {

    private LineGraphSeries<DataPoint> series1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);



        if(getIntent().hasExtra("com.example.fireapp.AGE")){
            TextView tv = (TextView) findViewById(R.id.textView);
            Double eoy = getIntent().getDoubleExtra("com.example.fireapp.EOY", 0);
            Integer target = getIntent().getIntExtra("com.example.fireapp.TARGET", 0);;
            Double growth = getIntent().getDoubleExtra("com.example.fireapp.GROWTH", 0);
            Integer savings = getIntent().getIntExtra("com.example.fireapp.SAVE", 0);;
            Integer age = getIntent().getIntExtra("com.example.fireapp.AGE", 0);

            GraphView graph = (GraphView) findViewById(R.id.graph);
            series1 = new LineGraphSeries<>();
            series1.appendData(new DataPoint(age,eoy), true, 250);


            while(eoy<target) {
                eoy = eoy * (1 + growth) + savings;
                age++;
                series1.appendData(new DataPoint(age,eoy), true, 250);

            }
            String hit = String.format("%.2f", eoy);

            graph.addSeries(series1);
            tv.setText("You can achieve FIRE with a net worth of $" + hit + " at age " + age);
        }

    }

}
