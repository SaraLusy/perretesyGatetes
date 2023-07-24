package com.empresa.perretesGatetes.rest.pedidosArticulos;

import com.empresa.perretesGatetes.business.pedidosArticulos.IPedidosArticulosService;
import com.empresa.perretesGatetes.domain.dtos.PedidosArticulosDTO;
import com.empresa.perretesGatetes.domain.entities.PedidosArticulosID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidosArticulosControllerImpl implements IPedidosArticulosController{

    @Autowired
    IPedidosArticulosService pedidosArticulosService;

    @Override
    public ResponseEntity<PedidosArticulosDTO> crearPedidosArticulos(PedidosArticulosDTO pedidosArticulosDTO) {
        return new ResponseEntity<>(pedidosArticulosService.crearPedidosArticulos(pedidosArticulosDTO), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<PedidosArticulosDTO> modificarPedidosArticulos(PedidosArticulosDTO pedidosArticulosDTO) {
        return new ResponseEntity<>(pedidosArticulosService.modificarPedidosArticulos(pedidosArticulosDTO), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<PedidosArticulosDTO> eliminarPedidosArticulos(PedidosArticulosID codigoPedidosArticulos) {
        return new ResponseEntity<>(pedidosArticulosService.eliminarPedidosArticulos(codigoPedidosArticulos), HttpStatus.OK);
    }
}
