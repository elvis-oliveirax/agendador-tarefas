package com.javaeo.agendadortarefas.controller;

import com.javaeo.agendadortarefas.business.TarefasService;
import com.javaeo.agendadortarefas.business.dto.TarefasDTORecordO;
import com.javaeo.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

	private final TarefasService tarefasService;

	@PostMapping
	public ResponseEntity<TarefasDTORecordO> gravarTarefas(@RequestBody TarefasDTORecordO dto,
													@RequestHeader("Authorization") String token){

		return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));
	}

	@GetMapping("/eventos")
	public ResponseEntity<List<TarefasDTORecordO>> buscaListaDeTarefasPorPeriodo(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal){

		return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
	}

	@GetMapping
	public ResponseEntity<List<TarefasDTORecordO>> buscaTarefasPorEmail(@RequestHeader("Authorization") String token){
		List<TarefasDTORecordO> tarefas = tarefasService.buscaTarefasPorEmail(token);
		return ResponseEntity.ok(tarefas);

	}

	@DeleteMapping
	public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id){

		tarefasService.deletaTarefaPorID(id);

		return ResponseEntity.ok().build();
	}

	@PatchMapping
	public ResponseEntity<TarefasDTORecordO> alteraStatusNotificacao(@RequestParam("status")StatusNotificacaoEnum status,
															  @RequestParam ("id") String id){
		return  ResponseEntity.ok(tarefasService.alterarStatus(status, id));
	}

	@PutMapping
	public ResponseEntity<TarefasDTORecordO> updateTarefas(@RequestBody TarefasDTORecordO dto, @RequestParam ("id") String id){
		return ResponseEntity.ok(tarefasService.updateTarefas(dto, id));
	}

}
