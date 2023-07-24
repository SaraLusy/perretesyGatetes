package com.empresa.perretesGatetes.persistence.usuario;

import com.empresa.perretesGatetes.business.usuario.IUsuarioRepositoryCustom;
import com.empresa.perretesGatetes.domain.entities.MetodoPago;
import com.empresa.perretesGatetes.domain.entities.Usuario;
import com.empresa.perretesGatetes.domain.filters.UsuarioFiltroDTO;
import com.empresa.perretesGatetes.persistence.IBaseRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class IUsuarioRepositoryImpl extends IBaseRepositoryImpl implements IUsuarioRepositoryCustom {
    @Override
    public List<Usuario> findUsuariosByFiltro(UsuarioFiltroDTO usuariofiltroDTO) {

        String query = "SELECT DISTINCT u "
                    + " FROM Usuario u "
                    + " LEFT JOIN FETCH u.rol r "
                    + " WHERE 1=1 ";

        query += this.getParameters(usuariofiltroDTO);

        TypedQuery<Usuario> typedQuery = em.createQuery(query, Usuario.class);
        this.setFilterQuery(usuariofiltroDTO,typedQuery);

        return typedQuery.getResultList();
    }

    private String getParameters(UsuarioFiltroDTO usuariofiltroDTO) {
        String querySql = "";

        if(usuariofiltroDTO == null) {
            return querySql;
        }

        if (StringUtils.hasText(usuariofiltroDTO.getTexto())) {
            querySql += " AND (u.nombre LIKE :texto OR u.apellidos LIKE :texto OR u.email LIKE :texto)";
        }

        if(usuariofiltroDTO.getCodigoRol() != null) {
            querySql += " AND rol.codigoRol = :codigoRol";
        }

        return querySql;
    }

    private void setFilterQuery(UsuarioFiltroDTO usuariofiltroDTO, TypedQuery<Usuario> query) {
        if (usuariofiltroDTO != null && StringUtils.hasText(usuariofiltroDTO.getTexto())) {
            query.setParameter("texto", "%" + usuariofiltroDTO.getTexto() + "%");
        }

        if (usuariofiltroDTO.getCodigoRol() != null) {
            query.setParameter("codigoRol", usuariofiltroDTO.getCodigoRol());
        }
    }
}
