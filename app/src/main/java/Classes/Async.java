package Classes;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Alexandre on 03/04/2016.
 * This class has for purpose to connect to a web server and get data from it
 */
public class Async extends AsyncTask<String, Void, JSONObject> {

    public interface AsyncResponse {
        void processFinish(String output);
    }

    public AsyncResponse delegate = null;
    public Async (AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        try {
            return downloadUrl(params[0]);
        } catch (IOException e){
            return null;
        }
    }

    @Override
    protected void onPostExecute(JSONObject result){
         //delegate.processFinish(result);
    }

    private JSONObject downloadUrl(String myurl) throws IOException{
        InputStream is = null;
        try{
            URL url = new URL(myurl);
            int len = 500;
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            //Start Query
            connection.connect();
            int response = connection.getResponseCode();
            Log.d("CONTEXT", "the response is " + response);
            is = new BufferedInputStream(connection.getInputStream());
            JSONObject laREP = ReadJSON(is);
            connection.disconnect();
            return laREP;
        } finally {
            if (is != null)
            {
                is.close();
            }
        }
    }

    private JSONObject ReadJSON(InputStream is) {
        StringBuilder reponse = null;
        JSONObject objJSON = null;
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            reponse = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                reponse.append(line);
            }
            Log.d("JSON PARSE", reponse.toString());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        try{
            objJSON = new JSONObject(reponse.toString());
        }catch (JSONException e)
        {
            e.printStackTrace();
        }

        return objJSON;
    }

    public String readIt(InputStream is, int length) throws IOException {
        Reader reader;

        reader = new InputStreamReader(is, "UTF-8");
        char[] buffer = new char[length];
        reader.read(buffer);
        return new String(buffer);
    }
}
