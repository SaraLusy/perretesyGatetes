package com.empresa.perretesGatetes.domain.entities;

import javax.persistence.*;

@Entity
@Table (name="estado_pedido")
public class EstadoPedido {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long codigoEstadoPedido;
    private String nombre;
    private String descripcion;


    public long getCodigoEstadoPedido() {return codigoEstadoPedido;}

    public void setCodigoEstadoPedido(long codigoEstadoPedido) {this.codigoEstadoPedido = codigoEstadoPedido;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
}
