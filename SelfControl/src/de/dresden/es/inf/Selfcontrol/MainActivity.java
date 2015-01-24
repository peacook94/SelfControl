package de.dresden.es.inf.Selfcontrol;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
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
import android.inputmethodservice.*;

public class MainActivity extends Activity implements OnTouchListener{
	
	private final static String fileName = "count.txt";
	
	int counter = 0;
	
	protected ServiceConnection mServerConn = new ServiceConnection() {
		
	    @Override
	    public void onServiceConnected(ComponentName name, IBinder binder) {
	        //Log.d(LOG_TAG, "onServiceConnected");
	    }

	    @Override
	    public void onServiceDisconnected(ComponentName name) {
	        //Log.d(LOG_TAG, "onServiceDisconnected");
	    	
	    }
	};
	
	public void saveLocally() throws IOException{
		
//		for(int i=0; i<=fileList().length; i++){
//			if(fileList()[i].toString() == fileName) break;
//			
//		}
		
		FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
		fos.write(String.valueOf(counter).getBytes());
		fos.close();
		
	}

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
        //switchButton.setOnTouchListener(this);
        
        Button serviceButton = (Button) findViewById(R.id.startServiceButton);
        
        serviceButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//toogles to start the Service or not
				startMyService();
				
			}
		});
        
        
               
    }
	
	public boolean startMyService(){
		Intent i = new Intent(this, SuperService.class);
		
		if(!isMyServiceRunning(SuperService.class)){
			
			this.bindService(i, mServerConn, Context.BIND_AUTO_CREATE);
		    this.startService(i);
		  
		  return true;
		}else{
			
			this.stopService(new Intent(this, SuperService.class));
			this.unbindService(mServerConn);
			Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show();
		return false;
		}
	}
	
	
	
	private boolean isMyServiceRunning(Class<?> serviceClass) {
	    ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	    
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if (serviceClass.getName().equals(service.service.getClassName())) {
	            return true;
	        }
	    }
	    
	    return false;
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
