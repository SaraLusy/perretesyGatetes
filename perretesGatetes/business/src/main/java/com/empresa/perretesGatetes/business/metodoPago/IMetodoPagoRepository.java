package com.empresa.perretesGatetes.business.metodoPago;

import com.empresa.perretesGatetes.domain.entities.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface IMetodoPagoRepository extends JpaRepository<MetodoPago, Long>, IMetodoPagoRepositoryCustom{
    @Query("SELECT distinct m "
            + "FROM MetodoPago m "
            + "ORDER BY m.nombre ")
    public List<MetodoPago> findMetodosPago();

    @Query("SELECT distinct m "
           + "FROM MetodoPago m "
            + " WHERE m.codigoMetodoPago = :codigoMetodoPago " )
    public MetodoPago findMetodoPagoById(Long codigoMetodoPago);
}
