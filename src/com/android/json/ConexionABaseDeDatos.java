package com.android.json;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;


public class ConexionABaseDeDatos extends SQLiteOpenHelper {

	private String crearCanal="CREATE TABLE `canal` ( `idcanales` INT NOT NULL ,  `nombre` VARCHAR(45) NULL ,  PRIMARY KEY (`idcanales`) );";
	private String crearPrograma= "CREATE TABLE `programa` ( `idprogramas` INT NOT NULL , `nombre` VARCHAR(60) NULL, `horaDeInicio` VARCHAR(60) NULL , `canales_idcanales` INT NOT NULL , PRIMARY KEY (`idprogramas`,`idprogramas`), FOREIGN KEY (`canales_idcanales` ) REFERENCES `canal` (`idcanales` ));";
	public ConexionABaseDeDatos(Context context){
		 super(context,"bdProgramas",null, 1);
		}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(crearCanal);
		db.execSQL(crearPrograma);
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("DROP TABLE canal");
		db.execSQL("DROP TABLE programa");
		this.onCreate(db);
		
	}
	public void insertarCanal(int id, String nombre) {
		try{
		ContentValues valores = new ContentValues();
		valores.put("idcanales", id);
		valores.put("nombre", nombre);
	
		this.getWritableDatabase().insert("canal", null, valores);
		}
		catch(Exception e){
			
		}
	}
	//insertar Programas
	public void insertarPrograma(int id, String nombre, String horaDeInicio, int idCanal) {
		ContentValues valores = new ContentValues();
		valores.put("idprogramas", id);
		valores.put("nombre", nombre);
		valores.put("horaDeInicio", horaDeInicio);
		valores.put("canales_idcanales", idCanal);
		this.getWritableDatabase().insert("programa", null, valores);

	}
	
	//eliminar Programas 
	public void eliminarTodosLosProgramas(){
		this.getWritableDatabase().delete("programa", null, null);
	}
	//eliminar Canales
	public void eliminarTodosLosCanales(){
		this.getWritableDatabase().delete("canal", null, null);
	}
	
	//leer programas
	public String leerPrograma(int idCanal){
		String resultado ="resultado:";
		String columna[] = {"nombre","horaDeInicio"};
		Cursor c = this.getReadableDatabase().query("programas", columna, null, null, null, null, null);
			int indexNombre= c.getColumnIndex(columna[0]);
			int indexHoraDeInicio= c.getColumnIndex(columna[1]);
			
		while (c.moveToNext()) {
			resultado= resultado+c.getString(indexHoraDeInicio)+" - "+c.getString(indexNombre)+"\n";
			
		}		
		return resultado;
	}
	
	//obtener Canales 
	public String leerCanal(){
		String resultado ="resultado:";
		String columna[] = {"nombre"};
		Cursor c = this.getReadableDatabase().query("canal", columna, null, null, null, null, null);
			int indexNombre= c.getColumnIndex(columna[0]);
		while (c.moveToNext()) {
			resultado= resultado+c.getString(indexNombre)+"\n";
			
		}		
		return resultado;
	}
	public void abrir(){
		this.getWritableDatabase();
	}
	public void cerrar(){
			this.close();
	}


}
