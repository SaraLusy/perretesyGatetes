package com.empresa.perretesGatetes.domain.dtos;

import com.empresa.perretesGatetes.domain.entities.Pedido;
import org.springframework.util.CollectionUtils;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class PedidoDTO {
    private long codigoPedido;
    private BigDecimal importeTotal;
    private BigDecimal pesoTotal;
    private Date fechaPedido;
    private Date fechaEstimadaEntrega;
    private Date fechaModificacion;

    private MetodoPagoDTO metodoPago;
    private DireccionDTO direccion;
    private UsuarioDTO usuario;

    private List<PedidosEstadosDTO> pedidosEstados;

    public static PedidoDTO toDTO(Pedido pedido){
        return PedidoDTO.toDTO(pedido, Arrays.asList(MetodoPagoDTO.class, DireccionDTO.class, UsuarioDTO.class));
    }

    public static PedidoDTO toDTO(Pedido pedido, List<Class<?>> includeRelacion) {
        PedidoDTO pedidoDTO = new PedidoDTO();

        if (pedido == null) {
            return pedidoDTO;
        }
        pedidoDTO.setCodigoPedido((pedido.getCodigoPedido() >= 0 ? pedido.getCodigoPedido() : 0));
        pedidoDTO.setImporteTotal(pedido.getImporteTotal() != null ? pedido.getImporteTotal() : BigDecimal.valueOf(0));
        pedidoDTO.setPesoTotal(pedido.getPesoTotal() != null ? pedido.getPesoTotal() : BigDecimal.valueOf(0));
        pedidoDTO.setFechaPedido(pedido.getFechaPedido() != null ? pedido.getFechaPedido() : new Date());
        pedidoDTO.setFechaEstimadaEntrega(pedido.getFechaEstimadaEntrega());
        pedidoDTO.setFechaModificacion(pedido.getFechaModificacion());

        //Hacer funcion para calcular la fecha estimada de entrega +5 dias
        if(!CollectionUtils.isEmpty(includeRelacion) && includeRelacion.contains(MetodoPagoDTO.class)) {
            pedidoDTO.setMetodoPago(MetodoPagoDTO.toDTO(pedido.getMetodoPago()));
        }

        if(!CollectionUtils.isEmpty(includeRelacion) && includeRelacion.contains(RolDTO.class)) {
            pedidoDTO.setUsuario(UsuarioDTO.toDTO(pedido.getUsuario()));
        }

        if (!CollectionUtils.isEmpty(includeRelacion) && includeRelacion.contains(DireccionDTO.class)) {
            pedidoDTO.setDireccion(DireccionDTO.toDTO(pedido.getDireccion()));
        }

        return pedidoDTO;
    }

    public static List<PedidoDTO> toDTO(List<Pedido> pedidos) {
        if(pedidos == null){
            return Collections.emptyList();
        }
        return pedidos.stream()
                .map(PedidoDTO::toDTO)
                .collect(Collectors.toList());
    }

    public static Pedido toDomain(PedidoDTO pedidoDTO) {
        if (pedidoDTO == null) {
            return null;
        }

        Pedido pedido = new Pedido();
        pedido.setCodigoPedido(pedidoDTO.getCodigoPedido() > 0 ? pedidoDTO.getCodigoPedido() : 0);
        pedido.setImporteTotal(pedidoDTO.getImporteTotal() != null ? pedidoDTO.getImporteTotal() : BigDecimal.valueOf(0));
        pedido.setPesoTotal(pedidoDTO.getPesoTotal() != null ? pedidoDTO.getPesoTotal() : BigDecimal.valueOf(0));

        pedido.setFechaPedido(pedidoDTO.getFechaPedido() != null ? pedidoDTO.getFechaPedido() : new Date());
        pedido.setFechaEstimadaEntrega(pedidoDTO.getFechaEstimadaEntrega());
        pedido.setFechaModificacion(pedidoDTO.getFechaModificacion());

        pedido.setMetodoPago(MetodoPagoDTO.toDomain(pedidoDTO.getMetodoPago()));
        pedido.setUsuario(UsuarioDTO.toDomain(pedidoDTO.getUsuario()));
        pedido.setDireccion(DireccionDTO.toDomain(pedidoDTO.getDireccion()));

        return pedido;
    }
    //	----------------------------------------------------------------------------

    public long getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(long codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

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

    public MetodoPagoDTO getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPagoDTO metodoPago) {
        this.metodoPago = metodoPago;
    }

    public DireccionDTO getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionDTO direccion) {
        this.direccion = direccion;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public List<PedidosEstadosDTO> getPedidosEstados() {
        return pedidosEstados;
    }

    public void setPedidosEstados(List<PedidosEstadosDTO> pedidosEstados) {
        this.pedidosEstados = pedidosEstados;
    }
}
