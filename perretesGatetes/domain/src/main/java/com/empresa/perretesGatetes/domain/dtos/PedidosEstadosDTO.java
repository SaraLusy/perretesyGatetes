package com.empresa.perretesGatetes.domain.dtos;

import com.empresa.perretesGatetes.domain.entities.PedidosEstados;
import org.springframework.util.CollectionUtils;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PedidosEstadosDTO {

    private UsuarioDTO usuario;
    private PedidoDTO pedido;
    private EstadoPedidoDTO estadoPedido;
    private Date fecha_cambio_estado;

    public static PedidosEstadosDTO toDTO(PedidosEstados pedidosEstados) {
        return PedidosEstadosDTO.toDTO(pedidosEstados, Arrays.asList(UsuarioDTO.class, PedidoDTO.class, EstadoPedidoDTO.class));
    }

    public static PedidosEstadosDTO toDTO(PedidosEstados pedidosEstados, List<Class<?>> includeRelacion) {
        PedidosEstadosDTO pedidosEstadosDTO = new PedidosEstadosDTO();

        if (pedidosEstados == null) {
            return pedidosEstadosDTO;
        }
        if (!CollectionUtils.isEmpty(includeRelacion) && includeRelacion.contains(UsuarioDTO.class)) {
            pedidosEstadosDTO.setUsuario(UsuarioDTO.toDTO(pedidosEstados.getUsuario(), includeRelacion));
        }
        if (!CollectionUtils.isEmpty(includeRelacion) && includeRelacion.contains(PedidoDTO.class)) {
            pedidosEstadosDTO.setPedido(PedidoDTO.toDTO(pedidosEstados.getPedido(), includeRelacion));
        }
//        if (!CollectionUtils.isEmpty(includeRelacion) && includeRelacion.contains(EstadoPedidoDTO.class)) {
//            pedidosEstadosDTO.setEstadoPedido(EstadoPedidoDTO.toDTO(pedidosEstados.getEstadoPedido(), includeRelacion));
//        }
        //Como inicializar una fecha por defecto
        //TODO
        // pedidosEstadosDTO.setFecha_cambio_estado(pedidosEstados.getFecha_cambio_estado());

        return pedidosEstadosDTO;
    }

    public static List<PedidosEstadosDTO> toDTO(List<PedidosEstados> pedidosEstados) {
        if (pedidosEstados == null){
            Arrays.asList();
        }
        return pedidosEstados.stream()
                .map(PedidosEstadosDTO::toDTO)
                .collect(Collectors.toList());
    }

    public static List<PedidosEstadosDTO> toDTO(List<PedidosEstados> pedidosEstados, List<Class<?>> includeRelacion) {
        if (pedidosEstados == null) {
            Arrays.asList();
        }
        return pedidosEstados.stream()
                .map(pE -> PedidosEstadosDTO.toDTO(pE, includeRelacion))
                .collect(Collectors.toList());
    }

    public static PedidosEstados toDomain(PedidosEstadosDTO pedidosEstadosDTO){
        PedidosEstados pedidosEstados = new PedidosEstados();

        if(pedidosEstadosDTO == null){
            return null;
        }
        pedidosEstados.setEstadoPedido(EstadoPedidoDTO.toDomain(pedidosEstadosDTO.getEstadoPedido()));
        pedidosEstados.setPedido(PedidoDTO.toDomain(pedidosEstadosDTO.getPedido()));
        pedidosEstados.setUsuario(UsuarioDTO.toDomain(pedidosEstadosDTO.getUsuario()));
        //TODO
        //Como inicializar una fecha a un valor por defecto
        //pedidosEstados.setFecha_cambio_estado(pedidosEstadosDTO.getFecha_cambio_estado());

        return pedidosEstados;
    }

    public static List<PedidosEstados> toDomain(List<PedidosEstadosDTO> pedidosEstadosDTO){
        if(pedidosEstadosDTO == null){
            return Arrays.asList();
        }
        return pedidosEstadosDTO.stream()
                .map(PedidosEstadosDTO::toDomain)
                .collect(Collectors.toList());
    }

    public UsuarioDTO getUsuario() {return usuario;}

    public void setUsuario(UsuarioDTO usuario) {this.usuario = usuario;}

    public PedidoDTO getPedido() {return pedido;}

    public void setPedido(PedidoDTO pedido) {this.pedido = pedido;}

    public EstadoPedidoDTO getEstadoPedido() {return estadoPedido;}

    public void setEstadoPedido(EstadoPedidoDTO estadoPedido) {this.estadoPedido = estadoPedido;}

    public Date getFecha_cambio_estado() {return fecha_cambio_estado;}

    public void setFecha_cambio_estado(Date fecha_cambio_estado) {
        this.fecha_cambio_estado = fecha_cambio_estado;
    }
}
