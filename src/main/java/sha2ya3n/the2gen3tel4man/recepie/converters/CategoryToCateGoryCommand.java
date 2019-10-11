package sha2ya3n.the2gen3tel4man.recepie.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import sha2ya3n.the2gen3tel4man.recepie.commands.CategoryCommand;
import sha2ya3n.the2gen3tel4man.recepie.model.Category;

@Component
public class CategoryToCateGoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        if(source == null){
            return null;
        }

        final CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(source.getId());
        categoryCommand.setDescription(source.getDescription());

        return categoryCommand;
    }
}
