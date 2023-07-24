package com.empresa.perretesGatetes.rest.metodoPago;

import com.empresa.perretesGatetes.business.metodoPago.IMetodoPagoService;
import com.empresa.perretesGatetes.domain.dtos.MetodoPagoDTO;
import com.empresa.perretesGatetes.domain.filters.MetodoPagoFiltroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class MetodoPagoControllerImpl implements IMetodoPagoController {
    @Autowired
    IMetodoPagoService metodoPagoService;

    @Override
    public ResponseEntity<List<MetodoPagoDTO>> getMetodosPago() {
        return  new ResponseEntity<>(metodoPagoService.getMetodosPago(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MetodoPagoDTO> getMetodoPagoPorId(Long codigoMetodoPago) {
        return new ResponseEntity<>(metodoPagoService.getMetodoPagoPorId(codigoMetodoPago), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<MetodoPagoDTO>> getMetodosPagoFiltrado(MetodoPagoFiltroDTO metodoPagoFiltroDTO) {
        return new ResponseEntity<>(metodoPagoService.getMetodosPagoFiltrado(metodoPagoFiltroDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MetodoPagoDTO> crearMetodoPago(MetodoPagoDTO metodoPagoDTO) {
        return new ResponseEntity<>(metodoPagoService.crearMetodoPago(metodoPagoDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MetodoPagoDTO> modificarMetodoPago(MetodoPagoDTO metodoPagoDTO) {
        return new ResponseEntity<>(metodoPagoService.modificarMetodoPago(metodoPagoDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MetodoPagoDTO> eliminarMetodoPago(Long codigoMetodoPago) {
        return new ResponseEntity<>(metodoPagoService.eliminarMetodoPago(codigoMetodoPago), HttpStatus.OK);
    }
}
