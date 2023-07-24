package com.empresa.perretesGatetes.rest.metodoPago;

import com.empresa.perretesGatetes.domain.dtos.MetodoPagoDTO;
import com.empresa.perretesGatetes.domain.filters.MetodoPagoFiltroDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("metodoPago")
public interface IMetodoPagoController {

    @GetMapping("getMetodosPago")
    public ResponseEntity<List<MetodoPagoDTO>> getMetodosPago();

    @GetMapping("getMetodoPagoPorId/{id}")
    public ResponseEntity<MetodoPagoDTO> getMetodoPagoPorId(@PathVariable("id") Long codigoMetodoPago);

    @PostMapping("getMetodosPagoFiltrado")
    public ResponseEntity<List<MetodoPagoDTO>> getMetodosPagoFiltrado(@RequestBody MetodoPagoFiltroDTO metodoPagoFiltroDTO);

    @PostMapping("crearMetodoPago")
    public ResponseEntity<MetodoPagoDTO> crearMetodoPago(@RequestBody MetodoPagoDTO metodoPagoDTO);

    @PostMapping("modificarMetodoPago")
    public ResponseEntity<MetodoPagoDTO> modificarMetodoPago(@RequestBody MetodoPagoDTO metodoPagoDTO);

    @DeleteMapping("eliminarMetodoPago/{id}")
    public ResponseEntity<MetodoPagoDTO> eliminarMetodoPago(@PathVariable("id") Long codigoMetodoPago);
}
