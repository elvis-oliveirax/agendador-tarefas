package com.javaeo.agendadortarefas.business;


import com.javaeo.agendadortarefas.business.dto.TarefasDTO;
import com.javaeo.agendadortarefas.business.mapper.TarefasConverter;
import com.javaeo.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.javaeo.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.javaeo.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.javaeo.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

	private final TarefasRepository tarefasRepository;
	private final TarefasConverter tarefaConverter;
	private final JwtUtil jwtUtil;

	public TarefasDTO gravarTarefa(String token, TarefasDTO dto){
		String email = jwtUtil.extrairEmailToken(token.substring(7));
		dto.setDataCriacao(LocalDateTime.now());
		dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
		dto.setEmailUsuario(email);
		TarefasEntity entity = tarefaConverter.paraTarefaEntity(dto);

		return tarefaConverter.paraTarefasDTO(
				tarefasRepository.save(entity));
	}

	public List<TarefasDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal){

		return tarefaConverter.paraListaTarefasDTO(
				tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
	}
}
