package com.adem.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by DELL on 3/3/2017.
 */

public class UserPostModify {
    DBHelper dbHelper;

    public UserPostModify(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    public  void insertUserPost (UserPost userPost)
    {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COL1_CAPTION, userPost.getCaption());
        contentValues.put(DBHelper.COL1_EMOTION, userPost.getEmotion());
        contentValues.put(DBHelper.COL1_LOCATION, userPost.getLocation());
        contentValues.put(DBHelper.COL1_FRIEND_TAG, userPost.getFriendtag());
        contentValues.put(DBHelper.COL1_HASHTAG, userPost.getHashtag());
        contentValues.put(DBHelper.COL1_LIST_IMAGES, userPost.getListimages());
        contentValues.put(DBHelper.COL1_LIST_VIDEOS, userPost.getListvideos());
        db.close();
    }
    public  void deleteall(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DBHelper.TABLE1_NAME, null, null);
        db.close();
    }
    public Cursor getAll(){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+DBHelper.TABLE1_NAME, null);
        if (cursor==null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public void deleteById(int id){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete(DBHelper.TABLE1_NAME,DBHelper.COL1_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public  UserPost findByID(int ID){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+DBHelper.TABLE1_NAME+ " WHERE "+DBHelper.COL1_ID + "=?", new String[]{String.valueOf(ID)});
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return new UserPost(cursor.getString(DBHelper.INDEX1_CAPTION),
                cursor.getString(DBHelper.INDEX1_LIST_IMAGES),
                cursor.getString(DBHelper.INDEX1_LIST_VIDEOS),
                cursor.getString(DBHelper.INDEX1_LOCATION),
                cursor.getString(DBHelper.INDEX1_EMOTION),
                cursor.getString(DBHelper.INDEX1_FRIENG_TAG),
                cursor.getString(DBHelper.INDEX1_HASHTAG));
    }

    public  void update (UserPost userPost, int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COL1_CAPTION, userPost.getCaption());
        contentValues.put(DBHelper.COL1_EMOTION, userPost.getEmotion());
        contentValues.put(DBHelper.COL1_LOCATION, userPost.getLocation());
        contentValues.put(DBHelper.COL1_FRIEND_TAG, userPost.getFriendtag());
        contentValues.put(DBHelper.COL1_HASHTAG, userPost.getHashtag());
        contentValues.put(DBHelper.COL1_LIST_IMAGES, userPost.getListimages());
        contentValues.put(DBHelper.COL1_LIST_VIDEOS, userPost.getListvideos());
        db.update(DBHelper.TABLE1_NAME, contentValues, DBHelper.COL1_ID + "=?", new String[]{String.valueOf(id)});
        db.close();

    }

}
