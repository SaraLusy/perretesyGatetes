package com.empresa.perretesGatetes.business.pedido;

import com.empresa.perretesGatetes.business.direccion.IDireccionRepository;
import com.empresa.perretesGatetes.business.estadoPedido.IEstadoPedidoRepository;
import com.empresa.perretesGatetes.business.metodoPago.IMetodoPagoRepository;
import com.empresa.perretesGatetes.business.usuario.IUsuarioRepository;
import com.empresa.perretesGatetes.domain.dtos.PedidoDTO;
import com.empresa.perretesGatetes.domain.dtos.pageable.PageableResult;
import com.empresa.perretesGatetes.domain.entities.*;
import com.empresa.perretesGatetes.domain.filters.PedidoFiltroDTO;
import com.empresa.perretesGatetes.domain.types.EstadoPedidoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Service
public class PedidoServiceImpl implements IPedidoService {
    @Autowired
    private IPedidoRepository pedidoRepository;
    @Autowired
    private IMetodoPagoRepository metodoPagoRepository;
    @Autowired
    private IDireccionRepository direccionRepository;
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private IEstadoPedidoRepository estadoPedidoRepository;
    @Transient
    private EstadoPedido estadoPedidoMasReciente;

    @Override
    public List<PedidoDTO> getPedidos() {
        List<Pedido> pedidos;
        pedidos = pedidoRepository.findPedidos();
        return PedidoDTO.toDTO(pedidos);
    }
    @Override
    public PedidoDTO getPedidoPorId(Long codigoPedido) {
        Pedido pedido;
        pedido = pedidoRepository.findPedidoById(codigoPedido);
        return  PedidoDTO.toDTO(pedido);
    }
    @Override
    public PageableResult<PedidoDTO> getPedidosFiltrado(PedidoFiltroDTO pedidoFiltroDTO) {
        return new PageableResult<>(pedidoFiltroDTO.getPageNumber(),
                pedidoRepository.getMaxResults(pedidoFiltroDTO),
                PedidoDTO.toDTO(pedidoRepository.findPedidosByFiltro(pedidoFiltroDTO)));
    }

    @Override
    public PedidoDTO crearPedido(PedidoDTO pedidoDTO) {
        Pedido pedidoNuevo = PedidoDTO.toDomain(pedidoDTO);

        validarPedido(pedidoNuevo);
        pedidoNuevo = this.pedidoRepository.saveAndFlush(pedidoNuevo);

        return PedidoDTO.toDTO(pedidoNuevo);
    }
    @Override
    public PedidoDTO modificarPedido(PedidoDTO pedidoDTO) {
        Pedido pedidoUpdate = PedidoDTO.toDomain(pedidoDTO);
        validarPedido(pedidoUpdate);

        Pedido pedidoEncontrado = pedidoRepository.findPedidoById(pedidoUpdate.getCodigoPedido());
        existePedido(pedidoEncontrado.getCodigoPedido());
        pedidoUpdate = this.pedidoRepository.saveAndFlush(pedidoUpdate);

        return PedidoDTO.toDTO(pedidoUpdate);
    }
    @Override
    public PedidoDTO eliminarPedido(Long codigoPedido) {
        Pedido pedidoEncontrado = pedidoRepository.findPedidoById(codigoPedido);
        existePedido(pedidoEncontrado.getCodigoPedido());

        PedidoDTO pedidoDTO = PedidoDTO.toDTO(this.pedidoRepository.findPedidoById(codigoPedido));
        this.pedidoRepository.deleteById(codigoPedido);

        return pedidoDTO;
    }


