package com.revisao_preventiva.revisao_preventiva.service;

import com.revisao_preventiva.revisao_preventiva.model.Revisao;
import com.revisao_preventiva.revisao_preventiva.model.Usuario;
import com.revisao_preventiva.revisao_preventiva.repository.RevisaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevisaoService {

    @Autowired
    private RevisaoRepository repository;

    public List<Revisao> listaTodasRevisoes(){
        return repository.findAll();
    }

    public List<Revisao> listaTodasRevisoesPorVeiculo(Long veiculoId){
        return repository.findByVeiculoId(veiculoId);
    }

    public Revisao buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

        public List<Revisao> listarTodasRevisoesVeiculoUserId (Long userId){
        return repository.findByVeiculoUserId(userId);
    }
}
