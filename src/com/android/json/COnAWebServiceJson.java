package com.android.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class COnAWebServiceJson {
	private ConexionABaseDeDatos bd;
	private int k = 0;

	// Constructor
	public COnAWebServiceJson(Context contexto) {
		bd = new ConexionABaseDeDatos(contexto);
	}

	// Función Para pasar de json a cadena
	private static String getStringFromInputStream(InputStream is) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String linea;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			while ((linea = br.readLine()) != null) {
				sb.append(linea);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	public void ObtenerJson(String numeroCanal) {
		try {
			// HttpCliente
			DefaultHttpClient httpCliente = new DefaultHttpClient();
			// Url del Webservice
			String url = "http://apibananatv-chaqui.rhcloud.com/?canal="
					+ numeroCanal;

			// Obteniendo datos
			HttpGet httpget = new HttpGet(url);
			httpget.addHeader("accept", "application/json");
			HttpResponse response;
			response = httpCliente.execute(httpget);

			// Convirtiendolos en un objeto JSON
			String JsonResult = getStringFromInputStream(response.getEntity()
					.getContent());
			JSONObject object = new JSONObject(JsonResult);
			JSONArray programas = new JSONArray(object
					.getJSONArray("programas").toString());

			// iniciando la base de datos
			bd.abrir();

			// Almacenando los canales
			for (int i = 0; i < programas.length(); i++) {
				JSONObject jsonPrograma = programas.getJSONObject(i);
				JSONObject nombreJson = jsonPrograma.getJSONObject("programa");
				String nombre = nombreJson.getString("nombre");
				int id = nombreJson.getInt("id");
				String horaDeInicio = nombreJson.getString("horario");
				int idCanal = Integer.parseInt(numeroCanal);
				bd.insertarPrograma(id + k, nombre, horaDeInicio, idCanal);
			}
			bd.close();
			k = k + programas.length();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
