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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graciafernandez.autotrack_springboot.model.EstadoFactura;
import com.graciafernandez.autotrack_springboot.model.Factura;
import com.graciafernandez.autotrack_springboot.service.FacturaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class FacturaController {

    private final FacturaService facturaService;

    @GetMapping
    public List<Factura> listarFacturas() {
        return facturaService.listarTodas();
    }

    @GetMapping("/{idFactura}")
    public ResponseEntity<Factura> buscarFactura(@PathVariable String idFactura) {
        return ResponseEntity.ok(facturaService.buscarPorId(idFactura));
    }

    @GetMapping("/empresa/{cif}")
    public List<Factura> buscarPorEmpresa(@PathVariable String cif) {
        return facturaService.buscarPorEmpresa(cif);
    }

    @GetMapping("/{idFactura}/demora")
    public ResponseEntity<Long> calcularDemora(@PathVariable String idFactura) {
        Factura factura = facturaService.buscarPorId(idFactura);
        return ResponseEntity.ok(facturaService.calcularDemora(factura));
    }

    @PostMapping
    public ResponseEntity<Factura> guardar(@RequestBody Factura factura) {
        return ResponseEntity.ok(facturaService.guardar(factura));
    }

    @PutMapping("/{idFactura}/estado")
    public ResponseEntity<Void> actualizarEstado(
            @PathVariable String idFactura,
            @RequestParam EstadoFactura estado) {
        facturaService.actualizarEstado(idFactura, estado);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idFactura}")
    public ResponseEntity<Void> eliminar(@PathVariable String idFactura) {
        facturaService.eliminar(idFactura);
        return ResponseEntity.noContent().build();
    }

}
