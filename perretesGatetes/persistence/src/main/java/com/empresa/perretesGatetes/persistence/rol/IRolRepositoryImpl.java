package com.empresa.perretesGatetes.persistence.rol;

import com.empresa.perretesGatetes.business.rol.IRolRepositoryCustom;
import com.empresa.perretesGatetes.domain.entities.Rol;
import com.empresa.perretesGatetes.domain.filters.RolFiltroDTO;
import com.empresa.perretesGatetes.persistence.IBaseRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.TypedQuery;

@Repository
public class IRolRepositoryImpl extends IBaseRepositoryImpl implements IRolRepositoryCustom {
    @Override
    public List<Rol> findRolesByFiltro(RolFiltroDTO rolFiltroDTO) {
        String query = "SELECT distinct r FROM Rol r "
                + "WHERE 1=1";

        Map<String, Object> parameters = getParameters(rolFiltroDTO);
        String queryConditions = getQueryConditions(rolFiltroDTO);

        TypedQuery<Rol> typedQuery = em.createQuery(query + queryConditions, Rol.class);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            typedQuery.setParameter(entry.getKey(),entry.getValue());
        }
        return typedQuery.getResultList();
    }

    private String getQueryConditions(RolFiltroDTO rolFiltroDTO){
        String queryConditions = "";

        if(StringUtils.hasText(rolFiltroDTO.getTexto())){
            queryConditions += " AND r.nombre like :nombre OR r.descripcion like :descripcion ";
        }
        return queryConditions;
    }
    private Map<String, Object> getParameters(RolFiltroDTO rolFiltroDTO) {
        Map<String, Object> parameters = new HashMap<>();

        if (StringUtils.hasText(rolFiltroDTO.getTexto())) {
            parameters.put("nombre", "%" + rolFiltroDTO.getTexto() + "%");
            parameters.put("descripcion", "%" + rolFiltroDTO.getTexto() + "%");
        }
        return parameters;
    }
}
