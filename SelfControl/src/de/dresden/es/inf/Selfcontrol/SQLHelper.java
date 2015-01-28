package de.dresden.es.inf.Selfcontrol;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;
import java.util.Map;

public class SQLHelper extends SQLiteOpenHelper{
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "Apps";
	
	//Table-name
	private static final String TABLE_APPS = "apps";
	
	//Apps-Table Columns name
	private static final String KEY_ID = "id";
	private static final String KEY_DATE ="date";
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
		"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"date DATE" +
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
	
	public void addApp(Date date,String appName, long runtime){
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
	
//	public Map<Date, String> getAppWithDate(String appName){
//		// 1. get reference to readable DB
//	    SQLiteDatabase db = this.getReadableDatabase();
//	    
//	 // 2. build query
//	    Cursor cursor = 
//	    		db.query(TABLE_APPS, COLUMNS,"label=?", selectionArgs, groupBy, having, orderBy, limit, cancellationSignal)
//	}
	
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
