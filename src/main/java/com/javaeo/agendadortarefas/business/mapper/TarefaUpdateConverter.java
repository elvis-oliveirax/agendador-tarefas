package com.javaeo.agendadortarefas.business.mapper;

import com.javaeo.agendadortarefas.business.dto.TarefasDTORecordORecord;
import com.javaeo.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

	void updateTarefas(TarefasDTORecordORecord dto, @MappingTarget TarefasEntity entity);
}
