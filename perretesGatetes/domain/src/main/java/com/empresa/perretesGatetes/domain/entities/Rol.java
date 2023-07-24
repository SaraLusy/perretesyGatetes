package com.empresa.perretesGatetes.domain.entities;

import javax.persistence.*;

@Entity
@Table(name="rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigoRol;
    private String nombre;
    private String descripcion;

    public long getCodigoRol() {return codigoRol;}

    public void setCodigoRol(long codigoRol) {this.codigoRol = codigoRol;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
}
