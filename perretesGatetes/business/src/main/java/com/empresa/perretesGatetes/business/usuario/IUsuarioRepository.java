package com.empresa.perretesGatetes.business.usuario;

import com.empresa.perretesGatetes.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long>, IUsuarioRepositoryCustom {

    @Query("SELECT distinct u "
            + "FROM Usuario u "
            + "LEFT JOIN FETCH u.rol "
            + "LEFT JOIN FETCH u.metodoPago "
            + "ORDER BY u.nombre ASC, u.apellidos ASC")
    public List<Usuario> findUsuarios();

    @Query("SELECT distinct u FROM Usuario u "
            + "WHERE u.codigoUsuario = :codigoUsuario ")
    public Usuario findUsuarioById(long codigoUsuario);

    @Query("SELECT count(*) FROM Usuario u "
            + "WHERE u.rol.codigoRol = :codigoRol ")
    public int getTotalUsuariosByRol(long codigoRol);

    @Query("SELECT count(*) FROM Usuario u "
            + "WHERE u.metodoPago.codigoMetodoPago = :codigoMetodoPago ")
    public int getTotalUsuariosByMetodoPago(long codigoMetodoPago);

    //TODO Es necesario hacer esta validación
//    @Query("SELECT count(*) FROM Usuario u "
//            + "WHERE u.pedidos = :codigoPedido ")
//    public int getTotalUsuariosByPedido(long codigoPedido);


//    @Query("SELECT count(*) FROM Usuario u "
//            + "WHERE u.direcciones = :codigoDireccion ")
//    public int getTotalUsuariosByDireccion(long codigoDireccion);

}
