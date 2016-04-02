package almapacasa.almapacasa;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;
import com.couchbase.lite.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Accueil extends AppCompatActivity {

    public static final String DB_NAME = "couchbaseevents";
    public static final String TAG = "couchbaseevents";
    private Manager manager;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
       helloCBL();
    }

    private void helloCBL() {
        manager = null;
        database = null;
        try {
            manager = new Manager(new AndroidContext(this), Manager.DEFAULT_OPTIONS);
            database = manager.getDatabase(DB_NAME);
        } catch (Exception e) {
            Log.e(TAG, "Error getting database", e);
            return;
        }
        // Create the document
        String documentId = createDocument(database);
        Log.d(TAG, documentId);
        Log.d(TAG, database.toString() + "hello c'est le document");
        Document retrieveDocument = database.getDocument(documentId);
        Log.d(TAG, String.valueOf(retrieveDocument.getProperties()));
        Log.d(TAG, String.valueOf(database.getDocumentCount()));
        try {
            database.delete();
        }
        catch (CouchbaseLiteException e)
        {

        }
    /* Get and output the contents */
        //outputContents(database, documentId);
    /* Update the document and add an attachment */
        //updateDoc(database, documentId);
        // Add an attachment
        //addAttachment(database, documentId);
    /* Get and output the contents with the attachment */
        //outputContentsWithAttachment(database, documentId);

    }

    private String createDocument(Database database) {
        // Create a new document and add data
        Document document = database.createDocument();
        String documentId = document.getId();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "Big Party");
        map.put("location", "My House");
        try {
            // Save the properties to the document
            document.putProperties(map);
        } catch (CouchbaseLiteException e) {
            Log.e(TAG, "Error putting", e);
        }
        return documentId;
    }

    public Database getDatabaseInstance() throws CouchbaseLiteException {
        if ((this.database == null) & (this.manager != null)) {
            this.database = manager.getDatabase(DB_NAME);
        }
        return database;
    }
    public Manager getManagerInstance() throws IOException {
        if (manager == null) {
            manager = new Manager(new AndroidContext(this), Manager.DEFAULT_OPTIONS);
        }
        return manager;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accueil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
