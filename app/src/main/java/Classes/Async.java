package Classes;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Alexandre on 03/04/2016.
 * This class has for purpose to connect to a web server and get data from it
 */
public class Async extends AsyncTask<String, Void, JSONArray> {

    StringBuilder sbParams;
    String LOGIN_URL = "http://192.168.1.38/almapacasa/androidLogin.php";
    String IMPORT_URL = "http://192.168.1.38/almapacasa/";


    public interface AsyncResponse {
        void processFinish(JSONArray output);
    }

    public AsyncResponse delegate = null;

    public Async (AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected JSONArray doInBackground(String... params) {
        if(params[0].equals("login"))
        {
            try{
                return login(params[1],params[2]);
            } catch (IOException e) {
                return null;
            }
        }
        if(params[0].equals("import"))
        {
            try {
                return downloadUrl(params[1], params[2]);
            } catch (IOException e){
                return null;
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONArray result){
         delegate.processFinish(result);
    }

    private JSONArray login(String identifiant, String mdp) throws IOException{
        InputStream is = null;
        try{
            URL url = new URL(LOGIN_URL);
            try {
                sbParams = new StringBuilder();
                sbParams.append("&").append("id").append("=").append(URLEncoder.encode(identifiant, "UTF-8"));
                sbParams.append("&").append("mdp").append("=").append(URLEncoder.encode(mdp, "UTF-8"));
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.connect();
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(sbParams.toString());
            wr.flush();
            wr.close();
            is = new BufferedInputStream(connection.getInputStream());
            JSONArray laREP = ReadJSON(is);
            connection.disconnect();
            return laREP;
        }catch (IOException e)
        {
            e.printStackTrace();
            return null;
        } finally {
            if (is != null)
            {
                is.close();
            }
        }
    }

    private JSONArray downloadUrl(String identifiant, String mdp) throws IOException{
        InputStream is = null;

        try{
            URL url = new URL(IMPORT_URL);

            return //laREP;
        }catch (IOException e)
        {
            e.printStackTrace();
            return null;
        } finally {
            if (is != null)
            {
                is.close();
            }
        }
    }

    private JSONArray ReadJSON(InputStream is) throws IOException {
        StringBuilder reponse = null;
        JSONArray objJSON = null;
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
            objJSON = new JSONArray(reponse.toString());
        }catch (JSONException e)
        {
            e.printStackTrace();
            Log.d("hey", e.toString());
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
