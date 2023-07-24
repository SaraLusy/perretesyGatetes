package com.empresa.perretesGatetes.business.usuario;

import java.util.List;
import com.empresa.perretesGatetes.business.metodoPago.IMetodoPagoRepository;
import com.empresa.perretesGatetes.business.pedido.IPedidoRepository;
import com.empresa.perretesGatetes.business.rol.IRolRepository;
import com.empresa.perretesGatetes.domain.dtos.UsuarioDTO;
import com.empresa.perretesGatetes.domain.entities.Usuario;
import com.empresa.perretesGatetes.domain.filters.UsuarioFiltroDTO;
import com.empresa.perretesGatetes.domain.utils.Constantes;
import com.empresa.perretesGatetes.domain.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IMetodoPagoRepository metodoPagoRepository;

    @Autowired
    private IRolRepository rolRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Override
    public List<UsuarioDTO> getUsuarios() {
        List<Usuario> usuarios;
        usuarios = usuarioRepository.findUsuarios();
        return UsuarioDTO.toDTO(usuarios);
    }

    @Override
    public UsuarioDTO getUsuarioById(Long codigoUsuario) {
        Usuario usuario;
        usuario = usuarioRepository.findUsuarioById(codigoUsuario);
        return UsuarioDTO.toDTO(usuario);
    }

    @Override
    public List<UsuarioDTO> getUsuariosFiltrado(UsuarioFiltroDTO usuarioFiltroDTO){
        return UsuarioDTO.toDTO(usuarioRepository.findUsuariosByFiltro(usuarioFiltroDTO));
    }

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuarioNuevo = UsuarioDTO.toDomain(usuarioDTO);

        validarUsuario(usuarioNuevo);
        usuarioNuevo = this.usuarioRepository.saveAndFlush(usuarioNuevo);

        return UsuarioDTO.toDTO(usuarioNuevo);
    }

    @Override
    public UsuarioDTO modificarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuarioUpdate = UsuarioDTO.toDomain(usuarioDTO);
        validarUsuario(usuarioUpdate);

        Usuario usuarioEncontrado= usuarioRepository.findUsuarioById(usuarioUpdate.getCodigoUsuario());
        existeUsuario(usuarioEncontrado.getCodigoUsuario());
        usuarioUpdate = this.usuarioRepository.saveAndFlush(usuarioUpdate);

        return UsuarioDTO.toDTO(usuarioUpdate);
    }


    @Override
    public UsuarioDTO eliminarUsuario(Long codigoUsuario) {
        Usuario usuarioEncontrado= usuarioRepository.findUsuarioById(codigoUsuario);

        existeUsuario(codigoUsuario);
        validarEliminarUsuario(usuarioEncontrado);
        usuarioRepository.deleteById(codigoUsuario);

        return UsuarioDTO.toDTO(usuarioEncontrado);

    }

    private void validarUsuario(Usuario usuario) {
        existeUsuario(usuario.getCodigoUsuario());

        if (!StringUtils.hasText(usuario.getNombre())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.requeridoNombre");
        }

        if (usuario.getNombre().length() > Constantes.USUARIO_NOMBRE_MAX) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.caracteresMaxNombre");
        }

        if (!StringUtils.hasText(usuario.getApellidos())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.requeridoApellido");
        }

        if (usuario.getApellidos().length() > Constantes.USUARIO_APELLIDOS_MAX) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.caracteresMaxApellido");
        }

        if (!StringUtils.hasText(usuario.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.requeridoEmail");
        }

        if (usuario.getEmail().length() > Constantes.USUARIO_EMAIL_MAX) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.caracteresMaxEmail");
        }

        if (!Utils.isEmail(usuario.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.formatoErroneoEmail");
        }
        if (usuario.getDni().length() > Constantes.USUARIO_DNI_MAX) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.caracteresMaxDni");
        }
        if (!StringUtils.hasText(usuario.getDni())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.requeridoDni");
        }
        if (usuario.getTelefono().length() > Constantes.USUARIO_TELEFONO_MAX) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.caracteresMaxTelefono");
        }
        if (!StringUtils.hasText(usuario.getTelefono())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.requeridoTelefono");
        }
        // Validación metodo pago
        if (usuario.getMetodoPago() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.requeridoMetodoPago");
        }

        if (usuario.getMetodoPago() != null
                && !this.metodoPagoRepository.existsById(usuario.getMetodoPago().getCodigoMetodoPago())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.noEncontradoMetodoPago");
        }

        // Validación rol
        if (usuario.getRol() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.requeridoRol");
        }

        if (usuario.getRol() != null
                && !this.rolRepository.existsById(usuario.getRol().getCodigoRol())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.noEncontradoRol");
        }

    }

    private void existeUsuario(Long codigoUsuario) {
        if (codigoUsuario == null || !this.usuarioRepository.existsById(codigoUsuario)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.noEncontrado");
        }
    }

    private void validarEliminarUsuario(Usuario usuario) {
        existeUsuario(usuario.getCodigoUsuario());

        if (pedidoRepository.getTotalPedidosByUsuarios(usuario.getCodigoUsuario()) > 0 ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.pedidosAsociados");
        }

    }
}
