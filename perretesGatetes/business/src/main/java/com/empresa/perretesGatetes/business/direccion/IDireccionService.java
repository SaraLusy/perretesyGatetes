package com.empresa.perretesGatetes.business.direccion;

import com.empresa.perretesGatetes.domain.dtos.DireccionDTO;
import com.empresa.perretesGatetes.domain.filters.DireccionFiltroDTO;
import java.util.List;

public interface IDireccionService {
    public List<DireccionDTO> getDirecciones();

    public DireccionDTO getDireccionPorId(Long codigoDireccion);

    public List<DireccionDTO> getDireccionesFiltrado(DireccionFiltroDTO direccionFiltroDTO);

    public DireccionDTO crearDireccion(DireccionDTO direccionDTO);

    public DireccionDTO modificarDireccion(DireccionDTO direccionDTO);

    public DireccionDTO eliminarDireccion(Long codigoDireccion);
}