    public PedidoDTO cambiarEstadoPedidoRecibido(Long codigoPedido){
        Pedido pedidoCambiar = pedidoRepository.findPedidoById(codigoPedido);
        existePedido(pedidoCambiar.getCodigoPedido());
        //TODO Como coger el usuario que esta conectado
        Usuario usuario = new Usuario();

        if (permitirAdminTrabajador(usuario)){
            comprobarEstadoRecibido(pedidoCambiar);
            pedidoCambiar = marcarEstadoRecibidoAEnProceso(pedidoCambiar, usuario);
        }
        return PedidoDTO.toDTO(pedidoCambiar);
    }
    @Override
    public PedidoDTO cambiarEstadoPedidoEnProceso(Long codigoPedido) {
        Pedido pedidoCambiarEP = pedidoRepository.findPedidoById(codigoPedido);
        existePedido(pedidoCambiarEP.getCodigoPedido());
        Usuario usuario = new Usuario();

        if (permitirAdminTrabajador(usuario)){
            comprobarEstadoEnProceso(pedidoCambiarEP);
            pedidoCambiarEP = marcarEstadoPedidoEnProcesoAEmpaquetado(pedidoCambiarEP, usuario);
        }
        return PedidoDTO.toDTO(pedidoCambiarEP);
    }
    @Override
    public PedidoDTO cambiarEstadoPedidoEmpaquetado(Long codigoPedido) {
        Pedido pedidoCambiarE = pedidoRepository.findPedidoById(codigoPedido);
        existePedido(pedidoCambiarE.getCodigoPedido());
        Usuario usuario = new Usuario();

        if (permitirAdminTrabajador(usuario)){
            comprobarEstadoEmpaquetado(pedidoCambiarE);
            pedidoCambiarE = marcarEstadoPedidoEmpaquetadoAEnviado(pedidoCambiarE, usuario);
        }
        return PedidoDTO.toDTO(pedidoCambiarE);
    }
    @Override
    public PedidoDTO cambiarEstadoPedidoEnviado(Long codigoPedido) {
        Pedido pedidoCambiarEn = pedidoRepository.findPedidoById(codigoPedido);
        existePedido(pedidoCambiarEn.getCodigoPedido());
        Usuario usuario = new Usuario();

        if (permitirAdminTrabajador(usuario)){
            comprobarEstadoEnviado (pedidoCambiarEn);
            pedidoCambiarEn = marcarEstadoPedidoEnviadoAFinalizado(pedidoCambiarEn, usuario);
        }
        return PedidoDTO.toDTO(pedidoCambiarEn);
    }
    @Override
    public PedidoDTO cambiarEstadoPedidoFinalizado(Long codigoPedido) {
        Pedido pedidoCambiarF = pedidoRepository.findPedidoById(codigoPedido);
        existePedido(pedidoCambiarF.getCodigoPedido());
        Usuario usuario = new Usuario();

        if (permitirAdminTrabajador(usuario)){
            comprobarEstadoFinalizado(pedidoCambiarF);
        }
        return PedidoDTO.toDTO(pedidoCambiarF);
    }
    @Override
    public PedidoDTO cambiarEstadoPedidoCancelado(Long codigoPedido) {
        Pedido pedidoCambiarC = pedidoRepository.findPedidoById(codigoPedido);
        existePedido(pedidoCambiarC.getCodigoPedido());
        Usuario usuario = new Usuario();

        if (permitirAdminTrabajadorCliente(usuario)){
            comprobarEstadoEnviado(pedidoCambiarC);
            comprobarEstadoEnProceso(pedidoCambiarC);
        }
        return PedidoDTO.toDTO(pedidoCambiarC);
    }

    private boolean permitirAdminTrabajador(Usuario usuario) {
        if (!usuario.isAdmin() && !usuario.isTrabajador()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pedido.noPermitidoCambiarEstado");
        }
        return true;
    }

    private boolean permitirAdminTrabajadorCliente(Usuario usuario) {
        if (!usuario.isAdmin() && !usuario.isTrabajador() && !usuario.isCliente()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pedido.noPermitidoCambiarEstado");
        }
        return true;
    }

    private void comprobarEstadoRecibido (Pedido pedido) {
        pedido.calcularEstadoPedidoMasReciente();

//        if(pedido.getEstadoPedidoMasReciente() != null &&
//                !pedido.getEstadoPedidoMasReciente().getDescripcion().equalsIgnoreCase(EstadoPedidoType.RECIBIDO.getDescripcion())){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pedido.noPermitidoCambioEstadoEvento");
//        }
    }

    private void comprobarEstadoEnProceso (Pedido pedido) {
        pedido.calcularEstadoPedidoMasReciente();
//            if(pedido.getEstadoPedidoMasReciente() != null
//                    && !pedido.getEstadoPedidoMasReciente().getDescripcion().equalsIgnoreCase(EstadoPedidoType.EN_PROCESO.getDescripcion())){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pedido.noPermitidoCambioEstadoPedido");
//            }
    }

    private void comprobarEstadoEmpaquetado (Pedido pedido) {
        pedido.calcularEstadoPedidoMasReciente();
//            if(pedido.getEstadoPedidoMasReciente() != null
//                    && !pedido.getEstadoPedidoMasReciente().getDescripcion().equalsIgnoreCase(EstadoPedidoType.EMPAQUETADO.getDescripcion())){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pedido.noPermitidoCambioEstadoPedido");
//            }
    }

