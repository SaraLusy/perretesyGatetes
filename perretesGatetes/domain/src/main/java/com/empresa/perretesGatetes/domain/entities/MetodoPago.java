package com.empresa.perretesGatetes.domain.entities;

import javax.persistence.*;

@Entity
@Table(name="metodo_pago")
public class MetodoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigoMetodoPago;
    private String nombre;
    private String descripcion;

    public long getCodigoMetodoPago() {return codigoMetodoPago;}

    public void setCodigoMetodoPago(long codigoMetodoPago) {
        this.codigoMetodoPago = codigoMetodoPago;
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
