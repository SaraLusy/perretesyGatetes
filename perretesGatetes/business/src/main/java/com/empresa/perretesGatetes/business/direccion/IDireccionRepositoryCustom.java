package com.empresa.perretesGatetes.business.direccion;

import com.empresa.perretesGatetes.domain.entities.Direccion;
import com.empresa.perretesGatetes.domain.filters.DireccionFiltroDTO;
import java.util.List;

public interface IDireccionRepositoryCustom {
    List<Direccion> findDireccionesFiltrado(DireccionFiltroDTO direccionFiltroDTO);
}
