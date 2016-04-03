package Classes;
import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;
import com.couchbase.lite.Document;

/**
 * Created by Alexandre on 02/04/2016.
 */
public class MyBDD {

    public static final String DB_NAME = "almapacasa";
    public static final String TAG = "almapacasa-db";
    private static Manager myManager = null;
    private static Database myDatabase = null;

    public MyBDD (AndroidContext TheContext)
    {
        try{
            myManager = new Manager(TheContext, Manager.DEFAULT_OPTIONS);
            myDatabase = myManager.getDatabase(DB_NAME);
            Log.e(TAG, "Database is created");
        }catch (Exception e)
        {
            Log.e(TAG, "Error getting the database", e);
        }
    }













}
