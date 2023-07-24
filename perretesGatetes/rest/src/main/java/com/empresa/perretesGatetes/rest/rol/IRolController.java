package com.empresa.perretesGatetes.rest.rol;

import com.empresa.perretesGatetes.domain.dtos.RolDTO;
import com.empresa.perretesGatetes.domain.filters.RolFiltroDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("rol")
public interface IRolController {
    @GetMapping("getRoles")
    public ResponseEntity<List<RolDTO>> getRoles();

    @GetMapping("getRolPorId/{id}")
    public ResponseEntity<RolDTO> getRolPorId(@PathVariable("id") Long codigoRol);

    @PostMapping("getRolesFiltrado")
    public ResponseEntity<List<RolDTO>> getRolesFiltrado(@RequestBody RolFiltroDTO rolFiltroDTO);

    @PostMapping("crearRol")
    public ResponseEntity<RolDTO> crearRol(@RequestBody RolDTO rolDTO);

    @PostMapping("modificarRol")
    public ResponseEntity<RolDTO> modificarRol(@RequestBody RolDTO rolDTO);

    @DeleteMapping("eliminarRol/{id}")
    public ResponseEntity<RolDTO> eliminarRol(@PathVariable("id") Long codigoRol);
}
