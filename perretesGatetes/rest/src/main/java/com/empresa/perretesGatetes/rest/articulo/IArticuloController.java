package com.empresa.perretesGatetes.rest.articulo;

import com.empresa.perretesGatetes.domain.dtos.ArticuloDTO;
import com.empresa.perretesGatetes.domain.dtos.pageable.PageableResult;
import com.empresa.perretesGatetes.domain.filters.ArticuloFiltroDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("articulo")
public interface IArticuloController {
    @GetMapping("getArticulos")
    public ResponseEntity<List<ArticuloDTO>> getArticulos();

    @GetMapping("getArticuloPorId/{id}")
    public ResponseEntity<ArticuloDTO> getArticuloPorId(@PathVariable("id") Long codigoArticulo);

    @PostMapping("getArticulosFiltrado")
    public ResponseEntity<PageableResult<ArticuloDTO>> getArticulosFiltrado(@RequestBody ArticuloFiltroDTO articuloFiltroDTO);

    @PostMapping("crearArticulo")
    public ResponseEntity<ArticuloDTO> crearArticulo(@RequestBody ArticuloDTO articuloDTO);

    @PostMapping("modificarArticulo")
    public ResponseEntity<ArticuloDTO> modificarArticulo(@RequestBody ArticuloDTO articuloDTO);

    @DeleteMapping("eliminarArticulo/{id}")
    public ResponseEntity<ArticuloDTO> eliminarArticulo(@PathVariable("id") Long codigoArticulo);
}
