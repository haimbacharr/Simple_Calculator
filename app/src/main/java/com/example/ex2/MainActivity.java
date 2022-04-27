package com.example.ex2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    private EditText ed1;
    private EditText ed2;
    private TextView tv1;
    private Button addButton;
    private Button minusButton;
    private Button multipicationButton;
    private Button divideButton;
    private Object View;
    private SeekBar sb;
    private float result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("*** onCreate ***","*** onCreate ***");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_calculator);
        ed1 = findViewById(R.id.edit_text1);
        ed2 =  findViewById(R.id.edit_text2);
        ed1.addTextChangedListener(new myTextWatcher()); //member class option
        ed2.addTextChangedListener(new myTextWatcher()); //member class option
        tv1 = findViewById(R.id.textView_result);
        addButton = findViewById(R.id.button_add);
        minusButton = findViewById(R.id.button_minus);
        multipicationButton = findViewById(R.id.button_multipication);
        divideButton = findViewById(R.id.button_divide);


        sb = findViewById(R.id.seekBar);
        sb.setOnSeekBarChangeListener(this);
        /* This is dynamiclly adding layout */
        /*LinearLayout parentLayout = (LinearLayout) findViewById(R.id.dynamic_content);
        View child = getLayoutInflater().inflate(R.layout.slide_bar_layout, parentLayout, false);
        parentLayout.addView(child);*/



        /*Anonymous class */
        findViewById(R.id.button_clear).setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0) {
                ed1.getText().clear();
                ed2.getText().clear();
                tv1.setText(null);
                setAllButtonState(false);
                sb.setEnabled(false);
                sb.setProgress(0);
            }
        });

        setAllButtonState(false);
        sb.setEnabled(false);


    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outstate) {

        outstate.putString("result",tv1.getText().toString());
        super.onSaveInstanceState(outstate);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){

        super.onRestoreInstanceState(savedInstanceState);
        tv1.setText(savedInstanceState.getString(("result")));
    }

    public void pressedAdd(View v) {
        if(checkOperaandValid() == 1)
            return;
        float num1 = Float.parseFloat(ed1.getText().toString());
        float num2 = Float.parseFloat(ed2.getText().toString());
        result = num1 + num2;
        resetSeekBarAndUpdateResultTextView();
    }

    public void pressMinus(View v){
        if(checkOperaandValid() == 1)
            return;
        float num1 = Float.parseFloat(ed1.getText().toString());
        float num2 = Float.parseFloat(ed2.getText().toString());
        result = num1 - num2;
        resetSeekBarAndUpdateResultTextView();
    }

    public void pressDivide(View v){
        if(checkOperaandValid() == 1)
            return;
        float num1 = Float.parseFloat(ed1.getText().toString());
        float num2 = Float.parseFloat(ed2.getText().toString());
        if(num2 == 0){
            Toast toast=Toast.makeText(getApplicationContext(),"Divide by zero",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        else{
            result = num1 / num2;
            resetSeekBarAndUpdateResultTextView();
        }
    }
    public void pressMultipication(View v){
        if(checkOperaandValid() == 1)
            return;
        float num1 = Float.parseFloat(ed1.getText().toString());
        float num2 = Float.parseFloat(ed2.getText().toString());
        result = num1 * num2;
        resetSeekBarAndUpdateResultTextView();
    }

    /* implemented methods of SeekBar.OnSeekBarChangeListener */
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        int zeroAmount=sb.getProgress();
        if(!tv1.getText().toString().isEmpty()){
            tv1.setText((String.format("%."  + zeroAmount + "f",result)));
        }
        else sb.setEnabled(false);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    /* Mmember class option */
    public class myTextWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // nothing to do.
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // nothing to do.
        }

        @Override
        public void afterTextChanged(Editable editable) {

            if (ed1.getText().toString().length() != 0 && ed2.getText().toString().length() != 0){
                addButton.setEnabled(true);
                minusButton.setEnabled(true);
                multipicationButton.setEnabled(true);
                float num2 = Float.parseFloat(ed2.getText().toString());
                if(num2 != 0){
                    divideButton.setEnabled(true);
                }
                else  divideButton.setEnabled(false);
            }
            else {
                setAllButtonState(false);
            }



        }
    }

/* This function enable/disable the buttons */
    private void setAllButtonState(boolean state) {
        addButton.setEnabled(state);
        minusButton.setEnabled(state);
        multipicationButton.setEnabled(state);
        divideButton.setEnabled(state);
    }
    /* This function will reset the seek bar and update the textview. */
    private void resetSeekBarAndUpdateResultTextView(){
        tv1.setText(String.valueOf(result));
        sb.setEnabled(true);
        sb.setProgress(0);
        int zeroAmount=sb.getProgress();
        tv1.setText((String.format("%."  + zeroAmount + "f",result)));
    }


    /* Check if edit text of opernd 1 and 2 are not empty, if one of them empty will return 1, if none return 0.*/
    private int checkOperaandValid(){
        if (ed1.getText().toString().length() == 0){
            Toast toast=Toast.makeText(getApplicationContext(),"First operand is empty",Toast.LENGTH_SHORT);
            toast.show();
            return 1; // first edit text is empty.
        }
        if (ed2.getText().toString().length() == 0){
            Toast toast=Toast.makeText(getApplicationContext(),"Second operand is empty",Toast.LENGTH_SHORT);
            toast.show();
            return 1; // second edit text is empty.
        }

        return 0; // edit text are not empty.
    }
}