package com.empresa.perretesGatetes.rest.estadoPedido;

import com.empresa.perretesGatetes.business.estadoPedido.IEstadoPedidoService;
import com.empresa.perretesGatetes.domain.dtos.EstadoPedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class EstadoPedidoControllerImpl implements IEstadoPedidoController {

    @Autowired
    IEstadoPedidoService estadoPedidoService;

    @Override
    public ResponseEntity<List<EstadoPedidoDTO>> getEstadosPedido() {
        return new ResponseEntity<>(estadoPedidoService.getEstadosPedido(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EstadoPedidoDTO> getEstadoPedidoPorId(Long codigoEstadoPedido) {
        return new ResponseEntity<>(estadoPedidoService.getEstadoPedidoPorId(codigoEstadoPedido), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EstadoPedidoDTO> crearEstadoPedido(EstadoPedidoDTO estadoPedidoDTO) {
        return new ResponseEntity<>(estadoPedidoService.crearEstadoPedido(estadoPedidoDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EstadoPedidoDTO> modificarEstadoPedido(EstadoPedidoDTO estadoPedidoDTO) {
        return new ResponseEntity<>(estadoPedidoService.modificarEstadoPedido(estadoPedidoDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EstadoPedidoDTO> eliminarEstadoPedido(Long codigoEstadoPedido) {

        return new ResponseEntity<>(estadoPedidoService.eliminarEstadoPedido(codigoEstadoPedido), HttpStatus.OK);
    }
}
