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


public class MainActivity extends AppCompatActivity {

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
        ed1 = (EditText) findViewById(R.id.edit_text1);
        ed2 = (EditText) findViewById(R.id.edit_text2);
        ed1.addTextChangedListener(new myTextWatcher()); //member class option
        ed2.addTextChangedListener(new myTextWatcher()); //member class option
        tv1 = (TextView) findViewById(R.id.textView_result);
        addButton = (Button) findViewById(R.id.button_add);
        minusButton = (Button) findViewById(R.id.button_minus);
        multipicationButton = (Button) findViewById(R.id.button_multipication);
        divideButton = (Button) findViewById(R.id.button_divide);


        LinearLayout parentLayout = (LinearLayout) findViewById(R.id.dynamic_content);
        View child = getLayoutInflater().inflate(R.layout.slide_bar_layout, parentLayout, false);
        /*RelativeLayout.LayoutParams rlp =new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        rlp.addRule(RelativeLayout.BELOW, R.id.button_clear);*/
        //rlp.setMargins(0,dp2px(100),0,0);
        parentLayout.addView(child);

        sb = (SeekBar) findViewById(R.id.seekBar);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int zeroAmount=seekBar.getProgress();
                if(!tv1.getText().toString().isEmpty())
                    tv1.setText((String.format("%."  + zeroAmount + "f",result)));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        /*Anonymous class */
        findViewById(R.id.button_clear).setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0) {
                ed1.getText().clear();
                ed2.getText().clear();
                tv1.setText(null);
            }
        });



        addButton.setEnabled(false);
        minusButton.setEnabled(false);
        multipicationButton.setEnabled(false);
        divideButton.setEnabled(false);
    }
    @Override
    protected void onStart(){
        Log.i("*** onStart ***","*** onStart ***");
        super.onStart();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outstate) {
        Log.i("*** onSaveInstanceState ***","*** onSaveInstanceState ***");
        outstate.putString("result",tv1.getText().toString());
        super.onSaveInstanceState(outstate);
    }

    @Override
    protected void onResume(){
        Log.i("*** onResume ***","*** onResume ***");
        super.onResume();
    }

    @Override
    protected void onPause(){
        Log.i("*** onPause ***","*** onPause ***");
        super.onPause();
    }

    @Override
    protected void onStop(){
        Log.i("*** onStop ***","*** onStop ***");
        super.onStop();
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        Log.i("*** onRestoreInstanceState ***","*** onRestoreInstanceState ***");
        super.onRestoreInstanceState(savedInstanceState);
        tv1.setText(savedInstanceState.getString(("result")));
    }

    @Override
    protected void onDestroy(){
        Log.i("*** onDestroy ***","*** onDestroy ***");
        super.onDestroy();
    }


    public void pressedAdd(View v) {
        if (ed1.getText().toString().length() == 0){
            Toast toast=Toast.makeText(getApplicationContext(),"First operand is empty",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (ed2.getText().toString().length() == 0){
            Toast toast=Toast.makeText(getApplicationContext(),"Second operand is empty",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        float num1 = Float.parseFloat(ed1.getText().toString());
        float num2 = Float.parseFloat(ed2.getText().toString());
        float sum = num1 + num2;
        result = sum;
        tv1.setText(String.valueOf(sum));
    }

    public void pressMinus(View v){
        if (ed1.getText().toString().length() == 0){
            Toast toast=Toast.makeText(getApplicationContext(),"First operand is empty",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (ed2.getText().toString().length() == 0){
            Toast toast=Toast.makeText(getApplicationContext(),"Second operand is empty",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        float num1 = Float.parseFloat(ed1.getText().toString());
        float num2 = Float.parseFloat(ed2.getText().toString());
        float sum = num1 - num2;
        result = sum;
            tv1.setText(String.valueOf(sum));
    }

    public void pressDivide(View v){
        if (ed1.getText().toString().length() == 0){
            Toast toast=Toast.makeText(getApplicationContext(),"First operand is empty",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (ed2.getText().toString().length() == 0){
            Toast toast=Toast.makeText(getApplicationContext(),"Second operand is empty",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        float num1 = Float.parseFloat(ed1.getText().toString());
        float num2 = Float.parseFloat(ed2.getText().toString());
        if(num2 == 0){
            Toast toast=Toast.makeText(getApplicationContext(),"Divide by zero",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        else{
            float sum = num1 / num2;
            result = sum;
            tv1.setText(String.valueOf(sum));
        }
    }
    public void pressMultipication(View v){
        if (ed1.getText().toString().length() == 0){
            Toast toast=Toast.makeText(getApplicationContext(),"First operand is empty",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (ed2.getText().toString().length() == 0){
            Toast toast=Toast.makeText(getApplicationContext(),"Second operand is empty",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        float num1 = Float.parseFloat(ed1.getText().toString());
        float num2 = Float.parseFloat(ed2.getText().toString());
        float sum = num1 * num2;
        result = sum;
        tv1.setText(String.valueOf(sum));
    }

    public class myTextWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

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
                addButton.setEnabled(false);
                minusButton.setEnabled(false);
                multipicationButton.setEnabled(false);
                divideButton.setEnabled(false);
            }



        }
    }
}