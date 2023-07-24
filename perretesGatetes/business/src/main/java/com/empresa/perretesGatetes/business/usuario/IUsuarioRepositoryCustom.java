package com.empresa.perretesGatetes.business.usuario;

import java.util.List;

import com.empresa.perretesGatetes.domain.entities.Usuario;
import com.empresa.perretesGatetes.domain.filters.UsuarioFiltroDTO;

public interface IUsuarioRepositoryCustom {
	List<Usuario> findUsuariosByFiltro(UsuarioFiltroDTO usuariofiltroDTO);
}
