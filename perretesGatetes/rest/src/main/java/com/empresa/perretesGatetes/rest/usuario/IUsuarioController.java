package com.empresa.perretesGatetes.rest.usuario;

import com.empresa.perretesGatetes.domain.dtos.UsuarioDTO;
import com.empresa.perretesGatetes.domain.filters.UsuarioFiltroDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("usuario")
public interface IUsuarioController {

	@GetMapping("getUsuarios")
	public ResponseEntity<List<UsuarioDTO>> getUsuarios();

	@GetMapping("getUsuarioById/{id}")
	public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable("id") Long codigoUsuario);

	@PostMapping("getUsuariosByFiltro")
	public ResponseEntity<List<UsuarioDTO>> getUsuariosFiltrado(@RequestBody UsuarioFiltroDTO usuarioFiltroDTO);

	@PostMapping("crearUsuario")
	public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO);

	@PostMapping("modificarUsuario")
	public ResponseEntity<UsuarioDTO> modificarUsuario(@RequestBody UsuarioDTO usuarioDTO);

	@DeleteMapping("eliminarUsuario/{id}")
	public ResponseEntity<UsuarioDTO> eliminarUsuario(@PathVariable("id") Long codigoUsuario);

	@GetMapping("getUsuarioDetalles")
	UserDetails getUsuarioDetalles();
}
