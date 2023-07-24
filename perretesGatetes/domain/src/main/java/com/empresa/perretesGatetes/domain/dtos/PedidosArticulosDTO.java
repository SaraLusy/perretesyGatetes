package com.empresa.perretesGatetes.domain.dtos;

import com.empresa.perretesGatetes.domain.entities.PedidosArticulos;
import org.springframework.util.CollectionUtils;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PedidosArticulosDTO {

    private ArticuloDTO articulo;
    private PedidoDTO pedido;
    private int cantidad;

    public static PedidosArticulosDTO toDTO(PedidosArticulos pedidosArticulos) {
        return PedidosArticulosDTO.toDTO(pedidosArticulos, Arrays.asList(ArticuloDTO.class, PedidoDTO.class));
    }

    public static PedidosArticulosDTO toDTO(PedidosArticulos pedidosArticulos, List<Class<?>> includeRelacion){
        PedidosArticulosDTO pedidosArticulosDTO = new PedidosArticulosDTO();

        if(pedidosArticulos == null){
            return pedidosArticulosDTO;
        }
        if (!CollectionUtils.isEmpty(includeRelacion) && includeRelacion.contains(ArticuloDTO.class)){
            pedidosArticulosDTO.setArticulo(ArticuloDTO.toDTO(pedidosArticulos.getArticulo()));
        }
        if (!CollectionUtils.isEmpty(includeRelacion) && includeRelacion.contains(PedidoDTO.class)) {
            pedidosArticulosDTO.setPedido(PedidoDTO.toDTO(pedidosArticulos.getPedido(), includeRelacion));
        }
        pedidosArticulosDTO.setCantidad(Math.max(pedidosArticulos.getCantidad(), 0));

        return pedidosArticulosDTO;
    }

    public static List<PedidosArticulosDTO> toDTO(List<PedidosArticulos> pedidosArticulos) {
        if (pedidosArticulos == null){
            Arrays.asList();
        }
        return pedidosArticulos.stream()
                .map(PedidosArticulosDTO::toDTO)
                .collect(Collectors.toList());
    }

    public static List<PedidosArticulosDTO> toDTO(List<PedidosArticulos> pedidosArticulos, List<Class<?>> includeRelacion){
        if (pedidosArticulos == null) {
            Arrays.asList();
        }
        return pedidosArticulos.stream()
                .map(pA -> PedidosArticulosDTO.toDTO(pA, includeRelacion))
                .collect(Collectors.toList());
    }

    public static PedidosArticulos toDomain(PedidosArticulosDTO pedidosArticulosDTO){
        PedidosArticulos pedidosArticulos = new PedidosArticulos();

        if(pedidosArticulosDTO == null){
            return null;
        }
        pedidosArticulos.setArticulo(ArticuloDTO.toDomain(pedidosArticulosDTO.getArticulo()));
        pedidosArticulos.setPedido(PedidoDTO.toDomain(pedidosArticulosDTO.getPedido()));
        pedidosArticulos.setCantidad(Math.max(pedidosArticulosDTO.getCantidad(), 0));

        return pedidosArticulos;
    }

    public static List<PedidosArticulos> toDomain(List<PedidosArticulosDTO> pedidosArticulosDTO){
        if(pedidosArticulosDTO == null){
            return Arrays.asList();
        }
        return pedidosArticulosDTO.stream()
                .map(PedidosArticulosDTO::toDomain)
                .collect(Collectors.toList());
    }



    public ArticuloDTO getArticulo() {return articulo;}

    public void setArticulo(ArticuloDTO articulo) {this.articulo = articulo;}

    public PedidoDTO getPedido() {return pedido;}

    public void setPedido(PedidoDTO pedido) {this.pedido = pedido;}

    public int getCantidad() {return cantidad;}

    public void setCantidad(int cantidad) {this.cantidad = cantidad;}
}
