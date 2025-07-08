package com.example.agendador_tarefas.controller;

import com.example.agendador_tarefas.business.dto.TarefasDTO;
import com.example.agendador_tarefas.business.service.TarefasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
