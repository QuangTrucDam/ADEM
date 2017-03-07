package com.adem.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by DELL on 3/3/2017.
 */

public class UserFriendsModify {
    DBHelper dbHelper;

    public UserFriendsModify(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    public void insertFriend (UserFriends userFriends){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COL2_FRIENDNAME, userFriends.getFriendname());
        contentValues.put(DBHelper.COL2_FRIENDID, userFriends.getFriendid());
        contentValues.put(DBHelper.COL2_FRIENDLOCATION, userFriends.getFriendlocation());
        contentValues.put(DBHelper.COL2_FRIENDPROFILEPICTURE, userFriends.getFriendprofilepicture());
        db.close();
    }
    public  void deleteall(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DBHelper.TABLE2_NAME, null, null);
        db.close();
    }
    public Cursor getAll(){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+DBHelper.TABLE2_NAME, null);
        if (cursor==null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public void deleteById(int id){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete(DBHelper.TABLE2_NAME,DBHelper.COL2_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public UserFriends findByID (int id){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+DBHelper.TABLE2_NAME+ " WHERE "+DBHelper.COL2_ID + "=?", new String[]{String.valueOf(id)});
        if(cursor!=null){
            cursor.moveToFirst();
        }


        return new UserFriends(cursor.getString(DBHelper.INDEX2_FRIENDNAME),
                cursor.getString(DBHelper.INDEX2_FRIENDID),
                cursor.getString(DBHelper.INDEX2_FRIENDPROFILEPICTURE),
                cursor.getString(DBHelper.INDEX2_FRIENDLOCATION));
    }

    public  void update (UserFriends userFriends, int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COL2_FRIENDNAME, userFriends.getFriendname());
        contentValues.put(DBHelper.COL2_FRIENDID, userFriends.getFriendid());
        contentValues.put(DBHelper.COL2_FRIENDPROFILEPICTURE, userFriends.getFriendprofilepicture());
        contentValues.put(DBHelper.COL2_FRIENDLOCATION, userFriends.getFriendlocation());
        db.update(DBHelper.TABLE2_NAME, contentValues, DBHelper.COL2_ID + "=?", new String[]{String.valueOf(id)});
        db.close();

    }
}
