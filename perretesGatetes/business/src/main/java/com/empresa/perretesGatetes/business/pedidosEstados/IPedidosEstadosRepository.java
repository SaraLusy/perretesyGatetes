package com.empresa.perretesGatetes.business.pedidosEstados;

import com.empresa.perretesGatetes.domain.entities.PedidosEstados;
import com.empresa.perretesGatetes.domain.entities.PedidosEstadosID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPedidosEstadosRepository extends JpaRepository<PedidosEstados, Long> {

    @Query("SELECT distinct pe FROM PedidosEstados pe "
            + "WHERE pe.codigoPedidosEstados = :codigoPedidosEstados ")
    public PedidosEstados findPedidosEstadosById(PedidosEstadosID codigoPedidosEstados);


    @Query("SELECT count(*) FROM PedidosEstados pe "
            + "WHERE pe.estadoPedido.codigoEstadoPedido = :codigoEstadoPedido ")
    public int getTotalPedidosEstadosByEstadoPedido(long codigoEstadoPedido);

}
