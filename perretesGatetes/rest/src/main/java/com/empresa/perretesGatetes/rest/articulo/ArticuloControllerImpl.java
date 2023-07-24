package com.empresa.perretesGatetes.rest.articulo;

import com.empresa.perretesGatetes.business.articulo.IArticuloService;
import com.empresa.perretesGatetes.domain.dtos.ArticuloDTO;
import com.empresa.perretesGatetes.domain.dtos.pageable.PageableResult;
import com.empresa.perretesGatetes.domain.filters.ArticuloFiltroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticuloControllerImpl implements IArticuloController {
    @Autowired
    IArticuloService articuloService;

    @Override
    public ResponseEntity<List<ArticuloDTO>> getArticulos() {
        return new ResponseEntity<>(articuloService.getArticulos(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ArticuloDTO> getArticuloPorId(Long codigoArticulo) {
        return new ResponseEntity<>(articuloService.getArticuloPorId(codigoArticulo), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageableResult<ArticuloDTO>> getArticulosFiltrado(ArticuloFiltroDTO articuloFiltroDTO) {
        return new ResponseEntity<>(articuloService.getArticulosFiltrado(articuloFiltroDTO), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ArticuloDTO> crearArticulo(ArticuloDTO articuloDTO) {
        return new ResponseEntity<>(articuloService.crearArticulo(articuloDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ArticuloDTO> modificarArticulo(ArticuloDTO articuloDTO) {
        return new ResponseEntity<>(articuloService.modificarArticulo(articuloDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ArticuloDTO> eliminarArticulo(Long codigoArticulo) {
        return new ResponseEntity<>(articuloService.eliminarArticulo(codigoArticulo), HttpStatus.OK);

    }
}
