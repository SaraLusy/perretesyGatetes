package com.empresa.perretesGatetes.rest.estadoPedido;

import com.empresa.perretesGatetes.domain.dtos.EstadoPedidoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("estadoPedido")
public interface IEstadoPedidoController {

    @GetMapping("getEstadosPedido")
    public ResponseEntity<List<EstadoPedidoDTO>> getEstadosPedido();

    @GetMapping("getEstadoPedidoPorId/{id}")
    public ResponseEntity<EstadoPedidoDTO> getEstadoPedidoPorId(@PathVariable("id") Long codigoEstadoPedido);

    @PostMapping("crearEstadoPedido")
    public ResponseEntity<EstadoPedidoDTO> crearEstadoPedido(@RequestBody EstadoPedidoDTO estadoPedidoDTO);

    @PostMapping("modificarEstadoPedido")
    public ResponseEntity<EstadoPedidoDTO> modificarEstadoPedido(@RequestBody EstadoPedidoDTO estadoPedidoDTO);

    @DeleteMapping("eliminarEstadoPedido/{id}")
    public ResponseEntity<EstadoPedidoDTO> eliminarEstadoPedido(@PathVariable("id") Long codigoEstadoPedido);
}
