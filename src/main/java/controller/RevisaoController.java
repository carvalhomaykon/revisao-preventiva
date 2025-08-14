package controller;

import model.Revisao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.RevisaoService;

import java.util.List;

@RestController
@RequestMapping("/revisoes")
public class RevisaoController {

    private final RevisaoService service;

    public RevisaoController(RevisaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Revisao> listaTodasRevisoes(){
        return service.listaTodasRevisoes();
    }

    @GetMapping("/veiculo/{veiculoId}")
    public List<Revisao> listaTodasRevisoesPorVeiculo(Long veiculoId){
        return service.listaTodasRevisoesPorVeiculo(veiculoId);
    }

    @GetMapping("/{id}")
    public Revisao buscaRevisaoPorId(@RequestParam Long id){
        return service.buscarPorId(id);
    }

}
