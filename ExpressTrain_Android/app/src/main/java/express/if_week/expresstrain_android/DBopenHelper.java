package express.if_week.expresstrain_android;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jaery on 2018-05-04.
 */

class DBOpenHelper {

    private static final String DATABASE_NAME = "Log.db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;


    private class DatabaseHelper extends SQLiteOpenHelper {

        // 생성자
        public DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // 최초 DB를 만들때 한번만 호출된다.
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CreateDB._CREATE);

        }

        // 버전이 업데이트 되었을 경우 DB를 다시 만들어 준다.
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+CreateDB._TABLENAME);
            onCreate(db);
        }


    }

    public DBOpenHelper(Context context){
        this.mCtx = context;
    }

    public int getCount(){
        Cursor cursor= mDB.rawQuery("select * from Log",null);
        return cursor.getCount();
    }


    public int getautoLogin()   //0 로그린안함 1 일시로그인 2 자동로그인
    {
        Cursor cursor= mDB.rawQuery("select auto from Log",null);

        if(cursor.moveToFirst()) {
            do {
                return cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return -1;

    }

    public String getID()
    {
        Cursor cursor= mDB.rawQuery("select NickName from Log",null);

        if(cursor.moveToFirst()) {
            do {
                return cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return null;

    }

    public int getTYPE()
    {
        Cursor cursor= mDB.rawQuery("select TYPE from Log",null);

        if(cursor.moveToFirst()) {
            do {
                return cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return -1;

    }

    public void insert(String name, int type,int auto)
    {
       mDB.execSQL("INSERT INTO Log(NickName,TYPE,auto) values (\'"+name+"\',"+type+","+auto+")");
    }

    public void UpdateAuto(int auto)
    {
        mDB.execSQL("UPDATE Log set auto="+auto);
    }

    public void UpdateNickName(String name)
    {
        mDB.execSQL("UPDATE Log set NickName=\'"+name+"\')");
    }




    public DBOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        mDB.close();
    }

    public void clear(){
        mDB.execSQL("delete from "+CreateDB._TABLENAME);
    }

}
