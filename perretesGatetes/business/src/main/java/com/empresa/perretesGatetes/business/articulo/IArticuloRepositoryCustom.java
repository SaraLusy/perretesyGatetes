package com.empresa.perretesGatetes.business.articulo;

import com.empresa.perretesGatetes.domain.entities.Articulo;
import com.empresa.perretesGatetes.domain.filters.ArticuloFiltroDTO;
import java.util.List;

public interface IArticuloRepositoryCustom {

    List<Articulo> findArticulosByFiltro(ArticuloFiltroDTO articulofiltroDTO);

    int getMaxResults(ArticuloFiltroDTO articuloFiltroDTO);
}
