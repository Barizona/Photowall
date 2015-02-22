package com.androiddev.bartvanderlinden.photowall;



import android.content.Context;
import android.content.ContentValues;
import android.database.*;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;
//http://code.google.com/p/contentbrowser/source/browse/trunk/Teams/casey/DatabaseAPIs/src/kr/mobileplace/DatabaseAPIs/DatabaseAdapter.java?spec=svn27&r=27
public class Dbadapter{
	private static SQLiteDatabase mdb;
	private Helper dbHelper;
	private final Context context;
	private static final String db_name = "photowall";
	private static final int db_version = 1;
	private static final String table_collage = "collage";
	private static final String table_foto = "foto";
	private static final String create_table_geld = "CREATE TABLE " +
            table_foto +
			"(id integer primary key autoincrement, " +
			"geld text not null," +
			"beschrijving text not null, " +
			"datum text not null," +
			"tijd text not null," +
			"type text not null," +
			"persoon text not null);";
	
	private static final String create_table_personen = "CREATE TABLE " + table_collage +
	"(id integer primary key autoincrement, " +
	"voornaam text not null, " +
	"achternaam text not null, " +
	"rekeningnummer text not null" +
	");";
	
			
	public Dbadapter(Context _context){
		context = _context;
		dbHelper = new Helper(context, db_name, null, db_version);
	}
	public Dbadapter open() throws SQLException{
		mdb = dbHelper.getWritableDatabase();
		return this;
	}
	public void close() {
		mdb.close();
	}
	 
	
	/*public long insertTransaction(String geld, String beschrijving, String datum, String tijd, String type, String persoon){
		ContentValues contentValues = new ContentValues();
		contentValues.put("geld", geld);
		contentValues.put("beschrijving", beschrijving);
		contentValues.put("datum", datum);
		contentValues.put("tijd", tijd);
		contentValues.put("type", type);
		contentValues.put("persoon", persoon);
		return mdb.insert(table_foto, null, contentValues);
	}
	public long changeTransaction(String idtoupdate, String geld, String beschrijving, String datum, String tijd, String type, String persoon){
		ContentValues contentValues = new ContentValues();
		contentValues.put("geld", geld);
		contentValues.put("beschrijving", beschrijving);
		contentValues.put("datum", datum);
		contentValues.put("tijd", tijd);
		contentValues.put("type", type);
		contentValues.put("persoon", persoon);
		String where = "id" + "=" + idtoupdate;
		return mdb.update(table_foto, contentValues, where, null);
	}
	public int DeleteTransaction(String id){
		return mdb.delete(table_foto, "id=" + id, null);
	}
	public static Cursor totaalUitgaven(){
		return mdb.rawQuery("Select SUM(SUBSTR(geld, 1)) from " + table_foto + " where type='Uitgave';", null);
	}
	public static Cursor totaalInkomsten(){
		return mdb.rawQuery("Select SUM(SUBSTR(geld, 1)) from " + table_foto + " where type='Inkomst';", null);
	}
	public static Cursor totaalHerrineringen(){
		return mdb.rawQuery("Select SUM(SUBSTR(geld, 1)) from " + table_geld + " where type='Herinnering';", null);
	}
	public Cursor getAllTransactions(){
		return mdb.rawQuery("select id, geld, beschrijving, type, tijd, datum, persoon from " +  table_geld + ";", null);
	}
	
	public static Cursor getUitgaven(){
		return mdb.rawQuery("select id, geld, beschrijving, type, tijd, datum, persoon from " +  table_geld + " where type='Uitgave';", null)*//*mdb.query(
		table_geld,
		new String[] {"geld", "beschrijving"},
		"Uitgave",
		null,
		null,//where
		null,
		null		
		)*//*;
	}
	public static Cursor getInkomsten(){
		return mdb.rawQuery("select id, geld, beschrijving, type, tijd, datum, persoon from " + table_geld + " where type='Inkomst';", null);*//*mdb.query(
		table_geld,
		new String[] {"geld", "beschrijving"},
		"type=Inkomst",
		null,
		null,
		null,
		null
		);*//*
	}
	public static Cursor getReminds(){
		return mdb.rawQuery("select id, geld, beschrijving, type,  tijd, datum, persoon from " + table_geld + " where type='Herinnering';", null);
	}
	public Cursor getPersonen(){
		return mdb.rawQuery("select id, voornaam, achternaam, rekeningnummer from " + table_personen, null);
	}

	public static int deletePersoon(String id){
		return mdb.delete(table_personen, "id=" + id, null);
	}

	public long insertPersoon(String voornaam, String achternaam, String rekeningnummer){
		ContentValues contentValues = new ContentValues();
		contentValues.put("voornaam", voornaam);
		contentValues.put("achternaam", achternaam);
		contentValues.put("rekeningnummer", rekeningnummer);
		return mdb.insert(table_personen, null, contentValues);
	}
	public long editPersoon(String id, String voornaam, String achternaam, String rekeningnummer){
		ContentValues contentValues = new ContentValues();
		contentValues.put("voornaam", voornaam);
		contentValues.put("achternaam", achternaam);
		contentValues.put("rekeningnummer", rekeningnummer);
		String where = "id" + "=" + id;
		return mdb.update(table_personen, contentValues, where, null);
	}*/


private static class Helper extends SQLiteOpenHelper {
	public Helper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
    public void onCreate(SQLiteDatabase db) {
    	db.execSQL(create_table_geld);
    	db.execSQL(create_table_personen);
    }
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 
        Log.w("TaskDBAdapter", "Upgrading from version " +
                                                   oldVersion + " to " +
                                                   newVersion + 
                                                   ", which will destroy all old data");
        
     
        db.execSQL("DROP TABLE IF EXIST " + table_foto);
        db.execSQL("DROP TABLE IF EXIST " + table_collage);

        onCreate(db);
		
	}
	}
}
