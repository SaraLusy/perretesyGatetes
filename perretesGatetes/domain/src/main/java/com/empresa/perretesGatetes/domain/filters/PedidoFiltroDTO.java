package com.empresa.perretesGatetes.domain.filters;

import java.math.BigDecimal;
import java.util.Date;

public class PedidoFiltroDTO extends BaseFiltroDTO {
    private Long codigoPedido;
    private Long codigoUsuario;
    private Long codigoDireccion;
    private Long codigoMetodoPago;
    private Date fechaPedido;
    private BigDecimal importeTotal;

    private Date fechaModificacion;

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Long getCodigoDireccion() {
        return codigoDireccion;
    }

    public void setCodigoDireccion(Long codigoDireccion) {
        this.codigoDireccion = codigoDireccion;
    }

    public Long getCodigoMetodoPago() {
        return codigoMetodoPago;
    }

    public void setCodigoMetodoPago(Long codigoMetodoPago) {
        this.codigoMetodoPago = codigoMetodoPago;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Long getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(Long codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public BigDecimal getImporteTotal() {return importeTotal;}

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public Date getFechaModificacion() {return fechaModificacion;}

    public void setFechaModificacion(Date fechaModificacion) {this.fechaModificacion = fechaModificacion;}
}
