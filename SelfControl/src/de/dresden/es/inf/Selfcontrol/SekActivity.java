package de.dresden.es.inf.Selfcontrol;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SekActivity extends Activity{
	
	public void drawGraph(){
		
		GraphView gView = (GraphView) findViewById(R.id.sekGraph);
		
//		BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>( new DataPoint[]{
//				new DataPoint(1, 2),
//				
//				new DataPoint(2, 4),
//		
//				
//		}); 
		
		DataPoint[] data = new DataPoint[20];
		
		for(int i=0; i<=19;i++){
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
		
		drawGraph();
		
	}

}

