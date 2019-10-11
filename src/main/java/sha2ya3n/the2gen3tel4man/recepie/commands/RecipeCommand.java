package sha2ya3n.the2gen3tel4man.recepie.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import sha2ya3n.the2gen3tel4man.recepie.model.Difficulty;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Component
public class RecipeCommand {

    private Long id;
    private String description;
    private String source;
    private String rating;
    private String direction;
    private String url;
    private Integer cookTime;
    private Integer prepTime;
    private Byte[] image;
    private NoteCommand noteCommand;
    private Set<IngredientCommand> ingredientCommandSe = new HashSet<>();
    private Set<CategoryCommand> categoryCommandSet = new HashSet<>();
    private Difficulty difficulty;

}
