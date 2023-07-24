package com.empresa.perretesGatetes.rest.direccion;

import com.empresa.perretesGatetes.domain.dtos.DireccionDTO;
import com.empresa.perretesGatetes.domain.filters.DireccionFiltroDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("direccion")
public interface IDireccionController {

    @GetMapping("getDirecciones")
    public ResponseEntity<List<DireccionDTO>> getDirecciones();

    @GetMapping("getDireccionPorId/{id}")
    public ResponseEntity<DireccionDTO> getDireccionPorId(@PathVariable("id") Long codigoDireccion);

    @PostMapping("getDireccionesFiltrado")
    public ResponseEntity<List<DireccionDTO>> getDireccionesFiltrado(@RequestBody DireccionFiltroDTO direccionFiltroDTO);

    @PostMapping("crearDireccion")
    public ResponseEntity<DireccionDTO> crearDireccion(@RequestBody DireccionDTO direccionDTO);

    @PostMapping("modificarDireccion")
    public ResponseEntity<DireccionDTO> modificarDireccion(@RequestBody DireccionDTO direccionDTO);

    @DeleteMapping("eliminarDireccion/{id}")
    public ResponseEntity<DireccionDTO> eliminarDireccion(@PathVariable("id") Long codigoDireccion);
}
