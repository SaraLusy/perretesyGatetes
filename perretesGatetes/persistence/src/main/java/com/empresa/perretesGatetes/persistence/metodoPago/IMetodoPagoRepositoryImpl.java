package com.empresa.perretesGatetes.persistence.metodoPago;

import com.empresa.perretesGatetes.business.metodoPago.IMetodoPagoRepositoryCustom;
import com.empresa.perretesGatetes.domain.entities.MetodoPago;
import com.empresa.perretesGatetes.domain.filters.MetodoPagoFiltroDTO;
import com.empresa.perretesGatetes.persistence.IBaseRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class IMetodoPagoRepositoryImpl extends IBaseRepositoryImpl implements IMetodoPagoRepositoryCustom {

    @Override
    public List<MetodoPago> findMetodosPagoByFilter(MetodoPagoFiltroDTO metodoPagofiltroDTO) {
        String query = "SELECT distinct mp FROM MetodoPago mp "
                         + "WHERE 1=1 ";

        Map<String, Object> parameters = getParameters(metodoPagofiltroDTO);
        String queryConditions = getQueryConditions(metodoPagofiltroDTO);

        TypedQuery<MetodoPago> typedQuery = em.createQuery(query + queryConditions, MetodoPago.class);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            typedQuery.setParameter(entry.getKey(),entry.getValue());
        }
        return typedQuery.getResultList();
    }

    private String getQueryConditions(MetodoPagoFiltroDTO metodoPagofiltroDTO){
        String queryConditions = "";

        if(StringUtils.hasText(metodoPagofiltroDTO.getTexto())){
            queryConditions += " AND mp.nombre like :nombre OR mp.descripcion like :descripcion ";
        }
        return queryConditions;
    }
    private Map<String, Object> getParameters(MetodoPagoFiltroDTO metodoPagofiltroDTO) {
        Map<String, Object> parameters = new HashMap<>();

        if (StringUtils.hasText(metodoPagofiltroDTO.getTexto())) {
            parameters.put("nombre", "%" + metodoPagofiltroDTO.getTexto() + "%");
            parameters.put("descripcion", "%" + metodoPagofiltroDTO.getTexto() + "%");
        }

        return parameters;
    }
}
