package com.empresa.perretesGatetes.rest.pedidosEstados;

import com.empresa.perretesGatetes.business.pedidosArticulos.IPedidosArticulosService;
import com.empresa.perretesGatetes.business.pedidosEstados.IPedidosEstadosService;
import com.empresa.perretesGatetes.domain.dtos.PedidosEstadosDTO;
import com.empresa.perretesGatetes.domain.entities.PedidosEstadosID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidosEstadosControllerImpl implements IPedidosEstadosController {
    @Autowired
    IPedidosEstadosService pedidosEstadosService;
    @Override
    public ResponseEntity<PedidosEstadosDTO> crearPedidosEstados(PedidosEstadosDTO pedidosEstadosDTO) {
        return new ResponseEntity<>(pedidosEstadosService.crearPedidosEstados(pedidosEstadosDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PedidosEstadosDTO> modificarPedidoEstados(PedidosEstadosDTO pedidosEstadosDTO) {
        return new ResponseEntity<>(pedidosEstadosService.modificarPedidoEstados(pedidosEstadosDTO), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<PedidosEstadosDTO> eliminarPedidosEstados(PedidosEstadosID codigoPedidosEstados) {
        return new ResponseEntity<>(pedidosEstadosService.eliminarPedidosEstados(codigoPedidosEstados), HttpStatus.OK);
    }
}
