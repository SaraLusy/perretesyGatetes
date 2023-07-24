package com.empresa.perretesGatetes.persistence.pedido;

import com.empresa.perretesGatetes.business.pedido.IPedidoRepositoryCustom;
import com.empresa.perretesGatetes.domain.entities.Pedido;
import com.empresa.perretesGatetes.domain.filters.ArticuloFiltroDTO;
import com.empresa.perretesGatetes.domain.filters.PedidoFiltroDTO;
import com.empresa.perretesGatetes.persistence.IBaseRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class IPedidoRepositoryImpl extends IBaseRepositoryImpl implements IPedidoRepositoryCustom {
    @Override
    public List<Pedido> findPedidosByFiltro(PedidoFiltroDTO pedidoFiltroDTO) {
        String query = "SELECT DISTINCT p FROM Pedido p "
                + " LEFT JOIN FETCH p.metodoPago metodoPago "
                + " LEFT JOIN FETCH p.direccion direccion "
                + " LEFT JOIN FETCH p.usuario usuario "
                + " LEFT JOIN FETCH p.pedidosEstados pe "
                + " LEFT JOIN FETCH pe.estadoPedido ep "
                + " WHERE 1=1 ";

        Map<String, Object> parameters = getParameters(pedidoFiltroDTO);
        String queryConditions = getQueryConditions(pedidoFiltroDTO);

        TypedQuery<Pedido> typedQuery = em.createQuery(query + queryConditions, Pedido.class);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            typedQuery.setParameter(entry.getKey(), entry.getValue());
        }
        if(pedidoFiltroDTO.getPageable()) {
            typedQuery.setFirstResult(pedidoFiltroDTO.getPageNumber() * pedidoFiltroDTO.getPageElements());
            typedQuery.setMaxResults(pedidoFiltroDTO.getPageElements());
        }
            return typedQuery.getResultList();
    }

    @Override
    public int getMaxResults(PedidoFiltroDTO pedidoFiltroDTO) {
        String query = "SELECT count(distinct p) FROM Pedido p "
                + "WHERE 1=1";

        Map<String,Object> parameters = getParameters(pedidoFiltroDTO);
        String queryConditions = getQueryConditions(pedidoFiltroDTO);
        String orderQuery = getOrderQuery(pedidoFiltroDTO);

        TypedQuery<Long> typedQuery = em.createQuery(query + queryConditions + orderQuery, Long.class);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            typedQuery.setParameter(entry.getKey(),entry.getValue());
        }

        return typedQuery.getSingleResult().intValue();
    }

    private Map<String, Object> getParameters(PedidoFiltroDTO pedidoFiltroDTO) {
        Map<String, Object> parameters = new HashMap<>();

        if(pedidoFiltroDTO.getCodigoMetodoPago() != null && pedidoFiltroDTO.getCodigoMetodoPago() != 0){
            parameters.put("codigoMetodoPago",pedidoFiltroDTO.getCodigoPedido());
        }
        if(pedidoFiltroDTO.getCodigoDireccion() != null && pedidoFiltroDTO.getCodigoDireccion() != 0){
            parameters.put("codigoDireccion",pedidoFiltroDTO.getCodigoPedido());
        }
        if(pedidoFiltroDTO.getCodigoUsuario() != null && pedidoFiltroDTO.getCodigoUsuario() != 0){
            parameters.put("codigoUsuario",pedidoFiltroDTO.getCodigoPedido());
        }
        return parameters;
    }

    private String getOrderQuery(PedidoFiltroDTO pedidoFiltroDTO){
        String orderQuery = "ORDER BY p.codigoPedido DESC ";

        if (StringUtils.hasText(pedidoFiltroDTO.getOrderBy())) {
            switch (pedidoFiltroDTO.getOrderBy()) {
                case "fechaPedido": {
                    orderQuery = "ORDER BY p.fechaPedido " + (pedidoFiltroDTO.getOrderDesc() ? " DESC " : " ASC ");
                    break;
                }
                case "fechaModificacion": {
                    orderQuery = "ORDER BY p.fechaModificacion " + (pedidoFiltroDTO.getOrderDesc() ? " DESC " : " ASC ");
                    break;
                }
                case "importeTotal": {
                    orderQuery = "ORDER BY p.importeTotal " + (pedidoFiltroDTO.getOrderDesc() ? " DESC " : " ASC ");
                    break;
                }
                case "metodoPago": {
                    orderQuery = "ORDER BY p.metodoPago.descripcion " + (pedidoFiltroDTO.getOrderDesc() ? " DESC " : " ASC ");
                    break;
                }
                case "direccion": {
                    orderQuery = "ORDER BY p.direccion.direccion " + (pedidoFiltroDTO.getOrderDesc() ? " DESC " : " ASC ");
                    break;
                }
                case "estadoPedido": {
                    orderQuery = "ORDER BY p.estadoPedido.descripcion " + (pedidoFiltroDTO.getOrderDesc() ? " DESC " : " ASC ");
                    break;
                }
                default: {
                    break;
                }
            }
        }

        return orderQuery;
    }
    private String getQueryConditions(PedidoFiltroDTO pedidoFiltroDTO) {
        String queryConditions = "";

        if (pedidoFiltroDTO.getCodigoUsuario() != null && pedidoFiltroDTO.getCodigoUsuario() != 0) {
            queryConditions += " AND d.usuario.codigoUsuario = :codigoUsuario ";
        }
        if (pedidoFiltroDTO.getCodigoDireccion() != null && pedidoFiltroDTO.getCodigoDireccion() != 0) {
            queryConditions += " AND d.direccion.codigoDireccion = :codigoDireccion ";
        }
        if (pedidoFiltroDTO.getCodigoMetodoPago() != null && pedidoFiltroDTO.getCodigoMetodoPago() != 0) {
            queryConditions += " AND d.metodoPago.codigoMetodoPago = :codigoMetodoPago ";
        }

        return queryConditions;
    }

}
