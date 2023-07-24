package com.empresa.perretesGatetes.business.pedido;

import com.empresa.perretesGatetes.domain.entities.Pedido;
import com.empresa.perretesGatetes.domain.filters.PedidoFiltroDTO;
import java.util.List;

public interface IPedidoRepositoryCustom {
    List<Pedido> findPedidosByFiltro(PedidoFiltroDTO pedidofiltroDTO);

    int getMaxResults(PedidoFiltroDTO pedidoFiltroDTO);
}
