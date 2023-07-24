package com.empresa.perretesGatetes.domain.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="pedidos_estados")
public class PedidosEstados {

    @EmbeddedId
    private PedidosEstadosID codigoPedidosEstados;

    @ManyToOne
    @MapsId("codigoPedido")
    @JoinColumn(name="codigoPedido")
    private Pedido pedido;

    @ManyToOne
    @MapsId("codigoEstadoPedido")
    @JoinColumn(name="codigoEstadoPedido")
    private EstadoPedido estadoPedido;

    @ManyToOne
    @MapsId("codigoUsuario")
    @JoinColumn(name="codigoUsuario")
    private Usuario usuario;

    Date fechaCambioEstado;

    public Pedido getPedido() {return pedido;}

    public void setPedido(Pedido pedido) {this.pedido = pedido;}

    public EstadoPedido getEstadoPedido() {return estadoPedido;}

    public void setEstadoPedido(EstadoPedido estadoPedido) {this.estadoPedido = estadoPedido;}

    public Usuario getUsuario() {return usuario;}

    public void setUsuario(Usuario usuario) {this.usuario = usuario;}

    public PedidosEstadosID getCodigoPedidosEstados() {
        return codigoPedidosEstados;
    }

    public void setCodigoPedidosEstados(PedidosEstadosID codigoPedidosEstados) {
        this.codigoPedidosEstados = codigoPedidosEstados;
    }

    public Date getFechaCambioEstado() {
        return fechaCambioEstado;
    }

    public void setFechaCambioEstado(Date fechaCambioEstado) {
        this.fechaCambioEstado = fechaCambioEstado;
    }
}
