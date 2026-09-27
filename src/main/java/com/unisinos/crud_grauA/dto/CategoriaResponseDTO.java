package com.unisinos.crud_grauA.dto;

import com.unisinos.crud_grauA.entity.Categoria;

public record CategoriaResponseDTO(
        Long id,
        String nome,
        String descricao
) {

    public static CategoriaResponseDTO fromEntity(Categoria categoria) {
        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao()
        );
    }
}
