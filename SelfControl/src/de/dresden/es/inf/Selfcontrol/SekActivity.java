package de.dresden.es.inf.Selfcontrol;

import java.text.ParseException;
import java.util.Date;
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
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class SekActivity extends Activity{
	public final static float sec = 1.0f/60;
	
	
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
//	
	public void drawGraph(Map<Date, AppId> myMap, AppId myAppId){
		//die zeit im Minuten!
//		float t_PHONEUNLOCKED[]=new float[24];
//		float t_BROWSER[]=new float[24];
//		float t_HANGOUTS[]=new float[24];
		float time[]=new float[24];
		for(int loopcounter=0;loopcounter<24;loopcounter++)
		{
			time[loopcounter]=0;
//			t_PHONEUNLOCKED[loopcounter]=0;
//			t_BROWSER[loopcounter]=0;
//			t_HANGOUTS[loopcounter]=0;
		}
		Iterator<Date> iter = myMap.keySet().iterator();
		while(iter.hasNext())
		{
			Date tmpDate =iter.next();
			int dateHour = tmpDate.getHours();
			time[dateHour]+=5*sec;
//			AppId id = myMap.get(tmpDate);
//			switch(id){
//			case PHONEUNLOCKED:
//				t_PHONEUNLOCKED[dateHour]+=5*sec;
//				break;
//			case BROWSER:
//				t_BROWSER[dateHour]+=5*sec;
//				break;
//			case HANGOUTS:
//				t_HANGOUTS[dateHour]+=5*sec;
//				break;
//			}
		}
		GraphView gView = (GraphView) findViewById(R.id.sekGraph);
		
//		BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>( new DataPoint[]{
//				new DataPoint(1, 2),
//				
//				new DataPoint(2, 4),
//		
//				
//		}); 
		
//		DataPoint[] data_PHONEUNLOCKED = new DataPoint[24];
//		DataPoint[] data_BROWSER = new DataPoint[24];
//		DataPoint[] data_HANGOUTS = new DataPoint[24];
		DataPoint[] data = new DataPoint[24];
		
		for(int i=0; i<24;i++){
			data[i] = new DataPoint(i, time[i]);
//			data_PHONEUNLOCKED[i] = new DataPoint(i, t_PHONEUNLOCKED[i]);
//			data_BROWSER[i] = new DataPoint(i, t_BROWSER[i]);
//			data_HANGOUTS[i] = new DataPoint(i, t_HANGOUTS[i]);
		}
		
		LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(data);
//		LineGraphSeries<DataPoint> series_PHONEUNLOCKED = new LineGraphSeries<DataPoint>(data_PHONEUNLOCKED);
//		LineGraphSeries<DataPoint> series_BROWSER = new LineGraphSeries<DataPoint>(data_PHONEUNLOCKED);
//		LineGraphSeries<DataPoint> series_HANGOUTS = new LineGraphSeries<DataPoint>(data_PHONEUNLOCKED);
		
		series.setTitle(myAppId.toString(myAppId));
		
		gView.addSeries(series);
//		gView.addSeries(series_PHONEUNLOCKED);
//		gView.addSeries(series_BROWSER);
//		gView.addSeries(series_HANGOUTS);
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
		
		
		/*---------->drawGraph<--------------*/
		//was muss ich hier einfügen?!?!??!
		//drawGraph(5);
		AppsDataSource tmpAppsDataSource = new AppsDataSource(this);
		tmpAppsDataSource.open();
		for(int lc=0;lc<3;lc++)
		{
			AppId tmpAppId = AppId.parseString(lc);
			try {
				Map<Date, AppId> tmpMap=tmpAppsDataSource.getAppWithDates(tmpAppId);
				drawGraph(tmpMap,tmpAppId);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		tmpAppsDataSource.close();
		
	}

}

