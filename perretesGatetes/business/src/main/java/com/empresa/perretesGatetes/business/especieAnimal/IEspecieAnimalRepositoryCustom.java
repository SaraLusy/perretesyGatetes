package com.empresa.perretesGatetes.business.especieAnimal;

import com.empresa.perretesGatetes.domain.entities.EspecieAnimal;
import com.empresa.perretesGatetes.domain.filters.EspecieAnimalFiltroDTO;
import java.util.List;

public interface IEspecieAnimalRepositoryCustom {

    List<EspecieAnimal> findEspeciesAnimalesByFiltro(EspecieAnimalFiltroDTO especieAnimalFiltroDTO);
}
