package com.empresa.perretesGatetes.business.pedidosEstados;

import com.empresa.perretesGatetes.domain.dtos.PedidosEstadosDTO;
import com.empresa.perretesGatetes.domain.entities.PedidosEstadosID;

public interface IPedidosEstadosService {
    PedidosEstadosDTO crearPedidosEstados(PedidosEstadosDTO pedidosEstadosDTO);
    PedidosEstadosDTO modificarPedidoEstados(PedidosEstadosDTO pedidosEstadosDTO);
    PedidosEstadosDTO eliminarPedidosEstados(PedidosEstadosID codigoPedidosEstados);
}
