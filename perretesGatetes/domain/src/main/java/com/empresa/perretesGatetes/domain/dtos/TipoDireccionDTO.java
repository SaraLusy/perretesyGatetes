package com.empresa.perretesGatetes.domain.dtos;

import com.empresa.perretesGatetes.domain.entities.TipoDireccion;
import org.springframework.util.StringUtils;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TipoDireccionDTO {
    private long codigoTipoDireccion;
    private String nombre;
    private String descripcion;

    public static TipoDireccionDTO toDTO(TipoDireccion tipoDireccion){
        TipoDireccionDTO tipoDireccionDTO = new TipoDireccionDTO();

        if(tipoDireccion == null){
            return  tipoDireccionDTO;
        }

        tipoDireccionDTO.setcodigoTipoDireccion((tipoDireccion.getcodigoTipoDireccion() > 0) ? tipoDireccion.getcodigoTipoDireccion() : 0);
        tipoDireccionDTO.setNombre((StringUtils.hasText(tipoDireccion.getNombre())) ? tipoDireccion.getNombre() : "");
        tipoDireccionDTO.setDescripcion((StringUtils.hasText(tipoDireccion.getDescripcion())) ? tipoDireccion.getDescripcion() : "");

        return tipoDireccionDTO;
    }

    public static List<TipoDireccionDTO> toDTO(List<TipoDireccion> tiposDireccion){
        if(tiposDireccion == null){
            return Arrays.asList();
        }
        return tiposDireccion.stream()
                .map(TipoDireccionDTO::toDTO)
                .collect(Collectors.toList());
    }

    public static TipoDireccionDTO toDTO(TipoDireccion tipoDireccion, List<Class<?>> includeRelacion) {
        TipoDireccionDTO tipoDireccionDTO = new TipoDireccionDTO();

        if (tipoDireccion == null) {
            return tipoDireccionDTO;
        }

        tipoDireccionDTO.setcodigoTipoDireccion((tipoDireccion.getcodigoTipoDireccion()));
        tipoDireccionDTO.setNombre(StringUtils.hasText(tipoDireccion.getNombre()) ? tipoDireccion.getNombre() : "");
        tipoDireccionDTO.setDescripcion(StringUtils.hasText(tipoDireccion.getDescripcion()) ? tipoDireccion.getDescripcion() : "");

        return tipoDireccionDTO;
    }

    public static TipoDireccion toDomain(TipoDireccionDTO tipoDireccionDTO){
        TipoDireccion  tipoDireccion = new TipoDireccion();
        if(tipoDireccionDTO == null){
            return null;
        }
        tipoDireccion.setcodigoTipoDireccion((tipoDireccionDTO.getcodigoTipoDireccion() > 0) ? tipoDireccionDTO.getcodigoTipoDireccion() : 0);
        tipoDireccion.setNombre((StringUtils.hasText(tipoDireccionDTO.getNombre())) ? tipoDireccionDTO.getNombre() : "");
        tipoDireccion.setDescripcion((StringUtils.hasText(tipoDireccionDTO.getDescripcion())) ?tipoDireccionDTO.getDescripcion() : "");

        return tipoDireccion;
    }

    public static List<TipoDireccion> toDomain(List<TipoDireccionDTO> tiposDireccionDTO){
        if(tiposDireccionDTO == null){
            return Arrays.asList();
        }
        return tiposDireccionDTO.stream()
                .map(TipoDireccionDTO::toDomain)
                .collect(Collectors.toList());
    }

    //	----------------------------------------------------------------------------

    public long getcodigoTipoDireccion() {
        return codigoTipoDireccion;
    }

    public void setcodigoTipoDireccion(long codigoTipoDireccion) {
        this.codigoTipoDireccion = codigoTipoDireccion;
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
