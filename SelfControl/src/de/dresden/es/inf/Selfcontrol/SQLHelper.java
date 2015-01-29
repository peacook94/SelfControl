package de.dresden.es.inf.Selfcontrol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SQLHelper extends SQLiteOpenHelper{
	
	private static DateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "Apps";
	
	//Table-name
	private static final String TABLE_APPS = "apps";
	
	/**
	 * "id" ist ein automatisch vergebener aufsteigend nummerierte Identifier
	 * "date" ist ein Zeitstempel, der aussagt wann entsprechende App gestartet wurde
	 * "label" Name/Bezeichnugn der App
	 * "runtime" wie lange lief die App
	 * 
	 */
	
	//Apps-Table Columns name
	private static final String KEY_DATE ="date";
	private static final String KEY_ID = "AppId";
	private static final String KEY_LABEL ="label";
	private static final String KEY_RUNTIME ="runtime";
	
	private static final String[] COLUMNS = {KEY_ID, KEY_DATE, KEY_LABEL, KEY_RUNTIME};
	
	public SQLHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create table "apps"
		String CREATE_APPS_TABLE = "CREATE TABLE apps (" +
				"date DATE PRIMARY KEY" +
				"AppId INTEGER, " +
				"label TEXT, " +
				"runtime LONG";
		
		db.execSQL(CREATE_APPS_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS apps");
 
        // create fresh books table
        this.onCreate(db);
		
	}
	
	public void addApp(int AppId, Date date,String appName, long runtime){
		Log.d("adding App: ", appName);
		
		// 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, date.toString());
        values.put(KEY_LABEL, appName);
        values.put(KEY_RUNTIME, String.valueOf(runtime));
        
        //3. Insert
        db.insert(TABLE_APPS, null, values);
        
        //4. close
        db.close();
	}
	
	/**
	 * Gibt eine Map zurück, in der zu jedem Zeitstempel die entsprechende App eingetragen ist.
	 * 
	 * @param appName einer oder mehrere appBezeichnungen
	 * @return
	 * @throws ParseException wenn das eingetragene Datum von String zu Date konvertiert wird ein Fehler auftritt
	 */
	
	public Map<Date, String> getAppWithDate(String appName) throws ParseException{
		Map<Date, String> temp = new HashMap<Date, String>();
		
		// 1. get reference to readable DB
	    SQLiteDatabase db = this.getReadableDatabase();
	    
	 // 2. build query
	    Cursor cursor = 
	    		db.query(TABLE_APPS, COLUMNS,"label=?",  new String[] {appName}, null, null, null);
	    
	   //3. search result
	    while(!cursor.isAfterLast()){	    	
	    	temp.put(sdf.parse(cursor.getString(1)), cursor.getString(2)); //date un label in die temp-Map schreiben, Zeiel für Zeile 	    	
	    	cursor.moveToNext();
	    }
	    
	    return temp;
	}
	
//	public long getRuntime(int id){
//		
//	}
//	
//	public Map<Date, Long> getAllRuntimes(String AppName){
//		
//	}
	
	public void update(String appName){
		
	}
	
	public void delete(String appName){
		
	}

}
