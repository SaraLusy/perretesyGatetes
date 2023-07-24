package com.empresa.perretesGatetes.business.metodoPago;

import com.empresa.perretesGatetes.business.pedido.IPedidoRepository;
import com.empresa.perretesGatetes.business.usuario.IUsuarioRepository;
import com.empresa.perretesGatetes.domain.dtos.MetodoPagoDTO;
import com.empresa.perretesGatetes.domain.entities.MetodoPago;
import com.empresa.perretesGatetes.domain.filters.MetodoPagoFiltroDTO;
import com.empresa.perretesGatetes.domain.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class MetodoPagoServiceImpl implements IMetodoPagoService {
    @Autowired
    private IMetodoPagoRepository metodoPagoRepository;
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Override
    public List<MetodoPagoDTO> getMetodosPago() {
        List<MetodoPago> metodosPago;
        metodosPago  = metodoPagoRepository.findMetodosPago();
        return MetodoPagoDTO.toDTO(metodosPago);
    }

    @Override
    public MetodoPagoDTO getMetodoPagoPorId(Long codigoMetodoPago) {
        MetodoPago metodoPagoEncontrado;
        metodoPagoEncontrado = metodoPagoRepository.findMetodoPagoById(codigoMetodoPago);
        return MetodoPagoDTO.toDTO(metodoPagoEncontrado);
    }

    @Override
    public List<MetodoPagoDTO> getMetodosPagoFiltrado(MetodoPagoFiltroDTO metodoPagoFiltroDTO) {
        return MetodoPagoDTO.toDTO(metodoPagoRepository.findMetodosPagoByFilter(metodoPagoFiltroDTO));
    }

    @Override
    public MetodoPagoDTO crearMetodoPago(MetodoPagoDTO metodoPagoDTO) {
        MetodoPago metodoPago = MetodoPagoDTO.toDomain(metodoPagoDTO);
        validarMetodoPago(metodoPago);
        metodoPago = metodoPagoRepository.save(metodoPago);
        return MetodoPagoDTO.toDTO(metodoPago);
    }

    @Override
    public MetodoPagoDTO modificarMetodoPago(MetodoPagoDTO metodoPagoDTO) {
        MetodoPago metodoPagoUpdate = MetodoPagoDTO.toDomain(metodoPagoDTO);
        validarMetodoPago(metodoPagoUpdate);

        MetodoPago metodoPagoEncontrado = metodoPagoRepository.findMetodoPagoById(metodoPagoUpdate.getCodigoMetodoPago());
        existeMetodoPago(metodoPagoEncontrado);
        metodoPagoUpdate = metodoPagoRepository.save(metodoPagoUpdate);

        return MetodoPagoDTO.toDTO(metodoPagoUpdate);
    }

    @Override
    public MetodoPagoDTO eliminarMetodoPago(Long codigoMetodoPago) {
        MetodoPago metodoPagoEncontrado = metodoPagoRepository.findMetodoPagoById(codigoMetodoPago);
        validarEliminarMetodoPago(metodoPagoEncontrado);
        metodoPagoRepository.deleteById(codigoMetodoPago);

        return MetodoPagoDTO.toDTO(metodoPagoEncontrado);
    }


    private void validarMetodoPago(MetodoPago metodoPago) {
        existeMetodoPago(metodoPago);

        if (!StringUtils.hasText(metodoPago.getNombre())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "metodoPago.requeridoNombre");
        }

        if ((StringUtils.hasText(metodoPago.getNombre()) && metodoPago.getNombre().length() > Constantes.METODO_PAGO_NOMBRE_MAX)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "metodoPago.caracteresMaxNombre");
        }

        if ((StringUtils.hasText(metodoPago.getDescripcion()) && metodoPago.getDescripcion().length() > Constantes.METODO_PAGO_DESCRIPCION_MAX)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "metodoPago.caracteresMaxDescripcion");
        }
    }
    private void validarEliminarMetodoPago(MetodoPago metodoPago) {
        existeMetodoPago(metodoPago);

        if (usuarioRepository.getTotalUsuariosByMetodoPago(metodoPago.getCodigoMetodoPago()) > 0 ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "metodoPago.usuariosAsociados");
        }
        if (pedidoRepository.getTotalPedidosByMetodoPago(metodoPago.getCodigoMetodoPago()) > 0 ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "metodoPago.pedidosAsociados");
        }
    }

    private void existeMetodoPago(MetodoPago metodoPago) {
        if (metodoPago == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "metodoPago.noEncontrado");
        }
    }

}
