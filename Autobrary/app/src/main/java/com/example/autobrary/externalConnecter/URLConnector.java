package com.example.autobrary.externalConnecter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.autobrary.main.Rpage;
import com.example.autobrary.notice.Notice2Fragment;
import com.example.autobrary.notice.NoticeFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.util.EntityUtils;


public class URLConnector extends AsyncTask<Void, Integer, Boolean> {

    private final String HOST = "https://www.slobrary.com/app/";

    public HttpResponse getResult() {
        return result;
    }
    Handler handler = new Handler();
    private HttpResponse result;
    ProgressDialog progressDialog = new ProgressDialog(Rpage.context);
    private String URL;
    private HashMap<String, String> param = new HashMap<String, String>();
    private String longData;
    public URLConnector(String url, HashMap param){
        URL = HOST + url;
        this.param = param;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = HOST + URL;
    }

    public HashMap<String, String> getParam() {
        return param;
    }

    public void setParam(HashMap<String, String> param) {
        this.param = param;
    }


    public String getData(){
        return longData;
    }
    @Override
    protected void onPreExecute() {
//        progressDialog.setMessage("ProgressDialog running...");
//        progressDialog.setCancelable(false);
//        progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Horizontal);
//        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
//        progressDialog.dismiss();
    }

    @Override
    protected void onCancelled() {
//        progressDialog.dismiss();
        super.onCancelled();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        final HttpClient httpclient = new DefaultHttpClient();
        HttpResponse reps = null;
        final HttpPost httppost = new HttpPost(URL);

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {

            }
        });

        try {

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(param.size());
            // 파라미터 추가
            Set key = param.keySet();
            for (Iterator it = key.iterator(); it.hasNext(); ) {
                String keyName = (String) it.next();
                String valueName = (String) param.get(keyName);
                nameValuePairs.add(new BasicNameValuePair(keyName, valueName));
            }

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            // Execute HTTP Post Request

            reps = httpclient.execute(httppost);
            longData = EntityUtils.toString(reps.getEntity());
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        this.result = reps;
        return null;
    }
}
