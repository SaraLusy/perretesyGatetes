package com.empresa.perretesGatetes.business.metodoPago;

import com.empresa.perretesGatetes.domain.dtos.MetodoPagoDTO;
import com.empresa.perretesGatetes.domain.filters.MetodoPagoFiltroDTO;
import java.util.List;

public interface IMetodoPagoService {
    public List<MetodoPagoDTO> getMetodosPago();

    public MetodoPagoDTO getMetodoPagoPorId(Long codigoMetodoPago);

    public List<MetodoPagoDTO> getMetodosPagoFiltrado(MetodoPagoFiltroDTO metodoPagoFiltroDTO);

    public MetodoPagoDTO crearMetodoPago(MetodoPagoDTO metodoPagoDTO);

    public MetodoPagoDTO modificarMetodoPago(MetodoPagoDTO metodoPagoDTO);

    public MetodoPagoDTO eliminarMetodoPago(Long codigoMetodoPago);
}
