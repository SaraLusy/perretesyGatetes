package com.empresa.perretesGatetes.business.pedido;

import com.empresa.perretesGatetes.domain.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface IPedidoRepository extends JpaRepository<Pedido, Long>, IPedidoRepositoryCustom {

    @Query("SELECT distinct p "
            + "FROM Pedido p "
            + " LEFT JOIN FETCH p.direccion "
            + " LEFT JOIN FETCH p.usuario "
            + " LEFT JOIN FETCH p.metodoPago "
            + "WHERE 1 = 1 ")
    public List<Pedido> findPedidos();

    @Query("SELECT distinct p FROM Pedido p "
            + "WHERE p.codigoPedido = :codigoPedido ")
    public Pedido findPedidoById(long codigoPedido);

    @Query("SELECT count(*) FROM Pedido p "
            + "WHERE p.direccion.codigoDireccion = :codigoDireccion ")
    public int getTotalPedidosByDireccion(long codigoDireccion);

    @Query("SELECT count(*) FROM Pedido p "
            + "WHERE p.metodoPago.codigoMetodoPago = :codigoMetodoPago ")
    public int getTotalPedidosByMetodoPago(long codigoMetodoPago);

    @Query("SELECT count(*) FROM Pedido p "
            + "WHERE p.usuario.codigoUsuario = :codigoUsuario ")
    public int getTotalPedidosByUsuarios(long codigoUsuario);



}

