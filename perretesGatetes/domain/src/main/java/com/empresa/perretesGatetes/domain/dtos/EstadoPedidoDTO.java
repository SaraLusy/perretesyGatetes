package com.empresa.perretesGatetes.domain.dtos;

import com.empresa.perretesGatetes.domain.entities.EstadoPedido;
import org.springframework.util.StringUtils;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EstadoPedidoDTO {
    private long codigoEstadoPedido;

    private String nombre;

    private String descripcion;

    public static EstadoPedidoDTO toDTO(EstadoPedido estadoPedido){
        EstadoPedidoDTO estadoPedidoDTO = new EstadoPedidoDTO();

        if(estadoPedido == null){
            return estadoPedidoDTO;
        }

        estadoPedidoDTO.setCodigoEstadoPedido((estadoPedido.getCodigoEstadoPedido() > 0) ? estadoPedido.getCodigoEstadoPedido() : 0);
        estadoPedidoDTO.setNombre((StringUtils.hasText(estadoPedido.getNombre())) ? estadoPedido.getNombre() : "");
        estadoPedidoDTO.setDescripcion((StringUtils.hasText(estadoPedido.getDescripcion())) ? estadoPedido.getDescripcion() : "");

        return estadoPedidoDTO;
    }

    public static List<EstadoPedidoDTO> toDTO(List<EstadoPedido> estadosPedido){
        if(estadosPedido == null){
            return Arrays.asList();
        }
        return estadosPedido.stream()
                .map(EstadoPedidoDTO::toDTO)
                .collect(Collectors.toList());
    }

    public static EstadoPedido toDomain(EstadoPedidoDTO estadoPedidoDTO){
        EstadoPedido estadoPedido = new EstadoPedido();
        if(estadoPedidoDTO == null){
            return null;
        }
        estadoPedido.setCodigoEstadoPedido((estadoPedidoDTO.getCodigoEstadoPedido() > 0) ? estadoPedido.getCodigoEstadoPedido() : 0);
        estadoPedido.setNombre((StringUtils.hasText(estadoPedidoDTO.getNombre())) ? estadoPedidoDTO.getNombre() : "");
        estadoPedido.setDescripcion((StringUtils.hasText(estadoPedidoDTO.getDescripcion())) ? estadoPedido.getDescripcion(): "");

        return estadoPedido;
    }

    public static List<EstadoPedido> toDomain(List<EstadoPedidoDTO> estadosPedidoDTO){
        if(estadosPedidoDTO == null){
            return Arrays.asList();
        }
        return estadosPedidoDTO.stream()
                .map(EstadoPedidoDTO::toDomain)
                .collect(Collectors.toList());
    }

    public long getCodigoEstadoPedido() {return codigoEstadoPedido;}

    public void setCodigoEstadoPedido(long codigoEstadoPedido) {this.codigoEstadoPedido = codigoEstadoPedido;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
}
