package com.empresa.perretesGatetes.domain.dtos;

import com.empresa.perretesGatetes.domain.entities.Rol;
import org.springframework.util.StringUtils;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RolDTO {
    private long codigoRol;
    private String nombre;
    private String descripcion;

    public static RolDTO toDTO(Rol rol){
        RolDTO rolDTO = new RolDTO();

        if(rol == null){
            return rolDTO;
        }

        rolDTO.setCodigoRol((rol.getCodigoRol() > 0) ? rol.getCodigoRol() : 0);
        rolDTO.setNombre((StringUtils.hasText(rol.getNombre())) ? rol.getNombre() : "");
        rolDTO.setDescripcion((StringUtils.hasText(rol.getDescripcion())) ? rol.getDescripcion() : "");

        return rolDTO;
    }

    public static List<RolDTO> toDTO(List<Rol> roles){
        if(roles == null){
            return Arrays.asList();
        }
        return roles.stream()
                .map(rol -> RolDTO.toDTO(rol))
                .collect(Collectors.toList());
    }

    public static Rol toDomain(RolDTO rolDTO){
        Rol rol = new Rol();
        if(rolDTO == null){
            return null;
        }
        rol.setCodigoRol((rolDTO.getCodigoRol() > 0) ? rolDTO.getCodigoRol() : 0);
        rol.setNombre((StringUtils.hasText(rolDTO.getNombre())) ? rolDTO.getNombre() : "");
        rol.setDescripcion((StringUtils.hasText(rolDTO.getDescripcion())) ? rolDTO.getDescripcion() : "");

        return rol;
    }

    public static List<Rol> toDomain(List<RolDTO> rolesDTO){
        if(rolesDTO == null){
            return Arrays.asList();
        }
        return rolesDTO.stream()
                .map(rolDTO -> RolDTO.toDomain(rolDTO))
                .collect(Collectors.toList());
    }

    //	----------------------------------------------------------------------------

    public long getCodigoRol() {return codigoRol;}

    public void setCodigoRol(long codigoRol) {this.codigoRol = codigoRol;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

}
