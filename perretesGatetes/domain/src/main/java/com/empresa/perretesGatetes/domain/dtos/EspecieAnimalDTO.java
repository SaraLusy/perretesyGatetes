package com.empresa.perretesGatetes.domain.dtos;

import org.springframework.util.StringUtils;
import com.empresa.perretesGatetes.domain.entities.EspecieAnimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EspecieAnimalDTO {
    private long codigoEspecieAnimal;
    private String nombre;
    private String descripcion;

    public static EspecieAnimalDTO toDTO(EspecieAnimal especieAnimal){
        EspecieAnimalDTO especieAnimalDTO = new EspecieAnimalDTO();

        if (especieAnimal == null){
            return especieAnimalDTO;
        }

        especieAnimalDTO.setCodigoEspecieAnimal((especieAnimalDTO.getCodigoEspecieAnimal() > 0) ? especieAnimal.getCodigoEspecieAnimal(): 0);
        especieAnimalDTO.setNombre((StringUtils.hasText(especieAnimal.getNombre())) ? especieAnimal.getNombre() : "");
        especieAnimalDTO.setDescripcion((StringUtils.hasText(especieAnimal.getDescripcion())) ? especieAnimal.getDescripcion(): "");

        return especieAnimalDTO;
    }

    public static List<EspecieAnimalDTO> toDTO(List<EspecieAnimal> especiesAnimales){
        if(especiesAnimales == null){
            return Arrays.asList();
        }
        return especiesAnimales.stream()
                .map(EspecieAnimalDTO::toDTO)
                .collect(Collectors.toList());
    }

    public static EspecieAnimal toDomain(EspecieAnimalDTO especieAnimalDTO){
        EspecieAnimal especieAnimal = new EspecieAnimal();
        if(especieAnimalDTO == null){
            return null;
        }
        especieAnimal.setCodigoEspecieAnimal((especieAnimalDTO.getCodigoEspecieAnimal() >= 0) ? especieAnimalDTO.getCodigoEspecieAnimal() : 0);
        especieAnimal.setNombre((StringUtils.hasText(especieAnimalDTO.getNombre())) ? especieAnimalDTO.getNombre() : "");
        especieAnimal.setDescripcion((StringUtils.hasText(especieAnimalDTO.getDescripcion())) ? especieAnimalDTO.getDescripcion() : "");

        return especieAnimal;
    }

    public static List<EspecieAnimal> toDomain(List<EspecieAnimalDTO> especieAnimalDTOList){
        if(especieAnimalDTOList == null){
            return Arrays.asList();
        }
        return especieAnimalDTOList.stream()
                .map(EspecieAnimalDTO::toDomain)
                .collect(Collectors.toList());
    }

    public long getCodigoEspecieAnimal() {
        return codigoEspecieAnimal;
    }

    public void setCodigoEspecieAnimal(long codigoEspecieAnimal) {
        this.codigoEspecieAnimal = codigoEspecieAnimal;
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
