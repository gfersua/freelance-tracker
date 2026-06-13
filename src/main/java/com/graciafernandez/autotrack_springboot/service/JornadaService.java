package com.graciafernandez.autotrack_springboot.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;

import com.graciafernandez.autotrack_springboot.model.Factura;
import com.graciafernandez.autotrack_springboot.model.Jornada;
import com.graciafernandez.autotrack_springboot.repository.FacturaRepository;
import com.graciafernandez.autotrack_springboot.repository.JornadaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JornadaService {

    private final JornadaRepository jornadaRepository;

    private final FacturaRepository facturaRepository;

    public List<Jornada> listarTodas() {
        return jornadaRepository.findAll();
    }

    public Jornada buscarPorId(Long id) {
        return jornadaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jornada no encontrada: " + id));
    }

    public List<Jornada> jornadaPorEmpresa(String cif) {
        return jornadaRepository.listarJornadasPorEmpresa(cif);
    }

    public List<Jornada> jornadaPorFactura(String idFactura) {
        return jornadaRepository.findByFacturaIdFactura(idFactura);
    }

    public BigDecimal mediaPorEmpresa(String cif) {
        Double resultado = jornadaRepository.calcularMediaPorEmpresa(cif);
        return resultado != null
                ? BigDecimal.valueOf(resultado).setScale(2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;
    }

    public Jornada guardar(Jornada jornada) {
        Factura factura = facturaRepository.findById(jornada.getFactura().getIdFactura())
                .orElseThrow(() -> new RuntimeException("Factura no encontrada: " + jornada.getFactura().getIdFactura()));
        jornada.setFactura(factura);
        return jornadaRepository.save(jornada);
    }

    public void eliminar(long id) {
        jornadaRepository.deleteById(id);
    }

}
