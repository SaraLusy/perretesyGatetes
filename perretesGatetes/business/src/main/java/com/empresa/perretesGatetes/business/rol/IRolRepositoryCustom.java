package com.empresa.perretesGatetes.business.rol;

import com.empresa.perretesGatetes.domain.entities.Rol;
import com.empresa.perretesGatetes.domain.filters.RolFiltroDTO;
import java.util.List;

public interface IRolRepositoryCustom {
    List<Rol> findRolesByFiltro(RolFiltroDTO rolfiltroDTO);
}
