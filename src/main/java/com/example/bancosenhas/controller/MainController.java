package com.example.bancosenhas.controller;

import com.example.bancosenhas.dto.ColaboradorDTO;
import com.example.bancosenhas.model.Colaborador;
import com.example.bancosenhas.service.ColaboradorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class MainController {

    private final ColaboradorService colaboradorService;

    public MainController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @GetMapping("/colaborador")
    public ResponseEntity<List<Colaborador>> getAllColaboradores() {
        return ResponseEntity.ok(this.colaboradorService.getAllColaboradores());
    }


    @GetMapping("/colaborador/{id}")
    public ResponseEntity<Colaborador>  getColaborador(@PathVariable Long id) {
        return ResponseEntity.ok(this.colaboradorService.getColaborador(id));
    }

    @PostMapping("/colaborador")
    public ResponseEntity<Colaborador>  createColaborador(@RequestBody ColaboradorDTO colaborador) {
        return ResponseEntity.ok(this.colaboradorService.createColaborador(colaborador));
    }

    @PatchMapping("/colaborador")
    public ResponseEntity<Colaborador> updateColaborador(@RequestBody ColaboradorDTO colaborador) {
        return ResponseEntity.ok(this.colaboradorService.updateColaborador(colaborador));
    }

    @DeleteMapping("/colaborador/{id}")
    public ResponseEntity<Void> deleteColaborador(@PathVariable Long id) {
        this.colaboradorService.deleteColaborador(id);
        return ResponseEntity.ok().build();

    }


}
