package utils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.*;

import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class HttpService {
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONArray readJsonArrayFromUrl(String url) throws IOException, JSONException {
        URLConnection urlConnection = new URL(url).openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        InputStream is = urlConnection.getInputStream();

        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);

            JSONArray json = new JSONArray(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        URLConnection urlConnection = new URL(url).openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        InputStream is = urlConnection.getInputStream();

        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);


            JSONObject json = new
                    JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static String getCaseByCountries()  {
        JSONArray json = null;
        try {
            json = readJsonArrayFromUrl("https://disease.sh/v3/covid-19/countries");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json.toString();
    }
    public  static  JSONArray getCasesByCountriesAsJson(){
        JSONArray json = null;
        try {
            json = readJsonArrayFromUrl("https://disease.sh/v3/covid-19/countries");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }
    public static JSONObject getTotalCase() {

        JSONObject json = null;
        try { json = readJsonFromUrl("https://disease.sh/v3/covid-19/all");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(json.getInt("cases"));
        System.out.println(json.toString());
        return json;
    }

}
