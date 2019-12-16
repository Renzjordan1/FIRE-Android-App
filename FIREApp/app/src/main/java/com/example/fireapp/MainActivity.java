package com.example.fireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.TextView;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.w3c.dom.Text;

import java.lang.annotation.Target;
import java.time.Year;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Interact with inputs
        final EditText ageEdit = (EditText) findViewById(R.id.ageEdit);
        final EditText incomeEdit = (EditText) findViewById(R.id.incomeEdit);
        final EditText portfolioEdit = (EditText) findViewById(R.id.portfolioEdit);
        final EditText savingsEdit = (EditText) findViewById(R.id.savingsEdit);

        final EditText stocksEdit = (EditText) findViewById(R.id.stocksEdit);
        final EditText bondsEdit = (EditText) findViewById(R.id.bondsEdit);
        final EditText cashEdit = (EditText) findViewById(R.id.cashEdit);
        final EditText stocksReturnEdit = (EditText) findViewById(R.id.stocksReturnEdit);
        final EditText bondsReturnEdit = (EditText) findViewById(R.id.bondsReturnEdit);
        final EditText inflationEdit = (EditText) findViewById(R.id.inflationEdit);

        RadioGroup goalGroup = (RadioGroup) findViewById(R.id.goalGroup);
        RadioButton worthBtn = (RadioButton) findViewById(R.id.worthBtn);
        RadioButton ageBtn = (RadioButton) findViewById(R.id.ageBtn);
        final TextView worthTxt = (TextView) findViewById(R.id.worthTxt);
        final EditText worthEdit = (EditText) findViewById(R.id.worthEdit);
        final EditText expenseEdit = (EditText) findViewById(R.id.expenseEdit);
        final TableRow expenseRow = (TableRow) findViewById(R.id.expenseRow);

        Button calcBtn = (Button) findViewById(R.id.calcBtn);

        //Auto Calc Expenses
        double currentExpense = Integer.parseInt(incomeEdit.getText().toString())*(1-Integer.parseInt(savingsEdit.getText().toString())/100.0);
        expenseEdit.setText(Double.toString(currentExpense));


        //Set input min max class
        class InputFilterMinMax implements InputFilter {

            private int min, max;

            public InputFilterMinMax(int min, int max) {
                this.min = min;
                this.max = max;
            }

            public InputFilterMinMax(String min, String max) {
                this.min = Integer.parseInt(min);
                this.max = Integer.parseInt(max);
            }

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                try {
                    String stringInput = dest.toString() + source.toString();
                    int value;
                    if (stringInput.length() == 1 && stringInput.charAt(0) == '-') {
                        value = -1;
                    } else {
                        value = Integer.parseInt(stringInput);
                    }
                    if (isInRange(min, max, value))
                        return null;
                } catch (NumberFormatException nfe) {
                }
                return "";
            }

            private boolean isInRange(int min, int max, int value) {
                return max > min ? value >= min && value <= max : value >= max && value <= min;
            }
        }

        //Setting min/max for inputs
        ageEdit.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "80")});
        savingsEdit.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "100")});
        stocksEdit.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "100")});
        bondsEdit.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "100")});
        cashEdit.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "100")});
        stocksReturnEdit.setFilters(new InputFilter[]{ new InputFilterMinMax("-100", "100")});
        bondsReturnEdit.setFilters(new InputFilter[]{ new InputFilterMinMax("-100", "100")});
        inflationEdit.setFilters(new InputFilter[]{ new InputFilterMinMax("-100", "100")});

        //Change input box with radio button
        worthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                worthTxt.setText("Net Worth ($)");
                worthEdit.setText("");
                worthEdit.setFilters(new InputFilter[] {});
                expenseRow.setVisibility(View.GONE);
            }
        });

        ageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                worthTxt.setText("Age");
                worthEdit.setText("");
                worthEdit.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "80")});
                expenseRow.setVisibility(View.VISIBLE);

            }
        });

        calcBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                int age = Integer.parseInt(ageEdit.getText().toString());
                int income = Integer.parseInt(incomeEdit.getText().toString());
                int portfolio = Integer.parseInt(portfolioEdit.getText().toString());
                int savings = Integer.parseInt(savingsEdit.getText().toString());

                int stocks = Integer.parseInt(stocksEdit.getText().toString());
                int bonds = Integer.parseInt(bondsEdit.getText().toString());
                int cash = Integer.parseInt(cashEdit.getText().toString());

                int stockReturn = Integer.parseInt(stocksReturnEdit.getText().toString());
                int bondReturn = Integer.parseInt(bondsReturnEdit.getText().toString());
                int inflation = Integer.parseInt(inflationEdit.getText().toString());

                int worth = Integer.parseInt(worthEdit.getText().toString());
                int expense = (int)Double.parseDouble(expenseEdit.getText().toString());

                int targetAmount = worth;

                if(expenseRow.getVisibility() == View.VISIBLE) {
                    targetAmount = expense * 25;
                }


                double growthRate = ((stocks*stockReturn) + (bonds*bondReturn) + (cash*inflation))/100.0/100.0;
                Log.d("HAHA", Double.toString(growthRate));

                int annualSavings = income*(savings/100);

                double eoySavings = portfolio;

//                int years = 0;


//                while(eoySavings<targetAmount) {
//                    eoySavings = eoySavings * (1 + growthRate) + annualSavings;
//                    Log.d("HAHA", Double.toString(eoySavings));
//
//                    years++;
//
//                }
//
//
//                String expectedAge = Integer.toString(age+years);
//                String target = String.format("%.2f", eoySavings);
//



                Intent startIntent = new Intent(getApplicationContext(), ResultActivity.class);
                startIntent.putExtra("com.example.fireapp.EOY",eoySavings);
                startIntent.putExtra("com.example.fireapp.TARGET",targetAmount);
                startIntent.putExtra("com.example.fireapp.GROWTH",growthRate);
                startIntent.putExtra("com.example.fireapp.SAVE",annualSavings);
                startIntent.putExtra("com.example.fireapp.AGE",age);




                startActivity(startIntent);
            }
        });









    }
}
