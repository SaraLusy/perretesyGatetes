package com.empresa.perretesGatetes.domain.dtos;

import com.empresa.perretesGatetes.domain.entities.MetodoPago;
import org.springframework.util.StringUtils;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MetodoPagoDTO {
    private long codigoMetodoPago;
    private String nombre;
    private String descripcion;

    public static MetodoPagoDTO toDTO(MetodoPago metodoPago){
        MetodoPagoDTO metodoPagoDTO = new MetodoPagoDTO();

        if(metodoPago == null){
            return metodoPagoDTO;
        }

        metodoPagoDTO.setCodigoMetodoPago((metodoPago.getCodigoMetodoPago() > 0) ? metodoPago.getCodigoMetodoPago() : 0);
        metodoPagoDTO.setNombre((StringUtils.hasText(metodoPago.getNombre())) ? metodoPago.getNombre() : "");
        metodoPagoDTO.setDescripcion((StringUtils.hasText(metodoPago.getDescripcion())) ? metodoPago.getDescripcion() : "");

        return metodoPagoDTO;
    }

    public static List<MetodoPagoDTO> toDTO(List<MetodoPago> metodosPago){
        if(metodosPago == null){
            return Arrays.asList();
        }
        return metodosPago.stream()
                .map(MetodoPagoDTO::toDTO)
                .collect(Collectors.toList());
    }

    public static MetodoPago toDomain(MetodoPagoDTO metodoPagoDTO){
        MetodoPago metodoPago = new MetodoPago();

        if(metodoPagoDTO == null){
            return null;
        }
        metodoPago.setCodigoMetodoPago((metodoPagoDTO.getCodigoMetodoPago() > 0) ? metodoPagoDTO.getCodigoMetodoPago() : 0);
        metodoPago.setNombre((StringUtils.hasText(metodoPagoDTO.getNombre())) ? metodoPagoDTO.getNombre() : "");
        metodoPago.setDescripcion((StringUtils.hasText(metodoPagoDTO.getDescripcion())) ? metodoPagoDTO.getDescripcion() : "");

        return metodoPago;
    }

    public static List<MetodoPago> toDomain(List<MetodoPagoDTO> metodosPagoDTO){
        if(metodosPagoDTO == null){
            return Arrays.asList();
        }
        return metodosPagoDTO.stream()
                .map(MetodoPagoDTO::toDomain)
                .collect(Collectors.toList());
    }

    //	----------------------------------------------------------------------------

    public long getCodigoMetodoPago() {return codigoMetodoPago;}

    public void setCodigoMetodoPago(long codigoMetodoPago) {
        this.codigoMetodoPago = codigoMetodoPago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
