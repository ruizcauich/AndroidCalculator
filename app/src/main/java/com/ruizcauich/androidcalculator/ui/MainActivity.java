package com.ruizcauich.androidcalculator.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ruizcauich.androidcalculator.R;
import com.ruizcauich.androidcalculator.logic.Division;
import com.ruizcauich.androidcalculator.logic.Multiplication;
import com.ruizcauich.androidcalculator.logic.Operation;
import com.ruizcauich.androidcalculator.logic.Subtraction;
import com.ruizcauich.androidcalculator.logic.Sum;

public class MainActivity extends AppCompatActivity {
    // These are for the 10 numbers in the calculator
    Button zero, one, two, three, four,
            five, six, seven, eight, nine,
            point;
    Button ac;
    TextView display;

    // These are for for operation buttons
    Button division, multiply, subtract, plus;
    Button equal; // for the equal button

    // To perform a operation
    Operation operation;

    // Stores the first number used to perform the operation
    private double currentNumber = 0;

    // Used to clear the display after an operation button is pressed
    private boolean mostClearDisplay = false;

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
        point       = findViewById( R.id.point); // and the point

        // AC button used to clear the calculator display
        ac          = findViewById(R.id.clearDisplay);

        // References to operation buttons
        division    = findViewById( R.id.division );
        multiply    = findViewById( R.id.multiply );
        subtract    = findViewById( R.id.subtract );
        plus        = findViewById( R.id.plus );
        equal       = findViewById( R.id.equal ); // and the equal one

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
        point.setOnClickListener( selector ); // is like the numbers

        // Registry the listener for the AC button
        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText("0");
            }
        });

        // To listen a click on the operation buttons
        OperationToPerformListener opListener = new OperationToPerformListener();

        // Registry the listener in every operation button
        division.setOnClickListener( opListener );
        multiply.setOnClickListener( opListener );
        subtract.setOnClickListener( opListener );
        plus.setOnClickListener( opListener );

        // Create and registry a listener for the equal button
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the second number used to perform the operation
                double secondNumber  =
                        Double.parseDouble( display.getText().toString() );

                // Perform the operation reusing secondNumber to store the result
                secondNumber = operation.perform( currentNumber, secondNumber);

                // Displays the result
                display.setText( String.valueOf( secondNumber ) );

                // To clear the display
                mostClearDisplay = true;
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
            if( display.getText().toString().equals("0") || mostClearDisplay ){
                // Logcat message, get the name of the Activity through the context of the View
                Log.v(v.getContext().getClass().getName(),
                        "Is the only number displayed 0?  " + display.getText().toString().equals("0")  );

                // Clear the display
                display.setText("");

                mostClearDisplay  = false;

            }
            // Display the clicked number
            display.append(number);
        }
    }

    // Nested listener class used for operations buttons
    private class OperationToPerformListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            // Every operation button has a specific operator char
            Button operationButton = (Button) v;
            String operator = operationButton.getText().toString();

            // According to the operator, create the operation to perform
            if( operator.equals("/") ){
                operation = new Division();

            } else if( operator.equals("X") ){
                operation = new Multiplication();

            } else if( operator.equals("-") ){
                operation = new Subtraction();

            } else if( operator.equals("+") ){
                operation = new Sum();
            }

            // Holds the current number
            currentNumber = Double.parseDouble( display.getText().toString() );

            // To clear the display if a number button is pressed after an operation one
            mostClearDisplay = true;

        }
    }

}
