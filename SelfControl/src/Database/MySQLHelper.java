package Database;

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
	
//	private static DateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
	
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
	public static final String KEY_ID = "AppId";	
	public static final String[] COLUMNS = {KEY_ID, KEY_DATE};
	
	/**
	 * Der Konstruktor der Datenbank
	 * 
	 * @param context zu welcher Aktivity gehört die Datenbank?
	 */
	
	public MySQLHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create table "apps"
		String CREATE_APPS_TABLE = "CREATE TABLE apps (" +
				"date DATE PRIMARY KEY" +
				"AppId AppId, ";
		
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
