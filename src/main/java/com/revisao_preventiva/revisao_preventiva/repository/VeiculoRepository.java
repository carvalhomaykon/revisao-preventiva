package com.revisao_preventiva.revisao_preventiva.repository;

import com.revisao_preventiva.revisao_preventiva.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{

    @Query("SELECT v FROM Veiculo v WHERE v.userId = :userId")
    List<Veiculo> findByUsuarioId(@Param("userId") Long userId);

}
