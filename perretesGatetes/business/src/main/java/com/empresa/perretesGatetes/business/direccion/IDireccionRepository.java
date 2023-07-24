package com.empresa.perretesGatetes.business.direccion;

import com.empresa.perretesGatetes.domain.entities.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface IDireccionRepository extends JpaRepository<Direccion, Long>, IDireccionRepositoryCustom {
    @Query("SELECT distinct d "
            + "FROM Direccion d "
            + " LEFT JOIN FETCH d.usuario ")
    public List<Direccion> findDirecciones();

    @Query("SELECT distinct d FROM Direccion d "
            + "WHERE d.codigoDireccion = :codigoDireccion ")
    public Direccion findDireccionById(long codigoDireccion);

    @Query("SELECT count(*) FROM Direccion d "
            + "WHERE d.usuario.codigoUsuario = :codigoUsuario ")
    public int getTotalDireccionesByUsuario(long codigoUsuario);

    @Query("SELECT count(*) FROM Direccion d "
            + "WHERE d.tipoDireccion.codigoTipoDireccion = :codigoTipoDireccion ")
    public int getTotalDireccionesByTipoDireccion(long codigoTipoDireccion);
}
