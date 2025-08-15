package com.revisao_preventiva.revisao_preventiva.controller;

import com.revisao_preventiva.revisao_preventiva.model.Revisao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.revisao_preventiva.revisao_preventiva.service.RevisaoService;

import java.util.List;

@RestController
@RequestMapping("/revisao")
public class RevisaoController {

    @Autowired
    private RevisaoService service;

    @GetMapping
    public List<Revisao> listaTodasRevisoes(){
        return service.listaTodasRevisoes();
    }

    @GetMapping("/veiculo/{veiculoId}")
    public List<Revisao> listaTodasRevisoesPorVeiculo(@PathVariable Long veiculoId){
        return service.listaTodasRevisoesPorVeiculo(veiculoId);
    }

    @GetMapping("/usuario/{userId}")
    public List<Revisao> listarTodasRevisoesVeiculoUserId(@PathVariable Long userId){
        return service.listarTodasRevisoesVeiculoUserId(userId);
    }

    @GetMapping("/{id}")
    public Revisao buscaRevisaoPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

}
