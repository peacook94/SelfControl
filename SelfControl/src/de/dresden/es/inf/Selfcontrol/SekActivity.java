package de.dresden.es.inf.Selfcontrol;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import de.dresden.es.inf.Selfcontrol.Database.AppsDataSource;
import de.dresden.es.inf.Selfcontrol.Util.AppId;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class SekActivity extends Activity{
	public final static float sec = 1.0f/60;
	
//	public final GraphView gView = (GraphView) findViewById(R.id.sekGraph);
	
	
//	public void onRadioButtonClicked(View view) {
//	    // Is the button now checked?
//	    boolean checked = ((RadioButton) view).isChecked();
//	    
//	    // Check which radio button was clicked
//	    switch(view.getId()) {
//	        case R.id.Vis1radio1_0:
//	            if (checked)
//	            {
//	            	drawGraph(24);
//	            }
//	            break;
//	        case R.id.Vis1radio1_1:
//	            if (checked)
//	            {
//	            	drawGraph(7);
//	            }
//	            break;
//	        case R.id.Vis1radio1_2:
//	            if (checked)
//	            {
//	            	drawGraph(30);
//	            }
//	            break;
//	    }
//	}
	
	public void drawGraph()
{
		GraphView gView = (GraphView) findViewById(R.id.sekGraph);
		
		float t_PHONEUNLOCKED[]=new float[24];
		float t_BROWSER[]=new float[24];
		float t_HANGOUTS[]=new float[24];
		for(int loopcounter=0;loopcounter<24;loopcounter++)
		{
			t_PHONEUNLOCKED[loopcounter]=0;
			t_BROWSER[loopcounter]=0;
			t_HANGOUTS[loopcounter]=0;
		}
		AppsDataSource tmpAppsDataSource = new AppsDataSource(this);
		tmpAppsDataSource.open();
		Map<Date, AppId> myMap = new HashMap<Date, AppId>();
		for(int lc=0;lc<3;lc++)
		{
			AppId tmpAppId = AppId.parseString(lc);
			try {
				Map<Date, AppId> tmpMap=tmpAppsDataSource.getAppWithDates(tmpAppId);
				myMap.putAll(tmpMap);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		tmpAppsDataSource.close();
		Iterator<Date> iter = myMap.keySet().iterator();
		
		while(iter.hasNext())
		{
			
			Date tmpDate =iter.next();
			AppId myAppId = myMap.get(tmpDate);
			int dateHour = tmpDate.getHours();
			if(tmpDate.getDay()==new Date().getDay())
			{
				switch(myAppId)
				{
				case PHONEUNLOCKED:
					t_PHONEUNLOCKED[dateHour]+=5*sec;
					break;
				case BROWSER:
					t_BROWSER[dateHour]+=5*sec;
					break;
				case HANGOUTS:
					t_HANGOUTS[dateHour]+=5*sec;
					break;
				}
			}
		}
		
		DataPoint[] data_PHONEUNLOCKED = new DataPoint[24];
		DataPoint[] data_BROWSER = new DataPoint[24];
		DataPoint[] data_HANGOUTS = new DataPoint[24];
		DataPoint[] data = new DataPoint[24];
		
		for(int i=0; i<24;i++){
			data_PHONEUNLOCKED[i] = new DataPoint(i, t_PHONEUNLOCKED[i]);
			data_BROWSER[i] = new DataPoint(i, t_BROWSER[i]);
			data_HANGOUTS[i] = new DataPoint(i, t_HANGOUTS[i]);
		}
		
		LineGraphSeries<DataPoint> series_PHONEUNLOCKED = new LineGraphSeries<DataPoint>(data_PHONEUNLOCKED);
		LineGraphSeries<DataPoint> series_BROWSER = new LineGraphSeries<DataPoint>(data_PHONEUNLOCKED);
		LineGraphSeries<DataPoint> series_HANGOUTS = new LineGraphSeries<DataPoint>(data_PHONEUNLOCKED);
		
		series_PHONEUNLOCKED.setTitle("Gesamtzeit");
		series_BROWSER.setTitle("Browser");
		series_HANGOUTS.setTitle("HANGOUTS");
		
		gView.getGridLabelRenderer().setHorizontalAxisTitle("min/h");
		gView.getGridLabelRenderer().setVerticalAxisTitle("time");
		gView.addSeries(series_PHONEUNLOCKED);
		gView.getSecondScale().addSeries(series_BROWSER);
		gView.getSecondScale().addSeries(series_HANGOUTS);
	}
	
	public LineGraphSeries<DataPoint> createSeries(Map<Date, AppId> myMap, AppId myAppId, int color){
	
float time[]=new float[24];
		
		for(int loopcounter=0;loopcounter<24;loopcounter++)
		{
			time[loopcounter]=0.f;
		}
		
		Iterator<Date> iter = myMap.keySet().iterator();
		while(iter.hasNext())
		{
			Date tmpDate =iter.next();
			@SuppressWarnings("deprecation")
			int dateHour = tmpDate.getHours();
//			if(tmpDate.getDay() == new Date().getDay())
//			{
				time[dateHour]+=5*sec;
//			}
		}
		GraphView gView = (GraphView) findViewById(R.id.sekGraph);
		
		DataPoint[] data = new DataPoint[24];
		
		for(int i=0; i<24;i++){
			data[i] = new DataPoint(i, time[i]);

		}
		
		
		
		LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(data);
		series.setColor(color);
		
		return series;
		
	}

	public void drawGraph(Map<Date, AppId> myMap, AppId myAppId, int color)
	{
		

		float time[]=new float[24];
		
		for(int loopcounter=0;loopcounter<24;loopcounter++)
		{
			time[loopcounter]=0.f;
		}
		
		Iterator<Date> iter = myMap.keySet().iterator();
		while(iter.hasNext())
		{
			Date tmpDate =iter.next();
			@SuppressWarnings("deprecation")
			int dateHour = tmpDate.getHours();
//			if(tmpDate.getDay() == new Date().getDay())
//			{
				time[dateHour]+=5*sec;
//			}
		}
		GraphView gView = (GraphView) findViewById(R.id.sekGraph);
		
		DataPoint[] data = new DataPoint[24];
		
		for(int i=0; i<24;i++){
			data[i] = new DataPoint(i, time[i]);

		}
		
		LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(data);
		
//		String seriesTitle;
//		switch (myAppId)
//		{
//		case PHONEUNLOCKED:
//			seriesTitle="Gesamtzeit";	
//		break;
//		case BROWSER:
//			seriesTitle="Browser";
//		break;
//		case HANGOUTS:
//			seriesTitle="Hangouts?";
//		break;
//		default:
//			seriesTitle="error";
//			break;
//		}
//		series.setTitle(seriesTitle);
		
//		switch (myAppId)
//		{
//		case PHONEUNLOCKED:
//			gView.addSeries(series);	
//			break;
//		default:
//			gView.getSecondScale().addSeries(series);
//			break;
//		}
		
		series.setColor(color);
		
		gView.addSeries(series);
		
		
		
	}
	
	public void setGraphLayout(){
		
		GraphView gView = (GraphView) findViewById(R.id.sekGraph);
		
		gView.setTitle("Browsernutzung");
		
		gView.getViewport().setScalable(true);
		gView.getViewport().setScrollable(true);
		
		gView.getViewport().setXAxisBoundsManual(true);
		gView.getViewport().setMinX(0);
		gView.getViewport().setMaxX(24);
		
		
//		gView.getGridLabelRenderer().setNumHorizontalLabels(23);
		gView.getGridLabelRenderer().setHorizontalAxisTitle("hours");
		gView.getGridLabelRenderer().setVerticalAxisTitle("min/h");
	}
	
	public void updateGraph(){
		AppsDataSource tmpAppsDataSource = new AppsDataSource(this);
		setGraphLayout();
		
		AppId tmpAppId = AppId.parseString(1);
		AppId tempAppId2 = AppId.parseString(2);
		GraphView gView = (GraphView) findViewById(R.id.sekGraph);
		
		tmpAppsDataSource.open();
//		for(int lc=0;lc<3;lc++)
//		{
			
			
			try {
				Map<Date, AppId> tmpMap=tmpAppsDataSource.getAppWithDates(tmpAppId);			
				gView.addSeries(createSeries(tmpMap,tmpAppId, 0xFF000000));
				
				Map<Date, AppId> tmpMap2=tmpAppsDataSource.getAppWithDates(tempAppId2);			
				gView.addSeries(createSeries(tmpMap2,tempAppId2, 0xBF00BF00));
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
//		}
		tmpAppsDataSource.close();
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
//		RadioButton radioButton[];
//		radioButton = new RadioButton[3];
//		radioButton[0] = (RadioButton) findViewById(R.id.Vis1radio1_0);
//		radioButton[1] = (RadioButton) findViewById(R.id.Vis1radio1_1);
//		radioButton[2] = (RadioButton) findViewById(R.id.Vis1radio1_2);
//		for(int c=0;c<3;c++)
//		{
//			radioButton[c].setOnClickListener(new View.OnClickListener()
//			{
//				@Override
//				public void onClick(View v)
//				{
//					onRadioButtonClicked(v);
//				}
//			});
//		}
		
//		drawGraph();
		
		GraphView gView = (GraphView) findViewById(R.id.sekGraph);
		updateGraph();
		
		
		gView.setOnTouchListener( new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getActionMasked() == MotionEvent.ACTION_DOWN) return true;
				if(event.getActionMasked() == MotionEvent.ACTION_UP){ 
				
					updateGraph();
		        

					return true;
				}
				 return false;
			}
		});
		
	}

}

