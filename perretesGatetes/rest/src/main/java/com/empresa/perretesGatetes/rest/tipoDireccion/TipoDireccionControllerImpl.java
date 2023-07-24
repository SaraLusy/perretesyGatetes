package com.empresa.perretesGatetes.rest.tipoDireccion;

import com.empresa.perretesGatetes.business.tipoDireccion.ITipoDireccionService;
import com.empresa.perretesGatetes.domain.dtos.TipoDireccionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class TipoDireccionControllerImpl implements ITipoDireccionController {

    @Autowired
    ITipoDireccionService tipoDireccionService;
    @Override
    public ResponseEntity<List<TipoDireccionDTO>> getTiposDireccion() {
        return new ResponseEntity<>(tipoDireccionService.getTiposDireccion(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TipoDireccionDTO> getTipoDireccionPorId(Long codigoTipoDireccion) {
        return new ResponseEntity<>(tipoDireccionService.getTipoDireccionPorId(codigoTipoDireccion), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TipoDireccionDTO> crearTipoDireccion(TipoDireccionDTO tipoDireccionDTO) {
        return new ResponseEntity<>(tipoDireccionService.crearTipoDireccion(tipoDireccionDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TipoDireccionDTO> modificarTipoDireccion(TipoDireccionDTO tipoDireccionDTO) {
        return new ResponseEntity<>(tipoDireccionService.modificarTipoDireccion(tipoDireccionDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TipoDireccionDTO> eliminarTipoDireccion(Long codigoTipoDireccion) {
        return new ResponseEntity<>(tipoDireccionService.eliminarTipoDireccion(codigoTipoDireccion), HttpStatus.OK);
    }
}
