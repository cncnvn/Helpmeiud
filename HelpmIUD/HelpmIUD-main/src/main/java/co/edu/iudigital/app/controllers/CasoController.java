package co.edu.iudigital.app.controllers;

import co.edu.iudigital.app.dtos.CasoDTO;
import co.edu.iudigital.app.dtos.DelitoDTO;
import co.edu.iudigital.app.dtos.DelitoDTORequest;
import co.edu.iudigital.app.models.Caso;
import co.edu.iudigital.app.services.ifaces.ICasoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/casos")
public class CasoController {

    @Autowired
    private ICasoService casoService;

    // LISTAR
    @GetMapping
    public ResponseEntity<List<Caso>> index(){
        return ResponseEntity.ok()
                .body(casoService.getAll());
    }

    // CREAR
    @PostMapping
    public ResponseEntity<CasoDTO> save(
            @Validated @RequestBody CasoDTO caso
            ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(casoService.save(caso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CasoDTO> edit(
            @PathVariable Long id,
            @Validated @RequestBody CasoDTO casoDTO) {
        CasoDTO editCaso = casoService.edit(id, casoDTO);
        if (editCaso != null) {
            return ResponseEntity.ok(editCaso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // LISTAR ID
    @GetMapping("/{id}")
    public ResponseEntity<CasoDTO> getById(@PathVariable Long id) {
        CasoDTO caso = casoService.getById(id);
        if (caso != null){
            return ResponseEntity.ok(caso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        casoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
