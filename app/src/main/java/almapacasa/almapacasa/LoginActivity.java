package almapacasa.almapacasa;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.couchbase.lite.android.AndroidContext;

import org.json.JSONArray;

import Classes.Async;
import Classes.MyBDD;

public class LoginActivity extends AppCompatActivity implements Async.AsyncResponse {

    public final static String DATABASE = "com.almapacasa.almapacasa.DATABASE";
    EditText textIdentifiant;
    EditText textMDP;
    MyBDD BDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textIdentifiant = (EditText)findViewById(R.id.EditTextIdentifiant);
        textMDP = (EditText)findViewById(R.id.EditTextMDP);
        BDD = MyBDD.getmInstance(new AndroidContext(this));
        BDD.emptyBDD();
        BDD.emptyInfirmiere();
        boolean var = BDD.autoLogin();
        if(var){
            Intent intent = new Intent(this,Accueil.class);
            startActivity(intent);
            finish();
        }
    }

    public void login(View view)
    {
        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ntwrkInfo = connMgr.getActiveNetworkInfo();
        if(ntwrkInfo != null && ntwrkInfo.isConnected())
        {
        Async asyncTask = (Async) new Async(new Async.AsyncResponse() {
            @Override
            public void processFinish(JSONArray output) {
                if(output != null && output.length() > 0) {
                    Log.d("LOGIN", "Connexion réussie");
                    boolean test = BDD.saveLogin(output);
                    if(test)
                    {
                        Log.d("LOGIN", "SaveBDD reussie");
                        importGlobal();
                        launchAccueil();
                    }
                }
                else
                {
                    Log.d("LOGIN", "FAILED");
                }
            }
        }).execute("login", textIdentifiant.getText().toString(), textMDP.getText().toString());
        }else
        {
            Toast.makeText(getApplicationContext(), "Pas d'accès Internet", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void processFinish(JSONArray output) {

    }

    public void launchAccueil()
    {
        Intent intent = new Intent(this,Accueil.class);
        startActivity(intent);
        finish();
    }

    public void importGlobal()
    {
        Async asyncTask = (Async) new Async(new Async.AsyncResponse() {
            @Override
            public void processFinish(JSONArray output) {
            if(output != null && output.length() > 0) {
                BDD.importDonnee(output);
            }
            }
        }).execute("import", textIdentifiant.getText().toString(), textMDP.getText().toString());
    }
}
