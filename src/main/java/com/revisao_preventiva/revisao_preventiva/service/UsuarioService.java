package com.revisao_preventiva.revisao_preventiva.service;

import com.revisao_preventiva.revisao_preventiva.model.Usuario;
import com.revisao_preventiva.revisao_preventiva.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario criar(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario atualizar(Long id, Usuario usuario) {
        usuario.setId(id);
        return repository.save(usuario);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }

    public Usuario buscarPorId(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<Usuario> listarTodosUsuarios(){
        return repository.findAll();
    }

}
