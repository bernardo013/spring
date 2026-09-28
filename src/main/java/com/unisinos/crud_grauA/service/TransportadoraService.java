package com.unisinos.crud_grauA.service;

import com.unisinos.crud_grauA.dto.TransportadoraRequestDTO;
import com.unisinos.crud_grauA.dto.TransportadoraResponseDTO;
import com.unisinos.crud_grauA.entity.Categoria;
import com.unisinos.crud_grauA.entity.Transportadora;
import com.unisinos.crud_grauA.exception.RecursoNaoEncontradoException;
import com.unisinos.crud_grauA.exception.RegraNegocioException;
import com.unisinos.crud_grauA.repository.CategoriaRepository;
import com.unisinos.crud_grauA.repository.ContatoRepository;
import com.unisinos.crud_grauA.repository.TransportadoraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransportadoraService {

    private final TransportadoraRepository transportadoraRepository;
    private final CategoriaRepository categoriaRepository;
    private final ContatoRepository contatoRepository;

    @Transactional(readOnly = true)
    public List<TransportadoraResponseDTO> listar() {
        return transportadoraRepository.findAllByOrderByRazaoSocial().stream()
                .map(TransportadoraResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public TransportadoraResponseDTO buscarPorId(Long id) {
        return TransportadoraResponseDTO.fromEntity(buscarEntidade(id));
    }

    @Transactional
    public TransportadoraResponseDTO criar(TransportadoraRequestDTO dto) {
        if (transportadoraRepository.existsByCnpj(dto.cnpj())) {
            throw new RegraNegocioException("Já existe uma transportadora com este CNPJ");
        }

        Categoria categoria = buscarCategoria(dto.categoriaId());

        Transportadora transportadora = new Transportadora();
        transportadora.setRazaoSocial(dto.razaoSocial());
        transportadora.setCnpj(dto.cnpj());
        transportadora.setTelefone(dto.telefone());
        transportadora.setAtivo(dto.ativo() != null ? dto.ativo() : true);
        transportadora.setCategoria(categoria);

        return TransportadoraResponseDTO.fromEntity(transportadoraRepository.save(transportadora));
    }

    @Transactional
    public TransportadoraResponseDTO atualizar(Long id, TransportadoraRequestDTO dto) {
        Transportadora transportadora = buscarEntidade(id);

        if (transportadoraRepository.existsByCnpjAndIdNot(dto.cnpj(), id)) {
            throw new RegraNegocioException("Já existe uma transportadora com este CNPJ");
        }

        Categoria categoria = buscarCategoria(dto.categoriaId());

        transportadora.setRazaoSocial(dto.razaoSocial());
        transportadora.setCnpj(dto.cnpj());
        transportadora.setTelefone(dto.telefone());
        transportadora.setAtivo(dto.ativo() != null ? dto.ativo() : transportadora.getAtivo());
        transportadora.setCategoria(categoria);

        return TransportadoraResponseDTO.fromEntity(transportadoraRepository.save(transportadora));
    }

    @Transactional
    public void excluir(Long id) {
        Transportadora transportadora = buscarEntidade(id);

        if (contatoRepository.existsByTransportadoraId(id)) {
            throw new RegraNegocioException("Não é possível excluir a transportadora pois ela possui contatos cadastrados");
        }

        transportadoraRepository.delete(transportadora);
    }

    private Categoria buscarCategoria(Long categoriaId) {
        return categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada"));
    }

    private Transportadora buscarEntidade(Long id) {
        return transportadoraRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Transportadora não encontrada"));
    }
}
