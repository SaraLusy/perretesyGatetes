package com.empresa.perretesGatetes.business.metodoPago;

import com.empresa.perretesGatetes.domain.entities.MetodoPago;
import com.empresa.perretesGatetes.domain.filters.MetodoPagoFiltroDTO;
import java.util.List;

public interface IMetodoPagoRepositoryCustom {
    List<MetodoPago> findMetodosPagoByFilter(MetodoPagoFiltroDTO metodoPagofiltroDTO);
}
