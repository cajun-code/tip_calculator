package com.cajuncode.tipcalc;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class TipCalculatorActivity extends Activity {
	private static final String FORMAT = "%.02f";
	private static final String BILL_TOTAL = "BILL_TOTAL";
	private static final String CUSTOM_PERCENT = "CUSTOM_PERCENT";
	
	private double billTotal;
	private int customPrecentage;
	
	private EditText tip13Amount;
	private EditText total13Amount;
	private EditText tip15Amount;
	private EditText total15Amount;
	private EditText tip17Amount;
	private EditText total17Amount;
	private EditText tip20Amount;
	private EditText total20Amount;
	
	private EditText tipCustomAmount;
	private EditText totalCustomAmount;
	private SeekBar customPresentageBar;
	private TextView customPresentageLabel;
	private EditText billAmount;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        if(savedInstanceState == null){
        	billTotal = 0.0;
        	customPrecentage = 18;
        }else{
        	billTotal = savedInstanceState.getDouble(BILL_TOTAL);
        	customPrecentage = savedInstanceState.getInt(CUSTOM_PERCENT);
        }
        
        tip13Amount = (EditText) findViewById(R.id.tip_13_amount);
        total13Amount = (EditText)findViewById(R.id.total_13_amount);
        
        tip15Amount = (EditText) findViewById(R.id.tip_15_amount);
        total15Amount = (EditText)findViewById(R.id.total_15_amount);
        
        tip17Amount = (EditText) findViewById(R.id.tip_17_amount);
        total17Amount = (EditText)findViewById(R.id.total_17_amount);
        
        tip20Amount = (EditText) findViewById(R.id.tip_20_amount);
        total20Amount = (EditText)findViewById(R.id.total_20_amount);
        
        tipCustomAmount = (EditText) findViewById(R.id.tip_custom_amount);
        totalCustomAmount = (EditText)findViewById(R.id.total_custom_amount);
        customPresentageBar = (SeekBar)findViewById(R.id.custom_precentage);
        customPresentageBar.setProgress(customPrecentage);
        customPresentageBar.setOnSeekBarChangeListener(customSeekBarListener);
        customPresentageLabel = (TextView)findViewById(R.id.custome_precent_value);
        
        billAmount = (EditText)findViewById(R.id.bill_total);
        billAmount.addTextChangedListener(textWatcher);
        
    }
    private OnSeekBarChangeListener customSeekBarListener =  new OnSeekBarChangeListener(){

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			customPrecentage = progress;
			customPresentageLabel.setText(Integer.toString(customPrecentage) + "%");
			updateAmount(tipCustomAmount, totalCustomAmount, customPrecentage);
			
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
    	
    };
    
    private TextWatcher textWatcher = new TextWatcher(){

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			try{
				billTotal = Double.parseDouble(s.toString());
			}catch(NumberFormatException e){
				billTotal = 0.0;
			}
			updateSetAmounts();
			updateAmount(tipCustomAmount, totalCustomAmount, customPrecentage);
		}
    	
    };
    
    @Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putInt(CUSTOM_PERCENT, customPrecentage);
		outState.putDouble(BILL_TOTAL, billTotal);
	}



	private void updateSetAmounts(){
    	updateAmount(tip13Amount, total13Amount, 13);
    	updateAmount(tip15Amount, total15Amount, 15);
    	updateAmount(tip17Amount, total17Amount, 17);
    	updateAmount(tip20Amount, total20Amount, 20);
    }
    
    private void updateAmount(EditText tip, EditText total, int amount){
    	
    	double t = billTotal * (amount / 100.0);
    	double tot = billTotal + t;
    	tip.setText(String.format(FORMAT, t));
    	total.setText(String.format(FORMAT, tot));
    }
    
    
}