package com.empresa.perretesGatetes.rest.rol;

import com.empresa.perretesGatetes.business.rol.IRolService;
import com.empresa.perretesGatetes.domain.dtos.RolDTO;
import com.empresa.perretesGatetes.domain.filters.RolFiltroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class RolControllerImpl implements IRolController {
    @Autowired
    IRolService rolService;

    @Override
    public ResponseEntity<List<RolDTO>> getRoles() {
        return new ResponseEntity<>(rolService.getRoles(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RolDTO> getRolPorId(Long codigoRol) {
        return new ResponseEntity<>(rolService.getRolPorId(codigoRol), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RolDTO>> getRolesFiltrado(RolFiltroDTO rolFiltroDTO) {
        return new ResponseEntity<>(rolService.getRolesFiltrado(rolFiltroDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RolDTO> crearRol(RolDTO rolDTO) {
        return new ResponseEntity<>(rolService.crearRol(rolDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RolDTO> modificarRol(RolDTO rolDTO) {
        return new ResponseEntity<>(rolService.modificarRol(rolDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RolDTO> eliminarRol(Long codigoRol) {
        return new ResponseEntity<>(rolService.eliminarRol(codigoRol), HttpStatus.OK);
    }
}
