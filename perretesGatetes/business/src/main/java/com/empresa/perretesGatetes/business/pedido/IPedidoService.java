package com.empresa.perretesGatetes.business.pedido;

import com.empresa.perretesGatetes.domain.dtos.PedidoDTO;
import com.empresa.perretesGatetes.domain.dtos.pageable.PageableResult;
import com.empresa.perretesGatetes.domain.filters.PedidoFiltroDTO;
import java.util.List;

public interface IPedidoService {
    List<PedidoDTO> getPedidos();

    PedidoDTO getPedidoPorId(Long codigoPedido);

    PageableResult<PedidoDTO> getPedidosFiltrado(PedidoFiltroDTO pedidoFiltroDTO);

    PedidoDTO crearPedido(PedidoDTO pedidoDTO);

    PedidoDTO modificarPedido(PedidoDTO pedidoDTO);

    PedidoDTO eliminarPedido(Long codigoPedido);

    PedidoDTO cambiarEstadoPedidoRecibido(Long codigoPedido);

    PedidoDTO cambiarEstadoPedidoEnProceso(Long codigoPedido);

    PedidoDTO cambiarEstadoPedidoEmpaquetado(Long codigoPedido);

    PedidoDTO cambiarEstadoPedidoEnviado(Long codigoPedido);

    PedidoDTO cambiarEstadoPedidoFinalizado(Long codigoPedido);

    PedidoDTO cambiarEstadoPedidoCancelado(Long codigoPedido);

}
