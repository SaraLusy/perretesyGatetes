package com.empresa.perretesGatetes.business.pedidosArticulos;

import com.empresa.perretesGatetes.domain.dtos.PedidosArticulosDTO;
import com.empresa.perretesGatetes.domain.entities.PedidosArticulosID;

public interface IPedidosArticulosService {
    PedidosArticulosDTO crearPedidosArticulos(PedidosArticulosDTO pedidosArticulosDTO);
    PedidosArticulosDTO modificarPedidosArticulos(PedidosArticulosDTO pedidosArticulosDTO);
    PedidosArticulosDTO eliminarPedidosArticulos(PedidosArticulosID codigoPedidosArticulos);
}
