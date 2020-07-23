package com.linkedin.curso.android.mitiempo.mitiempo.other.examples.javacode;

public class ArtistaJava {
    private long id;
    private String nombre;
    private String url;
    private String descripcion;

    public ArtistaJava(long id, String nombre, String url, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.url = url;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "ArtistaJava{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", url='" + url + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
