package de.dresden.es.inf.Selfcontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SekActivity extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sek);
		
		TextView sekCounter = (TextView) findViewById(R.id.sekCounter);
		
		Intent i = getIntent();
		String count = i.getStringExtra("count");
		
		sekCounter.setText(count);
		
	}

}
