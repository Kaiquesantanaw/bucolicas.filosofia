package bucolicasfilos.example.demo.repository;

import bucolicasfilos.example.demo.model.Ensaio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnsaioRepository extends JpaRepository<Ensaio, Long> {
}
