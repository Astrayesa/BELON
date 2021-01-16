package com.bengkelonline.belon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static String TAG = "DataBaseHelper";
    private static String DB_NAME = "bengkel.sqlite";
    private static int DB_VERSION = 1;
    private final File DB_FILE;
    private SQLiteDatabase sqLiteDatabase;
    private final Context dbcontext;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        DB_FILE = context.getDatabasePath(DB_NAME);
        this.dbcontext = context;
    }

    public void createDataBase() throws IOException {
        // If the database does not exist, copy it from the assets.
        boolean mDataBaseExist = checkDataBase();
        if (!mDataBaseExist) {
            this.getReadableDatabase();
            this.close();
            try {
                // Copy the database from assests
                copyDataBase();
                Log.e(TAG, "createDatabase database created");
            } catch (IOException e) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    // Check that the database file exists in databases folder
    private boolean checkDataBase() {
        return DB_FILE.exists();
    }

    // Copy the database from assets
    private void copyDataBase() throws IOException {
        InputStream targetdb = dbcontext.getAssets().open(DB_NAME);
        OutputStream sourcedb = new FileOutputStream(DB_FILE);
        byte[] buffer = new byte[1024];
        int mLength;
        while ((mLength = targetdb.read(buffer)) > 0) {
            sourcedb.write(buffer, 0, mLength);
        }
        sourcedb.flush();
        sourcedb.close();
        targetdb.close();
    }

    // Open the database, so we can query it
    public boolean openDataBase() throws SQLException {
        // Log.v("DB_PATH", DB_FILE.getAbsolutePath());
        sqLiteDatabase = SQLiteDatabase.openDatabase(String.valueOf(DB_FILE), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        // mDataBase = SQLiteDatabase.openDatabase(DB_FILE, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return sqLiteDatabase != null;
    }

    @Override
    public synchronized void close() {
        if (sqLiteDatabase != null) {
            sqLiteDatabase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
