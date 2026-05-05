package com.javaeo.agendadortarefas.business;


import com.javaeo.agendadortarefas.business.dto.TarefasDTORecordORecord;
import com.javaeo.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.javaeo.agendadortarefas.business.mapper.TarefasConverter;
import com.javaeo.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.javaeo.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.javaeo.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
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
	private final TarefaUpdateConverter tarefaUpdateConverter;

	public TarefasDTORecordORecord gravarTarefa(String token, TarefasDTORecordORecord dto) {
		String email = jwtUtil.extrairEmailToken(token.substring(7));
		dto.setDataCriacao(LocalDateTime.now());
		dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
		dto.setEmailUsuario(email);
		TarefasEntity entity = tarefaConverter.paraTarefaEntity(dto);

		return tarefaConverter.paraTarefasDTORecordORecord(
				tarefasRepository.save(entity));
	}

	public List<TarefasDTORecordORecord> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {

		return tarefaConverter.paraListaTarefasDTORecordORecord(
				tarefasRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(dataInicial, dataFinal, StatusNotificacaoEnum.PENDENTE));
	}

	public List<TarefasDTORecordORecord> buscaTarefasPorEmail(String token) {
		String email = jwtUtil.extrairEmailToken(token.substring(7));
		List<TarefasEntity> listaTarefas = tarefasRepository.findByEmailUsuario(email);

		return tarefaConverter.paraListaTarefasDTORecordO(listaTarefas);
	}

	public void deletaTarefaPorID(String id) {
		try {
			tarefasRepository.deleteById(id);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistetnte " + id,
					e.getCause());
		}
	}

	public TarefasDTORecordO alterarStatus(StatusNotificacaoEnum status, String id) {
		try {
			TarefasEntity entity = tarefasRepository.findById(id).
					orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada " + id));
			entity.setStatusNotificacaoEnum(status);
			return tarefaConverter.paraTarefasDTORecordO(tarefasRepository.save(entity));
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
		}
	}

	public TarefasDTORecordO updateTarefas(TarefasDTORecordO dto, String id){
		try {
			TarefasEntity entity = tarefasRepository.findById(id).
					orElseThrow(() -> new  ResourceNotFoundException("Tarefa não encontrada " + id));
			tarefaUpdateConverter.updateTarefas(dto, entity);
			return tarefaConverter.paraTarefasDTORecordO(tarefasRepository.save(entity));
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistetnte " + id, e.getCause());
		}
	}

}
