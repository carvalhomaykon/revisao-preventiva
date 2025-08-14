package repository;

import model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{

    //@Procedure(procedureName = "getDataPrevistaRevisao")
    //void calcularDataPrevistaRevisao(@Param("veiculos_id") Long id){}

}
