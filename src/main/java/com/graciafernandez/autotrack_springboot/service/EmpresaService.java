package com.graciafernandez.autotrack_springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.graciafernandez.autotrack_springboot.model.Empresa;
import com.graciafernandez.autotrack_springboot.repository.EmpresaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public Empresa buscarPorCif(String cif) {
        return empresaRepository.findById(cif)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado ninguna empresa con el cif" + cif));
    }

    public List<Empresa> listarTodas() {
        return empresaRepository.findAll();
    }

    public Empresa guardar(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public void eliminar(String cif) {
        empresaRepository.deleteById(cif);
    }



}
