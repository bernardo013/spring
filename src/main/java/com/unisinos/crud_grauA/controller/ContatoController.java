package com.unisinos.crud_grauA.controller;

import com.unisinos.crud_grauA.dto.ContatoRequestDTO;
import com.unisinos.crud_grauA.dto.ContatoResponseDTO;
import com.unisinos.crud_grauA.service.ContatoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transportadoras/{transportadoraId}/contatos")
@RequiredArgsConstructor
public class ContatoController {

    private final ContatoService contatoService;

    @GetMapping
    public ResponseEntity<List<ContatoResponseDTO>> listar(@PathVariable Long transportadoraId) {
        return ResponseEntity.ok(contatoService.listar(transportadoraId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContatoResponseDTO> buscarPorId(@PathVariable Long transportadoraId, @PathVariable Long id) {
        return ResponseEntity.ok(contatoService.buscarPorId(transportadoraId, id));
    }

    @PostMapping
    public ResponseEntity<ContatoResponseDTO> criar(@PathVariable Long transportadoraId, @Valid @RequestBody ContatoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.criar(transportadoraId, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContatoResponseDTO> atualizar(@PathVariable Long transportadoraId, @PathVariable Long id, @Valid @RequestBody ContatoRequestDTO dto) {
        return ResponseEntity.ok(contatoService.atualizar(transportadoraId, id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long transportadoraId, @PathVariable Long id) {
        contatoService.excluir(transportadoraId, id);
        return ResponseEntity.noContent().build();
    }
}
