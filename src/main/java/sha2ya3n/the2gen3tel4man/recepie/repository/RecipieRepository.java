package sha2ya3n.the2gen3tel4man.recepie.repository;

import org.springframework.data.repository.CrudRepository;
import sha2ya3n.the2gen3tel4man.recepie.model.Recipie;

import java.util.Optional;

public interface RecipieRepository extends CrudRepository<Recipie, Long> {

    Optional<Recipie> findByDescription (String description);


}
