package de.dresden.es.inf.Selfcontrol.Database;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.dresden.es.inf.Selfcontrol.MainActivity;
import de.dresden.es.inf.Selfcontrol.Util.App;
import de.dresden.es.inf.Selfcontrol.Util.AppId;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;


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
		dbHelper = MySQLHelper.getHelper(context);
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
	
	public String addApp(App app){
		Log.d("adding App: ", app.getId().toString());

		ContentValues values = new ContentValues();
		values.put(MySQLHelper.KEY_DATE, sdf.format(app.getStartingTimstamp()));
		values.put(MySQLHelper.KEY_ID, app.getId().toString());
        
		long newRowId = database.insert(MySQLHelper.TABLE_APPS, null, values);
		
		return String.valueOf(newRowId);
	}
	
	/**
	 * Gibt eine Map zurück, in der zu jedem Zeitstempel die entsprechende App eingetragen ist.
	 * 
	 * @param appName einer oder mehrere appBezeichnungen
	 * @return
	 * @throws ParseException wenn das eingetragene Datum von String zu Date konvertiert wird ein Fehler auftritt
	 */
	
	public Map<Date, AppId> getAppWithDates(AppId appId) throws ParseException{
		Map<Date, AppId> temp = new HashMap<Date, AppId>();
	    
	   
		Cursor cursor = database.rawQuery("SELECT * FROM "+MySQLHelper.TABLE_APPS+" WHERE appId=?", new String[] {appId.toString()});
	    	    	    
	   
	    while(cursor.moveToNext()){
	    	temp.put(sdf.parse(cursor.getString(0)), AppId.ident(cursor.getString(1)));
	    }
	   
	    cursor.close();
	    
	    return temp;
	}

}
