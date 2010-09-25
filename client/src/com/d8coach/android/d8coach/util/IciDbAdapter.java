package com.d8coach.android.d8coach.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.d8coach.android.d8coach.R;
import com.d8coach.android.d8coach.model.SortOrder;

/**
 * Simple Headlines database access helper class. Defines the basic CRUD operations
 * for the notepad example, and gives the ability to list all Headlines as well as
 * retrieve or modify a specific headline.
 * 
 * This has been improved from the first version of this tutorial through the
 * addition of better error handling and also using returning a Cursor instead
 * of using a collection of inner classes (which is less scalable and not
 * recommended).
 */
public class IciDbAdapter {
        
    static final double PI = 3.14159;

	/*
	 * Do not remove. This is used by Perl script to generate proper field names.
	 * BEGIN CONSTANTS
	 */

	//DB
    public static final String KEY_ROW_ID = "_id" ;

    //JSON
    public static final String APP_ID = "app_id";
    public static final String JSON_SORT_BY      = "sort_by"    ;
    public static final String JSON_KM_RADIUS    = "km_radius"    ;
    public static final String JSON_REFRESH_TIME = "refresh_time" ;
    public static final String JSON_CHANNEL_PARENT_ID = "channel_parent_id";

    //Headlines
    public static final String KEY_ADDRESS    = "address"    ;
    public static final String KEY_CAPTION    = "caption"    ;
    public static final String KEY_CHANNEL_ID = "channel_id" ;
    public static final String KEY_DISTANCE   = "distance"   ;
    public static final String KEY_HEADLINE   = "headline"   ;
    public static final String KEY_IS_EVENT   = "is_event"   ;
    public static final String KEY_LAT        = "lat"        ;
    public static final String KEY_LNG        = "lng"        ;
    public static final String KEY_PHOTO_URL  = "photo_url"  ;
    public static final String KEY_POST_ID    = "post_id"    ;
    public static final String KEY_POST_TIME  = "post_time"  ;
    public static final String KEY_RANK       = "rank"       ;
    public static final String KEY_SUBTITLE   = "sub_title"  ;
    public static final String KEY_TEXT       = "text"       ;
    public static final String KEY_THUMB_URL  = "thumb_url"  ;
    public static final String KEY_USER_ID    = "user_id"    ;
    public static final String KEY_USER_NAME  = "user_name"  ;
    public static final String KEY_TEMP_ID    = "temp_id"    ;

    //Channels
    public static final String KEY_CHANNEL        = "channel";
    public static final String KEY_CHANNEL_ACCESS = "channel_access";
    public static final String KEY_CHANNEL_DESC   = "channel_desc";
    public static final String KEY_CHANNEL_EDITOR = "channel_editor";
    public static final String KEY_CHANNEL_PARENT = "channel_parent";
    public static final String KEY_CHANNEL_PARENT_ID = JSON_CHANNEL_PARENT_ID;
    public static final String KEY_CHANNEL_PRICE  = "channel_price";
    public static final String KEY_ICI_ZIP8       = "ici_zip8";
    public static final String KEY_STATUS         = "status";
    public static final String KEY_TIME_STAMP     = "time_stamp";
    
    public static final String HEADLINE_TABLE = "posts";
    public static final String CHANNEL_TABLE  = "channels";
    public static final String CLICKS_TABLE   = "clicks";

    //private static final String TAG = "HeadlineDbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
	/*
	 * END CONSTANTS
	 * Do not remove. This is used by Perl script to generate proper field names.
	 */

    private static final String DATABASE_NAME  = "data";
    private static final int DATABASE_VERSION = 4;

    private final Context mCtx;

    /**
     * Minor overrides from SQLiteOpenHelper. 
     * Mainly just the creation and upgrade logic has been touched.
     * @author dk
     *
     */
    private static class DatabaseHelper extends SQLiteOpenHelper {

