package com.empresa.perretesGatetes.business.tipoDireccion;

import com.empresa.perretesGatetes.domain.entities.TipoDireccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ITipoDireccionRepository extends JpaRepository<TipoDireccion, Long> {
    @Query("SELECT distinct td "
            + "FROM TipoDireccion td "
            + "ORDER BY td.nombre")
    List<TipoDireccion> findTiposDireccion();

    @Query("SELECT distinct td FROM TipoDireccion td "
            + "WHERE td.codigoTipoDireccion = :codigoTipoDireccion ")
    TipoDireccion findTipoDireccionById(long codigoTipoDireccion);
}
