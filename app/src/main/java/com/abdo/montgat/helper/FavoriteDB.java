package com.abdo.montgat.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;


public class FavoriteDB extends SQLiteOpenHelper {
    private static int DB_VERSION = 1;
//    private static String DB_PATH = "/data/data/com.abdo.montgat/databases/MontgatDB";
    private static String DATABASE_NAME = "/data/data/com.abdo.montgat/databases/MontgatDB";
    private static String TABLE_NAME = "favoriteTable";
    public static String KEY_ID = "id";
    public static String ITEM_IMAGE = "itemImage";
//    public static String ITEM_DEPRECATED_PRICE = "itemDeprecatedPrice";
    public static String ITEM_PRICE = "itemPrice";
    public static String ITEM_TITLE = "itemTitle";
//    public static String ITEM_DESCRIPTION = "itemDescription";
//    public static String ITEM_COLOR = "itemColor";
//    public static String ITEM_SIZE = "itemSize";
//    public static String ITEM_QUANTITY = "itemQuantity";
    public static String FAVORITE_STATUS = "fStatus";

    // dont forget write this spaces
    private static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + KEY_ID + " TEXT,"
            + ITEM_IMAGE+ " TEXT,"
//            + ITEM_DEPRECATED_PRICE + " TEXT,"
            + ITEM_PRICE + " TEXT,"
            + ITEM_TITLE + " TEXT,"
//            + ITEM_DESCRIPTION + " TEXT,"
//            + ITEM_COLOR + " TEXT,"
//            + ITEM_SIZE + " TEXT,"
//            + ITEM_QUANTITY + " TEXT,"
            + FAVORITE_STATUS+" TEXT )";

    public FavoriteDB( Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // create empty table
    public void insertEmpty() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        // enter your value
        for (int x = 1; x < 101; x++) {
            cv.put(KEY_ID, x);
            cv.put(FAVORITE_STATUS, "0");

            db.insert(TABLE_NAME,null, cv);
        }
    }

    // insert data into database
    public void insertIntoTheDatabase(
            int item_image,
//            String item_deprecatedPrice,
            String item_price,
            String item_title,
//            String item_description,
//            String item_color,
//            String item_size,
//            String item_quantity,
            String id,
            String fav_status)
    {
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ITEM_IMAGE, item_image);
//        cv.put(ITEM_DEPRECATED_PRICE, item_deprecatedPrice);
        cv.put(ITEM_PRICE, item_price);
        cv.put(ITEM_TITLE, item_title);
//        cv.put(ITEM_DESCRIPTION, item_deprecatedPrice);
//        cv.put(ITEM_COLOR, item_color);
//        cv.put(ITEM_SIZE, item_size);
//        cv.put(ITEM_QUANTITY, item_quantity);
        cv.put(KEY_ID, id);
        cv.put(FAVORITE_STATUS, fav_status);
        db.insert(TABLE_NAME,null, cv);
        Log.d("Status", item_title + item_price +", favstatus - "+fav_status+" - . " + cv);
    }

    // read all data
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public Cursor read_all_data(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_NAME + " where " + KEY_ID+"="+id+"";
        return db.rawQuery(sql,null,null);
    }

    // remove line from database
    public void remove_fav(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE " + TABLE_NAME + " SET  "+ FAVORITE_STATUS+" ='0' WHERE "+KEY_ID+"="+id+"";
        db.execSQL(sql);
        Log.d("remove", id.toString());

    }

    // select all favorite list
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public Cursor select_all_favorite_list() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE "+FAVORITE_STATUS+" ='1'";
        return db.rawQuery(sql,null,null);
    }

    public int getProfilesCount() {
        int itemCount = 0;

        String countQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE "+FAVORITE_STATUS+" ='1'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            itemCount = cursor.getCount();
        }
        cursor.close();
        Log.d("itemcount", String.valueOf(itemCount));

        return itemCount;
    }
}
