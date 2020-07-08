package com.zeroapps.v2b_twitterclient;

public class Tweet {
    private String _nombre;
    private String _contenido;

    public Tweet(){
        this.setNombre("Default");
        this.setContenido("DefaultContent");
    }

    Tweet(String nombre, String contenido){
        this.setNombre(nombre);
        this.setContenido(contenido);
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String getContenido() {
        return _contenido;
    }

    public void setContenido(String _contenido) {
        this._contenido = _contenido;
    }
}
