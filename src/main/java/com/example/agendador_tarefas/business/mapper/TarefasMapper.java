package com.example.agendador_tarefas.business.mapper;

import com.example.agendador_tarefas.business.dto.TarefasDTO;
import com.example.agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasMapper {

    TarefasEntity paraTarefasEntity(TarefasDTO dto);
    TarefasDTO paraTarefasDTO(TarefasEntity entity);
}
