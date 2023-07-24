package com.empresa.perretesGatetes.domain.entities;

import javax.persistence.*;

@Entity
@Table(name="tipo_direccion")
public class TipoDireccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigoTipoDireccion;
    private String nombre;
    private String descripcion;

    public long getcodigoTipoDireccion() {
        return codigoTipoDireccion;
    }

    public void setcodigoTipoDireccion(long codigoTipoDireccion) {
        this.codigoTipoDireccion = codigoTipoDireccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
