package com.laboratorio.labanalise.controller;

import com.laboratorio.labanalise.model.Procedimento;
import com.laboratorio.labanalise.services.ProcedimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path="/procedimento")
public class ProcedimentoController {

    @Autowired
    private ProcedimentoService service;

    @PostMapping
    public ResponseEntity<Procedimento> salvarProcedimento(@RequestBody Procedimento procedimento) {
        procedimento = service.salvar(procedimento);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(procedimento.getId()).toUri();
        return ResponseEntity.created(uri).body(procedimento);
    }

    @GetMapping
    public ResponseEntity<List<Procedimento>> listarProcedimento() {
        List<Procedimento> procedimentos = service.buscarTodos();
        return ResponseEntity.ok().body(procedimentos);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Procedimento> deletarProcedimento(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

}
