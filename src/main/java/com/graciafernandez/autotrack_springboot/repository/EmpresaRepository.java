package com.graciafernandez.autotrack_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graciafernandez.autotrack_springboot.model.Empresa;

public interface  EmpresaRepository extends JpaRepository<Empresa, String> {
    
}
