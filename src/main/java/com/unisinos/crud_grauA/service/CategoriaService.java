package com.unisinos.crud_grauA.service;

import com.unisinos.crud_grauA.dto.CategoriaRequestDTO;
import com.unisinos.crud_grauA.dto.CategoriaResponseDTO;
import com.unisinos.crud_grauA.entity.Categoria;
import com.unisinos.crud_grauA.exception.RecursoNaoEncontradoException;
import com.unisinos.crud_grauA.exception.RegraNegocioException;
import com.unisinos.crud_grauA.repository.CategoriaRepository;
import com.unisinos.crud_grauA.repository.TransportadoraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final TransportadoraRepository transportadoraRepository;

    @Transactional(readOnly = true)
    public List<CategoriaResponseDTO> listar() {
        return categoriaRepository.findAll().stream()
                .map(CategoriaResponseDTO::fromEntity)
                .toList();
    }

    @Transactional
    public CategoriaResponseDTO criar(CategoriaRequestDTO dto) {
        if (categoriaRepository.existsByNome(dto.nome())) {
            throw new RegraNegocioException("Já existe uma categoria com este nome");
        }

        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome());
        categoria.setDescricao(dto.descricao());

        return CategoriaResponseDTO.fromEntity(categoriaRepository.save(categoria));
    }

    @Transactional
    public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO dto) {
        Categoria categoria = buscarEntidade(id);

        if (categoriaRepository.existsByNomeAndIdNot(dto.nome(), id)) {
            throw new RegraNegocioException("Já existe uma categoria com este nome");
        }

        categoria.setNome(dto.nome());
        categoria.setDescricao(dto.descricao());

        return CategoriaResponseDTO.fromEntity(categoriaRepository.save(categoria));
    }

    @Transactional
    public void excluir(Long id) {
        Categoria categoria = buscarEntidade(id);

        if (transportadoraRepository.existsByCategoriaId(id)) {
            throw new RegraNegocioException("Não é possível excluir a categoria pois ela possui transportadoras vinculadas");
        }

        categoriaRepository.delete(categoria);
    }

    private Categoria buscarEntidade(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada"));
    }
}
