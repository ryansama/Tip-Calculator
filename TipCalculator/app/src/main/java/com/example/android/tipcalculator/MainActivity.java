package com.example.android.tipcalculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

import java.util.Scanner;


public class MainActivity extends ActionBarActivity {

    EditText billInput;//input for bill amount in dollars
    SeekBar tipInput;//input for tip amount in %
    TextView tipText;//TextView showing desired tip amount
    TextView tipAmount;
    TextView totalAmount;//TextView showing total

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeVariables();

        //Initialize the TextView showing current tip with '0%'.
        tipText.setText("Tip: " + tipInput.getProgress() + "%");

        //set up SeekBar
        tipInput.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                //Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tipText.setText("Tip: " + progress + "%");
                //Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });

        //set up NumberPicker
        NumberPicker np = (NumberPicker) findViewById(R.id.splitInput);
        np.setMaxValue(30);
        np.setMinValue(0);


    }

    //initialize variables by finding ID
    private void initializeVariables() {
        tipInput = (SeekBar) findViewById(R.id.tipInput);
        tipText = (TextView) findViewById(R.id.tipText);
        billInput = (EditText) findViewById(R.id.billInput);
        tipAmount = (TextView) findViewById(R.id.tipAmount);
        totalAmount = (TextView) findViewById(R.id.totalAmount);
    }

    public void calculate(View view) {
        double bill = getBill(billInput);
        double tipPercent = getTipPercent(tipInput);
        double tip = bill * tipPercent;
        double total = bill * (1 + tipPercent);

        tipAmount.setText("$" + tip);
        totalAmount.setText("$" + total);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Get user input of tip (in %) and attempt
     * parsing to an int.
     *
     * @return tip percent as a double
     */
    private static double getTipPercent(SeekBar input) {
        int tip = input.getProgress();
        double tipDouble = tip / 100;
        return tipDouble;
    }

    /**
     * Get user input of bill and attempt
     * parsing to a double.
     *
     * @return bill
     */
    private double getBill(EditText input) {
        double bill = 0;
        String billStr = input.getText().toString();

        try {
            bill = Double.parseDouble(billStr);
        } catch (NumberFormatException e) {
            System.exit(-1);
        }

        return bill;
    }
}
