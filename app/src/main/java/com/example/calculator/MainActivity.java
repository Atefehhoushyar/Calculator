package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    boolean nextNumber = false;
    boolean showHistory = false;
    Button buttonAdd,buttonSub,buttonTimes,buttonDivide,buttonEquals,buttonClear,buttonHistory;
    Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button0;
    TextView resultText, historyText;
    calculator calculatorObj;
    ArrayList<String> historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orangered)));

        resultText =(TextView) findViewById(R.id.result_text);
        resultText.setText("");
        historyText =(TextView) findViewById(R.id.history);
        historyText.setMovementMethod(new ScrollingMovementMethod());
        historyText.setText("");
        calculatorObj = new calculator();
        historyList =((MyApp)getApplication()).getHistoryList();


        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        buttonAdd = findViewById(R.id.Plus_but);
        buttonDivide = findViewById(R.id.Div_but);
        buttonTimes = findViewById(R.id.Times_but);
        buttonSub = findViewById(R.id.Min_but);
        buttonClear = findViewById(R.id.buttonC);
        buttonEquals= findViewById(R.id.buttonEqul);
        buttonHistory= findViewById(R.id.buttonAdvance);




        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
        buttonTimes.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonSub.setOnClickListener(this);
        buttonHistory.setOnClickListener(this);
        buttonClear.setOnClickListener(this);
        buttonEquals.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
          String value;
          switch (view.getId()){
              case R.id.buttonAdvance:
                  if(!showHistory){
                      ((Button)view).setText(getString(R.string.noHistory));
                      view.setBackgroundColor(getColor(R.color.darkgray));
                      showHistory= true;
                      displayHistory();
                  }
                  else{
                      ((Button)view).setText(getString(R.string.withHistory));
                      view.setBackgroundColor(getColor(R.color.mediumslateblue));
                      showHistory = false;
                      clearHistory();
                  }
                  clearResult();
                  break;
              case R.id.buttonEqul:
                  if(nextNumber){
                      clearResult();
                      Toast.makeText(this,R.string.alertMessage,Toast.LENGTH_LONG).show();
                  }
                  else{
                      value = ((Button)view).getText().toString();
                      calculatorObj.push(value);
                      resultText.setText(calculatorObj.calcString);
                      getResult();
                      nextNumber= true;
                  }
                  break;
              case R.id.buttonC:
                  clearResult();
                  break;
              default:
                  if(nextNumber) clearResult();
                  value = ((Button)view).getText().toString();
                  calculatorObj.push(value);
                  resultText.setText(calculatorObj.calcString);
                  nextNumber = false;
                  break;
          }
    }

    void getResult(){
        int result = calculatorObj.calculate();
        if(calculatorObj.calcComplete){
            calculatorObj.calcString += result;
            resultText.setText(calculatorObj.calcString);
            if(showHistory){
                historyList.add(calculatorObj.calcString);
                ((MyApp)getApplication()).historyList= historyList;
                displayHistory();
            }
        }
        else{
            Toast.makeText(this,R.string.alertMessage, Toast.LENGTH_LONG).show();
            resultText.setText(calculatorObj.calcString+ "   "+ getString(R.string.invalidInput));
        }
    }
    void clearResult(){
        calculatorObj.calcString ="";
        resultText.setText(calculatorObj.calcString);
        nextNumber = true;
    }

    void displayHistory(){
        String stringList ="";
        for(String s : historyList)
            stringList += s + "\n";
        historyText.setText(stringList);
    }

    void clearHistory(){
        historyText.setText("");

    }
}