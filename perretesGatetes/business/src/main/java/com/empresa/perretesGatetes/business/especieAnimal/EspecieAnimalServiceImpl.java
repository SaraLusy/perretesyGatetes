package com.empresa.perretesGatetes.business.especieAnimal;

import com.empresa.perretesGatetes.business.articulo.IArticuloRepository;
import com.empresa.perretesGatetes.domain.dtos.EspecieAnimalDTO;
import com.empresa.perretesGatetes.domain.entities.EspecieAnimal;
import com.empresa.perretesGatetes.domain.filters.EspecieAnimalFiltroDTO;
import com.empresa.perretesGatetes.domain.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EspecieAnimalServiceImpl implements IEspecieAnimalService {

    @Autowired
    private IEspecieAnimalRepository especieAnimalRepository;

    @Autowired
    private IArticuloRepository articuloRepository;

    @Override
    public List<EspecieAnimalDTO> getEspeciesAnimal() {
        List<EspecieAnimal> especiesAnimales;
        especiesAnimales = especieAnimalRepository.findEspeciesAnimal();
        return EspecieAnimalDTO.toDTO(especiesAnimales);
    }

    @Override
    public EspecieAnimalDTO getEspecieAnimalPorId(Long codigoEspecieAnimal) {
        EspecieAnimal especieAnimal;
        especieAnimal = especieAnimalRepository.findEspecieAnimalById(codigoEspecieAnimal);
        return EspecieAnimalDTO.toDTO(especieAnimal);
    }

    @Override
    public List<EspecieAnimalDTO> getEspeciesAnimalesFiltrado(EspecieAnimalFiltroDTO especieAnimalFiltroDTO) {
        return EspecieAnimalDTO.toDTO(especieAnimalRepository.findEspeciesAnimalesByFiltro(especieAnimalFiltroDTO));
    }

    @Override
    public EspecieAnimalDTO crearEspecieAnimal(EspecieAnimalDTO especieAnimalDTO) {
        EspecieAnimal especieAnimal = EspecieAnimalDTO.toDomain(especieAnimalDTO);
        validarEspecieAnimal(especieAnimal);
        especieAnimal = especieAnimalRepository.save(especieAnimal);
        return EspecieAnimalDTO.toDTO(especieAnimal);
    }

    @Override
    public EspecieAnimalDTO modificarEspecieAnimal(EspecieAnimalDTO especieAnimalDTO) {
        EspecieAnimal especieAnimalUpdate = EspecieAnimalDTO.toDomain(especieAnimalDTO);
        validarEspecieAnimal(especieAnimalUpdate);

        EspecieAnimal especieAnimalEncontrada = especieAnimalRepository.findEspecieAnimalById(especieAnimalUpdate.getCodigoEspecieAnimal());
        existeEspecieAnimal(especieAnimalEncontrada);
        especieAnimalUpdate = especieAnimalRepository.save(especieAnimalUpdate);

        return EspecieAnimalDTO.toDTO(especieAnimalUpdate);
    }

    @Override
    public EspecieAnimalDTO eliminarEspecieAnimal(Long codigoEspecieAnimal) {
        EspecieAnimal especieAnimalDelete = especieAnimalRepository.findEspecieAnimalById(codigoEspecieAnimal);

        validarEliminarEspecieAnimal(especieAnimalDelete);
        especieAnimalRepository.deleteById(codigoEspecieAnimal);

        return EspecieAnimalDTO.toDTO(especieAnimalDelete);
    }

    private void validarEspecieAnimal(EspecieAnimal especieAnimal) {
        existeEspecieAnimal(especieAnimal);

        if(!StringUtils.hasText(especieAnimal.getNombre())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "especieAnimal.nombreRequerido");
        }
        if(!StringUtils.hasText(especieAnimal.getNombre()) && especieAnimal.getNombre().length() > Constantes.ESPECIE_ANIMAL_NOMBRE_MAX) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "especieAnimal.caracteresMaxNombre");
        }
        if(!StringUtils.hasText(especieAnimal.getDescripcion()) && especieAnimal.getDescripcion().length() > Constantes.ESPECIE_ANIMAL_DESCRIPCION_MAX) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "especieAnimal.caracteresMaxDescripcion");
        }
    }

    private void existeEspecieAnimal(EspecieAnimal especieAnimal) {
        if(especieAnimal == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "especieAnimal.noEncontrado");
        }
    }

    private void validarEliminarEspecieAnimal(EspecieAnimal especieAnimal) {
        existeEspecieAnimal(especieAnimal);

        if (articuloRepository.getTotalArticulosByEspecieAnimal(especieAnimal.getCodigoEspecieAnimal()) > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "especieAnimal.articulosAsociados");
        }
    }
}
