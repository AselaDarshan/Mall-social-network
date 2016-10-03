package com.example.android.bluetoothchat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Isuru on 10/02/2016.
 */
public class DBHandler extends SQLiteOpenHelper implements MsgListener {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "MsgDatabase.db";
    private static final String TABLE_NAME = "msg_table";
    private static final String KEY_ID = "_id";
    private static final String KEY_UID = "_uid";
    private static final String KEY_TSTAMP = "_tStamp";
    private static final String KEY_TYPE = "_type";
    private static final String KEY_INREPLYTOMESSAGEID = "_inReplyToMessageID";
    private static final String KEY_TEXT = "_text";
    private static final String KEY_RANK = "_rank";
    private static final String KEY_NOOFRANKERS = "_noOfRankers";
    private static final String KEY_IMAGE = "_image";

    String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_UID+" TEXT, "+KEY_TSTAMP+" TEXT,"+KEY_TYPE+" INTEGER,"+KEY_INREPLYTOMESSAGEID+" TEXT,"+KEY_TEXT+" TEXT, "+KEY_RANK+" INTEGER,"+KEY_NOOFRANKERS+" INTEGER,"+KEY_IMAGE+" TEXT)";
    String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    @Override
    public void addMsg(Msg msg) {
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put(KEY_UID, msg.getUID());
            values.put(KEY_TEXT, msg.getText());
            values.put(KEY_RANK,msg.getRank());
            values.put(KEY_TSTAMP,msg.getTstamp());
            values.put(KEY_TYPE,msg.getType());
            values.put(KEY_INREPLYTOMESSAGEID,msg.getInReplyToMessageID());
            values.put(KEY_NOOFRANKERS,msg.getNoOfRankers());
            values.put(KEY_IMAGE,msg.getImage());
            db.insert(TABLE_NAME, null, values);
            db.close();
        }catch (Exception e){
            Log.e("problem",e+"");
        }
    }

    @Override
    public ArrayList<Msg> getAllMsg() {

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Msg> msgList = null;
        try{
            msgList = new ArrayList<Msg>();
            String QUERY = "SELECT * FROM "+TABLE_NAME+" WHERE " + KEY_TYPE + "=1";
            Cursor cursor = db.rawQuery(QUERY, null);
            if(!cursor.isLast())
            {
                while (cursor.moveToNext())
                {
                    Msg msg = new Msg();
                    msg.setID(cursor.getInt(0));
                    msg.setUID(cursor.getString(1));
                    msg.setTstamp(cursor.getString(2));
                    msg.setType(cursor.getInt(3));
                    msg.setInReplyToMessageID(cursor.getString(4));
                    msg.setText(cursor.getString(5));
                    msg.setRank(cursor.getInt(6));
                    msg.setNoOfRankers(cursor.getInt(7));
                    msg.setImage(cursor.getString(8));
                    msgList.add(msg);
                }
            }
            db.close();
        }catch (Exception e){
            Log.e("Errorrrrrr",e+"");
        }
        return msgList;
    }

    public ArrayList<Comment> getComments(String uid) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Comment> commentList = null;
        try{
            commentList = new ArrayList<Comment>();
            String QUERY = "SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_INREPLYTOMESSAGEID +"='"+uid+"' LIMIT 5 ";
            Cursor cursor = db.rawQuery(QUERY, null);
            if(!cursor.isLast())
            {
                while (cursor.moveToNext())
                {
                    Comment comment = new Comment(cursor.getString(5), cursor.getString(8), cursor.getString(2));
                    commentList.add(comment);
                }
            }
            db.close();
        }catch (Exception e){
            Log.e("error",e+"");
        }
        return commentList;
    }

    @Override
    public int getMsgCount() {
        int num = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            String QUERY = "SELECT * FROM "+TABLE_NAME;
            Cursor cursor = db.rawQuery(QUERY, null);
            num = cursor.getCount();
            db.close();
            return num;
        }catch (Exception e){
            Log.e("error",e+"");
        }
        return 0;
    }

    public void updateRank(String uid,int rank) {

        SQLiteDatabase db = this.getReadableDatabase();
        try{
            String QUERY = "update " + TABLE_NAME+ " set KEY_RANK ='"+rank+"',KEY_NOOFRANKERS = KEY_NOOFRANKERS + 1 where id='" + uid + "'";
            Cursor cursor = db.rawQuery(QUERY, null);

            db.close();

        }catch (Exception e){
            Log.e("error",e+"");
        }

    }
}
