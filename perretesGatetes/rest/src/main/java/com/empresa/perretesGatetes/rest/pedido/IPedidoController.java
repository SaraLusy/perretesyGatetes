package com.empresa.perretesGatetes.rest.pedido;

import com.empresa.perretesGatetes.domain.dtos.PedidoDTO;
import com.empresa.perretesGatetes.domain.dtos.pageable.PageableResult;
import com.empresa.perretesGatetes.domain.filters.PedidoFiltroDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("pedido")
public interface IPedidoController {
    @GetMapping("getPedidos")
    ResponseEntity<List<PedidoDTO>> getPedidos();

    @GetMapping("getPedidoPorId/{id}")
    ResponseEntity<PedidoDTO> getPedidoPorId(@PathVariable("id") Long codigoPedido);

    @PostMapping("getPedidosFiltrado")
    ResponseEntity<PageableResult<PedidoDTO>> getPedidosFiltrado(@RequestBody PedidoFiltroDTO pedidoFiltroDTO);

    @PostMapping("crearPedido")
    ResponseEntity<PedidoDTO> crearPedido(@RequestBody PedidoDTO pedidoDTO);

    @PostMapping("modificarPedido")
    ResponseEntity<PedidoDTO> modificarPedido(@RequestBody PedidoDTO pedidoDTO);

    @DeleteMapping("eliminarPedido/{id}")
    ResponseEntity<PedidoDTO> eliminarPedido(@PathVariable("id") Long codigoPedido);

    @GetMapping("cambiarEstadoPedidoRecibido/{id}")
    ResponseEntity<PedidoDTO> cambiarEstadoPedidoRecibido(@PathVariable("id") Long codigoPedido);

    @GetMapping("cambiarEstadoPedidEnProceso/{id}")
    ResponseEntity<PedidoDTO> cambiarEstadoPedidoEnProceso(@PathVariable("id") Long codigoPedido);

    @GetMapping("cambiarEstadoPedidoEmpaquetado/{id}")
    ResponseEntity<PedidoDTO> cambiarEstadoPedidoEmpaquetado(@PathVariable("id") Long codigoPedido);

    @GetMapping("cambiarEstadoPedidoEnviado/{id}")
    ResponseEntity<PedidoDTO> cambiarEstadoPedidoEnviado(@PathVariable("id") Long codigoPedido);

    @GetMapping("cambiarEstadoPedidoFinalizado/{id}")
    ResponseEntity<PedidoDTO> cambiarEstadoPedidoFinalizado(@PathVariable("id") Long codigoPedido);

    @GetMapping("cambiarEstadoPedidoCancelado/{id}")
    ResponseEntity<PedidoDTO> cambiarEstadoPedidoCancelado(@PathVariable("id") Long codigoPedido);
}
