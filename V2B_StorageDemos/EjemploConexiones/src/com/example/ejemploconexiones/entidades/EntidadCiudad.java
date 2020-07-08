package com.example.ejemploconexiones.entidades;

public class EntidadCiudad {

	private String _nombre;
	private double _latitud;
	private double _longitud;
	
	EntidadCiudad() {
		this.setNombre("");
		this.setLatitud(-1);
		this.setLongitud(-1);
	}
	
	EntidadCiudad( String nombre, double latitud, double longitud ) {
		this.setNombre(nombre);
		this.setLatitud(latitud);
		this.setLongitud(longitud);
	}

	public String getnombre() {
		return _nombre;
	}

	public void setNombre(String _nombre) {
		this._nombre = _nombre;
	}

	public double getLatitud() {
		return _latitud;
	}

	public void setLatitud(double _latitud) {
		this._latitud = _latitud;
	}

	public double getLongitud() {
		return _longitud;
	}

	public void setLongitud(double _longitud) {
		this._longitud = _longitud;
	}
}
