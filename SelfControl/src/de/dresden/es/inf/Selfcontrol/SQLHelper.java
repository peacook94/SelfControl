package de.dresden.es.inf.Selfcontrol;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;
import java.util.Map;

public class SQLHelper extends SQLiteOpenHelper{
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "Apps";
	
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
	
	public void addApps(String appName, long runtime){
		
	}
	
//	public long getRuntime(int id){
//		
//	}
//	
//	public Map<Date, Long> getAllRuntimes(String AppName){
//		
//	}

}
