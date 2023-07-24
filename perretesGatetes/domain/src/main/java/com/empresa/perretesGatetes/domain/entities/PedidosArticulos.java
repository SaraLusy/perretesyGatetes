package com.empresa.perretesGatetes.domain.entities;

import com.empresa.perretesGatetes.domain.dtos.*;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name="pedidos_articulos")
public class PedidosArticulos {

    @EmbeddedId
    private PedidosArticulosID codigoPedidosArticulos;

    @ManyToOne
    @MapsId("codigoPedido")
    @JoinColumn(name="codigoPedido")
    private Pedido pedido;

    @ManyToOne
    @MapsId("codigoArticulo")
    @JoinColumn(name="codigoArticulo")
    private Articulo articulo;

    private int cantidad;


    public PedidosArticulosID getCodigoPedidosArticulos() {
        return codigoPedidosArticulos;
    }

    public void setCodigoPedidosArticulos(PedidosArticulosID codigoPedidosArticulos) {
        this.codigoPedidosArticulos = codigoPedidosArticulos;
    }

    public Pedido getPedido() {return pedido;}

    public void setPedido(Pedido pedido) {this.pedido = pedido;}

    public Articulo getArticulo() {return articulo;}

    public void setArticulo(Articulo articulo) {this.articulo = articulo;}

    public int getCantidad() {return cantidad;}

    public void setCantidad(int cantidad) {this.cantidad = cantidad;}
}
