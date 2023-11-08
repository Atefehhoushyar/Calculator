package com.example.calculator;
import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;

public class calculator {

    int num1=0 , num2=0, result=0 ;
    char operator;
    boolean oprFound, numFound, numError, opError, calcComplete;
    String calcString ="";

    void push(String text) { calcString += text; }

    int calculate() {
        char[] chars = calcString.toCharArray();
        numFound = false;
        oprFound = false;
        numError = false;
        opError = false;
        calcComplete = false;
        for (int i = 0; i < chars.length && !numError; i += 2) {
            if (!Character.isDigit(chars[i]) || Character.isDigit(chars[i + 1])) {
                numError = true;
            }
        }
        for (int i = 0; i < chars.length && !numError; i++) {
            switch (chars[i]) {
                case '+':
                case '-':
                case '*':
                case '/':
                    oprFound = true;
                    operator = chars[i];
                    break;
                case '=':
                    if (i != 1) calcComplete = true;
                    break;
                default:
                    break;
            }
            if (oprFound && i > 0) {
                if (!numFound) {
                    if (Character.isDigit(chars[i - 1]))
                        num1 = Integer.parseInt(String.valueOf(chars[i - 1]));
                    else
                        numError = true;
                }
                if(Character.isDigit(chars[i+1]))
                    num2 = Integer.parseInt(String.valueOf(chars[i+1]));
                else
                    numError = true;
                    if (!numError) {
                        switch (operator) {
                            case '+':
                                result = num1 + num2;
                                break;
                            case '-':
                                result = num1 - num2;
                                break;
                            case '*':
                                result = num1 * num2;
                                break;
                            case '/':
                                result = num1 / num2;
                                break;
                            default:
                                opError = true;
                                break;
                        }
                        num1 = result;
                        numFound = true;
                        oprFound = false;
                    }
                }
            }
            if (numError || opError)
                return -1;
            else
                return result;
        }

    }