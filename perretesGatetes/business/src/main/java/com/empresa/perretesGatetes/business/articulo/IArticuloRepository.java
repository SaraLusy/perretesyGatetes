package com.empresa.perretesGatetes.business.articulo;

import com.empresa.perretesGatetes.domain.entities.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IArticuloRepository extends JpaRepository<Articulo, Long>, IArticuloRepositoryCustom {
    @Query("SELECT distinct a "
            + "FROM Articulo a "
            + " LEFT JOIN FETCH a.especieAnimal "
            + "WHERE 1 = 1 ")
    public List<Articulo> findArticulos();

    @Query("SELECT distinct a FROM Articulo a "
            + "WHERE a.codigoArticulo = :codigoArticulo ")
    public Articulo findArticuloById(long codigoArticulo);

    @Query("SELECT count(*) FROM Articulo a "
            + "WHERE a.especieAnimal.codigoEspecieAnimal = :codigoEspecieAnimal ")
    public int getTotalArticulosByEspecieAnimal(long codigoEspecieAnimal);
}

