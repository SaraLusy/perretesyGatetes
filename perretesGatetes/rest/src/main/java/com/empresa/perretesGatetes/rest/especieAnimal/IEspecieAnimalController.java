package com.empresa.perretesGatetes.rest.especieAnimal;

import com.empresa.perretesGatetes.domain.dtos.EspecieAnimalDTO;
import com.empresa.perretesGatetes.domain.filters.EspecieAnimalFiltroDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("especieAnimal")
public interface IEspecieAnimalController {
    @GetMapping("getEspeciesAnimal")
    public ResponseEntity<List<EspecieAnimalDTO>> getEspeciesAnimal();

    @GetMapping("getEspecieAnimalPorId/{id}")
    public ResponseEntity<EspecieAnimalDTO> getEspecieAnimalPorId(@PathVariable("id") Long codigoEspecieAnimal);

    @PostMapping("getEspeciesAnimalesFiltrado")
    public ResponseEntity<List<EspecieAnimalDTO>> getEspeciesAnimalesFiltrado(@RequestBody EspecieAnimalFiltroDTO especieAnimalFiltroDTO);

    @PostMapping("crearEspecieAnimal")
    public ResponseEntity<EspecieAnimalDTO> crearEspecieAnimal(@RequestBody EspecieAnimalDTO especieAnimalDTO);

    @PostMapping("modificarEspecieAnimal")
    public ResponseEntity<EspecieAnimalDTO> modificarEspecieAnimal(@RequestBody EspecieAnimalDTO especieAnimalDTO);

    @DeleteMapping("eliminarEspecieAnimal/{id}")
    public ResponseEntity<EspecieAnimalDTO> eliminarEspecieAnimal(@PathVariable("id") Long codigoEspecieAnimal);

}
