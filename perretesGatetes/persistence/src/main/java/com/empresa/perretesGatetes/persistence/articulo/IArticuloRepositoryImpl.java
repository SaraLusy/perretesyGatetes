package com.empresa.perretesGatetes.persistence.articulo;

import com.empresa.perretesGatetes.business.articulo.IArticuloRepositoryCustom;
import com.empresa.perretesGatetes.domain.entities.Articulo;
import com.empresa.perretesGatetes.domain.filters.ArticuloFiltroDTO;
import com.empresa.perretesGatetes.persistence.IBaseRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class IArticuloRepositoryImpl extends IBaseRepositoryImpl implements IArticuloRepositoryCustom {
    @Override
    public List<Articulo> findArticulosByFiltro(ArticuloFiltroDTO articuloFiltroDTO) {
        String query = " SELECT DISTINCT a  FROM Articulo a "
                + " LEFT JOIN FETCH a.especieAnimal especieAnimal "
                //+ " LEFT JOIN FETCH a.pedidosArticulos pedidosArticulos "
                + " WHERE 1=1 ";

        Map<String, Object> parameters = getParameters(articuloFiltroDTO);
        String queryConditions = getQueryConditions(articuloFiltroDTO);
        String orderQuery = getOrderQuery(articuloFiltroDTO);

        TypedQuery<Articulo> typedQuery = em.createQuery(query + queryConditions + orderQuery, Articulo.class);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            typedQuery.setParameter(entry.getKey(), entry.getValue());
        }
        if(articuloFiltroDTO.getPageable()) {
            typedQuery.setFirstResult(articuloFiltroDTO.getPageNumber() * articuloFiltroDTO.getPageElements());
            typedQuery.setMaxResults(articuloFiltroDTO.getPageElements());
        }
        return typedQuery.getResultList();
    }
    private Map<String, Object> getParameters(ArticuloFiltroDTO articuloFiltroDTO) {
        Map<String, Object> parameters = new HashMap<>();

        if (StringUtils.hasText(articuloFiltroDTO.getTexto())) {
                parameters.put("nombre", "%" + articuloFiltroDTO.getTexto() + "%");
                parameters.put("descripcion", "%" + articuloFiltroDTO.getTexto() + "%");
        }

        if (articuloFiltroDTO.getCodigoEspecieAnimal() != null && articuloFiltroDTO.getCodigoEspecieAnimal() != 0){
            parameters.put("codigoEspecieAnimal",articuloFiltroDTO.getCodigoEspecieAnimal());
        }
        return parameters;
    }

    private String getOrderQuery(ArticuloFiltroDTO articuloFiltroDTO){
        String orderQuery = "ORDER BY a.nombre";
        List<String> orderParameters = Arrays.asList("codigoArticulo","nombre", "especieAnimal", "precio");

        if (StringUtils.hasText(articuloFiltroDTO.getOrderBy()) && orderParameters.contains(articuloFiltroDTO.getOrderBy())) {
            orderQuery = "ORDER BY a." + articuloFiltroDTO.getOrderBy() + (articuloFiltroDTO.getOrderDesc() ? " DESC" : " ASC");
        }
        return orderQuery;
    }

    private String getQueryConditions(ArticuloFiltroDTO articuloFiltroDTO) {
        String queryConditions = "";
        if (articuloFiltroDTO.getCodigoEspecieAnimal() != null && articuloFiltroDTO.getCodigoEspecieAnimal() != 0) {
            queryConditions += " AND a.especieAnimal.codigoEspecieAnimal = :codigoEspecieAnimal ";
        }
        return queryConditions;
    }

    @Override
    public int getMaxResults(ArticuloFiltroDTO articuloFiltroDTO) {
        String query = "SELECT count(distinct a) FROM Articulo a "
                + "WHERE 1=1";

        Map<String,Object> parameters = getParameters(articuloFiltroDTO);
        String queryConditions = getQueryConditions(articuloFiltroDTO);

        TypedQuery<Long> typedQuery = em.createQuery(query + queryConditions, Long.class);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            typedQuery.setParameter(entry.getKey(),entry.getValue());
        }

        return typedQuery.getSingleResult().intValue();
    }
}
