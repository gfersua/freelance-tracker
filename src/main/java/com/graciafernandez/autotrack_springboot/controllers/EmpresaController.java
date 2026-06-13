package com.graciafernandez.autotrack_springboot.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graciafernandez.autotrack_springboot.model.Empresa;
import com.graciafernandez.autotrack_springboot.service.EmpresaService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/empresas")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;

    @GetMapping
    public List<Empresa> listar() {
        return empresaService.listarTodas();
    }
    

    @GetMapping("/{cif}")
    public ResponseEntity<Empresa> buscarPorCif(@PathVariable String cif) {
        return ResponseEntity.ok(empresaService.buscarPorCif(cif));

    }

    @PostMapping
    public ResponseEntity<Empresa> guardar(@RequestBody Empresa empresa) {
        Empresa guardada = empresaService.guardar(empresa);
        return ResponseEntity.ok(guardada);
    }

    @PutMapping("/{cif}")
    public ResponseEntity<Empresa> actualizar(@PathVariable String cif, @RequestBody Empresa empresa) {
        Empresa existente = empresaService.buscarPorCif(cif);
        existente.setNombre(empresa.getNombre());
        return ResponseEntity.ok(empresaService.guardar(existente));
    }

    @DeleteMapping("/{cif}")
    public ResponseEntity<Void> eliminar(@PathVariable String cif) {
        empresaService.eliminar(cif);
        return ResponseEntity.noContent().build();
    }

}
