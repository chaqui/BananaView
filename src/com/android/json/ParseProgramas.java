package com.android.json;

import org.json.*;
import android.app.*;

public class ParseProgramas {
	private Activity activity;
	private JSONObject jObject; 
	private ProgressDialog progressDialog = null;
	private Runnable runReadAndParseJSON;

	public ParseProgramas(Activity a){
	   activity = a;
	}

	public void readAndParseJsonCanles() throws JSONException{
	   runReadAndParseJSON = new Runnable() {
	      @Override
	      public void run() {
	       try{
	    	   readJSONProgramas();
	       } catch(Exception e){}
	      }
	   };
	   Thread thread = new Thread(null, runReadAndParseJSON,"bgReadJSONProgramas");
	   thread.start();
	   progressDialog = ProgressDialog.show(activity, "Descargando información", "Por favor espere",true);
	}

	private void readJSONProgramas() throws JSONException{
	   jObject = JSONManager.getJSONfromURL("http://apibananatv-chaqui.rhcloud.com/?canal=1");
	   if(jObject != null)
		   parseJsonProgramas(jObject.getJSONArray("programas"));
	   activity.runOnUiThread(returnRes);
	}

	private void parseJsonProgramas(JSONArray programasArray) throws JSONException{
	  for(int i = 0; i < programasArray.length(); i++){
	  
	  }
	}

	private Runnable returnRes = new Runnable(){ 
	 @Override 
	 public void run() {
	  progressDialog.dismiss();
	 }
	};
}
