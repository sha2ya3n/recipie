package sha2ya3n.the2gen3tel4man.recepie.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import sha2ya3n.the2gen3tel4man.recepie.model.Category;
import sha2ya3n.the2gen3tel4man.recepie.model.UnitOfMeasure;
import sha2ya3n.the2gen3tel4man.recepie.repository.CategoryRepository;
import sha2ya3n.the2gen3tel4man.recepie.repository.UnitOfMeasureRepository;

@Slf4j
@Component
@Profile({"dev", "prod"})
public class DataLoaderSQL implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoaderSQL(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if(categoryRepository.count() == 0L){
            loadCategories();
            log.info("Category Repository was 0 and load from category load method that set manualy");
        }

        if(unitOfMeasureRepository.count() == 0L){
            loadUnitOfMeasure();
            log.info("UnitOfMeasure Repository was 0 and load  from load method that set manually");
        }

    }

    public void loadCategories(){
        Category cat1 = new Category();
        cat1.setDescription("Americans");
        categoryRepository.save(cat1);

        Category cat2 = new Category();
        cat2.setDescription("Mexican");
        categoryRepository.save(cat2);

        Category cat3 = new Category();
        cat3.setDescription("Italian");
        categoryRepository.save(cat3);

        Category cat4 = new Category();
        cat4.setDescription("Fast Food");
        categoryRepository.save(cat4);
    }

    public void loadUnitOfMeasure(){
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setDescription("Tablespoon");
        unitOfMeasureRepository.save(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setDescription("Tablespoon");
        unitOfMeasureRepository.save(uom2);

        UnitOfMeasure uom3 = new UnitOfMeasure();
        uom3.setDescription("Dash");
        unitOfMeasureRepository.save(uom3);

        UnitOfMeasure uom4 = new UnitOfMeasure();
        uom4.setDescription("Each");
        unitOfMeasureRepository.save(uom4);

        UnitOfMeasure uom5 = new UnitOfMeasure();
        uom5.setDescription("Cup");
        unitOfMeasureRepository.save(uom5);

        UnitOfMeasure uom6 = new UnitOfMeasure();
        uom6.setDescription("Pinch");
        unitOfMeasureRepository.save(uom6);

        UnitOfMeasure uom7 = new UnitOfMeasure();
        uom7.setDescription("Quence");
        unitOfMeasureRepository.save(uom7);
    }
}
