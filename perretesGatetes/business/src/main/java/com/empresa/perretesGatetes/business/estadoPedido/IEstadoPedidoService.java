package com.empresa.perretesGatetes.business.estadoPedido;

import com.empresa.perretesGatetes.domain.dtos.EstadoPedidoDTO;
import java.util.List;

public interface IEstadoPedidoService {
    public List<EstadoPedidoDTO> getEstadosPedido();

    public EstadoPedidoDTO getEstadoPedidoPorId(Long codigoEstadoPedido);

    public EstadoPedidoDTO crearEstadoPedido(EstadoPedidoDTO estadoPedidoDTO);

    public EstadoPedidoDTO modificarEstadoPedido(EstadoPedidoDTO estadoPedidoDTO);

    public EstadoPedidoDTO eliminarEstadoPedido(Long codigoEstadoPedido);

}
