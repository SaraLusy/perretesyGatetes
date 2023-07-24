package com.empresa.perretesGatetes.business.rol;

import com.empresa.perretesGatetes.business.usuario.IUsuarioRepository;
import com.empresa.perretesGatetes.domain.dtos.RolDTO;
import com.empresa.perretesGatetes.domain.entities.Rol;
import com.empresa.perretesGatetes.domain.filters.RolFiltroDTO;
import com.empresa.perretesGatetes.domain.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class RolServiceImpl implements IRolService {
    @Autowired
    private IRolRepository rolRepository;
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public List<RolDTO> getRoles() {
        List<Rol> roles;
        roles = rolRepository.findRoles();
        return RolDTO.toDTO(roles);
    }

    @Override
    public RolDTO getRolPorId(Long codigoRol) {
        Rol rol;
        rol = rolRepository.findRolById(codigoRol);
        return RolDTO.toDTO(rol);
    }

    @Override
    public List<RolDTO> getRolesFiltrado(RolFiltroDTO rolFiltroDTO) {
       return RolDTO.toDTO(rolRepository.findRolesByFiltro(rolFiltroDTO));
    }

    @Override
    public RolDTO crearRol(RolDTO rolDTO) {
        Rol rol = RolDTO.toDomain(rolDTO);
        validarRol(rol);
        rol = rolRepository.save(rol);
        return RolDTO.toDTO(rol);
    }

    @Override
    public RolDTO modificarRol(RolDTO rolDTO) {
        Rol rolUpdate = RolDTO.toDomain(rolDTO);
        validarRol(rolUpdate);

        Rol rolEncontrado = rolRepository.findRolById(rolUpdate.getCodigoRol());
        existeRol(rolEncontrado);
        rolUpdate = rolRepository.saveAndFlush(rolUpdate);

        return RolDTO.toDTO(rolUpdate);
    }

    @Override
    public RolDTO eliminarRol(Long codigoRol) {
        Rol rolEncontrado = rolRepository.findRolById(codigoRol);

        validarEliminarRol(rolEncontrado);
        rolRepository.deleteById(codigoRol);

        return RolDTO.toDTO(rolEncontrado);
    }

    private void existeRol(Rol rol) {
        if (rol == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "rol.noEncontrado");
        }
    }

    private void validarRol(Rol rol) {
        existeRol(rol);
        if (!StringUtils.hasText(rol.getNombre())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "rol.requeridoNombre");
        }

        if ((StringUtils.hasText(rol.getNombre()) && rol.getNombre().length() > Constantes.ROL_NOMBRE_MAX)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "rol.caracteresMaxNombre");
        }

        if ((StringUtils.hasText(rol.getDescripcion()) && rol.getDescripcion().length() > Constantes.ROL_DESCRIPCION_MAX)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "rol.caracteresMaxDescripcion");
        }
    }

    private void validarEliminarRol(Rol rol) {
        existeRol(rol);

        if (usuarioRepository.getTotalUsuariosByRol(rol.getCodigoRol()) > 0 ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "rol.usuariosAsociados");
        }
    }
}
