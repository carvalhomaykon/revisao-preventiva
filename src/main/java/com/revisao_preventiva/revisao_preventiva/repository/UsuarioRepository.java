package com.revisao_preventiva.revisao_preventiva.repository;

import com.revisao_preventiva.revisao_preventiva.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
