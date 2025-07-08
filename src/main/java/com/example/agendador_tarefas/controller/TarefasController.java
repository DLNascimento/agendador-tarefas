package com.example.agendador_tarefas.controller;

import com.example.agendador_tarefas.business.dto.TarefasDTO;
import com.example.agendador_tarefas.business.service.TarefasService;
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
}
