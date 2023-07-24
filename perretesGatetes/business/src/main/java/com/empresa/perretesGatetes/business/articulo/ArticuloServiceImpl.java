package com.empresa.perretesGatetes.business.articulo;

import com.empresa.perretesGatetes.business.especieAnimal.IEspecieAnimalRepository;
import com.empresa.perretesGatetes.domain.dtos.ArticuloDTO;
import com.empresa.perretesGatetes.domain.dtos.pageable.PageableResult;
import com.empresa.perretesGatetes.domain.entities.Articulo;
import com.empresa.perretesGatetes.domain.filters.ArticuloFiltroDTO;
import com.empresa.perretesGatetes.domain.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ArticuloServiceImpl implements IArticuloService {
    @Autowired
    IArticuloRepository articuloRepository;
    @Autowired
    IEspecieAnimalRepository especieAnimalRepository;

    @Override
    public List<ArticuloDTO> getArticulos() {
        List<Articulo> articulos;
        articulos = articuloRepository.findArticulos();
        return ArticuloDTO.toDTO(articulos);
    }

    @Override
    public ArticuloDTO getArticuloPorId(Long codigoArticulo) {
        Articulo articulo;
        articulo = articuloRepository.findArticuloById(codigoArticulo);
        return ArticuloDTO.toDTO(articulo);
    }

    @Override
    public PageableResult<ArticuloDTO> getArticulosFiltrado(ArticuloFiltroDTO articuloFiltroDTO) {
        return new PageableResult<>(articuloFiltroDTO.getPageNumber(),
                articuloRepository.getMaxResults(articuloFiltroDTO),
                ArticuloDTO.toDTO(articuloRepository.findArticulosByFiltro(articuloFiltroDTO)));
    }

    @Override
    public ArticuloDTO crearArticulo(ArticuloDTO articuloDTO) {
        Articulo articuloNuevo = ArticuloDTO.toDomain(articuloDTO);

        validarArticulo(articuloNuevo);
        articuloNuevo = this.articuloRepository.saveAndFlush(articuloNuevo);

        return ArticuloDTO.toDTO(articuloNuevo);
    }

    @Override
    public ArticuloDTO modificarArticulo(ArticuloDTO articuloDTO) {
        Articulo articuloUpdate = ArticuloDTO.toDomain(articuloDTO);
        validarArticulo(articuloUpdate);

        Articulo articuloEncontrado = articuloRepository.findArticuloById(articuloUpdate.getCodigoArticulo());
        existeArticulo(articuloEncontrado.getCodigoArticulo());
        articuloUpdate = this.articuloRepository.saveAndFlush(articuloUpdate);

        return ArticuloDTO.toDTO(articuloUpdate);
    }

    @Override
    public ArticuloDTO eliminarArticulo(Long codigoArticulo) {
        existeArticulo(codigoArticulo);

        ArticuloDTO articuloDTO = ArticuloDTO.toDTO(this.articuloRepository.findArticuloById(codigoArticulo));
        this.articuloRepository.deleteById(codigoArticulo);

        return articuloDTO;
    }

    private void validarArticulo(Articulo articulo) {
        existeArticulo(articulo.getCodigoArticulo());

        if (!StringUtils.hasText(articulo.getNombre())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "articulo.requeridoNombre");
        }
        if (articulo.getNombre().length() > Constantes.ARTICULO_NOMBRE_MAX) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "articulo.caracteresMaxNombre");
        }
        if (articulo.getNombre().length() > Constantes.ARTICULO_DESCRIPCION_MAX) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "articulo.caracteresMaxDescripcion");
        }
        if (articulo.getEspecieAnimal() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "articulo.requeridoEspecieAnimal");
        }
        if (articulo.getEspecieAnimal() != null
                && !this.especieAnimalRepository.existsById(articulo.getEspecieAnimal().getCodigoEspecieAnimal())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "articulo.noEncontradoEspecieAnimal");
        }
    }

    private void existeArticulo(Long codigoArticulo) {
        if(codigoArticulo == null || !this.articuloRepository.existsById(codigoArticulo)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "articulo.noEncontrado");
        }
    }
}
