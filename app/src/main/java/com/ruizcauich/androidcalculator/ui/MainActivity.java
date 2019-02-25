package com.ruizcauich.androidcalculator.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ruizcauich.androidcalculator.R;

public class MainActivity extends AppCompatActivity {
    // These are for the 10 numbers in the calculator
    Button zero, one, two, three, four,
            five, six, seven, eight, nine;
    Button ac;
    TextView display;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Reference to the display (calculator screen)
        display     = findViewById(R.id.display);

        // Reference to the ten numbers  0-9
        zero        = findViewById( R.id.zero);
        one         = findViewById( R.id.one);
        two         = findViewById( R.id.two);
        three       = findViewById( R.id.three);
        four        = findViewById( R.id.four);
        five        = findViewById( R.id.five);
        six         = findViewById( R.id.six);
        seven       = findViewById( R.id.seven);
        eight       = findViewById( R.id.eight);
        nine        = findViewById( R.id.nine);

        // AC button used to clear the calculator display
        ac          = findViewById(R.id.clearDisplay);

        // To listen for a click on the ten numbers
        NumberSelector selector = new NumberSelector();

        // Registry the listener in every number
        zero.setOnClickListener(selector);
        one.setOnClickListener(selector);
        two.setOnClickListener(selector);
        three.setOnClickListener(selector);
        four.setOnClickListener(selector);
        five.setOnClickListener(selector);
        six.setOnClickListener(selector);
        seven.setOnClickListener(selector);
        eight.setOnClickListener(selector);
        nine.setOnClickListener(selector);

        // Registry the listener for the AC button
        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText("0");
            }
        });



    }

    // Nested listener class used for number buttons
    private class NumberSelector implements Button.OnClickListener{

        @Override
        public void onClick(View v) {
            // Every button has its own number, this will be append in the display.
            Button button = (Button) v;
            String number = button.getText().toString();
            // There's only one number and is 0
            if( display.getText().toString().equals("0")){
                // Logcat message, get the name of the Activity through the context of the View
                Log.v(v.getContext().getClass().getName(),
                        "Is the only number displayed 0?  " + display.getText().toString().equals("0")  );
                // Clear the display
                display.setText("");

            }
            // Display the clicked number
            display.append(number);
        }
    }

}
