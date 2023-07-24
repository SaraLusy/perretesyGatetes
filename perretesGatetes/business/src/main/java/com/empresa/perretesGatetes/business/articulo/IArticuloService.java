package com.empresa.perretesGatetes.business.articulo;

import com.empresa.perretesGatetes.domain.dtos.ArticuloDTO;
import com.empresa.perretesGatetes.domain.dtos.pageable.PageableResult;
import com.empresa.perretesGatetes.domain.filters.ArticuloFiltroDTO;
import java.util.List;

public interface IArticuloService {
    public List<ArticuloDTO> getArticulos();

    public ArticuloDTO getArticuloPorId(Long codigoArticulo);

    PageableResult<ArticuloDTO> getArticulosFiltrado(ArticuloFiltroDTO articuloFiltroDTO);

    public ArticuloDTO crearArticulo(ArticuloDTO articuloDTO);

    public ArticuloDTO modificarArticulo(ArticuloDTO articuloDTO);

    public ArticuloDTO eliminarArticulo(Long codigoPedido);

}
