package com.asu.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn6,btn8,btn9,btn10,btn11,btn12,btn13,btn14,btn15,btn16,btn17,btn18,btn19,btn20,btn21,btn22,btn23;
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
        setContentView(R.layout.activity_main);
        btn6 = findViewById(R.id.button6);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);
        btn10 = findViewById(R.id.button10);
        btn11 = findViewById(R.id.button11);
        btn12 = findViewById(R.id.button12);
        btn13 = findViewById(R.id.button13);
        btn14 = findViewById(R.id.button14);
        btn15 = findViewById(R.id.button15);
        btn16 = findViewById(R.id.button16);
        btn17 = findViewById(R.id.button17);
        btn18 = findViewById(R.id.button18);
        btn19 = findViewById(R.id.button19);
        btn20 = findViewById(R.id.button20);
        btn21 = findViewById(R.id.button21);
        btn22 = findViewById(R.id.button22);
        btn23 = findViewById(R.id.button23);
        numbers = findViewById((R.id.textView));
        btn9.setOnClickListener(num_listener);
        btn10.setOnClickListener(num_listener);
        btn11.setOnClickListener(num_listener);
        btn13.setOnClickListener(num_listener);
        btn14.setOnClickListener(num_listener);
        btn15.setOnClickListener(num_listener);
        btn17.setOnClickListener(num_listener);
        btn18.setOnClickListener(num_listener);
        btn19.setOnClickListener(num_listener);
        btn21.setOnClickListener(num_listener);
        btn22.setOnClickListener(num_listener);
    }
}