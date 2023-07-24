package com.empresa.perretesGatetes.business.tipoDireccion;

import com.empresa.perretesGatetes.domain.dtos.TipoDireccionDTO;
import java.util.List;

public interface ITipoDireccionService {
    List<TipoDireccionDTO> getTiposDireccion();

    TipoDireccionDTO getTipoDireccionPorId(Long codigoTipoDireccion);

    TipoDireccionDTO crearTipoDireccion(TipoDireccionDTO tipoDireccionDTO);

    TipoDireccionDTO modificarTipoDireccion(TipoDireccionDTO tipoDireccionDTO);

    TipoDireccionDTO eliminarTipoDireccion(Long codigoTipoDireccion);

}
