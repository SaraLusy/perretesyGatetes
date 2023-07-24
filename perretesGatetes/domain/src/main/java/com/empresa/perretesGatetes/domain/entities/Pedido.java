package com.empresa.perretesGatetes.domain.entities;

import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name="pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigoPedido;
    private BigDecimal importeTotal;
    private BigDecimal pesoTotal;
    private Date fechaPedido;
    private Date fechaEstimadaEntrega;
    private Date fechaModificacion;

    @Transient
    private EstadoPedido estadoPedidoMasReciente;

    @OneToMany(mappedBy="pedido")
    private Set<PedidosEstados> pedidosEstados;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="codigoMetodoPago")
    private MetodoPago metodoPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="codigoUsuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="codigoDireccion")
    private Direccion direccion;

    public Set<PedidosEstados> getPedidosEstados() {
        return this.pedidosEstados;
    }

    public Long getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(Long codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public void setCodigoPedido(long codigoPedido) {this.codigoPedido = codigoPedido;}

    public BigDecimal getImporteTotal() {return importeTotal;}

    public void setImporteTotal(BigDecimal importeTotal) {this.importeTotal = importeTotal;}

    public BigDecimal getPesoTotal() {return pesoTotal;}

    public void setPesoTotal(BigDecimal pesoTotal) {this.pesoTotal = pesoTotal;}

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Date getFechaEstimadaEntrega() {
        return fechaEstimadaEntrega;
    }

    public void setFechaEstimadaEntrega(Date fechaEstimadaEntrega) {
        this.fechaEstimadaEntrega = fechaEstimadaEntrega;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public EstadoPedido getEstadoPedidoMasReciente() {
        return estadoPedidoMasReciente;
    }

    public void setEstadoPedidoMasReciente(EstadoPedido estadoPedidoMasReciente) {
        this.estadoPedidoMasReciente = estadoPedidoMasReciente;
    }

    public void setPedidosEstados(Set<PedidosEstados> pedidosEstados) {
        this.pedidosEstados = pedidosEstados;
    }

    public void calcularEstadoPedidoMasReciente() {
        //TODO Como calcular el estado del pedido mas reciente
//        if (!CollectionUtils.isEmpty(this.pedidosEstados)) {
//            this.estadoPedidoMasReciente = this.pedidosEstados.stream()
//                    .max(Comparator.comparing(PedidosEstados::getFechaCambioEstado));
//        }
    }
}
