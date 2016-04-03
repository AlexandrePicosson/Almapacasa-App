package almapacasa.almapacasa;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.EditText;
import android.widget.TextView;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;
import com.couchbase.lite.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Classes.Async;
import Classes.MyBDD;

public class Accueil extends AppCompatActivity implements Async.AsyncResponse {

    public static TextView editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        editText  =(TextView)findViewById(R.id.zoneAffichage);
       helloCBL();
    }

    public void chargement(View view)
    {
        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
        {
            String stringURL = "http://192.168.1.53/almapacasa/test.php";
           Async asyncTask = (Async) new Async(new Async.AsyncResponse() {
               @Override
               public void processFinish(String output) {
                    editText.setText(output);
               }
           }).execute(stringURL);
        }
        else
        {

            editText.setText("Erreur d'accès à internet");
        }
    }

    private void helloCBL() {
        //On instancie une connexion à la base de donnée interne
        MyBDD BDD = new MyBDD(new AndroidContext(this));
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

    @Override
    public void processFinish(String output) {

    }
}
