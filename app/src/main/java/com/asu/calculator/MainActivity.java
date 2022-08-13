package com.asu.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.asu.calculator.databinding.ActivityMainBinding;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    boolean lastNumeric = false; //flag to check if last user input was a number
    Expression result ;// solve mathematical expressions library
    double memory = 0;


    //numbers listener
    View.OnClickListener num_listener = new View.OnClickListener() {
        @Override
        public void onClick(View displayText) {
            Button tempBtn = (Button) displayText ;
            String buttonText = tempBtn.getText().toString();
            //display numbers on screen
            binding.display.append(buttonText);
            //set flag to true
            lastNumeric = true;
            }
    };

    //non-numbers listener
    View.OnClickListener operation_listener = new View.OnClickListener() {
        @Override
        public void onClick(View displayText) {
            Button tempBtn = (Button) displayText ;
            String buttonText = tempBtn.getText().toString();
            String displayString = binding.display.getText().toString();
            switch (buttonText){
                //plus operand
                case "\u002b":
                    if(lastNumeric){
                        binding.display.append("+");
                        lastNumeric = false ;
                    }
                    break;

                //minus operand
                case "\u2212":
                    //make sure cant add more than two minus
                    if(!displayString.endsWith("--")) {
                        binding.display.append("-");
                        lastNumeric = false ;
                    }
                    break;

                //multiplication operand
                case "\u00D7":
                    if(lastNumeric){
                        binding.display.append("*");
                        lastNumeric = false ;
                    }
                    break;

                //division operand
                case "\u00F7":
                    if(lastNumeric){
                        binding.display.append("/");
                        lastNumeric = false ;
                    }
                    break;

                //equal operand
                case "\u003D":
                    //check if last input was a number not an operand
                    if(lastNumeric){
                        //prevent app crash because of arithmetic exceptions
                        try {
                            //solve the mathematical expression
                            result = new ExpressionBuilder(displayString).build();
                            //display result on screen
                            binding.display.setText(String.valueOf(result.evaluate()));
                        }catch (ArithmeticException e){}
                    }
                    break;

                //decimal operand
                case "\u002E":
                    //check if last input was a number not an operand
                    if(lastNumeric){
                        binding.display.append(".");
                        lastNumeric = false ;
                    }
                    break;

                //clear operand
                case "C":
                    //clear all text
                    binding.display.setText("");
                    lastNumeric=false;
                    break;

                //backspace operand
                case"\u232b":
                    //checks that the display is not empty
                    if(!displayString.isEmpty())
                        //remove last character
                        binding.display.setText(displayString.substring(0,displayString.length()-1));
                    String newDisplay = binding.display.getText().toString();
                    //allow to write operands again after deleting operand
                    if(!(newDisplay.endsWith("-")||newDisplay.endsWith("+")||
                            newDisplay.endsWith("*")||newDisplay.endsWith("/")))
                        lastNumeric=true;
                    break;

                //negative operand
                case "+/-":
                    //if the number is negative change it to positive
                    if(displayString.startsWith("-"))
                        binding.display.setText(displayString.replaceFirst("-",""));
                    //else change the number to negative
                    else
                        binding.display.setText("-"+displayString);
                    break;

                case "MR":
                    binding.display.append(String.valueOf(memory));
                    break;
                case "MC":
                    memory = 0;
                    break;
                case "M+":
                    if(lastNumeric){
                        //checks if there is division by zero
                        try {
                            //solve the mathematical expression
                            result = new ExpressionBuilder(displayString).build();
                            memory += result.evaluate();
                        }catch (ArithmeticException e){}
                    }
                    break;
                case "M-":
                    if(lastNumeric){
                        //checks if there is division by zero
                        try {
                            //solve the mathematical expression
                            result = new ExpressionBuilder(displayString).build();
                            memory -= result.evaluate();
                        }catch (ArithmeticException e){}
                    }
                    break;

            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.oneButton.setOnClickListener(num_listener);
        binding.twoButton.setOnClickListener(num_listener);
        binding.threeButton.setOnClickListener(num_listener);
        binding.fourButton.setOnClickListener(num_listener);
        binding.fiveButton.setOnClickListener(num_listener);
        binding.sixButton.setOnClickListener(num_listener);
        binding.sevenButton.setOnClickListener(num_listener);
        binding.eightButton.setOnClickListener(num_listener);
        binding.nineButton.setOnClickListener(num_listener);
        binding.zeroButton.setOnClickListener(num_listener);
        binding.doubleZerosButton.setOnClickListener(num_listener);
        binding.addButton.setOnClickListener(operation_listener);
        binding.subtractButton.setOnClickListener(operation_listener);
        binding.multiplyButton.setOnClickListener(operation_listener);
        binding.divisionButton.setOnClickListener(operation_listener);
        binding.negativeButton.setOnClickListener(operation_listener);
        binding.equalButton.setOnClickListener(operation_listener);
        binding.clearButton.setOnClickListener(operation_listener);
        binding.backspaceButton.setOnClickListener(operation_listener);
        binding.decimalButton.setOnClickListener(operation_listener);
        binding.MCbutton.setOnClickListener(operation_listener);
        binding.MRbutton.setOnClickListener(operation_listener);
        binding.mPlusButton.setOnClickListener(operation_listener);
        binding.mMinusButton.setOnClickListener(operation_listener);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save display and last numeric state
        savedInstanceState.putString("displayString" , binding.display.getText().toString());
        savedInstanceState.putBoolean("lastNumeric", lastNumeric);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore display and last numeric state
         String displayString = savedInstanceState.getString("displayString");
         binding.display.setText(displayString);
         boolean l = savedInstanceState.getBoolean("lastNumeric");
         lastNumeric = l;
    }
}