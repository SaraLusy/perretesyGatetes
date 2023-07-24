package com.empresa.perretesGatetes.business.pedidosArticulos;

import com.empresa.perretesGatetes.business.articulo.IArticuloRepository;
import com.empresa.perretesGatetes.business.pedido.IPedidoRepository;
import com.empresa.perretesGatetes.domain.dtos.PedidosArticulosDTO;
import com.empresa.perretesGatetes.domain.entities.PedidosArticulos;
import com.empresa.perretesGatetes.domain.entities.PedidosArticulosID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PedidosArticulosServiceImpl implements IPedidosArticulosService {

   @Autowired
   private IPedidosArticulosRepository pedidosArticulosRepository;

   @Autowired
   private IPedidoRepository pedidoRepository;

    @Autowired
    private IArticuloRepository articuloRepository;

    @Override
    public PedidosArticulosDTO crearPedidosArticulos(PedidosArticulosDTO pedidosArticulosDTO) {
        PedidosArticulos pedidosArticulosNew = PedidosArticulosDTO.toDomain(pedidosArticulosDTO);

        validarPedidosArticulos(pedidosArticulosNew);
        pedidosArticulosNew = this.pedidosArticulosRepository.saveAndFlush(pedidosArticulosNew);

        return PedidosArticulosDTO.toDTO(pedidosArticulosNew);
    }

    @Override
    public PedidosArticulosDTO modificarPedidosArticulos(PedidosArticulosDTO pedidosArticulosDTO) {
        PedidosArticulos pedidosArticulosUpdate = PedidosArticulosDTO.toDomain(pedidosArticulosDTO);
        validarPedidosArticulos(pedidosArticulosUpdate);

        PedidosArticulos pedidosArticulosEncontrado = pedidosArticulosRepository.findPedidosArticulosById(pedidosArticulosUpdate.getCodigoPedidosArticulos());
        existePedidosArticulos(pedidosArticulosEncontrado.getCodigoPedidosArticulos());
        pedidosArticulosUpdate =this.pedidosArticulosRepository.saveAndFlush(pedidosArticulosUpdate);

        return PedidosArticulosDTO.toDTO(pedidosArticulosUpdate);
    }

    @Override
    public PedidosArticulosDTO eliminarPedidosArticulos(PedidosArticulosID codigoPedidosArticulos) {
        PedidosArticulos pedidosArticulosEncontrado = pedidosArticulosRepository.findPedidosArticulosById(codigoPedidosArticulos);
        existePedidosArticulos(pedidosArticulosEncontrado.getCodigoPedidosArticulos());

        PedidosArticulosDTO pedidosArticulosDTO = PedidosArticulosDTO.toDTO(this.pedidosArticulosRepository.findPedidosArticulosById(codigoPedidosArticulos));
        //this.pedidosArticulosRepository.deleteById(codigoPedidosArticulos);
        //TODO
        return pedidosArticulosDTO;
    }

    private void validarPedidosArticulos(PedidosArticulos pedidosArticulos) {
        existePedidosArticulos(pedidosArticulos.getCodigoPedidosArticulos());

        if (pedidosArticulos.getPedido() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pedidosArticulos.requeridoPedido");
        }
        if (pedidosArticulos.getPedido() != null
                && !this.pedidoRepository.existsById(pedidosArticulos.getPedido().getCodigoPedido())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pedidosArticulos.noEncontradoPedido");
        }

        if (pedidosArticulos.getArticulo() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pedidosArticulos.requeridoArticulo");
        }
        if (pedidosArticulos.getArticulo() != null
                && !this.articuloRepository.existsById(pedidosArticulos.getArticulo().getCodigoArticulo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pedidosArticulos.noEncontradoArticulo");
        }
    }

    private void existePedidosArticulos(PedidosArticulosID codigoPedidosArticulos) {
        if(codigoPedidosArticulos == null ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pedidosArticulos.noEncontrado");
        }
    }
}
