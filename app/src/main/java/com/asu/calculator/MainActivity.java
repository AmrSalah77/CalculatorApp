package com.asu.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.asu.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    TextView numbers;
    View.OnClickListener num_listener = new View.OnClickListener() {
        @Override
        public void onClick(View ob1) {
            Button tempBtn = (Button) ob1;
            numbers.append(tempBtn.getText().toString());
            double x = Double.parseDouble(numbers.getText().toString());
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }
}