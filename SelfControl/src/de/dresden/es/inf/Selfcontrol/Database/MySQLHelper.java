package de.dresden.es.inf.Selfcontrol.Database;

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

import de.dresden.es.inf.Selfcontrol.Util.App;
import de.dresden.es.inf.Selfcontrol.Util.AppId;

public class MySQLHelper extends SQLiteOpenHelper{
	
    private static MySQLHelper instance;
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "apps.db";
	
	//Table-name
	public static final String TABLE_APPS = "apps";
	
	/**
	 * "id" ist die AppId
	 * "date" ist ein Zeitstempel, der aussagt wann entsprechende App gestartet wurde
	 * 
	 */
	
	//Apps-Table Columns name
	public static final String KEY_DATE ="date";
	public static final String KEY_ID = "appId";
	public static final String KEY_WIFISTATUS = "wifistate";
	public static final String KEY_LOCKSTATE = "lockstate";
	public static final String[] COLUMNS = {KEY_ID, KEY_DATE};
	
	/**
	 * Diese Klasse dient dazu die Instance der Datenbank zurückzugeben. SINGLETON!!
	 * 
	 * @param context
	 * @return
	 */
	
	public static synchronized MySQLHelper getHelper(Context context){
		if( instance == null){
			instance = new MySQLHelper(context);
			
		}
		
		return instance;
	}
	
	/**
	 * Der Konstruktor der Datenbank
	 * 
	 * @param context zu welcher Aktivity gehört die Datenbank?
	 */
	
	private MySQLHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create table "apps"
		String CREATE_APPS_TABLE = "CREATE TABLE "+ TABLE_APPS+ "('"
		      + KEY_DATE + 		"' Text, '"
			   +KEY_ID   + 		"' Text, '"
			   +KEY_WIFISTATUS+	"' INTEGER, '"
			   +KEY_LOCKSTATE+	"' INTEGER);";
		
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPS);
		db.execSQL(CREATE_APPS_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPS);
 
        // create fresh books table
        this.onCreate(db);
		
	}
	
	
	

}
