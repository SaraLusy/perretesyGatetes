package com.empresa.perretesGatetes.rest.especieAnimal;

import com.empresa.perretesGatetes.business.especieAnimal.IEspecieAnimalService;
import com.empresa.perretesGatetes.domain.dtos.EspecieAnimalDTO;
import com.empresa.perretesGatetes.domain.filters.EspecieAnimalFiltroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class EspecieAnimalControllerImpl implements IEspecieAnimalController {
    @Autowired
    IEspecieAnimalService especieAnimalService;
    @Override
    public ResponseEntity<List<EspecieAnimalDTO>> getEspeciesAnimal() {
        return new ResponseEntity<>(especieAnimalService.getEspeciesAnimal(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EspecieAnimalDTO> getEspecieAnimalPorId(Long codigoEspecieAnimal) {
        return new ResponseEntity<>(especieAnimalService.getEspecieAnimalPorId(codigoEspecieAnimal), HttpStatus.OK);
    }

    @Override
    public ResponseEntity <List<EspecieAnimalDTO>> getEspeciesAnimalesFiltrado(EspecieAnimalFiltroDTO especieAnimalFiltroDTO) {
        return new ResponseEntity<>(especieAnimalService.getEspeciesAnimalesFiltrado(especieAnimalFiltroDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EspecieAnimalDTO> crearEspecieAnimal(EspecieAnimalDTO especieAnimalDTO) {

        return new ResponseEntity<>(especieAnimalService.crearEspecieAnimal(especieAnimalDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EspecieAnimalDTO> modificarEspecieAnimal(EspecieAnimalDTO especieAnimalDTO) {

        return new ResponseEntity<>(especieAnimalService.modificarEspecieAnimal(especieAnimalDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EspecieAnimalDTO> eliminarEspecieAnimal(Long codigoEspecieAnimal) {

        return new ResponseEntity<>(especieAnimalService.eliminarEspecieAnimal(codigoEspecieAnimal), HttpStatus.OK);
    }
}
