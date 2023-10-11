package com.example.bancosenhas.model;

import com.example.bancosenhas.constants.NivelHierarquia;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String nome;
    private String senha;
    private int forcaSenha;
    private NivelHierarquia nivelHierarquia;

}
