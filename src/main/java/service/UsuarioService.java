package service;

import model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import repository.UsuarioRepository;
import repository.VeiculoRepository;

import java.util.List;

public class UsuarioService {

    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

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
