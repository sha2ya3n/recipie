package sha2ya3n.the2gen3tel4man.recepie.repository;

import org.springframework.data.repository.CrudRepository;
import sha2ya3n.the2gen3tel4man.recepie.model.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription (String description);
}
