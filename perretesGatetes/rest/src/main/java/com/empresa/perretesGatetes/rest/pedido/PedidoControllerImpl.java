package com.empresa.perretesGatetes.rest.pedido;

import com.empresa.perretesGatetes.business.pedido.IPedidoService;
import com.empresa.perretesGatetes.domain.dtos.PedidoDTO;
import com.empresa.perretesGatetes.domain.dtos.pageable.PageableResult;
import com.empresa.perretesGatetes.domain.filters.PedidoFiltroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class PedidoControllerImpl implements IPedidoController {

    @Autowired
    IPedidoService pedidoService;

    @Override
    public ResponseEntity<List<PedidoDTO>> getPedidos() {
        return new ResponseEntity<>(pedidoService.getPedidos(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PedidoDTO> getPedidoPorId(Long codigoPedido) {
        return new ResponseEntity<>(pedidoService.getPedidoPorId(codigoPedido), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageableResult<PedidoDTO>> getPedidosFiltrado(PedidoFiltroDTO pedidoFiltroDTO) {
        return new ResponseEntity<>(pedidoService.getPedidosFiltrado(pedidoFiltroDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PedidoDTO> crearPedido(PedidoDTO pedidoDTO) {
        return new ResponseEntity<>(pedidoService.crearPedido(pedidoDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PedidoDTO> modificarPedido(PedidoDTO pedidoDTO) {
        return new ResponseEntity<>(pedidoService.modificarPedido(pedidoDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PedidoDTO> eliminarPedido(Long codigoPedido) {
        return new ResponseEntity<>(pedidoService.eliminarPedido(codigoPedido), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PedidoDTO> cambiarEstadoPedidoRecibido(Long codigoPedido) {
        return new ResponseEntity<>(pedidoService.cambiarEstadoPedidoRecibido(codigoPedido), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PedidoDTO> cambiarEstadoPedidoEnProceso(Long codigoPedido) {
        return new ResponseEntity<>(pedidoService.cambiarEstadoPedidoEnProceso(codigoPedido), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PedidoDTO> cambiarEstadoPedidoEmpaquetado(Long codigoPedido) {
        return new ResponseEntity<>(pedidoService.cambiarEstadoPedidoEmpaquetado(codigoPedido), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PedidoDTO> cambiarEstadoPedidoEnviado(Long codigoPedido) {
        return new ResponseEntity<>(pedidoService.cambiarEstadoPedidoEnviado(codigoPedido), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PedidoDTO> cambiarEstadoPedidoFinalizado(Long codigoPedido) {
        return new ResponseEntity<>(pedidoService.cambiarEstadoPedidoFinalizado(codigoPedido), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PedidoDTO> cambiarEstadoPedidoCancelado(Long codigoPedido) {
        return new ResponseEntity<>(pedidoService.cambiarEstadoPedidoCancelado(codigoPedido), HttpStatus.OK);
    }
}
