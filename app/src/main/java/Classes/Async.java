package Classes;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

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
public class Async extends AsyncTask<String, Void, String> {

    public interface AsyncResponse {
        void processFinish(String output);
    }

    public AsyncResponse delegate = null;
    public Async (AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            return downloadUrl(params[0]);
        } catch (IOException e){
            return "Unable to retrieve the web page";
        }
    }

    @Override
    protected void onPostExecute(String result){
         delegate.processFinish(result);
    }

    private String downloadUrl(String myurl) throws IOException{
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
            is = connection.getInputStream();
            return readIt(is, len);
        } finally {
            if (is != null)
            {
                is.close();
            }
        }
    }

    public String readIt(InputStream is, int length) throws IOException {
        Reader reader;
        reader = new InputStreamReader(is, "UTF-8");
        char[] buffer = new char[length];
        reader.read(buffer);
        return new String(buffer);
    }
}
