package com.empresa.perretesGatetes.domain.dtos;

import com.empresa.perretesGatetes.domain.entities.Usuario;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.*;
import java.util.stream.Collectors;

public class UsuarioDTO {
	private long codigoUsuario;
	private String dni;
	private String nombre;
	private String apellidos;
	private Date fechaNacimiento;
	private String telefono;
	private String contrasenia;
	private String email;
	private MetodoPagoDTO metodoPago;
	private RolDTO rol;
	private List<DireccionDTO> direcciones;

	private List<PedidoDTO> pedidos;

	public static UsuarioDTO toDTO(Usuario usuario) {
		return UsuarioDTO.toDTO(usuario, Arrays.asList( MetodoPagoDTO.class, DireccionDTO.class, RolDTO.class));
	}

	public static UsuarioDTO toDTO(Usuario usuario, List<Class<?>> includeRelacion) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();

		if (usuario == null) {
			return usuarioDTO;
		}

		usuarioDTO.setCodigoUsuario(usuario.getCodigoUsuario() > 0 ? usuario.getCodigoUsuario() : 0);
		usuarioDTO.setDni(StringUtils.hasText(usuario.getDni()) ? usuario.getDni() : "");
		usuarioDTO.setNombre(StringUtils.hasText(usuario.getNombre()) ? usuario.getNombre() : "");
		usuarioDTO.setApellidos(StringUtils.hasText(usuario.getApellidos()) ? usuario.getApellidos() : "");
		//TODO
		//usuario.setFecha_nacimiento(usuarioDTO.getFechaNacimiento() ? usuarioDTO.getFechaNacimiento() : "");
		//activado, contrasenia
		usuarioDTO.setEmail(StringUtils.hasText(usuario.getEmail()) ? usuario.getEmail() : "");
		usuarioDTO.setTelefono(StringUtils.hasText(usuario.getTelefono()) ? usuario.getTelefono() : "");

		if(!CollectionUtils.isEmpty(includeRelacion) && includeRelacion.contains(MetodoPagoDTO.class)) {
			usuarioDTO.setMetodoPago(MetodoPagoDTO.toDTO(usuario.getMetodoPago()));
		}

		if(!CollectionUtils.isEmpty(includeRelacion) && includeRelacion.contains(RolDTO.class)) {
			usuarioDTO.setRol(RolDTO.toDTO(usuario.getRol()));
		}

		if (!CollectionUtils.isEmpty(includeRelacion) && includeRelacion.contains(DireccionDTO.class)) {
			usuarioDTO.setDirecciones(DireccionDTO.toDTO(new ArrayList<>(usuario.getDirecciones()), Collections.emptyList()));
			//(usuario.getDirecciones().stream().collect(Collectors.toList()), Collections.emptyList())));
		}

		return usuarioDTO;
	}

	public static List<UsuarioDTO> toDTO(List<Usuario> usuarios) {
		if(usuarios == null){
			return Collections.emptyList();
		}
		return usuarios.stream()
				.map(UsuarioDTO::toDTO)
				.collect(Collectors.toList());
	}

	public static List<UsuarioDTO> toDTO(List<Usuario> usuarios, List<Class<?>> includeRelacion) {
		return usuarios.stream()
				.map(usuario -> UsuarioDTO.toDTO(usuario, includeRelacion))
				.collect(Collectors.toList());
	}

	public static Usuario toDomain(UsuarioDTO usuarioDTO) {
		if (usuarioDTO == null) {
			return null;
		}

		Usuario usuario = new Usuario();
		usuario.setCodigoUsuario(usuarioDTO.getCodigoUsuario() > 0 ? usuarioDTO.getCodigoUsuario() : 0);
		usuario.setDni(StringUtils.hasText(usuarioDTO.getDni()) ? usuarioDTO.getDni() : "");
		usuario.setNombre(StringUtils.hasText(usuarioDTO.getNombre()) ? usuarioDTO.getNombre().trim() : "");
		usuario.setApellidos(StringUtils.hasText(usuarioDTO.getApellidos()) ? usuarioDTO.getApellidos().trim() : "");
		//TODO
		//usuario.setFecha_nacimiento(usuarioDTO.getFechaNacimiento() ? usuarioDTO.getFechaNacimiento() : "");
		//activado, contrasenia
		usuario.setTelefono(StringUtils.hasText(usuarioDTO.getTelefono()) ? usuarioDTO.getTelefono() : "");
		usuario.setEmail(StringUtils.hasText(usuarioDTO.getEmail()) ? usuarioDTO.getEmail().trim().toLowerCase() : "");

		usuario.setMetodoPago(MetodoPagoDTO.toDomain(usuarioDTO.getMetodoPago()));
		usuario.setRol(RolDTO.toDomain(usuarioDTO.getRol()));
		usuario.setDirecciones(new HashSet<>(DireccionDTO.toDomain(usuarioDTO.getDirecciones())));

		return usuario;
	}

	public static List<Usuario> toDomain(List<UsuarioDTO> usuariosDTO) {
		if(usuariosDTO == null) {
			return Arrays.asList();
		}
		return usuariosDTO.stream()
				.map(UsuarioDTO::toDomain)
				.collect(Collectors.toList());
	}

	//	----------------------------------------------------------------------------

	public long getCodigoUsuario() {return codigoUsuario;}

	public void setCodigoUsuario(long codigoUsuario) {this.codigoUsuario = codigoUsuario;}

	public String getDni() {return dni;}

	public void setDni(String dni) {this.dni = dni;}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellido) {
		this.apellidos = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public MetodoPagoDTO getMetodoPago() {return metodoPago;}

	public void setMetodoPago(MetodoPagoDTO metodoPago) {
		this.metodoPago = metodoPago;
	}

	public RolDTO getRol() {
		return rol;
	}

	public void setRol(RolDTO rol) {
		this.rol = rol;
	}

	public List<DireccionDTO> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(List<DireccionDTO> direcciones) {
		this.direcciones = direcciones;
	}

	public List<PedidoDTO> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<PedidoDTO> pedidos) {
		this.pedidos = pedidos;
	}
}
