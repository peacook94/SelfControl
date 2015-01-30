package de.dresden.es.inf.Selfcontrol.Database;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.dresden.es.inf.Selfcontrol.Util.App;
import de.dresden.es.inf.Selfcontrol.Util.AppId;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/**
 * Das Datenbank-Interface! Niemals vergessen die Datenbank am Ende (nach dem Aufruf von open())
 * wieder zu schließen mit dem Aufruf von close();
 * 
 * @author User
 *
 */

public class AppsDataSource {
	
	private static DateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
	
	private SQLiteDatabase database;
	private MySQLHelper dbHelper;
	private String[] allColumns = MySQLHelper.COLUMNS;
	
	public AppsDataSource(Context context){
		dbHelper = new MySQLHelper(context);
	}
	
	public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }
	
	public void close() {
	    dbHelper.close();
	  }
	
	/**
	 * Hinzufügen einer App zur Datenbank -> Generierung eines neuen Datenbankeintrags
	 * 
	 * @param app bezeichnet den gefüllten App-Container
	 */
	
	public void addApp(App app){
		Log.d("adding App: ", app.getId().toString());

		ContentValues values = new ContentValues();
		values.put(MySQLHelper.KEY_DATE, app.getStartingTimstamp().toString());
		values.put(MySQLHelper.KEY_ID, app.getId().toString());
        
		database.insert(MySQLHelper.TABLE_APPS, null, values);
	}
	
	/**
	 * Gibt eine Map zurück, in der zu jedem Zeitstempel die entsprechende App eingetragen ist.
	 * 
	 * @param appName einer oder mehrere appBezeichnungen
	 * @return
	 * @throws ParseException wenn das eingetragene Datum von String zu Date konvertiert wird ein Fehler auftritt
	 */
	
	public Map<Date, String> getAppWithDates(AppId appId) throws ParseException{
		Map<Date, String> temp = new HashMap<Date, String>();
	    
	    // 2. build query
	    Cursor cursor = 
	    		database.query(MySQLHelper.TABLE_APPS, allColumns,"AppId=?",  new String[] {appId.toString()}, null, null, null);
	    	    	    
	   //3. search result
	    while(cursor.moveToNext()){	
	    	temp.put(sdf.parse(cursor.getString(0)), cursor.getString(1)); //date und AppId in die temp-Map schreiben, Zeiel für Zeile 	    	
	    }
	    cursor.close();
	    
	    return temp;
	}

}
