package com.empresa.perretesGatetes.business.tipoDireccion;

import com.empresa.perretesGatetes.business.direccion.IDireccionRepository;
import com.empresa.perretesGatetes.domain.dtos.TipoDireccionDTO;
import com.empresa.perretesGatetes.domain.entities.TipoDireccion;
import com.empresa.perretesGatetes.domain.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class TipoDireccionServiceImpl implements ITipoDireccionService {

    @Autowired
    private IDireccionRepository direccionRepository;
    @Autowired
    private ITipoDireccionRepository tipoDireccionRepository;

    @Override
    public List<TipoDireccionDTO> getTiposDireccion() {
        List<TipoDireccion> tiposDireccion;
        tiposDireccion = tipoDireccionRepository.findTiposDireccion();
        return TipoDireccionDTO.toDTO(tiposDireccion);
    }

    @Override
    public TipoDireccionDTO getTipoDireccionPorId(Long codigoTipoDireccion) {
        TipoDireccion tipoDireccion;
        tipoDireccion = tipoDireccionRepository.findTipoDireccionById(codigoTipoDireccion);
        return TipoDireccionDTO.toDTO(tipoDireccion);
    }

    @Override
    public TipoDireccionDTO crearTipoDireccion(TipoDireccionDTO tipoDireccionDTO) {
        TipoDireccion tipoDireccion = TipoDireccionDTO.toDomain(tipoDireccionDTO);
        validarTipoDireccion(tipoDireccion);
        tipoDireccion = tipoDireccionRepository.save(tipoDireccion);
        return TipoDireccionDTO.toDTO(tipoDireccion);
    }

    @Override
    public TipoDireccionDTO modificarTipoDireccion(TipoDireccionDTO tipoDireccionDTO) {
        TipoDireccion tipoDireccionUpdate = TipoDireccionDTO.toDomain(tipoDireccionDTO);
        validarTipoDireccion(tipoDireccionUpdate);

        TipoDireccion tipoDireccionEncontrada = tipoDireccionRepository.findTipoDireccionById(tipoDireccionUpdate.getcodigoTipoDireccion());
        existeTipoDireccion(tipoDireccionEncontrada);
        tipoDireccionUpdate = tipoDireccionRepository.saveAndFlush(tipoDireccionUpdate);

        return TipoDireccionDTO.toDTO(tipoDireccionUpdate);
    }

    @Override
    public TipoDireccionDTO eliminarTipoDireccion(Long codigoTipoDireccion) {
        TipoDireccion tipoDireccionEncontrada = tipoDireccionRepository.findTipoDireccionById(codigoTipoDireccion);

        validarEliminarTipoDireccion(tipoDireccionEncontrada);
        tipoDireccionRepository.deleteById(codigoTipoDireccion);

        return TipoDireccionDTO.toDTO(tipoDireccionEncontrada);
    }

    private void existeTipoDireccion(TipoDireccion tipoDireccion) {
        if (tipoDireccion == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "tipoDireccion.noEncontrado");
        }
    }

    private void validarTipoDireccion(TipoDireccion tipoDireccion) {
        existeTipoDireccion(tipoDireccion);

        if (!StringUtils.hasText(tipoDireccion.getNombre())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "tipoDireccion.requeridoNombre");
        }

        if ((StringUtils.hasText(tipoDireccion.getNombre()) && tipoDireccion.getNombre().length() > Constantes.TIPO_DIRECCION_NOMBRE_MAX)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "tipoDireccion.caracteresMaxNombre");
        }

        if ((StringUtils.hasText(tipoDireccion.getDescripcion()) && tipoDireccion.getDescripcion().length() > Constantes.TIPO_DIRECCION_APELLIDOS_MAX)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "tipoDireccion.caracteresMaxDescripcion");
        }
    }

    private void validarEliminarTipoDireccion(TipoDireccion tipoDireccion) {
        existeTipoDireccion(tipoDireccion);

        if (direccionRepository.getTotalDireccionesByTipoDireccion(tipoDireccion.getcodigoTipoDireccion()) > 0 ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "tipoDireccion.direccionesAsociadas");
        }
    }
}
