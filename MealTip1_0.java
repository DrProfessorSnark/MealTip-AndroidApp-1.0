package com.example.larsenapps.mealtip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

public class TipScreen extends AppCompatActivity {


    Button calculate;
    String receipt, tipLine, endBillS, receiptTipS;
        //string receipt is beforeTax value. tipLine is tipPercent value
    BigDecimal billWtax, billTip, bill, tax, endBill, tipAsPercent, hundred, receiptTip;
    EditText billInput;
    EditText percentInput;
    TextView tipTotal;
    TextView receiptTotal;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_screen);

        billInput = (EditText) findViewById(R.id.inputTwo);
        percentInput = (EditText) findViewById(R.id.inputOne);
        tipTotal = (TextView) findViewById(R.id.tipTotal);
        receiptTotal = (TextView) findViewById(R.id.receiptTotal);

        calculate = (Button) findViewById(R.id.calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                receipt = billInput.getText().toString();//extract user input for pre-tax bill and convert to string.


                bill = new BigDecimal(receipt); //this is the bill input by user (pre-tax)
                tipLine = percentInput.getText().toString(); //extract user input for tip percentage and convert to string.

                //tip

                billTip = new BigDecimal(tipLine); //at this point billTip is the whole number that was added to the line.

                hundred = new BigDecimal(100);

                tipAsPercent = billTip.divide(hundred);//this is now the tip as a decimal.

                receiptTip = tipAsPercent.multiply(bill); //this is working correctly as of 7/11/18
                //bill calculation

                tax = new BigDecimal("1.0775");

                billWtax = bill.multiply(tax); //billWtax now needs to be added to tip. (in test, this should be 37 or so.)

                endBill = billWtax.add(receiptTip);

                //conversion and printing to textView.

                endBillS = endBill.toString(); //this is printed to textView receiptTotal**

                receiptTipS = receiptTip.toString(); //this is printed to textView tipTotal**

                tipTotal.setText(receiptTipS);

                receiptTotal.setText(endBillS);

                }
            });
        }
    }
