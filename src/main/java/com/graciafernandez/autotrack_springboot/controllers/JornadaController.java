package com.graciafernandez.autotrack_springboot.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graciafernandez.autotrack_springboot.model.Jornada;
import com.graciafernandez.autotrack_springboot.service.JornadaService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class JornadaController {

    private final JornadaService jornadaService;
  
    @GetMapping("/{id_factura}/jornadas/{id_jornada}")
public ResponseEntity<Jornada> buscarJornada(@PathVariable Long id_jornada) {
    return ResponseEntity.ok(jornadaService.buscarPorId(id_jornada));
}


       @GetMapping("/{id_factura}/jornadas")
    public List<Jornada> listarPorFactura(@PathVariable String id_factura) {
        return jornadaService.jornadaPorFactura(id_factura);
    }


    @PostMapping("/{id_factura}/jornadas")
    public ResponseEntity<Jornada> registrarJornada(@RequestBody Jornada jornada) {

        return ResponseEntity.ok(jornadaService.guardar(jornada));
    }
    
    @DeleteMapping("/{id_factura}/jornadas/{id_jornada}")

      public ResponseEntity<Void> eliminarJornada(@PathVariable Long id_jornada) {

        jornadaService.eliminar(id_jornada);
        return ResponseEntity.noContent().build();
    }
    
}
