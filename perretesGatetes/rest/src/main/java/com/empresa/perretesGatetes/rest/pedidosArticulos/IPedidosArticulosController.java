package com.empresa.perretesGatetes.rest.pedidosArticulos;

import com.empresa.perretesGatetes.domain.dtos.PedidosArticulosDTO;
import com.empresa.perretesGatetes.domain.entities.PedidosArticulosID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("pedidosArticulos")
public interface IPedidosArticulosController {
    @PostMapping("crearPedidosArticulos")
    ResponseEntity<PedidosArticulosDTO> crearPedidosArticulos(@RequestBody PedidosArticulosDTO pedidosArticulosDTO);

    @PostMapping("modificarPedidosArticulos")
    ResponseEntity<PedidosArticulosDTO> modificarPedidosArticulos(@RequestBody PedidosArticulosDTO pedidosArticulosDTO);

    @DeleteMapping("eliminarPedidosArticulos")
    ResponseEntity<PedidosArticulosDTO> eliminarPedidosArticulos(@RequestBody PedidosArticulosID codigoPedidosArticulos);
}
