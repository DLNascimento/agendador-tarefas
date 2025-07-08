package com.example.agendador_tarefas.business.service;

import com.example.agendador_tarefas.business.dto.TarefasDTO;
import com.example.agendador_tarefas.business.mapper.TarefasMapper;
import com.example.agendador_tarefas.infrastructure.entity.TarefasEntity;
import com.example.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.example.agendador_tarefas.infrastructure.repository.TarefasRepository;
import com.example.agendador_tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository repository;
    private final TarefasMapper tarefasMapper;
    private final JwtUtil jwtUtil;


    public TarefasDTO cadastrarTarefa(String token, TarefasDTO dto){
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefasMapper.paraTarefasEntity(dto);
        return tarefasMapper.paraTarefasDTO(repository.save(entity));
    }

}
