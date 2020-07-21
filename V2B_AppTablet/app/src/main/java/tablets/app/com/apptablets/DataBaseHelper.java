package tablets.app.com.apptablets;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by booth05-mgr2 on 18/01/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper{
    private static String DB_PATH="/data/data/tablets.app.com.apptablets/databases/";
    private static String DB_NAME="contactos.sqlite";
    private SQLiteDatabase myDatabase;
    private final Context myContext;


    public DataBaseHelper(Context context){
        super(context,DB_NAME,null,1);
        this.myContext = context;
    }

    public void createDataBase() throws IOException{

        boolean dbExist = checkDataBase();
        SQLiteDatabase db_Read = null;

        if(dbExist){
        }else{
            db_Read = this.getReadableDatabase();
            db_Read.close();
            try{
                copyDataBase();
            }catch (IOException e){
                throw new Error("Error copiando BD");
            }
        }
    }

    public boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        }catch (Exception e){
            File dbFile = new File(DB_PATH+DB_NAME);
            return  dbFile.exists();

        }


        if(checkDB!= null)
            checkDB.close();

        return checkDB != null ? true : false;
    }


    public void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH+DB_NAME;
        OutputStream myOutput =  new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;

        while((length = myInput.read(buffer))!= -1){
            if(length>0){
                myOutput.write(buffer,0,length);
            }
        }

        myOutput.flush();
        myInput.close();
        myOutput.close();


    }

    public void openDatabase() throws SQLException{
        String myPath = DB_PATH+DB_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);
    }

    public synchronized   void close(){
        if(myDatabase != null){
            myDatabase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            createDataBase();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Cursor fetchAlllist(){
        Cursor cursor = myDatabase.rawQuery("SELECT * FROM contacto",null);

        if(cursor != null)
            cursor.moveToFirst();
        return cursor;
    }

    public Cursor fetchItemsList(String lista){
        Cursor cursor = myDatabase.rawQuery("SELECT * FROM "+lista,null);
        if(cursor != null)
            cursor.moveToFirst();

        return cursor;
    }
}
