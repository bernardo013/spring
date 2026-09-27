package com.unisinos.crud_grauA.repository;

import com.unisinos.crud_grauA.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    List<Contato> findByTransportadoraIdOrderByNome(Long transportadoraId);

    Optional<Contato> findByIdAndTransportadoraId(Long id, Long transportadoraId);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByTransportadoraId(Long transportadoraId);
}
