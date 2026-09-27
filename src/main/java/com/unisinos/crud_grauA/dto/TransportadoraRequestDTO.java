package com.unisinos.crud_grauA.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record TransportadoraRequestDTO(

        @NotBlank(message = "Razão social é obrigatória")
        String razaoSocial,

        @NotBlank(message = "CNPJ é obrigatório")
        @Pattern(regexp = "^\\d{14}$", message = "CNPJ deve conter 14 dígitos numéricos")
        String cnpj,

        @NotBlank(message = "Telefone é obrigatório")
        String telefone,

        Boolean ativo,

        @NotNull(message = "Categoria é obrigatória")
        Long categoriaId
) {
}
