package com.javaeo.agendadortarefas.business.mapper;

import com.javaeo.agendadortarefas.business.dto.TarefasDTORecord;
import com.javaeo.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

	void updateTarefas(TarefasDTORecord dto, @MappingTarget TarefasEntity entity);
}
