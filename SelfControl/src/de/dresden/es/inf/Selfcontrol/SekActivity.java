package de.dresden.es.inf.Selfcontrol;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class SekActivity extends Activity{
	
	public void onRadioButtonClicked(View view) {
	    // Is the button now checked?
	    boolean checked = ((RadioButton) view).isChecked();
	    
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.Vis1radio1_0:
	            if (checked)
	            {
	            	drawGraph(24);
	            }
	            break;
	        case R.id.Vis1radio1_1:
	            if (checked)
	            {
	            	drawGraph(7);
	            }
	            break;
	        case R.id.Vis1radio1_2:
	            if (checked)
	            {
	            	drawGraph(30);
	            }
	            break;
	    }
	}
	
	public void drawGraph(int length){
		
		GraphView gView = (GraphView) findViewById(R.id.sekGraph);
		
//		BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>( new DataPoint[]{
//				new DataPoint(1, 2),
//				
//				new DataPoint(2, 4),
//		
//				
//		}); 
		
		DataPoint[] data = new DataPoint[length];
		
		for(int i=0; i<length;i++){
			data[i] = new DataPoint(i*2, 2.0d+i);
		}
		
		BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(data);
		
		
		
		gView.addSeries(series);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sek);
		
		TextView sekCounter = (TextView) findViewById(R.id.sekCounter);
		
		Intent i = getIntent();
		String count = i.getStringExtra("count");
		
		sekCounter.setText(count);
		
//		radiobutton:
		RadioButton radioButton[];
		radioButton = new RadioButton[3];
		radioButton[0] = (RadioButton) findViewById(R.id.Vis1radio1_0);
		radioButton[1] = (RadioButton) findViewById(R.id.Vis1radio1_0);
		radioButton[2] = (RadioButton) findViewById(R.id.Vis1radio1_0);
		
		
		
		
		
	}

}

