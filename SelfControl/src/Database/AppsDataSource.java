package Database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class AppsDataSource {
	
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

}
