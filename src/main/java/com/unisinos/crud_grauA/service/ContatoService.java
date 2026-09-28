package com.unisinos.crud_grauA.service;

import com.unisinos.crud_grauA.dto.ContatoRequestDTO;
import com.unisinos.crud_grauA.dto.ContatoResponseDTO;
import com.unisinos.crud_grauA.entity.Contato;
import com.unisinos.crud_grauA.entity.Transportadora;
import com.unisinos.crud_grauA.exception.RecursoNaoEncontradoException;
import com.unisinos.crud_grauA.exception.RegraNegocioException;
import com.unisinos.crud_grauA.repository.ContatoRepository;
import com.unisinos.crud_grauA.repository.TransportadoraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContatoService {

    private final ContatoRepository contatoRepository;
    private final TransportadoraRepository transportadoraRepository;

    @Transactional(readOnly = true)
    public List<ContatoResponseDTO> listar(Long transportadoraId) {
        buscarTransportadoraAtiva(transportadoraId);

        return contatoRepository.findByTransportadoraIdOrderByNome(transportadoraId).stream()
                .map(ContatoResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ContatoResponseDTO buscarPorId(Long transportadoraId, Long id) {
        buscarTransportadoraAtiva(transportadoraId);
        return ContatoResponseDTO.fromEntity(buscarEntidade(id, transportadoraId));
    }

    @Transactional
    public ContatoResponseDTO criar(Long transportadoraId, ContatoRequestDTO dto) {
        Transportadora transportadora = buscarTransportadoraAtiva(transportadoraId);

        if (contatoRepository.existsByEmail(dto.email())) {
            throw new RegraNegocioException("Email já cadastrado");
        }

        Contato contato = new Contato();
        contato.setNome(dto.nome());
        contato.setEmail(dto.email());
        contato.setTelefone(dto.telefone());
        contato.setCargo(dto.cargo());
        contato.setTransportadora(transportadora);

        return ContatoResponseDTO.fromEntity(contatoRepository.save(contato));
    }

    @Transactional
    public ContatoResponseDTO atualizar(Long transportadoraId, Long id, ContatoRequestDTO dto) {
        buscarTransportadoraAtiva(transportadoraId);
        Contato contato = buscarEntidade(id, transportadoraId);

        if (contatoRepository.existsByEmailAndIdNot(dto.email(), id)) {
            throw new RegraNegocioException("Email já cadastrado");
        }

        contato.setNome(dto.nome());
        contato.setEmail(dto.email());
        contato.setTelefone(dto.telefone());
        contato.setCargo(dto.cargo());

        return ContatoResponseDTO.fromEntity(contatoRepository.save(contato));
    }

    @Transactional
    public void excluir(Long transportadoraId, Long id) {
        buscarTransportadoraAtiva(transportadoraId);
        Contato contato = buscarEntidade(id, transportadoraId);

        contatoRepository.delete(contato);
    }

    private Transportadora buscarTransportadoraAtiva(Long transportadoraId) {
        Transportadora transportadora = transportadoraRepository.findById(transportadoraId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Transportadora não encontrada"));

        if (!Boolean.TRUE.equals(transportadora.getAtivo())) {
            throw new RecursoNaoEncontradoException("Transportadora não encontrada");
        }

        return transportadora;
    }

    private Contato buscarEntidade(Long id, Long transportadoraId) {
        return contatoRepository.findByIdAndTransportadoraId(id, transportadoraId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Contato não encontrado"));
    }
}
