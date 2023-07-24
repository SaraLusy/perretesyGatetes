package com.empresa.perretesGatetes.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="articulo")
public class Articulo {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long codigoArticulo;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private BigDecimal pesoUnitario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="codigoEspecieAnimal")
    private EspecieAnimal especieAnimal;


    public Long getCodigoArticulo() {return codigoArticulo;}

    public void setCodigoArticulo(Long codigoArticulo) {this.codigoArticulo = codigoArticulo;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public BigDecimal getPrecio() {return precio;}

    public void setPrecio(BigDecimal precio) {this.precio = precio;}

    public BigDecimal getPesoUnitario() {return pesoUnitario;}

    public void setPesoUnitario(BigDecimal pesoUnitario) {this.pesoUnitario = pesoUnitario;}

    public EspecieAnimal getEspecieAnimal() {return especieAnimal;}

    public void setEspecieAnimal(EspecieAnimal especieAnimal) {this.especieAnimal = especieAnimal;}
}
