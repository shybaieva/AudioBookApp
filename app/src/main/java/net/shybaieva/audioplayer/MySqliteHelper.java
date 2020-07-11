package net.shybaieva.audioplayer;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class MySqliteHelper extends SQLiteOpenHelper {


    public MySqliteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData (String title, String author, String description, byte [] img){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO BOOKS VALUES (NULL, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);

        statement.clearBindings();

        statement.bindString(1, title);
        statement.bindString(2, author);
        statement.bindString(3, description);
        statement.bindBlob(4, img);

        statement.execute();
    }

    public Cursor getData (String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
