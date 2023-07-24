package com.empresa.perretesGatetes.business.pedidosArticulos;

import com.empresa.perretesGatetes.domain.entities.PedidosArticulos;
import com.empresa.perretesGatetes.domain.entities.PedidosArticulosID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPedidosArticulosRepository extends JpaRepository<PedidosArticulos, Long> {
    @Query("SELECT distinct pa FROM PedidosArticulos pa "
            + "WHERE pa.codigoPedidosArticulos = :codigoPedidosArticulos ")
    public PedidosArticulos findPedidosArticulosById(PedidosArticulosID codigoPedidosArticulos);


    @Query("SELECT count(*) FROM PedidosArticulos pa "
            + "WHERE pa.articulo.codigoArticulo = :codigoArticulo ")
    public int getTotalPedidosArticulosByArticulo(long codigoArticulo);
}