    private void comprobarEstadoEnviado (Pedido pedido) {
        pedido.calcularEstadoPedidoMasReciente();
            if(pedido.getEstadoPedidoMasReciente() != null
                    && !pedido.getEstadoPedidoMasReciente().getDescripcion().equalsIgnoreCase(EstadoPedidoType.ENVIADO.getDescripcion())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pedido.noPermitidoCambioEstadoPedido");
            }
    }

    private void comprobarEstadoFinalizado(Pedido pedido) {
        pedido.calcularEstadoPedidoMasReciente();
//            if(pedido.getEstadoPedidoMasReciente() != null
//                    && !pedido.getEstadoPedidoMasReciente().getDescripcion().equalsIgnoreCase(EstadoPedidoType.FINALIZADO.getDescripcion())){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pedido.noPermitidoCambioEstadoPedido");
//            }
    }


    private Pedido marcarEstadoRecibidoAEnProceso (Pedido pedido, Usuario usuario) {
        PedidosEstados pedidosEstados = new PedidosEstados();

        pedido.setFechaModificacion(new Date());
        pedidosEstados.setUsuario(usuario);
        pedidosEstados.setFechaCambioEstado(new Date());
        pedidosEstados.setEstadoPedido(estadoPedidoRepository.findEstadoPedidoById(EstadoPedidoType.EN_PROCESO.getCodigoEstadoPedido()));

        pedido.getPedidosEstados().add(pedidosEstados);

        return pedido;
    }

    private Pedido marcarEstadoPedidoEnProcesoAEmpaquetado (Pedido pedido, Usuario usuario) {
        PedidosEstados pedidosEstados = new PedidosEstados();

        pedido.setFechaModificacion(new Date());
        pedidosEstados.setUsuario(usuario);
        pedidosEstados.setFechaCambioEstado(new Date());
        pedidosEstados.setEstadoPedido(estadoPedidoRepository.findEstadoPedidoById(EstadoPedidoType.EMPAQUETADO.getCodigoEstadoPedido()));

        pedido.getPedidosEstados().add(pedidosEstados);

        return pedido;
    }

    private Pedido marcarEstadoPedidoEmpaquetadoAEnviado (Pedido pedido, Usuario usuario) {
        PedidosEstados pedidosEstados = new PedidosEstados();

        pedido.setFechaModificacion(new Date());
        pedidosEstados.setUsuario(usuario);
        pedidosEstados.setFechaCambioEstado(new Date());
        pedidosEstados.setEstadoPedido(estadoPedidoRepository.findEstadoPedidoById(EstadoPedidoType.ENVIADO.getCodigoEstadoPedido()));

        pedido.getPedidosEstados().add(pedidosEstados);

        return pedido;
    }

    private Pedido marcarEstadoPedidoEnviadoAFinalizado
            (Pedido pedido, Usuario usuario) {
        PedidosEstados pedidosEstados = new PedidosEstados();

        pedido.setFechaModificacion(new Date());
        pedidosEstados.setUsuario(usuario);
        pedidosEstados.setFechaCambioEstado(new Date());
        pedidosEstados.setEstadoPedido(estadoPedidoRepository.findEstadoPedidoById(EstadoPedidoType.FINALIZADO.getCodigoEstadoPedido()));

        pedido.getPedidosEstados().add(pedidosEstados);

        return pedido;
    }

    private void validarPedido(Pedido pedido) {
        existePedido(pedido.getCodigoPedido());

        if (pedido.getUsuario() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pedido.requeridoUsuario");
        }
        if (pedido.getUsuario() != null
                && !this.usuarioRepository.existsById(pedido.getUsuario().getCodigoUsuario())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pedido.noEncontradoUsuario");
        }

        if (pedido.getMetodoPago() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pedido.requeridoMetodoPago");
        }

        if (pedido.getMetodoPago() != null
                && !this.metodoPagoRepository.existsById(pedido.getMetodoPago().getCodigoMetodoPago())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pedido.noEncontradoMetodoPago");
        }

        if (pedido.getDireccion() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pedido.requeridoDireccion");
        }
        if (pedido.getDireccion() != null
                && !this.direccionRepository.existsById(pedido.getDireccion().getCodigoDireccion())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pedido.noEncontradoDireccion");
        }
    }

    private void existePedido(Long codigoPedido) {
        if(codigoPedido == null || !this.pedidoRepository.existsById(codigoPedido)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pedido.noEncontrado");
        }
    }
}
