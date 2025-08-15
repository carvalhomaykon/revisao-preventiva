package com.revisao_preventiva.revisao_preventiva.repository;

import com.revisao_preventiva.revisao_preventiva.model.Revisao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RevisaoRepository extends JpaRepository<Revisao, Long> {

    @Query("SELECT Revisao FROM Revisao r WHERE r.veiculoId IN " +
        "(SELECT v.id from Veiculo v WHERE v.userId = :userId)"
    )
    List<Revisao> findByVeiculoUserId(Long userId);

    @Query("SELECT r FROM Revisao r WHERE r.veiculoId = :veiculoId")
    List<Revisao> findByVeiculoId(Long veiculoId);

}
