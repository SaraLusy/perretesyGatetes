package com.empresa.perretesGatetes.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "especie_animal")
public class EspecieAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoEspecieAnimal;
    private String nombre;
    private String descripcion;

    public Long getCodigoEspecieAnimal() {return codigoEspecieAnimal;}

    public void setCodigoEspecieAnimal(Long codigoEspecieAnimal) {this.codigoEspecieAnimal = codigoEspecieAnimal;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
}
