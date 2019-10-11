package sha2ya3n.the2gen3tel4man.recepie.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import sha2ya3n.the2gen3tel4man.recepie.commands.CategoryCommand;
import sha2ya3n.the2gen3tel4man.recepie.model.Category;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {
        if(source == null){
            return null;
        }

        final Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());

        return category;

    }
}
