package de.dresden.es.inf.Selfcontrol;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity implements OnTouchListener{
	
	private final static String fileName = "count.txt";
	
	int counter = 0;

	/**
	 * Diese Methode ist essentiell für jeden OnTouchListener
	 * In dieser Methode wird spezifiziert, was geschehen soll, wenn das MotionEvent
	 * ausgelöst wird. 
	 * Beim Auswerten
	 */
	@SuppressLint("ClickableViewAccessibility")
	public boolean onTouch(View v, MotionEvent event)
    {
		
		//es ist wichtig, dass man auf die Reihenfolge der Abfragen achtet
		//So ist es zum Beispiel nötig, dass man das Event: ACTION_DOWN
		//abfängt, bevor ACTION_UP überhaupt vom Programm behandelt wird.
		//Denn es ist ja logisch, dass das Programm "denkt": " na wenn ich kein
		//ACTION_DOWN bekomme, dann gibt es auch kein ACTION_UP!"
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
        
        
        
        //Den Button zum ACTIVITY-switchen einbinden
        Button switchButton = (Button) findViewById(R.id.mainButton1);
        //Nun brauchen wir einen OnClickListener, damit wir mitbekommen, wann auf den Button gedrückt wurde
        switchButton.setOnClickListener(new View.OnClickListener(){
        	
        	
        	public void onClick(View arg0){
        		//Neues Intent anlegen
        		Intent nextScreen = new Intent(getApplicationContext(), SekActivity.class);
        		
        		TextView mainTextView = (TextView) findViewById(R.id.counter);
        		//Intent mit Daten füllen
        		nextScreen.putExtra("count", mainTextView.getText().toString());
        		
        		startActivity(nextScreen);
        	}
        });
        
        
        
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
