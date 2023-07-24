package com.empresa.perretesGatetes.persistence.direccion;

import com.empresa.perretesGatetes.business.direccion.IDireccionRepositoryCustom;
import com.empresa.perretesGatetes.domain.entities.Direccion;
import com.empresa.perretesGatetes.domain.filters.DireccionFiltroDTO;
import com.empresa.perretesGatetes.persistence.IBaseRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class IDireccionRepositoryImpl extends IBaseRepositoryImpl implements IDireccionRepositoryCustom {

    @Override
    public List<Direccion> findDireccionesFiltrado(DireccionFiltroDTO direccionFiltroDTO) {

        String query = "SELECT distinct d FROM Direccion d "
                + "LEFT JOIN FETCH d.tipoDireccion "
                + "LEFT JOIN FETCH d.usuario "
                + "WHERE 1=1";

        Map<String, Object> parameters = getParameters(direccionFiltroDTO);
        String queryConditions = getQueryConditions(direccionFiltroDTO);
        String orderQuery = getOrderQuery(direccionFiltroDTO);

        TypedQuery<Direccion> typedQuery = em.createQuery(query + queryConditions + orderQuery, Direccion.class);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            typedQuery.setParameter(entry.getKey(), entry.getValue());
        }
        return typedQuery.getResultList();
    }


    private String getQueryConditions(DireccionFiltroDTO direccionFiltroDTO) {
        String queryConditions = "";

        if (StringUtils.hasText(direccionFiltroDTO.getTexto())) {
            queryConditions += " AND d.codigoPostal like :codigoPostal OR "
                            + "d.localidad like :localidad"
                            + "d.comunidad like :comunidad "
                            + "d.direccion like :direccion";
        }

        if (direccionFiltroDTO.getCodigoTipoDireccion() != null && direccionFiltroDTO.getCodigoTipoDireccion() != 0) {
            queryConditions += " AND d.tipoDireccion.codigoTipoDireccion = :codigoTipoDireccion ";
        }
        if (direccionFiltroDTO.getCodigoUsuario() != null && direccionFiltroDTO.getCodigoUsuario() != 0) {
            queryConditions += " AND d.usuario.codigoUsuario = :codigoUsuario ";
        }
        return queryConditions;
    }

    private String getOrderQuery(DireccionFiltroDTO direccionFiltroDTO){
        String orderQuery = "ORDER BY d.direccion";
        List<String> orderParameters = Arrays.asList("direccion","localidad");

        if (StringUtils.hasText(direccionFiltroDTO.getOrderBy()) && orderParameters.contains(direccionFiltroDTO.getOrderBy())) {
            orderQuery = "ORDER BY d." + direccionFiltroDTO.getOrderBy() + (direccionFiltroDTO.getOrderDesc() ? " DESC" : " ASC");
        }
        return orderQuery;
    }

    private Map<String, Object> getParameters(DireccionFiltroDTO direccionFiltroDTO) {
        Map<String, Object> parameters = new HashMap<>();
        if (StringUtils.hasText(direccionFiltroDTO.getTexto())) {
            parameters.put("nombre", "%" + direccionFiltroDTO.getTexto() + "%");
            parameters.put("localidad", "%" + direccionFiltroDTO.getTexto() + "%");
            parameters.put("comunidad", "%" + direccionFiltroDTO.getTexto() + "%");
            parameters.put("codigoPostal", "%" + direccionFiltroDTO.getTexto() + "%");
        }
        return parameters;
    }
}
