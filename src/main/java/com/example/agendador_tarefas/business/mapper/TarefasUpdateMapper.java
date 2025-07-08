package com.example.agendador_tarefas.business.mapper;

import com.example.agendador_tarefas.business.dto.TarefasDTO;
import com.example.agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefasUpdateMapper {

    void updateTarefas(TarefasDTO tarefasDTO, @MappingTarget TarefasEntity entity);


}
