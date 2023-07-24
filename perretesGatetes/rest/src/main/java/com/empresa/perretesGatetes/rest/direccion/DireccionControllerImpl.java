package com.empresa.perretesGatetes.rest.direccion;

import com.empresa.perretesGatetes.business.direccion.IDireccionService;
import com.empresa.perretesGatetes.domain.dtos.DireccionDTO;
import com.empresa.perretesGatetes.domain.filters.DireccionFiltroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class DireccionControllerImpl implements IDireccionController {
    @Autowired
    IDireccionService direccionService;

    @Override
    public ResponseEntity<List<DireccionDTO>> getDirecciones() {
        return new ResponseEntity<>(direccionService.getDirecciones(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DireccionDTO> getDireccionPorId(Long codigoDireccion) {
        return new ResponseEntity<>(direccionService.getDireccionPorId(codigoDireccion), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DireccionDTO>> getDireccionesFiltrado(DireccionFiltroDTO direccionFiltroDTO) {
        return new ResponseEntity<>(direccionService.getDireccionesFiltrado(direccionFiltroDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DireccionDTO> crearDireccion(DireccionDTO direccionDTO) {
        return new ResponseEntity<>(direccionService.crearDireccion(direccionDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DireccionDTO> modificarDireccion(DireccionDTO direccionDTO) {
        return new ResponseEntity<>(direccionService.modificarDireccion(direccionDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DireccionDTO> eliminarDireccion(Long codigoDireccion) {
        return new ResponseEntity<>(direccionService.eliminarDireccion(codigoDireccion), HttpStatus.OK);
    }
}
