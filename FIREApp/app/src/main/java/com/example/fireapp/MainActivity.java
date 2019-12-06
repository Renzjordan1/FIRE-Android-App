package com.example.fireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText ageEdit = (EditText) findViewById(R.id.ageEdit);
        final EditText incomeEdit = (EditText) findViewById(R.id.incomeEdit);
        EditText portfolioEdit = (EditText) findViewById(R.id.portfolioEdit);
        EditText savingsEdit = (EditText) findViewById(R.id.savingsEdit);

        EditText stocksEdit = (EditText) findViewById(R.id.stocksEdit);
        EditText bondsEdit = (EditText) findViewById(R.id.bondsEdit);
        EditText cashEdit = (EditText) findViewById(R.id.cashEdit);
        EditText stocksReturnEdit = (EditText) findViewById(R.id.stocksReturnEdit);
        EditText bondsReturnEdit = (EditText) findViewById(R.id.bondsReturnEdit);
        EditText inflationEdit = (EditText) findViewById(R.id.inflationEdit);

        RadioGroup goalGroup = (RadioGroup) findViewById(R.id.goalGroup);
        RadioButton worthBtn = (RadioButton) findViewById(R.id.worthBtn);
        RadioButton ageBtn = (RadioButton) findViewById(R.id.ageBtn);
        final TextView worthTxt = (TextView) findViewById(R.id.worthTxt);
        EditText worthEdit = (EditText) findViewById(R.id.worthEdit);
        EditText expenseEdit = (EditText) findViewById(R.id.expenseEdit);

        Button calcBtn = (Button) findViewById(R.id.calcBtn);

//        incomeEdit.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                incomeEdit.setText("$" + incomeEdit.getText().toString());
//            }
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {}
//        });


        worthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                worthTxt.setText("Net Worth");
            }
        });

        ageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                worthTxt.setText("Age");
            }
        });

//        calcBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });





    }
}
