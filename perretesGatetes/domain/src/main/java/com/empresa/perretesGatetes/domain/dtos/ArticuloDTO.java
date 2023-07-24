package com.empresa.perretesGatetes.domain.dtos;

import com.empresa.perretesGatetes.domain.entities.Articulo;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArticuloDTO {
    private Long codigoArticulo;

    private String nombre;

    private String descripcion;

    private BigDecimal precio;

    private BigDecimal pesoUnitario;

    private EspecieAnimalDTO especieAnimal;

    public static ArticuloDTO toDTO(Articulo articulo){
        return ArticuloDTO.toDTO(articulo, Arrays.asList(EspecieAnimalDTO.class));
    }

    private static ArticuloDTO toDTO(Articulo articulo, List<Class<?>> includeRelacion){
        ArticuloDTO articuloDTO = new ArticuloDTO();

        if(articulo == null){
            return articuloDTO;
        }
        articuloDTO.setCodigoArticulo(articulo.getCodigoArticulo() >= 0 ? articulo.getCodigoArticulo() : 0);
        articuloDTO.setNombre(StringUtils.hasText(articulo.getNombre()) ? articulo.getNombre() : "");
        articuloDTO.setDescripcion(StringUtils.hasText(articulo.getDescripcion()) ? articulo.getDescripcion() : "");
        articuloDTO.setPrecio(articulo.getPrecio() != null ? articulo.getPrecio() : BigDecimal.valueOf(0));
        articuloDTO.setPesoUnitario(articulo.getPesoUnitario() != null ? articulo.getPesoUnitario() : BigDecimal.valueOf(0));

        if(!CollectionUtils.isEmpty(includeRelacion) && includeRelacion.contains(EspecieAnimalDTO.class)) {
            articuloDTO.setEspecieAnimal(EspecieAnimalDTO.toDTO(articulo.getEspecieAnimal()));
        }
        return articuloDTO;
    }

    public static List<ArticuloDTO> toDTO(List<Articulo> articulos){
        if(articulos == null){
            return Arrays.asList();
        }
        return articulos.stream()
                .map(ArticuloDTO::toDTO)
                .collect(Collectors.toList());
    }

    public static List<ArticuloDTO> toDTO(List<Articulo> articulos, List<Class<?>> includeRelacion){
        return articulos.stream()
                .map(art-> ArticuloDTO.toDTO(art, includeRelacion))
                .collect(Collectors.toList());
    }

    public static Articulo toDomain(ArticuloDTO articuloDTO){
        if(articuloDTO == null){
            return null;
        }
        Articulo articulo = new Articulo();

        articulo.setCodigoArticulo(articuloDTO.getCodigoArticulo() >= 0 ? articuloDTO.getCodigoArticulo() : 0);
        articulo.setNombre(StringUtils.hasText(articulo.getNombre()) ? articuloDTO.getNombre() : "");
        articulo.setDescripcion(StringUtils.hasText(articuloDTO.getDescripcion()) ? articuloDTO.getDescripcion() : "");
        articulo.setPrecio(articuloDTO.getPrecio() != null ? articuloDTO.getPrecio() : BigDecimal.valueOf(0));
        articulo.setPesoUnitario(articuloDTO.getPesoUnitario() != null ? articuloDTO.getPesoUnitario() : BigDecimal.valueOf(0));

        return articulo;
    }

    public  static List<Articulo> toDomain(List<ArticuloDTO> articulosDTO){
        if(articulosDTO == null){
            return Arrays.asList();
        }
        return articulosDTO.stream()
                .map(ArticuloDTO::toDomain)
                .collect(Collectors.toList());
    }

    public Long getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(Long codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getPesoUnitario() {
        return pesoUnitario;
    }

    public void setPesoUnitario(BigDecimal pesoUnitario) {
        this.pesoUnitario = pesoUnitario;
    }

    public EspecieAnimalDTO getEspecieAnimal() {
        return especieAnimal;
    }

    public void setEspecieAnimal(EspecieAnimalDTO especieAnimal) {
        this.especieAnimal = especieAnimal;
    }
}
