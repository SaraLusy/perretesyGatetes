package com.empresa.perretesGatetes.rest.tipoDireccion;

import com.empresa.perretesGatetes.domain.dtos.TipoDireccionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("tipoDireccion")
public interface ITipoDireccionController {
    @GetMapping("getTiposDireccion")
    public ResponseEntity<List<TipoDireccionDTO>> getTiposDireccion();

    @GetMapping("getDireccionPorId/{id}")
    public ResponseEntity<TipoDireccionDTO> getTipoDireccionPorId(@PathVariable("id") Long codigoTipoDireccion);

    @PostMapping("crearTipoDireccion")
    public ResponseEntity<TipoDireccionDTO> crearTipoDireccion(@RequestBody TipoDireccionDTO tipoDireccionDTO);

    @PostMapping("modificarTipoDireccion")
    public ResponseEntity<TipoDireccionDTO> modificarTipoDireccion(@RequestBody TipoDireccionDTO tipoDireccionDTO);

    @DeleteMapping("eliminarTipoDireccion/{id}")
    public ResponseEntity<TipoDireccionDTO> eliminarTipoDireccion(@PathVariable("id") Long codigoTipoDireccion);
}
