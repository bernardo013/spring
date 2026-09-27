package com.unisinos.crud_grauA.repository;

import com.unisinos.crud_grauA.entity.Transportadora;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransportadoraRepository extends JpaRepository<Transportadora, Long> {

    boolean existsByCnpj(String cnpj);

    boolean existsByCnpjAndIdNot(String cnpj, Long id);

    boolean existsByCategoriaId(Long categoriaId);

    @EntityGraph(attributePaths = "categoria")
    List<Transportadora> findAllByOrderByRazaoSocial();
}
