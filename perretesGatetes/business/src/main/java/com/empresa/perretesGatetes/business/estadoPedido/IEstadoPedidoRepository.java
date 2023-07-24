package com.empresa.perretesGatetes.business.estadoPedido;

import com.empresa.perretesGatetes.domain.entities.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IEstadoPedidoRepository extends JpaRepository<EstadoPedido, Long> {
    @Query("SELECT distinct ep "
            + "FROM EstadoPedido ep "
            + "ORDER BY ep.nombre ")
    public List<EstadoPedido> findEstadosPedido();

    @Query("SELECT distinct ep FROM EstadoPedido ep "
            + "WHERE ep.codigoEstadoPedido = :codigoEstadoPedido ")
    public EstadoPedido findEstadoPedidoById(long codigoEstadoPedido);
}
