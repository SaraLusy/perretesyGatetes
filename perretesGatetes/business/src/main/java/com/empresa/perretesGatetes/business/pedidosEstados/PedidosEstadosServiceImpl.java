package com.empresa.perretesGatetes.business.pedidosEstados;

import com.empresa.perretesGatetes.business.estadoPedido.IEstadoPedidoRepository;
import com.empresa.perretesGatetes.business.pedido.IPedidoRepository;
import com.empresa.perretesGatetes.business.usuario.IUsuarioRepository;
import com.empresa.perretesGatetes.domain.dtos.PedidosEstadosDTO;
import com.empresa.perretesGatetes.domain.entities.PedidosEstados;
import com.empresa.perretesGatetes.domain.entities.PedidosEstadosID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PedidosEstadosServiceImpl implements IPedidosEstadosService {
    @Autowired
    private IPedidosEstadosRepository pedidosEstadosRepository;
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private IPedidoRepository pedidoRepository;
    @Autowired
    private IEstadoPedidoRepository estadoPedidoRepository;

    @Override
    public PedidosEstadosDTO crearPedidosEstados(PedidosEstadosDTO pedidosEstadosDTO) {
        PedidosEstados pedidosEstadosNew = PedidosEstadosDTO.toDomain(pedidosEstadosDTO);

        validarPedidosEstados(pedidosEstadosNew);
        pedidosEstadosNew = this.pedidosEstadosRepository.saveAndFlush(pedidosEstadosNew);

        return PedidosEstadosDTO.toDTO(pedidosEstadosNew);
    }

    @Override
    public PedidosEstadosDTO modificarPedidoEstados(PedidosEstadosDTO pedidosEstadosDTO) {
        PedidosEstados pedidosEstadosUpdate = PedidosEstadosDTO.toDomain(pedidosEstadosDTO);
        validarPedidosEstados(pedidosEstadosUpdate);

       PedidosEstados pedidosEstadosEncontrado = pedidosEstadosRepository.findPedidosEstadosById(pedidosEstadosUpdate.getCodigoPedidosEstados());
       existePedidosEstados(pedidosEstadosEncontrado.getCodigoPedidosEstados());
       pedidosEstadosUpdate = this.pedidosEstadosRepository.saveAndFlush(pedidosEstadosUpdate);

        return PedidosEstadosDTO.toDTO(pedidosEstadosUpdate);
    }

    @Override
    public PedidosEstadosDTO eliminarPedidosEstados(PedidosEstadosID codigoPedidosEstados) {
        PedidosEstados pedidosEstadosEncontrado = pedidosEstadosRepository.findPedidosEstadosById(codigoPedidosEstados);
        existePedidosEstados(pedidosEstadosEncontrado.getCodigoPedidosEstados());

        PedidosEstadosDTO pedidosEstadosDTO = PedidosEstadosDTO.toDTO(this.pedidosEstadosRepository.findPedidosEstadosById(codigoPedidosEstados));
        //this.pedidosEstadosRepository.deleteById(codigoPedidosEstados);
        //TODO Tengo que crear el método deleteById?
        return pedidosEstadosDTO;
    }

    private void validarPedidosEstados(PedidosEstados pedidosEstados) {
        existePedidosEstados(pedidosEstados.getCodigoPedidosEstados());

        if (pedidosEstados.getPedido() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pedidosEstados.requeridoPedido");
        }
        if (pedidosEstados.getPedido() != null
                && !this.pedidoRepository.existsById(pedidosEstados.getPedido().getCodigoPedido())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pedidosEstados.noEncontradoPedido");
        }

        if (pedidosEstados.getEstadoPedido() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pedidosEstados.requeridoEstadoPedido");
        }
        if (pedidosEstados.getEstadoPedido() != null
                && !this.estadoPedidoRepository.existsById(pedidosEstados.getEstadoPedido().getCodigoEstadoPedido())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pedidosEstados.noEncontradoEstadoPedido");
        }

        if (pedidosEstados.getUsuario() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pedidosEstados.requeridoUsuario");
        }
        if (pedidosEstados.getUsuario() != null
                && !this.usuarioRepository.existsById(pedidosEstados.getUsuario().getCodigoUsuario())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pedidosEstados.noEncontradoUsuario");
        }
    }

    private void existePedidosEstados(PedidosEstadosID codigoPedidosEstados) {
        //TODO Tengo que crear el método existsById?
        //|| !this.pedidosEstadosRepository.existsById(codigoPedidosEstados)
        if(codigoPedidosEstados == null ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pedidosEstados.noEncontrado");
        }
    }
}
