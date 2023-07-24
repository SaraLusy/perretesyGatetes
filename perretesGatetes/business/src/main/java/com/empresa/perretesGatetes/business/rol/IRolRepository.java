package com.empresa.perretesGatetes.business.rol;

import com.empresa.perretesGatetes.domain.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface IRolRepository extends JpaRepository<Rol, Long>, IRolRepositoryCustom {
    @Query("SELECT distinct r "
            + "FROM Rol r "
            + "ORDER BY r.nombre")
    List<Rol> findRoles();

    @Query("SELECT distinct r FROM Rol r "
            + "WHERE r.codigoRol = :codigoRol ")
    Rol findRolById(long codigoRol);

}
