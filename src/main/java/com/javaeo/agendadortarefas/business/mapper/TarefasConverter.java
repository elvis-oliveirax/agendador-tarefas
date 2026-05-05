package com.javaeo.agendadortarefas.business.mapper;

import com.javaeo.agendadortarefas.business.dto.TarefasDTORecordORecord;
import com.javaeo.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

	@Mapping(source = "id", target = "id")
	@Mapping(source = "dataEvento", target = "dataEvento")
	@Mapping(source = "dataCriacao", target = "dataCriacao")

	TarefasEntity paraTarefaEntity(TarefasDTORecordORecord dto);

	TarefasDTORecordORecord paraTarefasDTORecordORecord(TarefasEntity entity);

 	List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTORecordORecord> dtos);

	List<TarefasDTORecordORecord> paraListaTarefasDTORecordORecord(List<TarefasEntity> entities);
}
