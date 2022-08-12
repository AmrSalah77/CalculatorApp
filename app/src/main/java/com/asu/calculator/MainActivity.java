package com.asu.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.asu.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    static int operand=0;
    static short operator=0;

    View.OnClickListener num_listener = new View.OnClickListener() {
        @Override
        public void onClick(View displayText) {
            Button tempBtn = (Button) displayText ;
            binding.display.append(tempBtn.getText().toString());
        }
    };
    View.OnClickListener operation_listener = new View.OnClickListener() {
        @Override
        public void onClick(View displayText) {
            Button tempBtn = (Button) displayText ;

            switch(operator){
                case 0:
                    operand+=Integer.parseInt(binding.display.getText().toString());
                    break;
                case 1:
                    operand-=Integer.parseInt(binding.display.getText().toString());
                    break;
                case 2:
                    operand*=Integer.parseInt(binding.display.getText().toString());
                    break;
                case 3:
                    operand/=Integer.parseInt(binding.display.getText().toString());
                    break;
                case 4:
                    binding.display.setText(Integer.toString(operand));
                    break;
            }
            binding.display.setText(Integer.toString(operand));
            if(tempBtn.getText().toString().equals("+")) operator=0;
            if(tempBtn.getText().toString().equals("-")) operator=1;
            if(tempBtn.getText().toString().equals("*")) operator=2;
            if(tempBtn.getText().toString().equals("/")) operator=3;
            if(tempBtn.getText().toString().equals("=")) operator=4;

        }
    };

    View.OnClickListener hidden_operation_listener = new View.OnClickListener() {
        @Override
        public void onClick(View displayText) {
            Button tempBtn = (Button) displayText ;
            String displayString = binding.display.getText().toString();
            if(tempBtn.getText().toString().equals("C")) operator=0;
            if(tempBtn.getText().toString().equals("\u232b")) operator=1;
            switch(operator){
                case 0:
                    binding.display.setText("");
                    break;
                case 1:
                    binding.display.setText(displayString.substring(0,displayString.length()-1));
                    break;
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
        binding.precentageButton.setOnClickListener(operation_listener);
        binding.clearButton.setOnClickListener(hidden_operation_listener);
        binding.backspaceButton.setOnClickListener(hidden_operation_listener);
    }
}