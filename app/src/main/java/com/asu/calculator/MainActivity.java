package com.asu.calculator;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.asu.calculator.databinding.ActivityMainBinding;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    boolean lastNumeric = false;
    Expression result ;



    View.OnClickListener num_listener = new View.OnClickListener() {
        @Override
        public void onClick(View displayText) {
            Button tempBtn = (Button) displayText ;
            String buttonText = tempBtn.getText().toString();
            binding.display.append(buttonText);
            lastNumeric = true;
            }
    };

    View.OnClickListener operation_listener = new View.OnClickListener() {
        @Override
        public void onClick(View displayText) {
            Button tempBtn = (Button) displayText ;
            String buttonText = tempBtn.getText().toString();
            String displayString = binding.display.getText().toString();
            switch (buttonText){
                case "\u002b":
                    if(lastNumeric){
                        binding.display.append("+");
                        lastNumeric = false ;
                    }
                    break;
                case "\u2212":
                    if(lastNumeric){
                        binding.display.append("-");
                        lastNumeric = false ;
                    }
                    break;
                case "\u00D7":
                    if(lastNumeric){
                        binding.display.append("*");
                        lastNumeric = false ;
                    }
                    break;
                case "\u00F7":
                    if(lastNumeric){
                        binding.display.append("/");
                        lastNumeric = false ;
                    }
                    break;
                case "\u003D":
                    if(lastNumeric){
                        if(!displayString.contains("/0")){
                            result = new ExpressionBuilder(displayString).build();
                            binding.display.setText(String.valueOf(result.evaluate()));
                        }
                    }
                    break;
                case "\u002E":
                    if(lastNumeric){
                        binding.display.append(".");
                        lastNumeric = false ;
                    }
                    break;
                case "C":
                    binding.display.setText("");
                    lastNumeric=false;
                    break;
                case"\u232b":
                    if(!displayString.isEmpty())
                        binding.display.setText(displayString.substring(0,displayString.length()-1));
                    String newDisplay = binding.display.getText().toString();
                    System.out.println(newDisplay);
                    if(!(newDisplay.endsWith("-")||newDisplay.endsWith("+")||
                            newDisplay.endsWith("*")||newDisplay.endsWith("/")))
                        lastNumeric=true;
                    break;
                case "+/-":
                    if(displayString.startsWith("-"))
                        binding.display.setText(displayString.replaceFirst("-",""));
                    else
                        binding.display.setText("-"+displayString);
            }
        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }
}