    	private Resources mResources;
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            mResources = context.getResources();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            /*
        	Log.v("Headlines", HEADLINE_TABLE_CREATE);
        	Log.v("Channels", CHANNEL_TABLE_CREATE);
            db.execSQL(HEADLINE_TABLE_CREATE);
            db.execSQL(CHANNEL_TABLE_CREATE); 
            */
            //String dbSchema = mResources.getString(R.string.dbschema);
            String dbSchema = readStringFromStream(mResources.openRawResource(R.raw.dbschema));

            //break it into bits
            String[] statements = dbSchema.split(";"); 
            for (String statement : statements) {
            	if (statement.length() > 4) {
            		statement = statement + ';';
            		db.execSQL(statement); 
            	}
            }
        }
     
        /**
         * Currently does a complete wipe of the db.
         * In the future, consider migrating the user's data.
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            /*
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + HEADLINE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CHANNEL_TABLE);
            onCreate(db);
            */
            onCreate(db);
        }
    }

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     * 
     * @param ctx the Context within which to work
     */
    public IciDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     * Open the headlines database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     * 
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public IciDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }
    
    public void close() {
        mDbHelper.close();
    }

    public long insertHeadlineArray(JSONArray array) {

        long result = 0;
		try {
            final int length = array.length(); 
            Log.v("JSON Length", Integer.toString(length));

            mDb.beginTransaction();
            for (int i=0; i<length; i++) {
                JSONObject object = (JSONObject) array.get(i);
                object = object.put(IciDbAdapter.KEY_TEMP_ID, i);
                //Now insert into DB
                insertHeadline(object);
            }

            mDb.setTransactionSuccessful();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            result = -1;
		} catch (NullPointerException e) {
			e.printStackTrace();
            result = -1;
		} finally {
            mDb.endTransaction();
        }	
        return result;
    }

    /**
     * Create a new headline using the title and body provided. If the headline is
     * successfully created return the new rowId for that headline, otherwise return
     * a -1 to indicate failure.
     * 
     * @param story the JSONObject containing all relevant data of the headline
     * @return rowId or -1 if failed
     */
    public long insertHeadline(JSONObject story) {
        try {
            String table = HEADLINE_TABLE;

            /* A list of all columns shared between Json and the local DB */
            String[] commonColumns = getCommonColumns();
            /* Add the local-specific ROW_ID */
            String[] dbColumns = appendString(KEY_ROW_ID, commonColumns);
            /* Add the json-specific POST_ID */
            String[] jsonColumns = appendString(KEY_POST_ID, commonColumns);

            /* We're inserting to the list of db columns */
            String columnString = IciDbAdapter.commaSeparatedList(dbColumns);

            /* Populate values using JSON array, but don't exceed what dbColumns expects */
            int length = dbColumns.length;
            String[] values = new String[length];
            for (int i=0; i<length; i++) {
                values[i] = story.getString(jsonColumns[i]);
            } 
            /* Make the question mark string based on how many values we have */
            length = values.length;
            String[] qMarks = new String [length];
            for (int i=0; i<length; i++) {
                qMarks[i] = "?";
            }
            String valueString = IciDbAdapter.commaSeparatedList(qMarks);

            String p_query = "INSERT INTO " + table 
                + " (" + columnString + ") Values(" + valueString + ")";
            mDb.execSQL(p_query, values);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLiteConstraintException e) {
			e.printStackTrace();
		}
		return -1;
    }

    /**
     * Delete the headline with the given rowId
     * 
     * @param rowId id of headline to delete
     * @return true if deleted, false otherwise
     */
    public boolean deleteHeadline(long rowId) {

        return mDb.delete(HEADLINE_TABLE, KEY_ROW_ID + "=" + rowId, null) > 0;
    }

    /**
     * Return a Cursor over the list of all headlines in the database
     * 
     * @return Cursor over all headlines
     */
    public Cursor fetchAllHeadlines(SortOrder order) {
        //TODO add a specific channel to select on
        String orderBy = null, where = null;
        switch (order) {
            case LATEST:
                orderBy = KEY_POST_TIME + " DESC";
                break;
            case CLOSEST:
                orderBy = KEY_DISTANCE + " ASC";
                break;
            case EVENTS:
                /* Only return events */
                orderBy = KEY_POST_TIME + " ASC";
                where   = KEY_IS_EVENT + " = 1";
                break;
            case TEMP_ID:
                orderBy = KEY_TEMP_ID + " ASC";
                break;
            default:
                //This should never happen
                //blow up!
			try {
				throw new IciDatabaseException();
			} catch (IciDatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        String[] columns = getCommonColumns(); 
        columns = appendString(KEY_ROW_ID, columns);
        columns = appendString(getVoodooStringForCurrentPosition(), columns);
        return mDb.query(
            HEADLINE_TABLE, 
            columns,
            where, 
            null, null, null, 
            orderBy);
    }

    /**
     * Return a Cursor positioned at the Headline that matches the given rowId
     * 
     * @param rowId id of Headline to retrieve
     * @return Cursor positioned to matching Headline, if found
     * @throws SQLException if Headline could not be found/retrieved
     */
    public Cursor fetchHeadline(long rowId) throws SQLException {
        String[] columns = appendString(KEY_ROW_ID, getCommonColumns());
        Cursor mCursor =
                mDb.query(true, HEADLINE_TABLE, columns,
                        KEY_ROW_ID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    /**
     * Update the Headline using the details provided. The Headline to be updated is
     * specified using the rowId, and it is altered to use the title and body
     * values passed in
     * 
     * @param rowId id of Headline to update
     * @param title value to set Headline title to
     * @param body value to set Headline body to
     * @return true if the Headline was successfully updated, false otherwise
     */
    public boolean updateHeadline(long rowId, String title, String body) {
        ContentValues args = new ContentValues();
        args.put(KEY_HEADLINE, title);
        args.put(KEY_TEXT, body);

        return mDb.update(HEADLINE_TABLE, args, KEY_ROW_ID + "=" + rowId, null) > 0;
    }

	public long insertChannel(JSONObject object) {
		// TODO Consider moving this to a separate Channel helper
        try {
			ContentValues initialValues = new ContentValues();
			initialValues.put(KEY_ROW_ID, object.getInt(KEY_CHANNEL_ID) );
			initialValues.put(KEY_CHANNEL, object.getString(KEY_CHANNEL) );
			initialValues.put(KEY_CHANNEL_DESC, object.getString(KEY_CHANNEL_DESC) );
			initialValues.put(JSON_CHANNEL_PARENT_ID, object.getString(JSON_CHANNEL_PARENT_ID ));

			return mDb.insert(CHANNEL_TABLE, null, initialValues);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLiteConstraintException e) {
			e.printStackTrace();
		}
		return -1;
	}

    /**
     * Return a Cursor positioned at the Channel that matches the given rowId
     * 
     * @param rowId id of Channel to retrieve
     * @return Cursor positioned to matching Channel, if found
     * @throws SQLException if Channel could not be found/retrieved
     */
    public Cursor fetchChannel(long rowId) throws SQLException {
        String[] columns = new String[] {KEY_ROW_ID, KEY_CHANNEL, KEY_CHANNEL_DESC};
        Cursor mCursor =
                mDb.query(true, CHANNEL_TABLE, columns,
                        KEY_ROW_ID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

	public Cursor fetchAllChannels() {
        String sortString = String.format("%s COLLATE NOCASE ASC", KEY_CHANNEL);
        String[] columns = new String[] {KEY_ROW_ID, KEY_CHANNEL, KEY_CHANNEL_DESC};
        String where = String.format("%s = %s",
            JSON_CHANNEL_PARENT_ID, KEY_ROW_ID
        );
        return mDb.query(CHANNEL_TABLE, 
                columns, 
                where, 
                null, null, null, 
                sortString);
	}

	public Cursor fetchSectionsForChannel(long parentId) {
        String sortString = String.format("%s COLLATE NOCASE ASC", KEY_CHANNEL);
        String[] columns = new String[] {KEY_ROW_ID, KEY_CHANNEL, KEY_CHANNEL_DESC};
        String where = String.format("%s = %d",
            JSON_CHANNEL_PARENT_ID, parentId
        );
        return mDb.query(CHANNEL_TABLE, 
                columns, 
                where, 
                null, null, null, 
                sortString);
	}

    public void clearTable(String table) {
        String[] tables = {table};
        clearTables(tables);
    }

    public void clearDb() {
        String[] tables = new String[] {HEADLINE_TABLE, CHANNEL_TABLE, CLICKS_TABLE};
        clearTables(tables);
    }
    
    public void clearTables(String[] tables) {
        for (String table : tables) {
            String statement = "DELETE FROM " + table + ";";
            mDb.execSQL(statement);
        }
    }

    public static String commaSeparatedList(String[] strings) {
        String finalString = "";
        int length = strings.length;
        for (int i=0; i<length; i++) {
            if (i == length-1) {
                finalString += strings[i];
            } else {
                finalString += strings[i] + ", ";
            }
        }
        return finalString;
    }

    private String[] getCommonColumns() {
        return new String[]{
            KEY_ADDRESS    ,
            KEY_CAPTION    ,
            KEY_CHANNEL_ID ,
            KEY_DISTANCE   ,
            KEY_HEADLINE   ,
            KEY_IS_EVENT   ,
            KEY_LAT        ,
            KEY_LNG        ,
            KEY_PHOTO_URL  ,
            KEY_POST_TIME  ,
            KEY_RANK ,
            KEY_SUBTITLE   ,
            KEY_TEMP_ID    ,
            KEY_TEXT       ,
            KEY_TEXT       ,
            KEY_THUMB_URL  ,
            KEY_USER_ID    ,
            KEY_USER_NAME
        };
    }
    private String[] appendString(String string, String[] array) {
        LinkedList<String> list = new LinkedList<String>(
            Arrays.asList(array)
        );
        String[] dummy = new String[array.length];
        list.add(string);
        return list.toArray(dummy);
    }

    private String getVoodooStringForCurrentPosition() {
        //get current location
        //Location loc = mCtx.getApplication().getLocation();
        //double lat1 = loc.getLatitude();
        //double lng1 = loc.getLongitude();
        double lat1 = -70.00;
        double lng1 = 70.00;

        //Pre-compute
        //lat1, lng1 refers to present location
        //lat_km/degree = 111.325km
        double latKMPerDegree = 111.325;
        //lng_km/degree = cos(lat1) * 111.325km
        double lngKMPerDegree = Math.cos(
            degreesToRadians(lat1 * 111.325)
        );

        //lat_km_sq = lat_km/degree * lat_km/degree
        double latKMSquared = latKMPerDegree * latKMPerDegree;
        //lng_km_sq = lng_km/degree * lng_km/degree
        double lngKMSquared = lngKMPerDegree * lngKMPerDegree;

        //Select statement of minor magic
        String voodoo = String.format("((%f - lat) * (%f - lat) * %f + (%f - lng) * (%f - lng) * %f) AS distance",
                 lat1, lat1, latKMSquared, 
                 lng1, lng1, lngKMSquared
         );

        return voodoo;
    }

	private double degreesToRadians(double d) {
        double radians = d * (PI / 180);
		return radians;
	}

    public static String readStringFromStream(InputStream stream) {
    	int size;
    	byte[] bytes = null;
    	try {
    		size = stream.available();
    		bytes = new byte[size];
    		stream.read(bytes);
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
       
        return new String(bytes);
    }
}
