package com.empresa.perretesGatetes.business.especieAnimal;

import com.empresa.perretesGatetes.domain.dtos.EspecieAnimalDTO;
import com.empresa.perretesGatetes.domain.filters.EspecieAnimalFiltroDTO;

import java.util.List;

public interface IEspecieAnimalService {

    public List<EspecieAnimalDTO> getEspeciesAnimal();

    public EspecieAnimalDTO getEspecieAnimalPorId (Long codigoEspecieAnimal);

    public List<EspecieAnimalDTO> getEspeciesAnimalesFiltrado(EspecieAnimalFiltroDTO especieAnimalFiltroDTO);

    public EspecieAnimalDTO crearEspecieAnimal(EspecieAnimalDTO especieAnimalDTO);

    public EspecieAnimalDTO modificarEspecieAnimal(EspecieAnimalDTO especieAnimalDTO);

    public EspecieAnimalDTO eliminarEspecieAnimal(Long codigoEspecieAnimal);
}
