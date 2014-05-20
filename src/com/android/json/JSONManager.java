package com.android.json;

import java.io.*;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.json.*;

public class JSONManager {
	public static JSONObject getJSONfromURL(String url){
	     InputStream is = null;
	     String result = "";
	     JSONObject json = null;																													
	 
	      try{
	         HttpClient httpclient = new DefaultHttpClient();
	         HttpGet httpGet = new HttpGet(url);
	         HttpResponse response = httpclient.execute(httpGet);
	         HttpEntity entity = response.getEntity();
	         is = entity.getContent();
	     }catch(Exception e){}

	      try{
	         BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	         StringBuilder sb = new StringBuilder();
	         String line = null;
	         while ((line = reader.readLine()) != null) {
	             sb.append(line + "\n");
	         }
	         is.close();
	         result=sb.toString();
	     } catch(Exception e){}

	     try{
	         json = new JSONObject(result);
	     }catch(JSONException e){}

	      return json;
	 }
}
