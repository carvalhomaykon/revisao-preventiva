package com.revisao_preventiva.revisao_preventiva.service;

import com.revisao_preventiva.revisao_preventiva.model.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revisao_preventiva.revisao_preventiva.repository.VeiculoRepository;

import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository repository;

    public Veiculo criar(Veiculo veiculo){
        return repository.save(veiculo);
    }

    public Veiculo atualizar(Long id, Veiculo veiculo){
        veiculo.setId(id);
        return repository.save(veiculo);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }

    public Veiculo buscarPorId(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<Veiculo> listarTodosVeiculos(){
        return repository.findAll();
    }

    public List<Veiculo> listarVeiculosPorUsuario(Long userId){
        return repository.findByUsuarioId(userId);
    }

}
