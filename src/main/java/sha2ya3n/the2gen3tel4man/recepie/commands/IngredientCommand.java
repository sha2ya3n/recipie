package sha2ya3n.the2gen3tel4man.recepie.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import sha2ya3n.the2gen3tel4man.recepie.model.UnitOfMeasure;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Component
public class IngredientCommand {

    private Long recipeId;
    private Long id;
    private BigDecimal amount;
    private String description;
    private UnitOfMeasureCommand unitOfMeasureCommand;
}
