package com.empresa.perretesGatetes.domain.types;

public enum RolType {
    ADMIN("ADMIN","Administrador"),
    DEPARTAMENTO_FINANCIERO("DP","Departamento Financiero"),
    TRABAJADOR("TRA","Trabajador"),
    CLIENTE("CLI","Cliente");

    private String nombre;
    private String descripcion;

    RolType(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
}
