package edu.com.ingsoft.acciones.MercadoValores.aplicacion.CRUD;


import edu.com.ingsoft.acciones.MercadoValores.infraestructura.servicio.IAccionServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import edu.com.ingsoft.acciones.MercadoValores.infraestructura.dto.AccionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccionCRUD {

    @Autowired
    private IAccionServicio servicio;

    @PostMapping({"/accion/guardar"})
    public ResponseEntity<AccionDTO> guardar(@Valid @RequestBody AccionDTO a){
        return  new ResponseEntity<>(servicio.guardarAccion(a), HttpStatus.CREATED);
    }

    @GetMapping({"accion/obtener"})
    public ResponseEntity<List<AccionDTO>> obtenerAcciones (){
        return new ResponseEntity<>(servicio.obtenerAcciones(),HttpStatus.FOUND);
    }



    @GetMapping({"accion/obtener/{id}"})
    public ResponseEntity<AccionDTO> obtenerAccion(@Valid @PathVariable(name = "id") Long id){
        return new ResponseEntity<>(servicio.obtenerAccionPorId(id),HttpStatus.FOUND);
    }


    @PutMapping({"accion/actualizar/{id}"})
    public ResponseEntity<AccionDTO> actualizar(@Valid @RequestBody AccionDTO upA ,
                                                @Valid @PathVariable(name = "id") Long id ){
        return ResponseEntity.ok(servicio.actualizarAccion(id,upA));
    }

    @PutMapping({"accion/actualizarPrecio/{id}"})
    public ResponseEntity<AccionDTO> actualizarPrecio(@Valid @RequestParam double precio ,
                                                      @Valid @PathVariable(name = "id") Long id ){
        return ResponseEntity.ok(servicio.cambiarPrecioAccion(id, precio));
    }
    @DeleteMapping({"accion/borrar/{id}"})
    public ResponseEntity<String> borrar(@Valid @PathVariable(name = "id") Long id){
        servicio.removerAccion(id);
        return new ResponseEntity<>("ACCION " + id + " ELIMINADA" ,HttpStatus.OK);
    }


}
