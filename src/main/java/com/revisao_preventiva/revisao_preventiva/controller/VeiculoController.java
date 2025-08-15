package com.revisao_preventiva.revisao_preventiva.controller;

import com.revisao_preventiva.revisao_preventiva.model.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.revisao_preventiva.revisao_preventiva.service.VeiculoService;

import java.util.List;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    @PostMapping
    public Veiculo criar(@RequestBody Veiculo veiculo){
        return service.criar(veiculo);
    }

    @PutMapping("/{id}")
    public Veiculo atualizar(@PathVariable Long id, @RequestBody Veiculo veiculo){
        return service.atualizar(id, veiculo);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }

    @GetMapping
    public List<Veiculo> listar(){
        return service.listarTodosVeiculos();
    }

    @GetMapping("/{id}")
    public Veiculo buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @GetMapping("usuario/{userId}")
    public List<Veiculo> listarVeiculosPorUsuario(@PathVariable Long userId){
        return service.listarVeiculosPorUsuario(userId);
    }

}
