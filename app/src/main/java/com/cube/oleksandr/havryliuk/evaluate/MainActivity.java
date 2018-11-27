package com.cube.oleksandr.havryliuk.evaluate;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String expresion;
    private TextView fieldTextView;
    private TextView prevFieldTextView;

    private TextView currentResultField;
    private ResultFragment resultFragment;
    private Evaluate calc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expresion = "";
        calc = new Evaluate();

        // init result fragment
        resultFragment = new ResultFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.result_fragment, resultFragment)
                .commit();

        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentResultField = resultFragment.getMainResult();
        resultFragment.mark(currentResultField);
    }

    public void initView() {
        TextView btn0 = findViewById(R.id.tv_0);
        btn0.setOnClickListener(this);
        TextView btn1 = findViewById(R.id.tv_1);
        btn1.setOnClickListener(this);
        TextView btn2 = findViewById(R.id.tv_2);
        btn2.setOnClickListener(this);
        TextView btn3 = findViewById(R.id.tv_3);
        btn3.setOnClickListener(this);
        TextView btn4 = findViewById(R.id.tv_4);
        btn4.setOnClickListener(this);
        TextView btn5 = findViewById(R.id.tv_5);
        btn5.setOnClickListener(this);
        TextView btn6 = findViewById(R.id.tv_6);
        btn6.setOnClickListener(this);
        TextView btn7 = findViewById(R.id.tv_7);
        btn7.setOnClickListener(this);
        TextView btn8 = findViewById(R.id.tv_8);
        btn8.setOnClickListener(this);
        TextView btn9 = findViewById(R.id.tv_9);
        btn9.setOnClickListener(this);
        ImageView btnAdd = findViewById(R.id.tv_add);
        btnAdd.setOnClickListener(this);
        ImageView btnSub = findViewById(R.id.tv_sub);
        btnSub.setOnClickListener(this);
        ImageView btnMul = findViewById(R.id.tv_mul);
        btnMul.setOnClickListener(this);
        ImageView btnDiv = findViewById(R.id.tv_div);
        btnDiv.setOnClickListener(this);
        TextView btnProcent = findViewById(R.id.tv_percent);
        btnProcent.setOnClickListener(this);
        TextView btnCancel = findViewById(R.id.tv_cancel);
        btnCancel.setOnClickListener(this);
        ImageView btnBack = findViewById(R.id.tv_back);
        btnBack.setOnClickListener(this);
        TextView btnResult = findViewById(R.id.tv_result_b);
        btnResult.setOnClickListener(this);
        TextView btnDot = findViewById(R.id.tv_dot);
        btnDot.setOnClickListener(this);
        ImageView btnSplit = findViewById(R.id.split_icon);
        btnSplit.setOnClickListener(this);

        fieldTextView = findViewById(R.id.tv_field);
        prevFieldTextView = findViewById(R.id.tv_field_2);
        prevFieldTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                expresion += prevFieldTextView.getText().toString();
                fieldTextView.setText(expresion);
                return false;
            }
        });
    }

    public void setCurrentResultField(TextView currentResultField){
        this.currentResultField = currentResultField;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //TODO: meke one result field, biggest button
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            //TODO; go to default
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.tv_0):
                expresion += "0";
                break;
            case (R.id.tv_1):
                expresion += "1";
                break;
            case (R.id.tv_2):
                expresion += "2";
                break;
            case (R.id.tv_3):
                expresion += "3";
                break;
            case (R.id.tv_4):
                expresion += "4";
                break;
            case (R.id.tv_5):
                expresion += "5";
                break;
            case (R.id.tv_6):
                expresion += "6";
                break;
            case (R.id.tv_7):
                expresion += "7";
                break;
            case (R.id.tv_8):
                expresion += "8";
                break;
            case (R.id.tv_9):
                expresion += "9";
                break;
            case (R.id.tv_dot):
                expresion += ".";
                break;
            case (R.id.tv_sub):
                expresion += " - ";
                break;
            case (R.id.tv_add):
                expresion += " + ";
                break;
            case (R.id.tv_div):
                expresion += " / ";
                break;
            case (R.id.tv_mul):
                expresion += " * ";
                break;
            case (R.id.tv_percent):
                //TODO: percent from (double) expresion
                break;
            case (R.id.split_icon):
                resultFragment.split(currentResultField);
                break;
            case (R.id.tv_cancel):
                expresion = "";
                break;
            case (R.id.tv_back):
                expresion = expresion.substring(0, expresion.length() - 1);
                break;
            case (R.id.tv_main_result):
                resultFragment.mark(resultFragment.getMainResult());
                break;
            case (R.id.tv_result_1):
                resultFragment.mark(resultFragment.getFirstResult());
                break;
            case (R.id.tv_result_2):
                resultFragment.mark(resultFragment.getSecondResult());
                break;
            case (R.id.tv_result_b):
                currentResultField.setText(String.valueOf(calc.calculate(expresion)));
                prevFieldTextView.setText(expresion);
                expresion = "";
                break;
            default:
        }

        fieldTextView.setText(expresion);
    }

    public void addNumber(String  number){
        expresion+= number;
    }

}
