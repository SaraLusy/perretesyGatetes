package com.empresa.perretesGatetes.domain.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PedidosArticulosID implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long codigoPedido;
    private Long codigoArticulo;

    public Long getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(Long codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public Long getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(Long codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidosArticulosID that = (PedidosArticulosID) o;
        return Objects.equals(codigoPedido, that.codigoPedido) && Objects.equals(codigoArticulo, that.codigoArticulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoPedido, codigoArticulo);
    }
}
