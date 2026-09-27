package com.unisinos.crud_grauA.controller;

import com.unisinos.crud_grauA.dto.TransportadoraRequestDTO;
import com.unisinos.crud_grauA.dto.TransportadoraResponseDTO;
import com.unisinos.crud_grauA.service.TransportadoraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transportadoras")
@RequiredArgsConstructor
public class TransportadoraController {

    private final TransportadoraService transportadoraService;

    @GetMapping
    public ResponseEntity<List<TransportadoraResponseDTO>> listar() {
        return ResponseEntity.ok(transportadoraService.listar());
    }

    @PostMapping
    public ResponseEntity<TransportadoraResponseDTO> criar(@Valid @RequestBody TransportadoraRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transportadoraService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransportadoraResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody TransportadoraRequestDTO dto) {
        return ResponseEntity.ok(transportadoraService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        transportadoraService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
