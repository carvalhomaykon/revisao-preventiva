package service;

import model.Revisao;
import model.Usuario;
import model.Veiculo;
import repository.RevisaoRepository;
import repository.UsuarioRepository;

import java.util.List;

public class RevisaoService {

    private final RevisaoRepository repository;

    public RevisaoService(RevisaoRepository repository) {
        this.repository = repository;
    }

    public List<Revisao> listaTodasRevisoes(){
        return repository.findAll();
    }

    public List<Revisao> listaTodasRevisoesPorVeiculo(Long veiculoId){
        return repository.findByVeiculoId(veiculoId);
    }

    public Revisao buscarPorId(Long id){
        return repository.findById(id).orElse(null);
    }

}
