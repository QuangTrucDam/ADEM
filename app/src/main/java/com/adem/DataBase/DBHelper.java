package com.adem.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DELL on 3/2/2017.
 */

public class DBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME= "UserInfomation";

    public static final String TABLE1_NAME = "UserPost";
    public static final String COL1_ID = "UserPost_ID";
    public static final String COL1_CAPTION = "Caption";
    public static final String COL1_LIST_IMAGES = "List_Imnages";
    public static final String COL1_LIST_VIDEOS = "List_Videos";
    public static final String COL1_LOCATION = "Location";
    public static final String COL1_EMOTION = "Emotion";
    public static final String COL1_FRIEND_TAG = "FriendTag";
    public static final String COL1_HASHTAG = "HashTag";

    public static final int INDEX1_ID = 0;
    public static final int INDEX1_CAPTION = 1;
    public static final int INDEX1_LIST_IMAGES = 2;
    public static final int INDEX1_LIST_VIDEOS = 3;
    public static final int INDEX1_LOCATION = 4;
    public static final int INDEX1_EMOTION = 5;
    public static final int INDEX1_FRIENG_TAG = 6;
    public static final int INDEX1_HASHTAG = 7;

    public static final String TABLE2_NAME = "UserFriendList";
    public static final String COL2_ID = "UserFriendList_ID";
    public static final String COL2_FRIENDNAME = "FriendName";
    public static final String COL2_FRIENDID = "FriendID";
    public static final String COL2_FRIENDPROFILEPICTURE = "FriendProfilePicture";
    public static final String COL2_FRIENDLOCATION = "FriendLocation";


    public static final int INDEX2_ID = 0;
    public static final int INDEX2_FRIENDNAME = 1;
    public static final int INDEX2_FRIENDID = 2;
    public static final int INDEX2_FRIENDPROFILEPICTURE = 3;
    public static final int INDEX2_FRIENDLOCATION = 4;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE if not exists "+TABLE1_NAME+"("+
                COL1_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL1_CAPTION + " TEXT, "+
                COL1_LIST_IMAGES + " TEXT, "+
                COL1_LIST_VIDEOS + " TEXT, "+
                COL1_LOCATION + " TEXT, "+
                COL1_EMOTION + " TEXT, "+
                COL1_FRIEND_TAG + " TEXT, "+
                COL1_HASHTAG + " TEXT);");
        sqLiteDatabase.execSQL("CREATE TABLE if not exists "+TABLE2_NAME+"("+
                COL2_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL2_FRIENDNAME + " TEXT, "+
                COL2_FRIENDID + " TEXT, "+
                COL2_FRIENDPROFILEPICTURE + " TEXT, "+
                COL2_FRIENDLOCATION + " TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE1_NAME );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE2_NAME );
    }
}
