package com.unisinos.crud_grauA.dto;

import com.unisinos.crud_grauA.entity.Transportadora;

public record TransportadoraResponseDTO(
        Long id,
        String razaoSocial,
        String cnpj,
        String telefone,
        Boolean ativo,
        Long categoriaId,
        String categoriaNome
) {

    public static TransportadoraResponseDTO fromEntity(Transportadora transportadora) {
        return new TransportadoraResponseDTO(
                transportadora.getId(),
                transportadora.getRazaoSocial(),
                transportadora.getCnpj(),
                transportadora.getTelefone(),
                transportadora.getAtivo(),
                transportadora.getCategoria().getId(),
                transportadora.getCategoria().getNome()
        );
    }
}
