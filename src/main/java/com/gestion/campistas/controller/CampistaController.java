package com.gestion.campistas.controller;

import com.gestion.campistas.exception.ResourceNotFoundException;
import com.gestion.campistas.model.Campista;
import com.gestion.campistas.repository.CampistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin( origins = "http://localhost:3000" )
@RestController
@RequestMapping("/api/v1")
public class CampistaController {


    @Autowired
    private CampistaRepository campistaRepository;

    @GetMapping("/campistas")
    public List<Campista> listarCampistas(){
        return campistaRepository.findAll();
    }

    @PostMapping("/campistas")
    public Campista guardarCampista(@RequestBody Campista campista){
        return campistaRepository.save(campista);
    }

    @GetMapping("/campistas/{id}")
    public ResponseEntity<Campista> listarCampistaPorId(@PathVariable Long id){
        Campista campista = campistaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El campista con ese ID no existe : " + id));
        return ResponseEntity.ok(campista);
    }

    @PutMapping("/campistas/{id}")
    public ResponseEntity<Campista> actualizarCampista(@PathVariable Long id,@RequestBody Campista campistaRequest){
        Campista campista = campistaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El campista con ese ID no existe : " + id));
        campista.setNombre(campistaRequest.getNombre());
        campista.setApellido(campistaRequest.getApellido());
        campista.setEmail(campistaRequest.getEmail());

        Campista campistaActualizado = campistaRepository.save(campista);
        return ResponseEntity.ok(campistaActualizado);
    }

    @DeleteMapping("/campistas/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarCampista(@PathVariable Long id){
        Campista campista = campistaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El campista con ese ID no existe : " + id));

        campistaRepository.delete(campista);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
