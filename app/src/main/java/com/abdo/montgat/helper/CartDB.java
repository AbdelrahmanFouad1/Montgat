package com.abdo.montgat.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class CartDB extends SQLiteOpenHelper {

    private static final String TAG = "cartDB";

    private static final int DB_VERSION = 2;
//    private static String DB_PATH = "/data/data/com.abdo.montgat/databases/CartDB";
    private static final String DATABASE_NAME = "/data/data/com.abdo.montgat/databases/CartDB";
    private static final String TABLE_NAME = "favoriteTable";
    public static String KEY_ID = "id";
    public static String ITEM_IMAGE = "itemImage";
    //    public static String ITEM_DEPRECATED_PRICE = "itemDeprecatedPrice";
    //    public static String ITEM_DESCRIPTION = "itemDescription";
    //    public static String ITEM_COLOR = "itemColor";
    public static String ITEM_PRICE = "itemPrice";
    public static String ITEM_NEW_PRICE = "itemNewPrice";
    public static String ITEM_TITLE = "itemTitle";
    public static String ITEM_SIZE = "itemSize";
    public static String ITEM_QUANTITY = "itemQuantity";
    public static String CART_STATUS = "fStatus";

    // dont forget write this spaces
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + KEY_ID + " TEXT,"
            + ITEM_IMAGE + " TEXT,"
//            + ITEM_DEPRECATED_PRICE + " TEXT,"
//            + ITEM_DESCRIPTION + " TEXT,"
//            + ITEM_COLOR + " TEXT,"
            + ITEM_PRICE + " TEXT,"
            + ITEM_NEW_PRICE + " TEXT,"
            + ITEM_TITLE + " TEXT,"
            + ITEM_SIZE + " TEXT,"
            + ITEM_QUANTITY + " TEXT,"
            + CART_STATUS + " TEXT )";

    public CartDB(Context context) {
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
    public void insertEmptyCart() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        // enter your value
        for (int x = 1; x < 101; x++) {
            cv.put(KEY_ID, x);
            cv.put(CART_STATUS, "0");

            db.insert(TABLE_NAME, null, cv);
        }
    }

    // insert data into database
    public void insertIntoTheDatabaseCart(
            int item_image,
//            String item_deprecatedPrice,
//            String item_description,
//            String item_color,
            String item_price,
            String item_new_price,
            String item_title,
            String item_size,
            String item_quantity,
            String id,
            String cart_status) {
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ITEM_IMAGE, item_image);
//        cv.put(ITEM_DEPRECATED_PRICE, item_deprecatedPrice);
//        cv.put(ITEM_DESCRIPTION, item_deprecatedPrice);
//        cv.put(ITEM_COLOR, item_color);
        cv.put(ITEM_PRICE, item_price);
        cv.put(ITEM_NEW_PRICE, item_new_price);
        cv.put(ITEM_TITLE, item_title);
        cv.put(ITEM_SIZE, item_size);
        cv.put(ITEM_QUANTITY, item_quantity);
        cv.put(KEY_ID, id);
        cv.put(CART_STATUS, cart_status);
        db.insert(TABLE_NAME, null, cv);
        Log.d(TAG,"Status " + item_title +" "+ item_price +" "+item_new_price+ " " +item_size + " "+item_quantity +", cartStatus - " + cart_status + " - .   " + cv);
    }

    // read all data
    public Cursor read_all_dataCart(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_NAME + " where " + KEY_ID + "=" + id + "";
        return db.rawQuery(sql, null, null);
    }

    // remove line from database
    public void remove_cart(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE " + TABLE_NAME + " SET  " + CART_STATUS + " ='0' WHERE " + KEY_ID + "=" + id + "";
        db.execSQL(sql);
        Log.d(TAG, "remove" + id.toString());

    }

    // remove all database
    public void remove_all_cart() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE " + TABLE_NAME + " SET  " + CART_STATUS + " ='0' ";
        db.execSQL(sql);

    }

    // select all cart list
    public Cursor select_all_cart_list() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + CART_STATUS + " ='1'";
        return db.rawQuery(sql, null, null);
    }

    // calculate how many item in Cart
    public int getCartCount() {
        int itemCount = 0;

        String countQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + CART_STATUS + " ='1'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            itemCount = cursor.getCount();
        }
        cursor.close();
        Log.d(TAG, "itemcount" + String.valueOf(itemCount));

        return itemCount;
    }
}
