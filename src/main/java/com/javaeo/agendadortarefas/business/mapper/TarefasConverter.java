package com.javaeo.agendadortarefas.business.mapper;

import com.javaeo.agendadortarefas.business.dto.TarefasDTO;
import com.javaeo.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

	TarefasEntity paraTarefaEntity(TarefasDTO dto);

	TarefasDTO paraTarefasDTO(TarefasEntity entity);

 	List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTO> dtos);

	List<TarefasDTO> paraListaTarefasDTO(List<TarefasEntity> entities);
}
