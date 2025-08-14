package service;

import model.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.VeiculoRepository;

import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository repository;

    public VeiculoService(VeiculoRepository repository) {
        this.repository = repository;
    }

    public Veiculo criar(Veiculo veiculo){
        return repository.save(veiculo);
    }

    public Veiculo atualizar(Long id, Veiculo veiculo){
        veiculo.setId(id);
        return repository.save(veiculo);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Veiculo buscarPorId(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<Veiculo> listarTodosVeiculos(){
        return repository.findAll();
    }

}
