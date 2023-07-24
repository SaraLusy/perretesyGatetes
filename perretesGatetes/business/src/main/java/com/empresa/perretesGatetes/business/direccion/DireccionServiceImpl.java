package com.empresa.perretesGatetes.business.direccion;

import com.empresa.perretesGatetes.business.pedido.IPedidoRepository;
import com.empresa.perretesGatetes.business.tipoDireccion.ITipoDireccionRepository;
import com.empresa.perretesGatetes.business.usuario.IUsuarioRepository;
import com.empresa.perretesGatetes.domain.dtos.DireccionDTO;
import com.empresa.perretesGatetes.domain.entities.Direccion;
import com.empresa.perretesGatetes.domain.entities.TipoDireccion;
import com.empresa.perretesGatetes.domain.filters.DireccionFiltroDTO;
import com.empresa.perretesGatetes.domain.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.util.StringUtils;
import java.util.List;

@Service
public class DireccionServiceImpl implements IDireccionService {
    @Autowired
    IDireccionRepository direccionRepository;
    @Autowired
    ITipoDireccionRepository tipoDireccionRepository;
    @Autowired
    IUsuarioRepository usuarioRepository;
    @Autowired
    IPedidoRepository pedidoRepository;

    @Override
    public List<DireccionDTO> getDirecciones() {
        List<Direccion> direcciones;

        direcciones = direccionRepository.findDirecciones();

        return DireccionDTO.toDTO(direcciones);
    }

    @Override
    public DireccionDTO getDireccionPorId(Long codigoDireccion) {
        Direccion direccion;

        direccion = direccionRepository.findDireccionById(codigoDireccion);

        return DireccionDTO.toDTO(direccion);
    }

    @Override
    public List<DireccionDTO> getDireccionesFiltrado(DireccionFiltroDTO direccionFiltroDTO) {
        return DireccionDTO.toDTO(direccionRepository.findDireccionesFiltrado(direccionFiltroDTO));
    }

    @Override
    public DireccionDTO crearDireccion(DireccionDTO direccionDTO) {
        Direccion direccionNueva = DireccionDTO.toDomain(direccionDTO);

        validarDireccion(direccionNueva);
        direccionNueva = this.direccionRepository.save(direccionNueva);

        return DireccionDTO.toDTO(direccionNueva);
    }

    @Override
    public DireccionDTO modificarDireccion(DireccionDTO direccionDTO) {
        Direccion direccionUpdate = DireccionDTO.toDomain(direccionDTO);
        validarDireccion(direccionUpdate);

        Direccion direccionEncontrado = direccionRepository.findDireccionById(direccionUpdate.getCodigoDireccion());
        existeDireccion(direccionEncontrado);
        direccionUpdate = this.direccionRepository.saveAndFlush(direccionUpdate);

        return DireccionDTO.toDTO(direccionUpdate);
    }

    @Override
    public DireccionDTO eliminarDireccion(Long codigoDireccion) {
        Direccion direccionEncontrada = direccionRepository.findDireccionById(codigoDireccion);

        validarEliminarDireccion(direccionEncontrada);
        direccionRepository.deleteById(codigoDireccion);

        return DireccionDTO.toDTO(direccionEncontrada);
    }

    private void existeDireccion(Direccion direccion) {
        if (direccion == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "direccion.noEncontrada");
        }
    }

    private void validarDireccion(Direccion direccion) {
        existeDireccion(direccion);

        if (!StringUtils.hasText(direccion.getCodigoPostal())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "direccion.requeridoCodigoPostal");
        }
        if (direccion.getCodigoPostal().length() < Constantes.DIRECCION_CODIGO_POSTAL_MIN) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "direccion.caracteresMaxCodigoPostal");
        }
        if (!StringUtils.hasText(direccion.getDireccion())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "direccion.requeridoDireccion");
        }
        if (direccion.getLocalidad() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "direccion.requeridoLocalidad");
        }
        if(direccion.getLocalidad().length() > Constantes.DIRECCION_LOCALIDAD_MAX){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "direccion.caracteresMaxLocalidad");
        }
        if(direccion.getComunidad().length() > Constantes.DIRECCION_COMUNIDAD_MAX){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "direccion.caracteresMaxComunidad");
        }
        if(direccion.getDireccion().length() > Constantes.DIRECCION_DIRECCION_MAX){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "direccion.caracteresMaxDireccion");
        }
        if(direccion.getEscalera().length() > Constantes.DIRECCION_ESCALERA_MAX){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "direccion.caracteresMaxEscalera");
        }
        if(direccion.getPiso().length() > Constantes.DIRECCION_PISO_MAX){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "direccion.caracteresMaxPiso");
        }
        if(direccion.getLetra().length() > Constantes.DIRECCION_LETRA_MAX){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "direccion.caracteresMaxLetra");
        }
        if (direccion.getNumero() >= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "direccion.numeroNoValido");
        }

        if (direccion.getTipoDireccion() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "direccion.requeridoTipoDireccion");
        }

        //Validación Tipo Direccion
        TipoDireccion tipoDireccion = tipoDireccionRepository.findTipoDireccionById(direccion.getTipoDireccion().getcodigoTipoDireccion());
        if (tipoDireccion == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "direccion.noEncontradoTipoDireccion");
        }
//        if (direccion.getTipoDireccion() != null && !tipoDireccionRepository.existsById(direccion.getTipoDireccion().getcodigoTipoDireccion())) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "direccion.noEncontradoTipoDireccion");
//        }

        //Validación Usuario
        if (direccion.getUsuario() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "direccion.requeridoUsuario");
        }

        if (direccion.getUsuario() != null
                && !this.usuarioRepository.existsById(direccion.getUsuario().getCodigoUsuario())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "direccion.noEncontradoUsuario");
        }
    }

    private void validarEliminarDireccion(Direccion direccion) {
        existeDireccion(direccion);

        if (pedidoRepository.getTotalPedidosByDireccion(direccion.getCodigoDireccion()) > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "direccion.pedidosAsociados");
        }
    }

}
