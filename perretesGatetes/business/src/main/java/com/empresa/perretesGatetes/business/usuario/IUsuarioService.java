package com.empresa.perretesGatetes.business.usuario;

import com.empresa.perretesGatetes.domain.dtos.UsuarioDTO;
import com.empresa.perretesGatetes.domain.filters.UsuarioFiltroDTO;
import java.util.List;

public interface IUsuarioService {

	public List<UsuarioDTO> getUsuarios();

	public UsuarioDTO getUsuarioById(Long codigoUsuario);

	public List<UsuarioDTO> getUsuariosFiltrado(UsuarioFiltroDTO usuarioFiltroDTO);

	public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);

	public UsuarioDTO modificarUsuario(UsuarioDTO usuarioDTO);

	public UsuarioDTO eliminarUsuario(Long codigoUsuario);
}
