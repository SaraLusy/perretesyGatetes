package com.empresa.perretesGatetes.domain.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PedidosEstadosID implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long codigoPedido;

    private Long codigoEstadoPedido;

    private Long codigoUsuario;

    public Long getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(Long codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public Long getCodigoEstadoPedido() {
        return codigoEstadoPedido;
    }

    public void setCodigoEstadoPedido(Long codigoEstadoPedido) {
        this.codigoEstadoPedido = codigoEstadoPedido;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidosEstadosID that = (PedidosEstadosID) o;
        return codigoPedido.equals(that.codigoPedido) && codigoEstadoPedido.equals(that.codigoEstadoPedido) && codigoUsuario.equals(that.codigoUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoPedido, codigoEstadoPedido, codigoUsuario);
    }
}
