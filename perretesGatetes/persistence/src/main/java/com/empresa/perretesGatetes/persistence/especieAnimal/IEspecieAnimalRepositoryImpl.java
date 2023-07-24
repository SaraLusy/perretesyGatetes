package com.empresa.perretesGatetes.persistence.especieAnimal;

import com.empresa.perretesGatetes.business.especieAnimal.IEspecieAnimalRepositoryCustom;
import com.empresa.perretesGatetes.domain.entities.EspecieAnimal;
import com.empresa.perretesGatetes.domain.filters.EspecieAnimalFiltroDTO;
import com.empresa.perretesGatetes.persistence.IBaseRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class IEspecieAnimalRepositoryImpl extends IBaseRepositoryImpl implements IEspecieAnimalRepositoryCustom {
    @Override
    public List<EspecieAnimal> findEspeciesAnimalesByFiltro(EspecieAnimalFiltroDTO especieAnimalFiltroDTO) {

        String query = "SELECT DISTINCT ea "
                + " FROM EspecieAnimal ea "
                + " WHERE 1=1 ";

        Map<String, Object> parameters = getParameters(especieAnimalFiltroDTO);
        String queryConditions = getQueryConditions(especieAnimalFiltroDTO);
        String orderQuery = getOrderQuery(especieAnimalFiltroDTO);

        TypedQuery<EspecieAnimal> typedQuery = em.createQuery(query + queryConditions + orderQuery, EspecieAnimal.class);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            typedQuery.setParameter(entry.getKey(),entry.getValue());
        }

        return typedQuery.getResultList();
    }

    private Map<String, Object> getParameters(EspecieAnimalFiltroDTO especieAnimalFiltroDTO) {
        Map<String, Object> parameters = new HashMap<>();

        if (StringUtils.hasText(especieAnimalFiltroDTO.getTexto())) {
            parameters.put("nombre", "%" + especieAnimalFiltroDTO.getTexto() + "%");
            parameters.put("descripcion", "%" + especieAnimalFiltroDTO.getTexto() + "%");
        }
        return parameters;
    }

    private String getOrderQuery(EspecieAnimalFiltroDTO especieAnimalFiltroDTO){
        String orderQuery = "ORDER BY ea.nombre";
        List<String> orderParameters = Arrays.asList("codigoEspecieAnimal","nombre");

        if (StringUtils.hasText(especieAnimalFiltroDTO.getOrderBy()) && orderParameters.contains(especieAnimalFiltroDTO.getOrderBy())) {
            orderQuery = "ORDER BY ea." + especieAnimalFiltroDTO.getOrderBy() + (especieAnimalFiltroDTO.getOrderDesc() ? " DESC" : " ASC");
        }
        return orderQuery;
    }
    private String getQueryConditions(EspecieAnimalFiltroDTO especieAnimalFiltroDTO) {
        String queryConditions = "";

        if(StringUtils.hasText(especieAnimalFiltroDTO.getTexto())){
            queryConditions += " AND ea.nombre like :nombre OR ea.descripcion like :descripcion ";
        }
        return queryConditions;
    }
}
