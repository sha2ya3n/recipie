package sha2ya3n.the2gen3tel4man.recepie.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import sha2ya3n.the2gen3tel4man.recepie.commands.IngredientCommand;
import sha2ya3n.the2gen3tel4man.recepie.model.Ingredient;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure unoConverter) {
        this.uomConverter = unoConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if(source == null){
            return null;
        }

        final Ingredient ingredient = new Ingredient();
        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        ingredient.setId(source.getId());
//        ingredient.set
        ingredient.setUnitOfMeasure(uomConverter.convert(source.getUnitOfMeasureCommand()));

        return ingredient;
    }
}
