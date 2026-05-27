package com.graciafernandez.autotrack_springboot.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.graciafernandez.autotrack_springboot.model.Jornada;

public interface  JornadaRepository extends JpaRepository<Jornada, Long> {


    List<Jornada> findByEmpresaCif(String cif);

    @Query("SELECT AVG(j.pagoDia) FROM Jornada j WHERE j.factura.empresa.cif = :cif")
    Double calcularMediaPorEmpresa(@Param("cif") String cif);


}
