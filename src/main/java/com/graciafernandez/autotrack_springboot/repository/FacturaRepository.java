package com.graciafernandez.autotrack_springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.graciafernandez.autotrack_springboot.model.EstadoFactura;
import com.graciafernandez.autotrack_springboot.model.Factura;


public interface FacturaRepository extends JpaRepository<Factura, String> {

    List<Factura> findByEstado(EstadoFactura estado);

    List<Factura> findByEmpresaCif(String cif);

    @Query("SELECT f FROM Factura f WHERE YEAR(f.fechaCobrada) = :anio AND MONTH(f.fechaCobrada) = :mes")
    List<Factura> findByMesYAnio(@Param("anio") int anio, @Param("mes") int mes);

    @Query("SELECT f FROM Factura f WHERE f.estado = :estado AND YEAR(f.fechaCobrada) = :anio AND MONTH(f.fechaCobrada) BETWEEN :mesInicio AND :mesFinal")
    List<Factura> facturasTrimestral(
            @Param("estado") EstadoFactura estado,
            @Param("anio") int anio,
            @Param("mesInicio") int mesInicio,
            @Param("mesFinal") int mesFinal
    );

    

}
