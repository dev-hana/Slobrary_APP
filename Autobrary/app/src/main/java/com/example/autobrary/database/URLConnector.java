package com.example.autobrary.database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderIterator;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.params.HttpParams;


public class URLConnector {
    private final String HOST = "https://www.slobrary.com/";
    private String result;
    private String URL;
    private HashMap<String, String> param = new HashMap<String, String>();

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

    public HttpResponse execute() {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse reps = null;
        HttpPost httppost = new HttpPost(URL);
        try {

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(param.size());

            // 파라미터 추가
            Set key = param.keySet();
            for (Iterator it = key.iterator(); it.hasNext();) {
                String keyName = (String) it.next();
                String valueName = (String) param.get(keyName);
                nameValuePairs.add(new BasicNameValuePair(keyName, valueName));
            }

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            // Execute HTTP Post Request

            reps = httpclient.execute(httppost);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        return(reps);
    }
}