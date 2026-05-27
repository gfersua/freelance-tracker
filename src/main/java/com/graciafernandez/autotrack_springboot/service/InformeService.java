package com.graciafernandez.autotrack_springboot.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.graciafernandez.autotrack_springboot.model.EstadoFactura;
import com.graciafernandez.autotrack_springboot.model.Factura;
import com.graciafernandez.autotrack_springboot.repository.FacturaRepository;
import com.graciafernandez.autotrack_springboot.repository.JornadaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InformeService {

    private final FacturaRepository facturaRepository;
    private final FacturaService facturaService;
    private final JornadaRepository jornadaRepository;

    public BigDecimal totalMes(int anio, int mes, boolean neto) {
        BigDecimal total = BigDecimal.ZERO;

        List<Factura> lista = facturaRepository.findByMesYAnio(anio, mes);

        for (Factura f : lista) {
            BigDecimal aux = neto ? facturaService.calculoNeto(f) : facturaService.calculoBruto(f);
            total = total.add(aux);
        }
        return total;
    }

    public BigDecimal totalTrimestre(int anio, int mesInicio, int mesFinal, boolean neto) {
        BigDecimal total = BigDecimal.ZERO;

        List<Factura> lista = facturaRepository.facturasTrimestral(EstadoFactura.COBRADA, anio, mesInicio, mesFinal);

        for (Factura f : lista) {
            BigDecimal aux = neto ? facturaService.calculoNeto(f) : facturaService.calculoBruto(f);
            total = total.add(aux);
        }
        return total;
    }

    public BigDecimal jornadaMediaPorEmpresa(String cif) {
        BigDecimal media = BigDecimal.valueOf(jornadaRepository.calcularMediaPorEmpresa(cif));
        return media;
    }

}
