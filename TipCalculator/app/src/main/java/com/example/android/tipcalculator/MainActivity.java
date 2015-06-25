package com.example.android.tipcalculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.support.v7.app.ActionBar;
import android.widget.SeekBar;

import java.util.Scanner;


public class MainActivity extends ActionBarActivity {

    EditText billInput;//input for bill amount in dollars
    SeekBar tipInput;//input for tip amount in %

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hide action bar upon launch
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



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
     *Get user input of tip (in %) and attempt
     *parsing to an int.
     *@return tip percent as a double
     */
    private static double getTipPercent(EditText input){
        String tipText = input.getText().toString();
        double tip = 0;

        try{
            tip = Double.parseDouble(tipText);
        }catch(NumberFormatException e){
            System.exit(-1);
        }

        return tip/100;
    }

    /**
     *Get user input of bill and attempt
     *parsing to an int. The user will be prompted
     *to specify again if the input was invalid.
     *@return bill
     */
    private static int getBill(Scanner sc){
        int bill = 0;

        while(true){
            System.out.print("Please specify bill total: $");
            String billStr = sc.next();
            try{
                bill = Integer.parseInt(billStr);
                break;
            }catch(NumberFormatException e){
                System.out.print("Please specify a valid bill amount: $");
            }

        }

        return bill;
    }

    /**
     *Calculate total by multiplying bill
     *with (1+tip).
     */
    private static void displayPrices(int bill, double tip){
        System.out.println("--------------------------------------------");
        System.out.println("Total: $" + (bill * (1+tip)));
    }
}
