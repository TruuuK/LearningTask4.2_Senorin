package com.example.gradecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText prelim, midterm, endterm;
    String strng_pre, strng_mid, strng_end, graderesult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCompute = (Button) findViewById(R.id.button);

        btnCompute.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Calculating....", Toast.LENGTH_SHORT).show();
        ComputeResult();
    }

    public void ComputeResult() {
        prelim = (EditText) findViewById(R.id.prelim);
        midterm = (EditText) findViewById(R.id.midterm);
        endterm = (EditText) findViewById(R.id.endterm);

        if (prelim.getText().toString().isEmpty() || midterm.getText().toString().isEmpty() || endterm.getText().toString().isEmpty()) {
            strng_pre = "0";
            strng_mid = "0";
            strng_end = "0";

        } else {
            strng_pre = prelim.getText().toString();
            strng_mid = midterm.getText().toString();
            strng_end = endterm.getText().toString();
        }
        double pre = Double.parseDouble(strng_pre);
        double mid = Double.parseDouble(strng_mid);
        double end = Double.parseDouble(strng_end);

        pre = (pre * .30);
        mid = (mid * .30);
        end = (end * .40);

        double result = pre + mid + end;

        if (result >= 75) {
            graderesult = "PASSED";
        } else if (result <= 74) {
            graderesult = "FAILED";
        }

        switch (graderesult) {
            case "PASSED":

                String resultgrade = String.valueOf(result);
                Bundle args = new Bundle();
                args.putString("result", graderesult);
                args.putString("result2", resultgrade);

                Intent intent = new Intent(MainActivity.this, ResultPassed.class);
                intent.putExtras(args);
                startActivity(intent);
                break;

            case "FAILED":
                resultgrade = String.valueOf(result);
                Bundle args2 = new Bundle();
                args2.putString("result", graderesult);
                args2.putString("result2", resultgrade);

                Intent intent2 = new Intent(MainActivity.this, ResultFailed.class);
                intent2.putExtras(args2);
                startActivity(intent2);
                break;
        }

    }
}