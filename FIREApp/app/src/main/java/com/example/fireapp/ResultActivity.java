package com.example.fireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity implements OnChartGestureListener, OnChartValueSelectedListener {

    private LineChart mChart;

    private String x = "";


    private static final String TAG = "MainActivity";

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i(TAG, "onChartGestureStart: X: " + me.getX() + "Y: " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i(TAG, "onChartGestureEnd: " + lastPerformedGesture);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i(TAG, "onChartLongPressed: ");

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i(TAG, "onChartDoubleTapped: ");


    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i(TAG, "onChartSingleTapped: ");

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i(TAG, "onChartFling: veloX: " + velocityX + "veloY: " +velocityY);

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i(TAG, "onChartScale: ScaleX: " + scaleX + "ScaleY: " +scaleY);

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i(TAG, "onChartTranslate: dX: " + dX + "dY: " +dY);


    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i(TAG, "onValueSelected: " + e.toString());
        x = "$"+(int)e.getY()+" at age " + (int)e.getX();

    }

    @Override
    public void onNothingSelected() {
        Log.i(TAG, "onNothingSelected: ");


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        final TextView point = (TextView) findViewById(R.id.point);



        if(getIntent().hasExtra("com.example.fireapp.AGE")){
            TextView tv = (TextView) findViewById(R.id.textView);
            Integer eoy = getIntent().getIntExtra("com.example.fireapp.EOY", 0);
            Integer target = getIntent().getIntExtra("com.example.fireapp.TARGET", 0);;
            Double growth = getIntent().getDoubleExtra("com.example.fireapp.GROWTH", 0);
            Double savings = getIntent().getDoubleExtra("com.example.fireapp.SAVE", 0);;
            Integer age = getIntent().getIntExtra("com.example.fireapp.AGE", 0);
            Integer old = getIntent().getIntExtra("com.example.fireapp.OLD", 0);




            mChart = (LineChart) findViewById(R.id.chart);

            mChart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    point.setText(x);
                    Log.d("HAHA", point.getText().toString());
                }
            });


            mChart.setOnChartGestureListener(ResultActivity.this);
            mChart.setOnChartValueSelectedListener(ResultActivity.this);

            mChart.setDragEnabled(true);
            mChart.setScaleEnabled(false);
//            mChart.setTouchEnabled(true);





            ArrayList<Entry> y = new ArrayList<Entry>();
            y.add(new Entry(age, eoy));


            while(eoy<target && age<125) {
                if(old!=0){
                    if(age==old){
                        old = 0;
                        tv.setText("You will have $" + eoy + " at age " + age+".\n\n");

                    }
                }
                eoy = (int) (eoy * (1 + growth) + savings);
                age++;
                y.add(new Entry(age, eoy));


            }

            LineDataSet set1 = new LineDataSet(y, "Net Worth ($)");

            set1.setFillAlpha(110);
            set1.setValueTextSize(0);
            mChart.getAxisRight().setEnabled(false);
            XAxis xAxis = mChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            Legend l = mChart.getLegend();
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
            l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
            l.setDrawInside(false);
            mChart.getDescription().setText("Age");




            ArrayList<ILineDataSet> datasets = new ArrayList<>();
            datasets.add(set1);

            LineData data = new LineData(datasets);

            mChart.setData(data);

            if(age==125){
                tv.append("You cannot achieve your current goal. Net worth of $" + eoy + " at age " + age);
            }
            else {
                tv.append("You can achieve FIRE with a net worth of $" + eoy + " at age " + age);
            }
        }

    }

}
