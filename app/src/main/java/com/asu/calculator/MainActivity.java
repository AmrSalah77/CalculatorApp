package com.asu.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.asu.calculator.databinding.ActivityMainBinding;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    boolean lastNumeric = false; //flag to check if last user input was a number
    Expression result ;// solve mathematical expressions library
    double memory = 0.0; // initialize memory
    String lastFormula; // for undo button


    //numbers listener
    View.OnClickListener num_listener = new View.OnClickListener() {
        @Override
        public void onClick(View displayText) {
            Button tempBtn = (Button) displayText ;
            String buttonText = tempBtn.getText().toString();
            //display numbers on screen
            binding.display.append(buttonText);
            binding.display.setText(binding.display.getText().toString());
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
            switch (buttonText) {
                //add operand
                case "\u002b":
                    if (lastNumeric && !(binding.display.getText().toString().length() == 0)) {
                        binding.display.append("+");
                        binding.display.setText(binding.display.getText().toString());
                        lastNumeric = false;
                    }
                    break;

                //subtract operand
                case "\u2212":
                    //make sure cant add more than two subtract
                    if (!displayString.endsWith("--")) {
                        binding.display.append("-");
                        binding.display.setText(binding.display.getText().toString());
                        lastNumeric = false;
                    }
                    break;

                //multiplication operand
                case "\u00D7":
                    if (lastNumeric && !(binding.display.getText().toString().length() == 0)) {
                        binding.display.append("*");
                        binding.display.setText(binding.display.getText().toString());
                        lastNumeric = false;
                    }
                    break;

                //division operand
                case "\u00F7":
                    if (lastNumeric && !(binding.display.getText().toString().length() == 0)) {
                        binding.display.append("/");
                        binding.display.setText(binding.display.getText().toString());
                        lastNumeric = false;
                    }
                    break;

                //equal operand
                case "\u003D":
                    //check if last input was a number not an operand
                    if (lastNumeric) {
                            //prevent app crash because of arithmetic exceptions
                            try {
                                //solve the mathematical expression
                                result = new ExpressionBuilder(displayString).build();
                                //display result on screen
                                if((result.evaluate()%1) == 0 && String.valueOf(result.evaluate()).length()<=12)
                                    //convert double to integer
                                    binding.display.setText(String.valueOf(Math.round(result.evaluate())));
                                else
                                binding.display.setText(String.valueOf(result.evaluate()));
                                //save last formula
                                lastFormula = displayString;
                                //handle infinity text problem
                                if(binding.display.getText().toString().contains("i")){
                                    binding.display.setText("infinity");
                                }
                            } catch (ArithmeticException e) {
                                Toast.makeText(getApplicationContext(),"Cannot be divided by 0",Toast.LENGTH_SHORT).show();
                            }
                            catch (NumberFormatException e){
                                Toast.makeText(getApplicationContext(),"Format error",Toast.LENGTH_SHORT).show();
                            }
                            catch (IllegalArgumentException e){
                                if(binding.display.getText().toString().contains("i")){
                                    Toast.makeText(getApplicationContext(),"delete infinity word",Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(getApplicationContext(),"Argument error",Toast.LENGTH_SHORT).show();
                            }
                    }
                    break;

                //decimal operand
                case "\u002E":
                    //check if last input was a number not an operand
                    if (lastNumeric) {
                        binding.display.append(".");
                        binding.display.setText(binding.display.getText().toString());
                        lastNumeric = false;
                    }
                    break;

                //clear operand
                case "C":
                    //clear all text
                    binding.display.setText("");
                    lastNumeric = false;
                    break;

                //backspace operand
                case "\u232b":
                    //checks that the display is not empty
                    if (!displayString.isEmpty())
                        //remove last character
                        binding.display.setText(displayString.substring(0, displayString.length() - 1));
                    String newDisplay = binding.display.getText().toString();
                    //allow to write operands again after deleting operand
                    if (!(newDisplay.endsWith("-") || newDisplay.endsWith("+") ||
                            newDisplay.endsWith("*") || newDisplay.endsWith("/"))){
                        lastNumeric = true;
                    }
                    else{
                        lastNumeric = false;
                    }
                    break;

                //negative operand
                case "+/-":
                    //if the number is negative change it to positive
                    if (displayString.startsWith("-"))
                        binding.display.setText(displayString.replaceFirst("-", ""));
                        //else change the number to negative
                    else
                        binding.display.setText("-" + displayString);
                    break;

                case "MR":
                    //recall saved number
                    if((memory%1) == 0 && String.valueOf(memory).length()<=12)
                        //convert double to integer
                        binding.display.append(String.valueOf(Math.round(memory)));
                    else
                        binding.display.append(String.valueOf(memory));
                    //update displayed text
                    binding.display.setText(binding.display.getText().toString());
                    lastNumeric=true;
                    break;
                case "MC":
                    //clear saved number
                    memory = 0.0;
                    break;
                case "M+":
                    if (lastNumeric) {
                        //checks if there is division by zero
                        try {
                            //solve the mathematical expression
                            result = new ExpressionBuilder(displayString).build();
                            memory += result.evaluate();
                        } catch (ArithmeticException e) {
                            Toast.makeText(getApplicationContext(),"Cannot be divided by 0",Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case "M-":
                    if (lastNumeric) {
                        //checks if there is division by zero
                        try {
                            //solve the mathematical expression
                            result = new ExpressionBuilder(displayString).build();
                            memory -= result.evaluate();
                        } catch (ArithmeticException e) {
                            Toast.makeText(getApplicationContext(),"Cannot be divided by 0",Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
            }
        }
    };

    View.OnClickListener lastOperation = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //display last formula calculated
            binding.display.setText(lastFormula);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Objects.requireNonNull(getSupportActionBar()).hide();
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
        binding.undoButton.setOnClickListener(lastOperation);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // save what is on display
        savedInstanceState.putString("displayString" , binding.display.getText().toString());
        //save last numeric flag state
        savedInstanceState.putBoolean("lastNumeric", lastNumeric);
        //save last formula calculated
        savedInstanceState.putString("last formula", lastFormula);
        //save memory number
        savedInstanceState.putDouble("memory", memory);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore what was on display
         String displayString = savedInstanceState.getString("displayString");
         binding.display.setText(displayString);
         //restore last numeric flag state
        lastNumeric = savedInstanceState.getBoolean("lastNumeric");
         //restore last formula calculated
        lastFormula = savedInstanceState.getString("last formula");
        // restore save memory number
        memory = savedInstanceState.getDouble("memory");
    }
}