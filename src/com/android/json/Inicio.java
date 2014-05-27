package com.android.json;

import android.content.Context;

public class Inicio {
	private ConexionABaseDeDatos bd;
	private COnAWebServiceJson webService;

	// constructor para agregar los contextos a cada objeto
	public Inicio(Context contexto) {
		bd = new ConexionABaseDeDatos(contexto);
		webService = new COnAWebServiceJson(contexto);
	}

	public void almacenar() {
		almacenarCanales();
		almacenarProgramas();

	}

	private void almacenarCanales() {
		this.bd.abrir();
		this.bd.eliminarTodosLosCanales();
		bd.insertarCanal(1, "Guatevision");
		bd.insertarCanal(2, "Fox Sports");
		bd.insertarCanal(3, "History Channel");
		bd.insertarCanal(4, "National Geographic Channel");
		bd.insertarCanal(5, "Cartoon Network");
		bd.insertarCanal(6, "Trece Vision");
		bd.insertarCanal(7, "MTV");
		bd.insertarCanal(8, "Cine Canal");
		bd.insertarCanal(9, "The Film Zone");
		bd.insertarCanal(10, "Warner Channel");
		this.bd.cerrar();
	}

	private void almacenarProgramas() {
		bd.eliminarTodosLosProgramas();
		for (int i = 1; i < 11; i++) {
			webService.ObtenerJson(String.valueOf(i));
		}

	}
}
