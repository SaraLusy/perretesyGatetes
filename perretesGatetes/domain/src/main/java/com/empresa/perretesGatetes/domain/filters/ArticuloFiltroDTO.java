package com.empresa.perretesGatetes.domain.filters;

import java.math.BigDecimal;

public class ArticuloFiltroDTO extends BaseFiltroDTO {

    private Long codigoEspecieAnimal;

    private BigDecimal precio;

    private BigDecimal pesoUnitario;

    public Long getCodigoEspecieAnimal() {
        return codigoEspecieAnimal;
    }

    public void setCodigoEspecieAnimal(Long codigoEspecieAnimal) {
        this.codigoEspecieAnimal = codigoEspecieAnimal;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getPesoUnitario() {
        return pesoUnitario;
    }

    public void setPesoUnitario(BigDecimal pesoUnitario) {
        this.pesoUnitario = pesoUnitario;
    }
}
