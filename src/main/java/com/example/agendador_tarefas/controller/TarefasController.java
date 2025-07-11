package com.example.agendador_tarefas.controller;

import com.example.agendador_tarefas.business.dto.TarefasDTO;
import com.example.agendador_tarefas.business.service.TarefasService;
import com.example.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> cadastrarTarefa(@RequestBody TarefasDTO tarefasDTO,
                                                      @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(tarefasService.cadastrarTarefa(token, tarefasDTO));

    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscarEventosPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal){

        return ResponseEntity.ok(tarefasService.buscarEventosPorPeriodo(dataInicial, dataFinal));

    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscarEventosPorEmail(@RequestHeader("Authorization") String token){
        List<TarefasDTO> tarefas = tarefasService.buscarEventosPorEmail(token);
        return ResponseEntity.ok(tarefas);

    }
    @DeleteMapping
    public ResponseEntity<Void> deletePorId(@RequestParam("id")String id){
        tarefasService.deletarPorId(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefasDTO> atualizaStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                                @RequestParam("id")String id){
        return ResponseEntity.ok(tarefasService.atualizaStatusNotificacao(status, id));
    }

    @PutMapping
    public ResponseEntity<TarefasDTO> updateTarefa(@RequestBody TarefasDTO dto,
                                                   @RequestParam("id")String id){
        return ResponseEntity.ok(tarefasService.updateTarefas(dto, id));
    }

}
