package com.graciafernandez.autotrack_springboot.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "facturas")
@Data
@NoArgsConstructor
public class Factura {

    @Id
    @Column(name = "id_factura", nullable = false)
    private String idFactura; // Ej: "2025-001"

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cif_empresa", nullable = false)
    private Empresa empresa;

    @JsonManagedReference("factura-jornada")
    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Jornada> jornadas = new ArrayList<>();

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total; // Base imponible

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal irpf; // Porcentaje, ej: 15.00

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal iva; // Porcentaje, ej: 21.00

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoFactura estado;

    @Column(name = "fecha_expedida", nullable = false)
    private LocalDate fechaExpedida;

    @Column(name = "fecha_cobrada")
    private LocalDate fechaCobrada;

    
}