package com.empresa.perretesGatetes.domain.entities;

import com.empresa.perretesGatetes.domain.types.RolType;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigoUsuario;
	private String dni;
	private String nombre;
	private String apellidos;
	private Date fecha_nacimiento;
	private String telefono;
	private String contrasenia;
	private String email;
	private boolean activado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="codigoMetodoPago")
	private MetodoPago metodoPago;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="codigoRol")
	private Rol rol;

	@OneToMany(mappedBy = "usuario")
	private Set<Direccion> direcciones;

	@OneToMany(mappedBy = "usuario")
	private Set<Pedido> pedidos;

	public boolean isAdmin() {
		return this.rol != null && RolType.ADMIN.getNombre().equalsIgnoreCase(rol.getNombre());
	}
	public boolean isTrabajador() {
		return this.rol != null && RolType.TRABAJADOR.getNombre().equalsIgnoreCase(rol.getNombre());
	}
	public boolean isCliente() {
		return this.rol != null && RolType.CLIENTE.getNombre().equalsIgnoreCase(rol.getNombre());
	}




	public Set<Direccion> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(Set<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

	public long getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(long codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

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

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
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

	public MetodoPago getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(MetodoPago metodoPago) {
		this.metodoPago = metodoPago;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public boolean isActivado() {return activado;}

	public void setActivado(boolean activado) {this.activado = activado;}

	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
}
