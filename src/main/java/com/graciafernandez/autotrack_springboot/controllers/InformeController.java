package com.graciafernandez.autotrack_springboot.controllers;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graciafernandez.autotrack_springboot.service.InformeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/informes")
@RequiredArgsConstructor
public class InformeController {

    private final InformeService informeService;


    @GetMapping("/mes")
    public ResponseEntity<BigDecimal> totalMes(
            @RequestParam int anio,
            @RequestParam int mes,
            @RequestParam boolean neto) {

        return ResponseEntity.ok(informeService.totalMes(anio, mes, neto));
    }



    @GetMapping("/trimestre")
    public  ResponseEntity<BigDecimal> totalTrimestre(
            @RequestParam int anio,
            @RequestParam int mesInicio,
            @RequestParam int mesFinal,
            @RequestParam boolean neto){
        return ResponseEntity.ok(informeService.totalTrimestre(anio, mesInicio, mesFinal, neto));
    }


    @GetMapping("/jornadamedia")
    public ResponseEntity<BigDecimal> jornadaMedia(@RequestParam String cif) {
        return ResponseEntity.ok(informeService.jornadaMediaPorEmpresa(cif));
    }
    

    

}
