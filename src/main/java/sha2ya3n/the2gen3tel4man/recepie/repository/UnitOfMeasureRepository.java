package sha2ya3n.the2gen3tel4man.recepie.repository;

import org.springframework.data.repository.CrudRepository;
import sha2ya3n.the2gen3tel4man.recepie.model.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByDescription (String description);
}
