package com.empresa.perretesGatetes.rest.pedidosEstados;

import com.empresa.perretesGatetes.domain.dtos.PedidosEstadosDTO;
import com.empresa.perretesGatetes.domain.entities.PedidosEstadosID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("pedidosEstados")
public interface IPedidosEstadosController {
    @PostMapping("crearPedidosEstados")
    ResponseEntity<PedidosEstadosDTO> crearPedidosEstados(@RequestBody PedidosEstadosDTO pedidosEstadosDTO);

    @PostMapping("modificarPedidosEstados")
    ResponseEntity<PedidosEstadosDTO> modificarPedidoEstados(@RequestBody PedidosEstadosDTO pedidosEstadosDTO);

    @DeleteMapping("eliminarPedidosEstados")
    ResponseEntity<PedidosEstadosDTO> eliminarPedidosEstados(@RequestBody PedidosEstadosID codigoPedidosEstados);
}
