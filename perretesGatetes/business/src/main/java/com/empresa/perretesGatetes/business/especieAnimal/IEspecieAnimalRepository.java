package com.empresa.perretesGatetes.business.especieAnimal;

import com.empresa.perretesGatetes.domain.entities.EspecieAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface IEspecieAnimalRepository extends JpaRepository<EspecieAnimal, Long>, IEspecieAnimalRepositoryCustom {

    @Query("SELECT distinct ea "
            + "FROM EspecieAnimal ea "
            + "ORDER BY ea.nombre")
    public List<EspecieAnimal> findEspeciesAnimal();
    @Query("SELECT distinct ea FROM EspecieAnimal ea "
            + "WHERE ea.codigoEspecieAnimal = :codigoEspecieAnimal ")
    public EspecieAnimal findEspecieAnimalById(long codigoEspecieAnimal);
}
