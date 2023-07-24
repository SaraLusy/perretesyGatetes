package com.empresa.perretesGatetes.domain.dtos;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import com.empresa.perretesGatetes.domain.entities.Direccion;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DireccionDTO {
	private Long codigoDireccion;
	private String codigoPostal;
	private String localidad;
	private String comunidad;
	private String direccion;
	private int numero;
	private String escalera;
	private String piso;
	private String letra;

	private TipoDireccionDTO tipoDireccion;
	private UsuarioDTO usuario;

	public static DireccionDTO toDTO(Direccion direccion){
		return DireccionDTO.toDTO(direccion, Arrays.asList(TipoDireccionDTO.class, UsuarioDTO.class));
	}

	public static DireccionDTO toDTO(Direccion direccion, List<Class<?>> includeRelacion){
		DireccionDTO direccionDTO = new DireccionDTO();

		if(direccion == null){
			return direccionDTO;
		}
		direccionDTO.setCodigoDireccion(direccion.getCodigoDireccion() >= 0 ? direccion.getCodigoDireccion() : 0);
		direccionDTO.setCodigoPostal(StringUtils.hasText(direccion.getCodigoPostal()) ? direccion.getCodigoPostal() : "");
		direccionDTO.setLocalidad(StringUtils.hasText(direccion.getLocalidad()) ? direccion.getLocalidad() : "");
		direccionDTO.setComunidad(StringUtils.hasText(direccion.getComunidad()) ? direccion.getComunidad() : "");
		direccionDTO.setDireccion(StringUtils.hasText(direccion.getDireccion()) ? direccion.getDireccion() : "");
		direccionDTO.setNumero(direccion.getNumero() >= 0 ? direccion.getNumero() : 0);
		direccionDTO.setEscalera(StringUtils.hasText(direccion.getEscalera()) ? direccion.getEscalera() : "");
		direccionDTO.setPiso(StringUtils.hasText(direccion.getPiso()) ? direccion.getPiso() : "");
		direccionDTO.setLetra(StringUtils.hasText(direccion.getLetra()) ? direccion.getLetra() : "");

		if(!CollectionUtils.isEmpty(includeRelacion) && includeRelacion.contains(TipoDireccionDTO.class)) {
			direccionDTO.setTipoDireccion(TipoDireccionDTO.toDTO(direccion.getTipoDireccion(), includeRelacion));
		}

		if(!CollectionUtils.isEmpty(includeRelacion) && includeRelacion.contains(UsuarioDTO.class) ) {
			direccionDTO.setUsuario(UsuarioDTO.toDTO(direccion.getUsuario(), includeRelacion));
		}

		return direccionDTO;
	}

	public static List<DireccionDTO> toDTO(List<Direccion> direcciones){
		if(direcciones == null){
			return Arrays.asList();
		}
		return direcciones.stream()
				.map(dir -> DireccionDTO.toDTO(dir))
				.collect(Collectors.toList());
	}

	public static List<DireccionDTO> toDTO(List<Direccion> direcciones, List<Class<?>> includeRelacion){
		return direcciones.stream()
				.map(dir-> DireccionDTO.toDTO(dir, includeRelacion))
				.collect(Collectors.toList());
	}

	public static Direccion toDomain(DireccionDTO direccionDTO){
		if(direccionDTO == null){
			return  null;
		}
		Direccion direccion = new Direccion();
		direccion.setCodigoDireccion(direccionDTO.getCodigoDireccion() >= 0 ? direccionDTO.getCodigoDireccion() : 0);
		direccion.setCodigoPostal(StringUtils.hasText(direccionDTO.getCodigoPostal()) ? direccionDTO.getCodigoPostal() : "");
		direccion.setLocalidad(StringUtils.hasText(direccionDTO.getLocalidad()) ? direccionDTO.getLocalidad() : "");
		direccion.setComunidad(StringUtils.hasText(direccionDTO.getComunidad()) ? direccionDTO.getComunidad() : "");
		direccion.setDireccion(StringUtils.hasText(direccionDTO.getDireccion()) ? direccionDTO.getDireccion() : "");
		direccion.setNumero(direccionDTO.getNumero() > 0 ? direccion.getNumero() : 0);
		direccion.setEscalera(StringUtils.hasText(direccionDTO.getEscalera()) ? direccion.getEscalera() : "");
		direccion.setPiso(StringUtils.hasText(direccionDTO.getPiso()) ? direccionDTO.getPiso() : "");
		direccion.setLetra(StringUtils.hasText(direccionDTO.getLetra()) ? direccionDTO.getLetra() : "");
		direccion.setUsuario(UsuarioDTO.toDomain(direccionDTO.getUsuario()));
		direccion.setTipoDireccion(TipoDireccionDTO.toDomain(direccionDTO.getTipoDireccion()));

		return direccion;
	}

	public static List<Direccion> toDomain(List<DireccionDTO> direccionesDTO){
		if(direccionesDTO == null){
			return Arrays.asList();
		}
		return direccionesDTO.stream()
				.map(dirDTO -> DireccionDTO.toDomain(dirDTO))
				.collect(Collectors.toList());
	}

//	----------------------------------------------------------------------------

	public Long getCodigoDireccion() {
		return codigoDireccion;
	}

	public void setCodigoDireccion(Long codigoDireccion) {
		this.codigoDireccion = codigoDireccion;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getComunidad() {
		return comunidad;
	}

	public void setComunidad(String comunidad) {
		this.comunidad = comunidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getEscalera() {
		return escalera;
	}

	public void setEscalera(String escalera) {
		this.escalera = escalera;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public TipoDireccionDTO getTipoDireccion() {
		return tipoDireccion;
	}

	public void setTipoDireccion(TipoDireccionDTO tipoDireccionDTO) {
		this.tipoDireccion = tipoDireccionDTO;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuarioDTO) {
		this.usuario = usuarioDTO;
	}

}
