package com.example.bancosenhas.service;

import com.example.bancosenhas.constants.NivelHierarquia;
import com.example.bancosenhas.dto.ColaboradorDTO;
import com.example.bancosenhas.model.Colaborador;
import com.example.bancosenhas.repository.ColaboradorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.bancosenhas.util.CalcSenha.calcForcaSenha;

@Service
public class ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;

    public ColaboradorService(ColaboradorRepository colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    public Colaborador getColaborador(Long id) {
        return this.colaboradorRepository.findById(id).orElse(null);
    }

    public List<Colaborador> getAllColaboradores() {
        return this.colaboradorRepository.findAll();
    }

    public Colaborador createColaborador(ColaboradorDTO dto) {
        Colaborador colaborador = Colaborador
                .builder()
                .nome(dto.getNome())
                .senha(dto.getSenha())
                .forcaSenha(calcForcaSenha(dto.getSenha()))
                .nivelHierarquia(NivelHierarquia.COLABORADOR)
                .build();

        return this.colaboradorRepository.save(colaborador);
    }

    public Colaborador updateColaborador(ColaboradorDTO dto) {
        Colaborador colaborador = Colaborador
                .builder()
                .nome(dto.getNome())
                .senha(dto.getSenha())
                .forcaSenha(calcForcaSenha(dto.getSenha()))
                .nivelHierarquia(NivelHierarquia.COLABORADOR)
                .build();

        return this.colaboradorRepository.save(colaborador);
    }

    public void deleteColaborador(Long id) {
        this.colaboradorRepository.deleteById(id);
    }
}
