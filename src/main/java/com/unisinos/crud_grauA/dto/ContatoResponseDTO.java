package com.unisinos.crud_grauA.dto;

import com.unisinos.crud_grauA.entity.Contato;

public record ContatoResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String cargo
) {

    public static ContatoResponseDTO fromEntity(Contato contato) {
        return new ContatoResponseDTO(
                contato.getId(),
                contato.getNome(),
                contato.getEmail(),
                contato.getTelefone(),
                contato.getCargo()
        );
    }
}
