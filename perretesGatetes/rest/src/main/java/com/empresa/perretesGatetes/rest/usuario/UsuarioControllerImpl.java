package com.empresa.perretesGatetes.rest.usuario;

import com.empresa.perretesGatetes.business.usuario.IUsuarioService;
import com.empresa.perretesGatetes.domain.dtos.UsuarioDTO;
import com.empresa.perretesGatetes.domain.filters.UsuarioFiltroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UsuarioControllerImpl implements IUsuarioController {

	@Autowired
	IUsuarioService usuarioService;

	@Override
	public ResponseEntity<List<UsuarioDTO>> getUsuarios() {
		return new ResponseEntity<>(usuarioService.getUsuarios(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UsuarioDTO> getUsuarioById(Long codigoUsuario) {
		return new ResponseEntity<>(usuarioService.getUsuarioById(codigoUsuario), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<UsuarioDTO>> getUsuariosFiltrado(UsuarioFiltroDTO usuarioFiltroDTO) {
		return new ResponseEntity<>(usuarioService.getUsuariosFiltrado(usuarioFiltroDTO), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UsuarioDTO> crearUsuario(UsuarioDTO usuarioDTO) {
		return new ResponseEntity<>(usuarioService.crearUsuario(usuarioDTO), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UsuarioDTO> modificarUsuario(UsuarioDTO usuarioDTO) {
		return new ResponseEntity<>(usuarioService.modificarUsuario(usuarioDTO), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UsuarioDTO> eliminarUsuario(Long codigoUsuario) {
		return new ResponseEntity<>(usuarioService.eliminarUsuario(codigoUsuario), HttpStatus.OK);
	}

	@Override
	public UserDetails getUsuarioDetalles() {
		return null;
		//return this.usuarioService.getUserAuthenticated();
	}
}
