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
            binding.display.append(tempBtn.getText().toString());
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
    }
}