package com.empresa.perretesGatetes.business.estadoPedido;

import com.empresa.perretesGatetes.business.pedidosEstados.IPedidosEstadosRepository;
import com.empresa.perretesGatetes.business.usuario.IUsuarioService;
import com.empresa.perretesGatetes.domain.dtos.EstadoPedidoDTO;
import com.empresa.perretesGatetes.domain.entities.EstadoPedido;
import com.empresa.perretesGatetes.domain.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EstadoPedidoServiceImpl implements IEstadoPedidoService{

    @Autowired
    IUsuarioService usuarioService;

    @Autowired
    IPedidosEstadosRepository pedidosEstadosRepository;

    @Autowired
    private IEstadoPedidoRepository estadoPedidoRepository;
    @Override
    public List<EstadoPedidoDTO> getEstadosPedido() {
        List<EstadoPedido> estadosPedido;
        estadosPedido = estadoPedidoRepository.findEstadosPedido();
        return EstadoPedidoDTO.toDTO(estadosPedido);
    }

    @Override
    public EstadoPedidoDTO getEstadoPedidoPorId(Long codigoEstadoPedido) {
        EstadoPedido estadoPedido;
        estadoPedido = estadoPedidoRepository.findEstadoPedidoById(codigoEstadoPedido);
        return EstadoPedidoDTO.toDTO(estadoPedido);
    }

    @Override
    public EstadoPedidoDTO crearEstadoPedido(EstadoPedidoDTO estadoPedidoDTO) {
        EstadoPedido estadoPedido = EstadoPedidoDTO.toDomain(estadoPedidoDTO);
        validarEstadoPedido(estadoPedido);
        estadoPedido = estadoPedidoRepository.save(estadoPedido);
        return EstadoPedidoDTO.toDTO(estadoPedido);
    }

    @Override
    public EstadoPedidoDTO modificarEstadoPedido(EstadoPedidoDTO estadoPedidoDTO){
        EstadoPedido estadoPedidoUpdate = EstadoPedidoDTO.toDomain(estadoPedidoDTO);
        validarEstadoPedido(estadoPedidoUpdate);

        EstadoPedido estadoPedidoEncontrada = estadoPedidoRepository.findEstadoPedidoById(estadoPedidoUpdate.getCodigoEstadoPedido());
        existeEstadoPedido(estadoPedidoEncontrada);
        estadoPedidoUpdate = estadoPedidoRepository.save(estadoPedidoUpdate);

        return EstadoPedidoDTO.toDTO(estadoPedidoUpdate);
    }

    @Override
    public EstadoPedidoDTO eliminarEstadoPedido(Long codigoEstadoPedido) {
        EstadoPedido estadoPedidoDelete = estadoPedidoRepository.findEstadoPedidoById(codigoEstadoPedido);

        validarEliminarEstadoPedido(estadoPedidoDelete);
        estadoPedidoRepository.deleteById(codigoEstadoPedido);

        return EstadoPedidoDTO.toDTO(estadoPedidoDelete);
    }



    private void validarEstadoPedido(EstadoPedido estadoPedido) {
        existeEstadoPedido(estadoPedido);

        if (!StringUtils.hasText(estadoPedido.getNombre())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "estadoPedido.requeridoNombre");
        }

        if ((StringUtils.hasText(estadoPedido.getNombre()) && estadoPedido.getNombre().length() > Constantes.ESTADO_PEDIDO_NOMBRE_MAX)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "estadoPedido.caracteresMaxNombre");
        }

        if ((StringUtils.hasText(estadoPedido.getDescripcion()) && estadoPedido.getDescripcion().length() > Constantes.ESTADO_PEDIDO_DESCRIPCION_MAX)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "estadoPedido.caracteresMaxDescripcion");
        }
    }

    private void existeEstadoPedido(EstadoPedido estadoPedido) {
        if (estadoPedido == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "estadoPedido.noEncontrado");
        }
    }

    private void validarEliminarEstadoPedido(EstadoPedido estadoPedido) {
        existeEstadoPedido(estadoPedido);

        if (pedidosEstadosRepository.getTotalPedidosEstadosByEstadoPedido(estadoPedido.getCodigoEstadoPedido()) > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "estadoPedido.pedidosEstadosAsociados");
        }

    }

}
