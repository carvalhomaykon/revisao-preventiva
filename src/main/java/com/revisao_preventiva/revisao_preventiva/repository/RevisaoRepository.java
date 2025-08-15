package repository;

import model.Revisao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RevisaoRepository extends JpaRepository<Revisao, Long> {

    List<Revisao> findByVeiculoId(Long veiculoId);

}
