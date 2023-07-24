package com.empresa.perretesGatetes.business.rol;

import com.empresa.perretesGatetes.domain.dtos.RolDTO;
import com.empresa.perretesGatetes.domain.filters.RolFiltroDTO;
import java.util.List;

public interface IRolService {
    List<RolDTO> getRoles();

    RolDTO getRolPorId(Long codigoRol);

    List<RolDTO> getRolesFiltrado(RolFiltroDTO rolFiltroDTO);

    RolDTO crearRol(RolDTO rolDTO);

    RolDTO modificarRol(RolDTO rolDTO);

    RolDTO eliminarRol(Long codigoRol);
}
