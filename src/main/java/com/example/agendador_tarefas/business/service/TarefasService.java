package com.example.agendador_tarefas.business.service;

import com.example.agendador_tarefas.business.dto.TarefasDTO;
import com.example.agendador_tarefas.business.mapper.TarefasMapper;
import com.example.agendador_tarefas.business.mapper.TarefasUpdateMapper;
import com.example.agendador_tarefas.infrastructure.entity.TarefasEntity;
import com.example.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.example.agendador_tarefas.infrastructure.exception.ResourceNotFoundException;
import com.example.agendador_tarefas.infrastructure.repository.TarefasRepository;
import com.example.agendador_tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository repository;
    private final TarefasMapper tarefasMapper;
    private final TarefasUpdateMapper tarefasUpdateMapper;
    private final JwtUtil jwtUtil;


    public TarefasDTO cadastrarTarefa(String token, TarefasDTO dto) {
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefasMapper.paraTarefasEntity(dto);
        return tarefasMapper.paraTarefasDTO(repository.save(entity));
    }

    public List<TarefasDTO> buscarEventosPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {

        return tarefasMapper.paraListaTarefasDTO(repository.findByDataEventoBetween(dataInicial, dataFinal));

    }

    public List<TarefasDTO> buscarEventosPorEmail(String token) {

        String emailUsuario = jwtUtil.extractUsername(token.substring(7));
        return tarefasMapper.paraListaTarefasDTO(repository.findByEmailUsuario(emailUsuario));

    }

    public void deletarPorId(String id) {

        try {
            repository.deleteById(id);

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("id inexistente" + id, e.getCause());
        }

    }

    public TarefasDTO atualizaStatusNotificacao(StatusNotificacaoEnum status, String id) {

        try {
            TarefasEntity entity = repository.findById(id).
                    orElseThrow(
                            () -> new ResourceNotFoundException(
                                    "id não encontrado " + id));
            entity.setStatusNotificacaoEnum(status);

            return tarefasMapper.paraTarefasDTO(entity);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao tentar atualizar o Status " + e.getCause());
        }


    }

    public TarefasDTO updateTarefas(TarefasDTO tarefasDTO, String id) {

        try {
            TarefasEntity entity = repository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("tarefa não encontrada " + id));
            tarefasUpdateMapper.updateTarefas(tarefasDTO, entity);
            return tarefasMapper.paraTarefasDTO(repository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro na tentativa de atualização " + e.getCause());
        }

    }

}
