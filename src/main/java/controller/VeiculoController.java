package controller;

import model.Veiculo;
import org.springframework.web.bind.annotation.*;
import service.VeiculoService;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService service;

    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

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

}
