package com.android.json;

import com.android.bananaView.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Json extends Activity {
	private Button btAceptar;
	private Button bt1;
	private ConexionABaseDeDatos bd = new ConexionABaseDeDatos(this);

	TextView lbl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.activity_json);
		btAceptar =  (Button) findViewById(R.id.btAceptar);
		btAceptar.setOnClickListener(new OnClickListener(){
            public void onClick(View arg0) {
            	rellenarTablaCanales();
            	
            }
            });
		bt1 =  (Button) findViewById(R.id.bt1);
		bt1.setOnClickListener(new OnClickListener(){
            public void onClick(View arg0) {
             	leerCanales();
            }
            });
		
	}
	public void MessageBox(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_json, menu);
		return true;
	}
	
	private void leerCanales(){
		this.bd.abrir();
		lbl = (TextView)findViewById(R.id.lbl);
		lbl.setText(this.bd.leerCanal());
		this.bd.cerrar();
	}
	private void rellenarTablaCanales(){
		this.bd.abrir();
		this.bd.eliminarTodosLosCanales();
	     bd.insertarCanal(1, "Guatevision" );
	     bd.insertarCanal(2, "Fox Sports");
	     bd.insertarCanal(3, "History Channel");
	     bd.insertarCanal(4, "National Geographic");
	     MessageBox("Datos Rellenados");
	     this.bd.cerrar();
	}



}
