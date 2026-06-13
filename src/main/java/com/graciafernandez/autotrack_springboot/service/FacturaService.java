package com.graciafernandez.autotrack_springboot.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.graciafernandez.autotrack_springboot.model.EstadoFactura;
import com.graciafernandez.autotrack_springboot.model.Factura;
import com.graciafernandez.autotrack_springboot.repository.FacturaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacturaService {

    private final FacturaRepository facturaRepository;


    public Factura buscarPorId(String id) {
        return facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado ninguna factura con el id " + id));
    }


     public List<Factura> buscarEstado(EstadoFactura estado) {
        return facturaRepository.findByEstado(estado);
    }


    public List<Factura> buscarPorEmpresa(String cif) {
        return facturaRepository.findByEmpresaCif(cif);
    }


    public List<Factura> listarTodas() {
        return facturaRepository.findAll();
    }


     public List<Factura> buscarMesyAnio(int mes, int anio) {
        return facturaRepository.findByMesYAnio(anio, mes);
    }

     public List<Factura> listarTrimestral(int anio, int mesInicio, int mesFinal) {
        return facturaRepository.facturasTrimestral(EstadoFactura.COBRADA, anio, mesInicio, mesFinal);
    }


    public Factura guardar(Factura factura) {
        return facturaRepository.save(factura);
    }

    public void eliminar(String cif) {
        facturaRepository.deleteById(cif);
    }

    public BigDecimal calculoBruto(Factura factura) {
        BigDecimal base = factura.getTotal();

        BigDecimal ivaImporte = base.multiply(factura.getIva())
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        return base.add(ivaImporte);
    }

    public BigDecimal calculoNeto(Factura factura) {

        BigDecimal base = factura.getTotal();
        BigDecimal irpfImporte = base.multiply(factura.getIrpf())
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        return base.subtract(irpfImporte);

    }

    public long calcularDemora(Factura factura) {

        LocalDate fechaInicio = factura.getFechaExpedida();

        LocalDate fechaFin = factura.getFechaCobrada();

        if (fechaFin == null) {
            fechaFin = LocalDate.now();
        }
        return ChronoUnit.DAYS.between(fechaInicio, fechaFin);

    }

    public void actualizarEstado(String idFactura, EstadoFactura estado) {

        Factura factura = facturaRepository.findById(idFactura)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada: " + idFactura));

        if (estado == EstadoFactura.COBRADA && factura.getFechaCobrada() == null) {
            factura.setFechaCobrada(LocalDate.now());
        }

        factura.setEstado(estado);
        facturaRepository.save(factura);
    }

}
