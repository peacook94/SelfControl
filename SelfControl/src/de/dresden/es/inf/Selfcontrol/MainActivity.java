package de.dresden.es.inf.Selfcontrol;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity implements OnTouchListener{
	
	int counter = 0;

	@SuppressLint("ClickableViewAccessibility")
	public boolean onTouch(View v, MotionEvent event)
    {
		
		
		if(event.getActionMasked() == MotionEvent.ACTION_DOWN) return true;
		if(event.getActionMasked() == MotionEvent.ACTION_UP){ 
		
			counter++;
        
			TextView textView = (TextView) findViewById(R.id.counter);
			textView.setText(String.valueOf(counter));
        

			return true;
		}
		 return false;
		//false indicates the event is not consumed
    }
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //den gesamten Bilschirm touchsensitiv machen
        LinearLayout mLinearLayoutMain = (LinearLayout) findViewById(R.id.layout_main);
        mLinearLayoutMain.setOnTouchListener(this);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
